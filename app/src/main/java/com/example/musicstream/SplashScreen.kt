package com.example.musicstream

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val currentUser = FirebaseAuth.getInstance().currentUser



        val intent1 = Intent(this,LoginActivity::class.java)
        val intent2 = Intent(this,MainActivity::class.java)

        Handler(Looper.getMainLooper()).postDelayed({
            if(currentUser!=null){
                startActivity(intent2)
            }
            else{
                startActivity(intent1)
            }
            finish()
        },1500)


    }
}