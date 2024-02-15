package Android_Lecture_35_Project_feat_jetpackCompose.Part0.kotlin

import java.time.DayOfWeek

fun main() {
    max(10,3)
    isHoliday("금")
    isHoliday("토")
}

/* 기존 java 코드
fun max(a: Int, b: Int){
    if(a > b){
        println(a)
    }else{
        println(b)
    }
}*/

fun max(a: Int, b: Int){
    /*val result = if(a > b){
        println(a)
    }else{
        println(b)
    }
    println(result)*/ // Kotlin.unit 이 찍히게 됨
    /*val result = if(a > b){
        a
    }else{
        b
    }*/
    val result = if(a > b) a else b //중괄호를 제거 가능
    println(result)
}

// switch 문과 비슷한 when
//월 화 수 목 금 토 일
fun isHoliday(dayOfWeek: String){
    val result = when(dayOfWeek) { //expression 으로 변수를 선언 할 경우 else 를 선언 해 주어야 한다.
        "토", "일" -> true
        //"월", "화", "수", "목", "금" -> false
        else -> false
    }
    println(result)
}
fun testAny(any: Any){
    when(any){
        in 2..4 -> {}
        in listOf(1,3,4) -> {}
    }
}