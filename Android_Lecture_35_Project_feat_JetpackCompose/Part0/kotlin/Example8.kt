package Android_Lecture_35_Project_feat_jetpackCompose.Part0.kotlin

fun main(){
    var name : String = "성민"
    //name = null <- null 값이 들어 갈 수 없음
    var number: Int = 10 // number 에도 null이 들어 갈 수 없음

    var nickname : String? = "안녕" //자료형 뒤에 ?를 붙여 주면 null이 가능
    var secondNumber : Int? = null
    //kotlin 에서는 선언 하는 것 부터 null이 가능 한지  판단을 해서 선언 해준다.

    /*val result = if(nickname == null){
        "값이 없음"
    } else{
        nickname
    }*/ //자바에서 사용하는 방식
    nickname = null
    val result = nickname?: "값이 없음" //엘비소 오퍼레이터
    println(result)

    val size = nickname?.length // null값이 들어 올수 있으면 ? 추가
    println(size)

}