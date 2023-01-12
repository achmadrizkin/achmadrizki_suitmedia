package com.achmadrizkin.achmadrizki_suitmedia.view_model

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    // palindrome
    fun isPalindromeString(inputStr: String): Boolean {
        val sb = StringBuilder(inputStr)

        //reverse the string
        val reverseStr = sb.reverse().toString()

        //compare reversed string with input string
        return inputStr.equals(reverseStr, ignoreCase = true)
    }

    fun setTextIfPalindrome(isPalindrome: Boolean): String {
        if (isPalindrome) {
            return "Palindrome"
        } else {
            return "Not Palindrome"
        }
    }

}