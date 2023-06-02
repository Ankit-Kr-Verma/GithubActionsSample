package com.githubactionssample.ui

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.githubactionssample.databinding.ActivityMainBinding
import com.githubactionssample.dto.UserInfo
import com.githubactionssample.util.EmailValidator
import com.githubactionssample.util.SharedPreferencesRepo
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.emailInput.addTextChangedListener(EmailValidator)
        setButtonListener()
    }

    private fun populateUi() {
        val preferences = SharedPreferencesRepo(this)
        if (TextUtils.isEmpty(preferences.getUserInfo().mName)) {
            Toast.makeText(this, "No information to retrieve.", Toast.LENGTH_LONG).show()
        } else {
            binding.userNameInput.setText(preferences.getUserInfo().mName)
            binding.dateOfBirthInput.init(
                preferences.getUserInfo().mDateOfBirth.get(Calendar.YEAR),
                preferences.getUserInfo().mDateOfBirth.get(Calendar.MONDAY),
                preferences.getUserInfo().mDateOfBirth.get(Calendar.DAY_OF_MONTH), null
            )
            binding.emailInput.setText(preferences.getUserInfo().mEmail)
            Toast.makeText(this, "Information retrieved", Toast.LENGTH_LONG).show()
        }
    }

    private fun setButtonListener() {

        /**
         * Called when the "Save" button is clicked.
         */
        binding.saveButton.setOnClickListener {
            if (!EmailValidator.isValid()) {
                binding.emailInput.error = "Invalid Email"
                return@setOnClickListener
            }

            val name = binding.userNameInput.text.toString()
            val dateOfBirth: Calendar = Calendar.getInstance()
            dateOfBirth.set(
                binding.dateOfBirthInput.year,
                binding.dateOfBirthInput.month,
                binding.dateOfBirthInput.dayOfMonth
            )
            val email = binding.emailInput.text.toString()

            val userInfo = UserInfo(name, dateOfBirth, email)
            val isSuccess = SharedPreferencesRepo(this).saveUserInfo(userInfo)

            if (isSuccess) {
                Toast.makeText(this, "Information saved", Toast.LENGTH_LONG).show()
                //reset
                binding.userNameInput.setText("")
                val currentDate: Calendar = Calendar.getInstance()
                binding.dateOfBirthInput.init(
                    currentDate.get(Calendar.YEAR),
                    currentDate.get(Calendar.MONTH),
                    currentDate.get(Calendar.DAY_OF_MONTH),
                    null
                )
                binding.emailInput.setText("")
            } else {
                Toast.makeText(this, "Failed to save", Toast.LENGTH_LONG).show()
            }

        }

        /**
         * Called when the "Retrieve" button is clicked.
         */
        binding.retrieveButton.setOnClickListener {
            populateUi()
        }

    }

}