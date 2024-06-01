package com.mustaqim.lab10

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mustaqim.lab10.databinding.ActivityLoginBinding
import com.mustaqim.lab10.databinding.ActivityMainBinding
import com.mustaqim.lab10.databinding.ActivityServiceBinding

class ServiceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityServiceBinding
    private lateinit var db: FirebaseFirestore;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityServiceBinding.inflate(layoutInflater)
        val view = binding.root

        db = Firebase.firestore
        setContentView(view)
        }

    fun readFirestoreData(){
        db.collection("customers")
            .get()
            . addOnCompleteListener {
                val result:StringBuffer = StringBuffer()
                if (it.isSuccessful){
                    for (document in it.result!!){
                        val city = document.get("city") ?: "N/A"
                        val country = document.get("country") ?: "N/A"
                        val name = document.get("name") ?: "N/A"
                        val phone = document.get("phone") ?: "N/A"


                        result.append("Name:  ").append(name).append("\n")
                            .append("Phone:  ").append(phone).append("\n")
                            .append("City:  ").append(city).append("\n")
                            .append("Country:  ").append(country).append("\n")
                    }

                    binding.resultTextViewText.text = result.toString().trim()
                }
            }
    }

}

