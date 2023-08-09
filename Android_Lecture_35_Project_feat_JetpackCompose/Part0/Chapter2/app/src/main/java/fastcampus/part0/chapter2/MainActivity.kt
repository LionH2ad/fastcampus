package fastcampus.part0.chapter2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //SAM (Singe Abstract Method)
        val view = View(this)

        /* 자바에서 사용하는 방식
        view.setOnClickListener(
            new View.OnClickListener(){
                @Override
                public void onClick(View : View){
                    //하려고 하는 것
                }
            }
        )*/
        view.setOnClickListener({ println("안녕")})
    }
}