package com.example.andriodapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.andriodapp.databinding.ActivityNextPageBinding

class NextPage : AppCompatActivity() {
     lateinit var binding: ActivityNextPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNextPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fullName = intent.getStringExtra("USERNAME").toString()
        val email = intent.getStringExtra("EMAIL").toString()
        val gender = intent.getStringExtra("GENDER").toString()
        val country = intent.getStringExtra("COUNTRY").toString()
        val city = intent.getStringExtra("CITY").toString()

        // Display the data
        binding.welcomeMessageTextView.text ="Welcome $fullName\n" +
                "Email:$email\n"+
                "Gender:$gender\n"+
                "Country:$country\n"+
                "City:$city"
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}