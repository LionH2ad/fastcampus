package Android_Lecture_35_Project_feat_jetpackCompose.Part0.kotlin

fun main(){
    val user = User("박성민")
    println(user.age)
    Kid("아이", 3, "male")
}

class User(val name: String, var age: Int = 100)

//상속을 위한 방법 class 앞에 open을 넣어 주어야 한다
open class User2(open val name: String,open var age: Int = 100)

class Kid(override val name : String,override var age : Int) : User2(name, age){
    var gender: String = "female"

    init {
        println("초기화 중 입니다.")
    }
    constructor(name: String, age: Int, gender: String) : this(name, age){
        this.gender = gender
        println("부 생성자 호출")
    }
}