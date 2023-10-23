package fastcampus.part2.chapter1

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(private val mainActivity: MainActivity) : FragmentStateAdapter(mainActivity){

    // Item 이 몇개 인지 알려 주는 기능
    override fun getItemCount(): Int {
        return 3
    }

    // fragment position 에 해당 하는 것이 무엇인지 정함
    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {
                return WebViewFragment(position, "https://m.comic.naver.com/webtoon/weekday?week=mon").apply {
                    listener = mainActivity
                }
            }
            1 -> {
                return WebViewFragment(position, "https://m.comic.naver.com/webtoon/weekday?week=tue").apply {
                    listener = mainActivity
                }
            }
            else -> {
                return WebViewFragment(position, "https://m.comic.naver.com/webtoon/weekday?week=wed").apply {
                    listener = mainActivity
                }
            }
        }
    }
}