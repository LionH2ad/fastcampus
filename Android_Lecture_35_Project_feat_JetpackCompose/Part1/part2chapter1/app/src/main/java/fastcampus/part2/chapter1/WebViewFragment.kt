package fastcampus.part2.chapter1

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import fastcampus.part2.chapter1.databinding.FragmentWebviewBinding

class WebViewFragment(private val position: Int, private val webViewUrl: String) : Fragment() {

    var listener: OnTabLayoutNameChanged? = null

    private lateinit var binding: FragmentWebviewBinding

    companion object {
        const val SHARED_PREFERENCE = "WEB_HISTORY"
    }

    // fragment activity 에서 처음에 실행이 되는 함수
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebviewBinding.inflate(inflater)
        return binding.root
    }

    //onCreateView 다음에 실행 되는 함수
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 생성자로 WebtoonWebViewClient를 생성하며 값을 넘겨 주는것
        // kotlin 에서 마지막 인자가 함수를 받게 된다면 (,) 에서 (){} 형식으로 변경 가능
        binding.webView.webViewClient = WebtoonWebViewClient(binding.progressBar) { url ->
            activity?.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE)?.edit {
                putString("tab$position", url) // 해당 포지션에 마지막 웹뷰 시점 저장
            }
        }
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl(webViewUrl) // 밖에서 지정한 url이 들어와서 화면 열어줌

        binding.backToLastButton.setOnClickListener {
            // fragment 는 자신이 어떤 activity 에 속해 있는지 알지만
            // 붙어 있지 않을 수도 있지 때문에 nullable 하게 해야 한다.
            val sharedPreference =
                activity?.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE)
            val url = sharedPreference?.getString("tab$position", "") // 각 해당하는 포지션의 값 불러오기
            if (url.isNullOrEmpty()) { // null 이거나 empty 라면
                Toast.makeText(
                    context,
                    getString(R.string.back_to_history_error_message),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                binding.webView.loadUrl(url)
            }
        }

        binding.changeTabNameButton.setOnClickListener {
            val dialog = AlertDialog.Builder(context)
            val editText = EditText(context)

            dialog.setView(editText)
            dialog.setPositiveButton(getString(R.string.save)) { _, _ ->
                activity?.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE)?.edit {
                    putString("tab${position}_name", editText.text.toString())
                    listener?.nameChanged(position, editText.text.toString())
                }

            }
            dialog.setNegativeButton(getString(R.string.cancel)) { dialogInterface, _ ->
                dialogInterface.cancel()
            }

            dialog.show()

        }

    }

    fun canGoBack(): Boolean {
        return binding.webView.canGoBack()
    }

    fun goBack() {
        binding.webView.goBack()
    }

}

// 이름이 변경된 것을 mainactivity 가 알 수 있도록
interface OnTabLayoutNameChanged {
    fun nameChanged(position: Int, name: String)
}