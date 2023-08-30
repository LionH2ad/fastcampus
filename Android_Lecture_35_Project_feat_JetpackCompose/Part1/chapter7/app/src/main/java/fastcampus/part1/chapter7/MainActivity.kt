package fastcampus.part1.chapter7

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import fastcampus.part1.chapter7.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() ,WordAdapter.ItemClickListener{
    private lateinit var binding: ActivityMainBinding
    private lateinit var wordAdapter: WordAdapter
    private var selectedWord: Word? = null
    private val updateAddWordResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val isUpdated = result.data?.getBooleanExtra("isUpdated", false) ?: false

        if (result.resultCode == RESULT_OK && isUpdated) {
            updateAddWord()
        }
    }

    private val updateEditWordResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val editWord = result.data?.getParcelableExtra<Word>("editWord")
        if (result.resultCode == RESULT_OK && editWord != null) {
            updateEditWord(editWord)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        binding.addButton.setOnClickListener {
            Intent(this, AddActivity::class.java).let{
                updateAddWordResult.launch(it)
            }
        }
        binding.deleteImageView.setOnClickListener {
            delete()
        }
        binding.editImageView.setOnClickListener {
            edit()
        }
    }

    private fun initRecyclerView(){
        /*val dummyList = mutableListOf(
            Word("weather","날씨","명사"),
            Word("honey","꿀","명사"),
            Word("run","실행하다","동사"),
        )

        wordAdapter = WordAdapter(dummyList, this)*/
        wordAdapter = WordAdapter(mutableListOf(), this)
        binding.wordRecyclerView.apply {
            adapter = wordAdapter
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            val dividerItemDecoration =
                DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
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

    private fun updateEditWord(word: Word) {
        val index = wordAdapter.list.indexOfFirst { it.id == word.id }
        //wordAdapter.list.set(index, word)
        wordAdapter.list[index] = word
        runOnUiThread {
            selectedWord = word // AddActivity 에서 수정된 사항을 받아서 화면에 표출
            wordAdapter.notifyItemChanged(index)
            binding.textTextView.text = word.text
            binding.meanTextView.text = word.mean
        }
    }

    private fun delete() {
        if (selectedWord == null) return

        Thread{
            selectedWord?.let{word ->
                AppDatabase.getInstance(this)?.wordDao()?.delete(word)
                runOnUiThread {
                    wordAdapter.list.remove(word)
                    wordAdapter.notifyDataSetChanged()
                    binding.textTextView.text = ""
                    binding.meanTextView.text = ""
                    Toast.makeText(this, "삭제가 완료됐습니다", Toast.LENGTH_SHORT).show()
                }
                selectedWord = null
            }

        }.start()
    }

    private fun edit() {
        if (selectedWord == null) return

        val intent = Intent(this, AddActivity::class.java).putExtra("originWord", selectedWord)
        updateEditWordResult.launch(intent)
    }


    override fun onClick(word: Word) {
        selectedWord = word
        binding.textTextView.text = word.text
        binding.meanTextView.text = word.mean

        Toast.makeText(this,"${word.text} 가 클릭 됐습니다",Toast.LENGTH_SHORT).show()
    }
}