package com.example.hw2_4_viewpager2.activiti

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.hw2_4_viewpager2.R
import com.example.hw2_4_viewpager2.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    private var progres = 0
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.animation.setAnimation(R.raw.animation)
        start()

    }

    private fun start(){
        Handler(Looper.getMainLooper()).postDelayed({
            if (progres>=1500){
                startApp()
                finish()
            }else{
                start()
                progres+=100
                binding.itemProgess.progress =  progres
            }
        },100)
    }
    private fun startApp() {
        val intent = Intent(this@SplashScreenActivity,MainActivity::class.java)
        startActivity(intent)
    }
}