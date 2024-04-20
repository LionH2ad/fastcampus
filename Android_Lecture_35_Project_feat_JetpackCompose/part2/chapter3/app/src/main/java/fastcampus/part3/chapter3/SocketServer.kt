package fastcampus.part3.chapter3

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket

fun main(){
    runBlocking {
        launch(Dispatchers.IO){
            startServer()
        }
    }
}

/**
 * InputStream
 * - network 로부터 받은 data 를 바이트 단위로 읽는 데 사용 합니다.
 * - 서버나 클라이언트가 데이터 수신을 위해 사용 합니다.
 * InputStreamReader
 * - 'InputStream' 에서 읽은 byte 를 문자(Char)로 변환 하여 읽는 데 사용 합니다.
 * - 다양한 인코딩을 지원하여 네트워크로 받은 바이트 데이터를 문자로 변환할 때 사용 합니다.
 * BufferedReader
 * - 입력 스트림의 data 를 버퍼링하여 효율적으로 처리하는 데 사용 합니다.
 * - InputStreamReader 를 감싸서 data 를 한 줄씩(줄 바꿈 기준) 읽을 수 있게 합니다.
 *OutputStream
 * - OutputStream은 네트워크를 통해 데이터를 바이트 단위로 전송할 때 사용됩니다.
 * - 서버나 클라이언트가 데이터 송신을 위해 사용합니다.
 *PrintWriter
 * - PrintWriter는 OutputStream을 감싸서 문자열을 편리하게 출력할 수 있는 도구 입니다.
 * - 자동 줄 바꿈 기능을 제공하고, flush() 메서드를 통해 버퍼를 비울 수 있습니다.
 * */
private fun startServer(){
    // 8080 포트에서 서버 소켓을 생성
    val port = 8080
    val serverSocket = ServerSocket(port)
    println("Server started on port 8080")

    while (true) {
        // 클라이언트 연결을 기다림
        val clientSocket = serverSocket.accept()
        println("Client connected from ${clientSocket.inetAddress}")

        // 클라이언트로부터 데이터를 읽음
        val inputStream = clientSocket.getInputStream()
        val inputStreamReader = InputStreamReader(inputStream)
        val bufferedReader = BufferedReader(inputStreamReader)
        val message = bufferedReader.readLine()
        // var message: String? = "-1"
        // while(message != null && message != ""){
        // message = bufferedReader.readLine()
        // }
        println("Received message from client: $message")

        // 클라이언트에 응답 전송
        val outputStream = clientSocket.getOutputStream()
        val printWriter = PrintWriter(outputStream)

        // HEADER
        printWriter.println("HTTP/1.1 200 OK")
        printWriter.println("Content-Type: text/html\r\n")

        // BODY
        printWriter.println("{\"message\": \"Today is Sunny\"}")
        printWriter.println("\r\n")
        printWriter.flush()
        bufferedReader.close()

        // 클라이언트 소켓 닫기
        clientSocket.close()
    }

}