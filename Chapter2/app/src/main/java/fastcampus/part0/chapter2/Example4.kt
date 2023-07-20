package fastcampus.part0.chapter2

var test : String? = "name"
lateinit var laText : String
//lateinit var age : Int
lateinit var age : Integer //이런식으로 선언을 하고 사용
val lazyTest : Int by lazy {
    println("초기화 중")
    100
}

fun main(){
    println("메인 함수 실행")
    laText = "name"
    age = Integer(10)

    println(laText) // 초기화를 하지 않고 부르면 error
    println(age)
    println("초기화 한 값 $lazyTest")
    println("두번째 호출 $lazyTest")
}