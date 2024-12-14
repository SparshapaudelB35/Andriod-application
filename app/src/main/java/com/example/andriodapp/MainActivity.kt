package com.example.andriodapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.andriodapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Spinner Setup
        val countries = arrayOf("Select Country","Nepal","USA", "Canada", "India", "Germany","China")
        val spinnerAdapter = ArrayAdapter(
            this@MainActivity,
            android.R.layout.simple_list_item_1,countries

        )
        spinnerAdapter.setDropDownViewResource(
            android.R.layout.simple_list_item_1
        )
        binding.spinner.adapter=spinnerAdapter

        // AutoCompleteTextView Setup
        val cities = arrayOf("Hetauda","Kathmandu", "Pokhara", "Lalitpur", "Bhaktapur", "Biratnagar", "Janakpur",
            "New York City", "Los Angeles", "Chicago", "Houston", "Phoenix",
            "Toronto", "Vancouver", "Montreal", "Calgary", "Ottawa",
            "Mumbai","Delhi", "Bangalore", "Kolkata", "Chennai",
            "Berlin","Hamburg", "Munich", "Cologne", "Frankfurt",
            "Beijing", "Shanghai", "Chengdu", "Guangzhou", "Shenzhen")
        val autoCompleteAdapter = ArrayAdapter(
            this@MainActivity,
            android.R.layout.simple_list_item_1, cities
        )
        binding.autoCompleteTextView.threshold=2
        binding.autoCompleteTextView.setAdapter(autoCompleteAdapter)

        binding.imageView.setImageResource(R.drawable.logo)

        //Button setup
        binding.btnsubmit.setOnClickListener{
            val fullName:String=binding.Usernameedit.text.toString()
            val email:String=binding.Emailaddedit.text.toString()
            val password:String=binding.Passedit.text.toString()
            val selectedGenderId = binding.radioGroupGender.checkedRadioButtonId
            val gender = if (selectedGenderId != -1) {
                val selectedRadioButton = findViewById<RadioButton>(selectedGenderId)
                selectedRadioButton.text.toString()
            } else {
                null
            }
            val country = binding.spinner.selectedItem.toString()
            val city = binding.autoCompleteTextView.text.toString()
            val isAgreed = binding.checkBox.isChecked

            if (fullName.isEmpty()) {
                binding.Usernameedit.error = "Full name can't be empty"
            } else if (email.isEmpty()) {
                binding.Emailaddedit.error = "Email can't be empty"
            } else if (password.isEmpty()) {
                binding.Passedit.error = "Password can't be empty"
            } else if (gender == null) {
                Toast.makeText(this, "Please select a gender", Toast.LENGTH_SHORT).show()
            } else if (country == "Select Country") {
                Toast.makeText(this, "Please select a country", Toast.LENGTH_SHORT).show()
            } else if (city.isEmpty()) {
                Toast.makeText(this, "Please select a city", Toast.LENGTH_SHORT).show()
            } else if (!isAgreed) {
                Toast.makeText(this, "Please agree to terms and conditions", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, NextPage::class.java).apply {
                    putExtra("USERNAME", fullName)
                    putExtra("EMAIL", email)
                    putExtra("GENDER", gender)
                    putExtra("COUNTRY", country)
                    putExtra("CITY", city)
                }
                startActivity(intent)
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}