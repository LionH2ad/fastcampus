package fastcampus.part1.chapter4

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.media.MediaPlayer.OnSubtitleDataListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import fastcampus.part1.chapter4.databinding.ActivityEditBinding
import fastcampus.part1.chapter4.databinding.ActivityMainBinding

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bloodTypeSpinner.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.blood_types,
            android.R.layout.simple_list_item_1
        )

        binding.birthdayLayer.setOnClickListener {
            val listener = OnDateSetListener{ _, year, month, dayOfMonth ->
                binding.tvBirthdayValue.text = "$year-${month.inc()}-$dayOfMonth"
            }
            DatePickerDialog(
                this,
                listener,
                2000,
                1,
                1
            ).show()
        }

        binding.cbWarning.setOnCheckedChangeListener { _, isChecked ->
            binding.etWarningValue.isVisible = isChecked
        }

        binding.etWarningValue.isVisible = binding.cbWarning.isChecked

        binding.btSave.setOnClickListener {
            saveData()
            finish()
        }
    }

    private fun saveData(){
        /*val editor = getSharedPreferences("userInformation", Context.MODE_PRIVATE).edit()
        editor.putString("name", binding.etNameValue.text.toString())
        editor.putString("bloodType", getBloodType())
        editor.putString("emergencyNumber", binding.etEmergencyNumberValue.text.toString())
        editor.putString("birthday", binding.tvBirthdayValue.text.toString())
        editor.putString("warning", getWarning())
        editor.apply()*/
        with(getSharedPreferences(USER_INFORMATION, Context.MODE_PRIVATE).edit()){
            putString(NAME, binding.etNameValue.text.toString())
            putString(BLOOD_TYPE, getBloodType())
            putString(EMERGENCY_NUMBER, binding.etEmergencyNumberValue.text.toString())
            putString(BIRTHDAY, binding.tvBirthdayValue.text.toString())
            putString(WARNING, getWarning())
            apply()
        }

        Toast.makeText(this, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show()
    }

    private fun getBloodType(): String {
        val bloodAlphabet = binding.bloodTypeSpinner.selectedItem.toString()
        val bloodSign = if(binding.bloodTypePlus.isChecked) "+" else "-"
        return "$bloodSign$bloodAlphabet"
    }

    private fun getWarning() : String {
        return if(binding.cbWarning.isChecked) binding.etWarningValue.text.toString() else ""
    }
}