package com.example.musicstream

import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.musicstream.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
class SignupActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.createAccountBtn.setOnClickListener {
            val email = binding.emailEdittext.text.toString()
            val password = binding.passwordEdittext.text.toString()
            val confirmpassword = binding.confirmPasswordEdittext.text.toString()

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.emailEdittext.error = "Invalid email format"
                return@setOnClickListener
            }

            if (password.length < 6) {
                binding.passwordEdittext.setError("Length should be 6 char")
                return@setOnClickListener
            }

            if (password != confirmpassword){
                binding.confirmPasswordEdittext.setError("Password not matched")
                return@setOnClickListener
            }

            createAccountWithFirebase(email,password)



        }

        binding.gotoLoginBtn.setOnClickListener{
            finish()
        }
    }


    fun createAccountWithFirebase(email : String,password: String){
       setInProgress(true)
       FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
           .addOnSuccessListener{
               setInProgress(false)
               Toast.makeText(applicationContext,"User created successfully",Toast.LENGTH_SHORT).show()
               finish()
           }.addOnSuccessListener{
               setInProgress(false)
               Toast.makeText(applicationContext,"Create account failed",Toast.LENGTH_SHORT).show()
           }
    }




    fun setInProgress(inProgress : Boolean){
        if (inProgress){
            binding.createAccountBtn.visibility = android.view.View.GONE
            binding.progressBar.visibility = android.view.View.VISIBLE
        }else{
            binding.createAccountBtn.visibility =  android.view.View.VISIBLE
            binding.progressBar.visibility = android.view.View.GONE
        }
    }

}