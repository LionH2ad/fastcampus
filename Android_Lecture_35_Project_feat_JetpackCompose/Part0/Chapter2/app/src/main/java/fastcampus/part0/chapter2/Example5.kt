package fastcampus.part0.chapter2

fun main(){
    val person = Person("성민", 34)
    val dog = Dog("Happy", 4)
    println(person.toString()) // 참조값 호출 fastcampus.part0.chapter2.Person@7a0ac6e3
    println(dog.toString()) // 데이터 호출 Dog(name=Happy, age=4)
    println(dog.copy(age = 3).toString())

    //sealed class
    val cat: Cat = BlueCat()
    val result = when(cat){ // Cat class 를 abstract 에서 sealed 로 변환시 Cat()에 무엇이 있는지 알 수 있음
        is BlueCat -> "Blue"
        is RedCat -> "red"
        is GreenCat -> "green"
        else -> "none"
    }
    println(result)
}

class Person(
    val name: String,
    val age: Int
)

 data class Dog(
     val name: String,
     val age: Int
 )/*{
     override fun toString(): String {
         return "직접 구현 $name"
     }
 }*/ // print 하는 곳에 -> 직접 구현 Happy

/* data class 는 아래와 같이 상속 불가능
data class Corgi(
    val male : Boolean = true
) : Dog()*/

//sealed class
//abstract class Cat
sealed class Cat
class BlueCat : Cat()
class RedCat : Cat()
class GreenCat : Cat()