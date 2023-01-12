package com.achmadrizkin.achmadrizki_suitmedia.view

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProvider
import com.achmadrizkin.achmadrizki_suitmedia.R
import com.achmadrizkin.achmadrizki_suitmedia.view_model.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var etPalindrome: EditText
    private lateinit var mainViewModel: MainViewModel
    private lateinit var btnCheck: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        btnCheck.setOnClickListener {
            if (etPalindrome.text.isNotEmpty() && etPalindrome.text != null) {
                // show dialog
                showDialog(mainViewModel.isPalindromeString(etPalindrome.text.toString()))
            } else {
                Toast.makeText(this, "Palindrome cannot be null / empty", Toast.LENGTH_LONG).show()
            }
        }
    }

    // show dialog
    private fun showDialog(isPalindrome: Boolean) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Is Palindrome ?")
        builder.setMessage(mainViewModel.setTextIfPalindrome(isPalindrome))

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            dialog.dismiss()
        }

        builder.show()
    }

    private fun init() {
        etPalindrome = findViewById(R.id.etPalindrome)
        btnCheck = findViewById(R.id.btnCheck)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }
}