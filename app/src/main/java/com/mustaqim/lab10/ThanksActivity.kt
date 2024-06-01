package com.mustaqim.lab10

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mustaqim.lab10.databinding.ActivityLoginBinding
import com.mustaqim.lab10.databinding.ActivityThanksBinding

class ThanksActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThanksBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityThanksBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.goToPortalBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}