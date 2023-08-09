package fastcampus.part1.chapter4

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.widget.Toast
import androidx.core.view.isVisible
import fastcampus.part1.chapter4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goInputActivityButton.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            startActivity(intent)
        }

        binding.btDelete.setOnClickListener {
            deleteData()
        }

        binding.emergencyNumberLayer.setOnClickListener {
            with(Intent(Intent.ACTION_VIEW)){
                val phoneNumber = binding.tvEmergencyNumberValue.text.toString()
                    .replace("-","")
                data = Uri.parse("tel:$phoneNumber")
                startActivity(this)
            }
        }

    }

    override fun onResume() {
        super.onResume()
        getDataUiUpdate()
    }

    private fun getDataUiUpdate(){
        with(getSharedPreferences(USER_INFORMATION, Context.MODE_PRIVATE)) {
            binding.tvNameValue.text = getString(NAME, "미정")
            binding.tvBirthdayValue.text = getString(BIRTHDAY, "미정")
            binding.tvBloodTypeValue.text = getString(BLOOD_TYPE, "미정")
            binding.tvEmergencyNumberValue.text = getString(EMERGENCY_NUMBER, "미정")
            val warning = getString(WARNING,"")

            binding.tvWarning.isVisible = warning.isNullOrEmpty().not()
            binding.tvWarningValue.isVisible = warning.isNullOrEmpty().not()

            if(!warning.isNullOrEmpty()){
                binding.tvWarningValue.text = warning
            }
        }
    }

    private fun deleteData(){
        with(getSharedPreferences(USER_INFORMATION, MODE_PRIVATE).edit()){
            clear()
            apply()
            getDataUiUpdate()
        }
        Toast.makeText(this, "초기화를 완료했습니다.", Toast.LENGTH_SHORT).show()
    }

}