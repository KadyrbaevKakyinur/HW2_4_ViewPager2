package com.example.hw2_4_viewpager2.init

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.hw2_4_viewpager2.R
import com.example.hw2_4_viewpager2.activiti.MainActivity
import com.example.hw2_4_viewpager2.board.BaseFragment
import com.example.hw2_4_viewpager2.databinding.FragmentPhoneBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class PhoneFragment : BaseFragment<FragmentPhoneBinding>(FragmentPhoneBinding::inflate) {


    private lateinit var mAuth: FirebaseAuth
    lateinit var phone: String
    lateinit var callback: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun setupUI() {
        mAuth = FirebaseAuth.getInstance()
    }



    override fun setupObserver() {
        binding.btnInit.setOnClickListener {
            phone = binding.edPhone.text.toString()
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone, 60, TimeUnit.SECONDS,
                activity as MainActivity, callback
            )
            Log.e("ololo1", "hello")
        }
        callback = object: PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                mAuth.signInWithCredential(credential).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(requireContext(), "Welcome to app", Toast.LENGTH_SHORT).show()
                        findNavController().navigateUp()
                    } else {
                        Log.e("ololo", task.exception?.message.toString())
                    }
                }
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Toast.makeText(requireContext(), "Your entered phone number invalid, please check and try again", Toast.LENGTH_SHORT).show()
                Log.e("ololo", p0.message.toString())
            }

            override fun onCodeSent(id: String, p1: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(id, p1)
                val bundle = Bundle()
                bundle.putString("id", id)
                findNavController().navigate(R.id.codeFragment, bundle)
            }
        }
    }

}