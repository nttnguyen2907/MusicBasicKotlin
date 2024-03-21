package com.example.music.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.music.constant.AboutUsConfig
import com.example.music.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private var mActivitySplashBinding: ActivitySplashBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        mActivitySplashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(mActivitySplashBinding!!.root)

        initUi()
        startMainActivity()
    }

    private fun initUi() {
        mActivitySplashBinding?.tvAboutUsTitle?.text = AboutUsConfig.ABOUT_US_TITLE
        mActivitySplashBinding?.tvAboutUsSlogan?.text = AboutUsConfig.ABOUT_US_SLOGAN
    }

    private fun startMainActivity() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }, 1500)
    }
}