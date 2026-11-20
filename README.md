# Dictionary

A simple desktop dictionary application built with Kotlin. It uses the
[API Ninjas Dictionary API](https://api-ninjas.com/api/dictionary) to
retrieve word definitions.

## Features

-   Search for word definitions.
-   Automatic search while typing.
-   Debounced requests to reduce unnecessary API calls.
-   Asynchronous network operations with Kotlin Coroutines.
-   Reactive data processing with Kotlin Flow.
-   JSON deserialization with Kotlinx Serialization.
-   Desktop graphical interface built with Java Swing.

## Tech Stack

-   Kotlin
-   Kotlin Coroutines
-   Kotlin Flow
-   Kotlinx Serialization
-   Java Swing
-   HttpURLConnection
-   API Ninjas

## Setup

1.  Get an API key from [API Ninjas](https://api-ninjas.com/).

2.  Create a `local.properties` file in the project root directory.

3.  Add your API key to the file:

    ``` properties
    API_KEY=your_api_key
    ```

4.  Open the project in IntelliJ IDEA.

5.  Run the application.


## License

MIT License

Copyright (c) 2026 lmfzl

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
