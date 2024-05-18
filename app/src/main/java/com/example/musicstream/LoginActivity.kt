package com.example.musicstream

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.musicstream.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()

        binding.loginBtn.setOnClickListener {
            val email = binding.emailEdittext.text.toString()
            val password = binding.passwordEdittext.text.toString()

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.emailEdittext.error = "Invalid email format"
                return@setOnClickListener
            }

            if (password.length < 6) {
                binding.passwordEdittext.setError("Length should be 6 char")
                return@setOnClickListener
            }

            loginwithFirebase(email, password)
        }

        binding.gotoSignupBtn.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }

    fun loginwithFirebase(email: String, password: String) {
        setInProgress(true)
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                setInProgress(false)
                if (task.isSuccessful) {
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                    Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Login failed. Please check your username and password.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onResume() {
        super.onResume()
        FirebaseAuth.getInstance().currentUser?.apply {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }
    }

    fun setInProgress(inProgress: Boolean) {
        if (inProgress) {
            binding.loginBtn.visibility = android.view.View.GONE
            binding.progressBar.visibility = android.view.View.VISIBLE
        } else {
            binding.loginBtn.visibility = android.view.View.VISIBLE
            binding.progressBar.visibility = android.view.View.GONE
        }
    }
}
