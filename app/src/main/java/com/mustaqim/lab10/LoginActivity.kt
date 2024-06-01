package com.mustaqim.lab10

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.oAuthProvider
import com.mustaqim.lab10.databinding.ActivityLoginBinding
import com.mustaqim.lab10.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()

        binding.loginBtn.setOnClickListener {
            if (binding.loginEmailEditText.text.trim().toString().isNotEmpty() ||
                binding.loginPasswordEditText.text.trim().toString().isNotEmpty()) {
                userLogin(binding.loginEmailEditText.text.toString(), binding.loginPasswordEditText.text.toString())
            } else {
                Snackbar.make(
                    binding.root,
                    "Enter a valid email and password",
                    Snackbar.LENGTH_LONG
                ).show()

            }

        }
    }

    fun userLogin(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {task->
            if (task.isSuccessful) {
                val intent = Intent(this, ServiceActivity::class.java)
                startActivity(intent)
            } else {
                Snackbar.make(
                    binding.root,
                    "Enter a valid email and password",
                    Snackbar.LENGTH_LONG
                ).show()
            }

        }

    }


}