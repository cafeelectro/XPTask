package com.ilidev.xptask.ui.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ilidev.xptask.R
import com.ilidev.xptask.databinding.ActivityFullScreenBinding

class activity_full_screen : AppCompatActivity() {

    private lateinit var binding: ActivityFullScreenBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // بررسی SharedPreferences برای نمایش صفحه یا هدایت به MainActivity
        sharedPreferences = getSharedPreferences("app_prefs", MODE_PRIVATE)
        if (sharedPreferences.getBoolean("isFirstTime", true)) {
            binding = ActivityFullScreenBinding.inflate(layoutInflater)
            setContentView(binding.root)

            binding.buttonStart.setOnClickListener {
                // به روزرسانی SharedPreferences
                sharedPreferences.edit().putBoolean("isFirstTime", false).apply()
                // هدایت به MainActivity و پایان دادن به این فعالیت
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        } else {
            // اگر کاربر قبلاً این صفحه را دیده باشد، مستقیماً به MainActivity هدایت شود
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}