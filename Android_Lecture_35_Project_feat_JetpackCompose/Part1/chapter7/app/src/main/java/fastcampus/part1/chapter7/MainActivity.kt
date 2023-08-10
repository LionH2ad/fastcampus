package fastcampus.part1.chapter7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import fastcampus.part1.chapter7.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() ,WordAdapter.ItemClickListener{
    private lateinit var binding: ActivityMainBinding
    private lateinit var wordAdapter: WordAdapter
    private val updateAddWordResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){result ->
        val isUpdated = result.data?.getBooleanExtra("isUpdated",false) ?: false

        if(result.resultCode == RESULT_OK && isUpdated){
            updateAddWord()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

        binding.addButton.setOnClickListener {
            Intent(this, AddActivity::class.java).let{
                updateAddWordResult.launch(it)
            }
        }
    }

    private fun initView(){
        initRecyclerView()
    }

    private fun initRecyclerView(){
        /*val dummyList = mutableListOf(
            Word("weather","날씨","명사"),
            Word("honey","꿀","명사"),
            Word("run","실행하다","동사"),
        )

        wordAdapter = WordAdapter(dummyList, this)*/
        wordAdapter = WordAdapter(mutableListOf(), this)
        binding.WordRecyclerView.apply {
            adapter = wordAdapter
            layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            val dividerItemDecoration = DividerItemDecoration(applicationContext,LinearLayoutManager.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }

        Thread{
            val list = AppDatabase.getInstance(this)?.wordDao()?.getALL() ?: emptyList() //Database에서 값을 가져오기
            wordAdapter.list.addAll(list) // 가져온 값을 넣어주기
            runOnUiThread { wordAdapter.notifyDataSetChanged() }// data 변경에 따른 화면 갱신 전체를 갱신하면 오래 걸림

        }.start()

    }
    private fun updateAddWord(){
        Thread{
            AppDatabase.getInstance(this)?.wordDao()?.getLatestWord()?.let { word ->
                wordAdapter.list.add(0, word)
                runOnUiThread { wordAdapter.notifyDataSetChanged() }
            }
        }.start()
    }

    override fun onClick(word: Word) {
        TODO("Not yet implemented")
    }
}