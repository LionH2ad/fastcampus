package fastcampus.part0.chapter2

fun main(){
    // 1. 익명함수
    // 2. 변수처럼 사용되서, 함수의 argument, return
    // 3. 한번 사용되고, 재사용되지 앟는 함수
    val a = fun(){println("hello")}

    /*
    * ()안은 입력 값을 넣을 공간이며 여러개가 들어갈 수 있다 -> 를 기점으로 리턴 값을 지정
    * {} 안에 함수의 구현부가 들어 가게 됩니다. 인수가 하나라면 it으로 사용이 가능 합니다.
    * */
    val b : (Int) -> Int = { it * 10}

    println(b) //이렇게 사용하면 코드 조각만 뜨게 된다. Function1<java.lang.Integer, java.lang.Integer>
    println( b(10) )

    val d = { i: Int, j: Int -> i * j }
    val f : (Int, String, Boolean) -> String = {_, b, c -> b + c} //_로 생략이 가능
    println(f(10, "Hello", true))
    hello(10, b)
}

fun hello(a: Int, b: (Int) -> Int){
    println(a)
    println(b(20))
}

fun helloReturn(a: Int, b: (Int) -> Int) : (Int) -> Int{
    println(a)
    println(b(20))
    return b
}