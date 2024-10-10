package kr.co.fastcampus.chapter3_11

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import kr.co.fastcampus.chapter3_11.ui.theme.Chapter311Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Chapter311Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CoilEx()
                }
            }
        }
    }
}

@Composable
fun CoilEx() {
    // 스텝 3: rememberImagePainter를 이용해 Image의 painter를 설정합니다.
    // (Compose 한국어 문서의 추천, but Deprecated)
    // 이미지 URI: https://raw.githubusercontent.com/Fastcampus-Android-Lecture-Project-2023/part4-chapter3/main/part-chapter3-10/app/src/main/res/drawable-hdpi/wall.jpg
    /*val painter =
        rememberImagePainter(data = "https://raw.githubusercontent.com/Fastcampus-Android-Lecture-Project-2023/part4-chapter3/main/part-chapter3-10/app/src/main/res/drawable-hdpi/wall.jpg")
    Image(
        painter = painter,
        contentDescription = "엔텔로프 캐년"
    )*/

    // 스텝 4: AsyncImage를 이용해봅니다. model에 주소를 적으면 됩니다.
    AsyncImage(
        model = "https://raw.githubusercontent.com/Fastcampus-Android-Lecture-Project-2023/part4-chapter3/main/part-chapter3-10/app/src/main/res/drawable-hdpi/wall.jpg",
        contentDescription = "엔텔로프 캐년"
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Chapter311Theme {
        CoilEx()
    }
}