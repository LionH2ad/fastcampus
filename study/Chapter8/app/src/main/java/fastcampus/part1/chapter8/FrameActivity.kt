package fastcampus.part1.chapter8

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.tabs.TabLayoutMediator
import fastcampus.part1.chapter8.databinding.ActivityFrameBinding

class FrameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFrameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFrameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.apply {
            title = "나만의 앨범"
            setSupportActionBar(this)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // toolbar 뒤로가기

        val images = (intent.getStringArrayExtra("images") ?: emptyArray()) // ?: null 일때 emptyArray()
            .map { uriString ->  FrameItem(Uri.parse(uriString)) } // String으로 받는 것을 uri로 다시 바꿈
        val frameAdapter = FrameAdapter(images)

        binding.viewPager.adapter = frameAdapter // adapter 에 연결 이렇게 하면 슬라이드 하면서 볼 수 있음

        TabLayoutMediator(  // tabMeditator 중재자를 통해서 페이저와 탭 레이아웃을 연결
            binding.tabLayout,
            binding.viewPager,
        ) {
                tab, position ->  // 람다식으로 구현
            binding.viewPager.currentItem = tab.position
        }.attach()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> { // 안드로이드에서 이미 지정한 값
                finish() // 뒤로 가기에 대한 것
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}