import java.io.FileInputStream
import java.util.Properties
object ApiConfig {
    private val properties = Properties().apply {
        load(FileInputStream("local.properties"))
    }

    val apiKey: String = properties.getProperty("API_KEY")
        ?: error("API_KEY not found")
}