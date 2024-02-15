package Android_Lecture_35_Project_feat_jetpackCompose.Part0.kotlin

fun main(){
    val list = mutableListOf(1,2,3,4,5)
    list.add(6)
    list.addAll(listOf(7,8,9))

    val list1 = listOf(1,2,3,4) //mutable이 아니기 때문에 추가가 불가능 호출만 가능
    list1[0]
    println(list1.map{it * 10}.joinToString("/"))

    val diverseList = listOf(1, "안녕", 1.78, true) //다양한 타입 선언 가능

    println(list.joinToString(","))

    val map = mapOf((1 to "안녕"), (2 to "hello"))
    val map1 = mutableMapOf((1 to "안녕"),(2 to "hello"))
    map1[3] = "응" // 3이라는 key 값에 응 이라는 value를 넣음
    map1[100] = "호이"

}