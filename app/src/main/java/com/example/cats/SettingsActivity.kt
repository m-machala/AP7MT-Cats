package com.example.cats

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cats.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        binding.radioButtonNone.isChecked = true

        binding.button.setOnClickListener {
            val i = Intent()

            var urlModifier = ""

            if(binding.radioButtonCute.isChecked) urlModifier += "/cute"
            else if(binding.radioButtonKitten.isChecked) urlModifier += "/kitten"
            else if(binding.radioButtonSad.isChecked) urlModifier += "/sad"
            else if(binding.radioButtonSleep.isChecked) urlModifier += "/sleep"
            val text = binding.editText.text.toString()
            if(text != "") urlModifier += "/says/$text"

            i.putExtra("urlModifier", urlModifier)
            setResult(1, i)

            finish()
        }

        setContentView(binding.root)
    }

}