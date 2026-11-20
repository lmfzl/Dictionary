import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.awt.BorderLayout
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.*
import kotlin.time.Duration.Companion.milliseconds

@OptIn(FlowPreview::class)
object Display {

    private val queries = MutableSharedFlow<String>()
    private val state = MutableStateFlow<ScreenState>(ScreenState.Initial)
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val repository = Repository
    private val enterWordLabel = JLabel("Enter word:")
    private val searchField = JTextField(20).apply {
        addKeyListener(object : KeyAdapter() {
            override fun keyReleased(e: KeyEvent?) {
                scope.launch {
                    loadDefinition()
                }

            }
        })
    }
    private val searchButton = JButton("Search").apply {
        addActionListener {
            loadDefinition()

        }
    }
    private val resultArea = JTextArea(24, 50).apply {
        isEditable = false
        lineWrap = true
        wrapStyleWord = true
    }
    private val topPanel = JPanel().apply {
        add(enterWordLabel)
        add(searchField)
        add(searchButton)
    }
    private val mainFrame = JFrame("Dictionary app").apply {
        layout = BorderLayout()
        add(topPanel, BorderLayout.NORTH)
        add(JScrollPane(resultArea), BorderLayout.CENTER)
        pack()
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE

    }

    private fun loadDefinition() {
        scope.launch {
            queries.emit(searchField.text.trim())
        }
    }

    fun show() {
        mainFrame.isVisible = true
    }

    init {
        queries
            .onEach { state.emit(ScreenState.Loading) }
            .debounce(500.milliseconds)
            .map {
                if (it.isEmpty()) {
                    state.emit(ScreenState.Initial)
                } else {
                    val result = repository.loadDefinition(it)
                    if (result.isEmpty()) {
                        state.emit(ScreenState.NotFound)
                    } else {
                        state.emit(ScreenState.DefinitionsLoaded(result))
                    }
                }
            }
            .retry {
                println(it)
                state.emit(ScreenState.SomethingWentWrong)
                true
            }.launchIn(scope)
        state
            .onEach {
            when (it) {
                is ScreenState.DefinitionsLoaded -> {
                    resultArea.text = it.definitions.joinToString("\n\n")
                    searchButton.isEnabled = true
                }
                ScreenState.Initial -> {
                    resultArea.text = ""
                    searchButton.isEnabled = false
                }
                ScreenState.Loading -> {
                    resultArea.text = "Loading..."
                    searchButton.isEnabled = false
                }
                ScreenState.NotFound -> {
                    resultArea.text = "Not Found"
                    searchButton.isEnabled = true
                }
                ScreenState.SomethingWentWrong -> {
                    resultArea.text = "Something went wrong"
                    searchButton.isEnabled = true
                }
            }
        }
            .launchIn(scope)
    }
}

fun main() {
    Display.show()
}