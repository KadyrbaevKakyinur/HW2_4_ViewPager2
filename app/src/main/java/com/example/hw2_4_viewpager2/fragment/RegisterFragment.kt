package com.example.hw2_4_viewpager2.fragment

import android.util.Log
import androidx.navigation.fragment.findNavController
import com.example.hw2_4_viewpager2.activiti.App
import com.example.hw2_4_viewpager2.board.BaseFragment
import com.example.hw2_4_viewpager2.databinding.FragmentRegisterBinding

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {
    override fun setupUI() {
        saveUserMod()
        saveBtnListener()

    }

    private fun saveBtnListener() {
        binding.btnSaveUserMod.setOnClickListener{
            App.prefs.saveUserMod()
            findNavController().navigateUp()
        }
    }

    private fun saveUserMod() {
        val name = binding.etName.text.toString()
        App.prefs.saveUserName(name)
        val surName = binding.etSurName.text.toString()
        App.prefs.saveSurUserName(surName)
        val number = binding.etNumber.text.toString()
        if (number.isNotEmpty()) {
            try {
                val numberInt = number.toInt()
                App.prefs.saveUserPhone(numberInt)
            } catch (e: NumberFormatException) {
                Log.e("ololo", "Exception: ${e.message}")
            }
        } else {
            Log.e("ololo", "Number is empty")
        }

        binding.smMerried.setOnCheckedChangeListener { _, isChecked ->
            if (App.prefs.isUserMarried(isChecked)) {
                binding.smMerried.setText("Married")
            } else {
                binding.smMerried.setText("Not married")
            }
        }


    }
}
