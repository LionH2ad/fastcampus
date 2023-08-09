package fastcampus.part1.chapter2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    val Tag: String = "Counter"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numberTextView = findViewById<TextView>(R.id.tv_number)
        val initButton  = findViewById<Button>(R.id.bt_init)
        val plusButton = findViewById<Button>(R.id.bt_plus)

        var number = 0

        initButton.setOnClickListener{
            Log.d(Tag,"initButton OnClickListener")
            number = 0
            numberTextView.text = number.toString()
        }

        plusButton.setOnClickListener {
            Log.d(Tag, "plusButton OnClickListener")
            number++
            numberTextView.text = number.toString()
        }
    }
}