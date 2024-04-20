package fastcampus.part3.chapter3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.google.gson.Gson
import fastcampus.part3.chapter3.databinding.ActivityMainBinding

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val client = OkHttpClient()
    private var serverHost = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.serverHostEditText.addTextChangedListener {
            serverHost = it.toString()
        }

        binding.confirmButton.setOnClickListener {
            val request: Request = Request.Builder()
                .url("http://$serverHost:8080")
                .build()

            /**
             *  OKHttp 로 통신하기
             * callback은 interface 이기에  object라는 구현체를 통하여 구현
             * 구현을 해주려고 하는 것은 OnFailure 와 onResponse 이다.
             */
            val callback = object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    runOnUiThread {
                        Toast.makeText(this@MainActivity, "수신에 실패했습니다.", Toast.LENGTH_SHORT).show()
                    }

                    Log.e("Client", e.toString())
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val response = response.body?.string()
                        if (response != null) {
                            Log.e("Client", response)
                        }
                        val message = Gson().fromJson(response, Message::class.java)

                        runOnUiThread {
                            binding.informationTextView.isVisible = true
                            binding.informationTextView.text = message.message

                            binding.serverHostEditText.isVisible = false
                            binding.confirmButton.isVisible = false
                        }
                    } else {
                        runOnUiThread {
                            Toast.makeText(this@MainActivity, "수신에 실패했습니다.", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }

            //client.newCall(request).execute() // execute() 직접 호출을 하는 함수
            // enqueue()를 사용하여 callback을 넘겨 받는 구조
            // 비동기로 진행을 하다가 완료가 되면 넘겨 주겠다는 구조
            client.newCall(request).enqueue(callback)
        }

        /**
         *    Socket 으로 직접 통신하기
         */
//        Thread {
//            try {
//                val socket = Socket("10.0.2.2", 8080)
//                val printer = PrintWriter(socket.getOutputStream())
//                val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
//
//                printer.println("GET / HTTP/1.1")
//                printer.println("Host: 127.0.0.1:8080")
//                printer.println("User-Agent: android")
//                printer.println("\r\n")
//                printer.flush()
//
//                var input: String? = "-1"
//                while(input != null) {
//                    input = reader.readLine()
//                    Log.e("Client", "$input")
//                }
//
//                reader.close()
//                printer.close()
//                socket.close()
//            } catch (e: Exception) {
//                Log.e("Client", e.toString())
//            }
//        }.start()

    }
}