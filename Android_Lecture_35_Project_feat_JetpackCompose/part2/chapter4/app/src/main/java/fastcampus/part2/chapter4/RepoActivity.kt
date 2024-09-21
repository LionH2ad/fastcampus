package fastcampus.part2.chapter4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fastcampus.part2.chapter4.databinding.ActivityRepoBinding

class RepoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRepoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}