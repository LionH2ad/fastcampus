package fastcampus.part0.chapter2

fun main(){
    //let, run, apply, also  with
    //1. let
    // 수신객체.let { it or user ->
    //
    // 마지막 줄 // return
    // }
    val a = 3
    a.let {  }
    val user = User("성민", 34, false)

    val age = user.let {user ->    // user -> 생략 가능
        user.age
    }
    println(age)
    val gender = user?.let {
        it.gender
    }
    println(gender)

    // 2. run
    // 수신객체.run { this ->
    //
    // 마지막 줄 return
    // }
    val kid = User("아이", 5, true)
    val kidAge = kid.run {
        this.age   //this. 생략 가능
    }
    println(kidAge)
    // 3. apply
    // 수신객체.apply{
    // dkfjkd //아무거나 사용
    // }
    // return 값이 수신객체
    val kidName = kid.apply{
        name
    }
    println(kidName)  //객체 자체가 나옴 fastcampus.part0.chapter2.User@b684286
    val female = User("슬기", 20, true, true)
    val femaleValue = female.apply {
        hasGlasses = false
    }
    println(femaleValue.hasGlasses)

    // 4. also
    // 수신객체.also { it -> // local variable
    //
    //
    // } // return 수신객체 (자기자신)
    val male = User("민수", 17, false, true)
    val maleValue = male.also {user->
        user.name
        user.hasGlasses = false
    } // 위와 같이 초기화 작업 같은 것을 할 때 사용 하는 것을 권장 하지 않음 안에 내용을 확인 하기 위해 로그를 찍을 때 사용
    println(maleValue) //객체 자체가 나옴 fastcampus.part0.chapter2.User@7a0ac6e3
    println(maleValue.hasGlasses)

    // 5. with
    // with(수신객체){
    // ---
    // 마지막줄 return
    // }
    val result = with(male){
        hasGlasses = true
        true
    }
    println(result)
}

class User(
    val name : String,
    val age : Int,
    val gender : Boolean,  // true : female, false : true
    var hasGlasses : Boolean = true,
)