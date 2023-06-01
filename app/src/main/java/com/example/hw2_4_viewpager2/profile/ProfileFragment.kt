package com.example.hw2_4_viewpager2.profile


import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.hw2_4_viewpager2.R
import com.example.hw2_4_viewpager2.activiti.App
import com.example.hw2_4_viewpager2.board.BaseFragment
import com.example.hw2_4_viewpager2.databinding.FragmentProfileBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    override fun setupUI() {

        showRegisterFragment()
        setTextFromSharedPref()
        binding.tvUserChecker.text = App.prefs.getUserMarried().toString()
        logOut()
    }

    private fun logOut() {
        val btnLogOutUser: FloatingActionButton = requireView().findViewById(R.id._btn_log_out_user)
        btnLogOutUser.setOnClickListener {
            App.prefs.logOut()
        }
    }

    private fun showRegisterFragment() {
        if (App.prefs.getUserName().isEmpty() && App.prefs.getSurUserName().isEmpty()) {
            Toast.makeText(requireContext(), "Please log again", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.registerFragment)
        } else {
            Toast.makeText(requireContext(), "Your profile", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setTextFromSharedPref() {
        binding.tvUserName.text = App.prefs.getUserName()
        binding.tvUserSurname.text = App.prefs.getSurUserName()
        binding.tvUserNumber.text = App.prefs.getUserNumber().toString()
    }


}