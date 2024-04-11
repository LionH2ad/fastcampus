package Android_Lecture_35_Project_feat_jetpackCompose.Part0.kotlin

fun main(){
    //println("Hello world!!")
    //test()
    //var result = testInt(1,3)
    //var result = testOverloading(1)
    var result = testPara(1, c=5)
    test2(id = "lionHead", name = "박성민", nickname = "성민");
    println(result)
    println(time1(1,3))
    println(time2(1,3))
}

// 2. 함수
fun test(){ // kotlin은 return 타입을 뒤쪽에 명시를 하는데 java에서 void와 같은 Unit은 생략이 가능 하다. : Unit
    println("test")
}

// 3. 인수를 받아 리턴을 하는 test
fun testInt (a: Int,b: Int) : Int{
    println(a+b)
    return a+b
}

// 4. 오버로딩 방법
fun testOverloading (a: Int, b: Int = 3) : Int {
    println(a+b)
    return a+b
}

// 5. 지정된 파라미터에만 값을 넣어주기
fun testPara (a: Int,b: Int = 3, c: Int = 4) : Int{
    println()
    return a+b
}

// 6. 순서 변경 및 유닛 테스트
fun test2(name : String, nickname: String, id: String) = println(name + nickname + id)

fun time1(a: Int, b: Int) : Int{
    return a*b
}

fun time2(a: Int, b: Int) = a*b
