package Android_Lecture_35_Project_feat_jetpackCompose.Part0.kotlin

fun main(){
    /*println(check("안녕"))
    println(check(3))
    println(check(true))*/
    /*cast("안녕")
    cast(3)*/
    println(smartCast("안녕"))
    println(smartCast(10))
    println(smartCast(true))
}

fun check(a: Any): String {
    return when (a) {
        is String -> {
            "문자열"
        }
        is Int -> {
            "숫자"
        }
        else -> {
            "몰라요"
        }
    }
    /*return  if(a is String){
        "문자열"
    } else if(a is Int){
        "숫자"
    } else{
        "몰라요"
    }*/
}

fun cast(a: Any){
    //val result =  a as? String //물음표가 없으면 ClassCastException
    val result = (a as? String) ?: "실패" //default value
    println(result)
}

fun smartCast(a: Any): Int{
    return if(a is String){
        a.length
    }else if(a is Int){
        a.dec()
    }else {
        -1
    }
}