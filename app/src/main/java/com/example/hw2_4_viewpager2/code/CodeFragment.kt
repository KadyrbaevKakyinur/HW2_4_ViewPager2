package com.example.hw2_4_viewpager2.code


import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.hw2_4_viewpager2.board.BaseFragment
import com.example.hw2_4_viewpager2.databinding.FragmentCodeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider

class CodeFragment : BaseFragment<FragmentCodeBinding>(FragmentCodeBinding::inflate) {

    lateinit var id: String
    lateinit var mAuth : FirebaseAuth

    override fun setupUI() {
        mAuth = FirebaseAuth.getInstance()
        if (arguments != null) {
            id = arguments?.getString("id").toString()
        }
    }



    override fun setupObserver() {
        binding.btnInit.setOnClickListener {
            val code = binding.edPhone.text.toString()
            val credential = PhoneAuthProvider.getCredential(id, code)
            mAuth.signInWithCredential(credential).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(requireContext(), "Authentication done, successful", Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp()
                } else {
                        Log.e("ololo", it.exception?.message.toString())
                }
            }
        }
    }


}