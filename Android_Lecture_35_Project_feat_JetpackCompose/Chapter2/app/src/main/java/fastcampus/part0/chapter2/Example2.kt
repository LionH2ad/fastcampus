package fastcampus.part0.chapter2

fun main(){
    // 확장 함수(Extension function)
    // 기존에 정의 되어 있는 클래스에 함수를 추가하는 기능
    val test = Test()
    Test().hello()
    test.hi() //java 에서는 hi() 라는 것을 사용하려면 Test() class 안에 hi()가 있어야 한다.
}

fun Test.hi() = print("하이")

class Test(){
    fun hello() = println("안녕")
    fun bye() = println("잘가")
}