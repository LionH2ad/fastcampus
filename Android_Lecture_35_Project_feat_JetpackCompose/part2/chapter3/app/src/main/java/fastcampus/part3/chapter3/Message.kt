package fastcampus.part3.chapter3

import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("message")
    val message: String
)