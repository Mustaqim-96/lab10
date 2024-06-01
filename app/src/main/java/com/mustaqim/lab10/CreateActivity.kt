package com.mustaqim.lab10

import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.rpc.context.AttributeContext.Auth
import com.mustaqim.lab10.databinding.ActivityCreateBinding
import com.mustaqim.lab10.databinding.ActivityMainBinding

class CreateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateBinding
    private lateinit var auth: FirebaseAuth;
    private lateinit var db:FirebaseFirestore;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()

        binding.createBtn.setOnClickListener {
            createUser(binding.createEmailEditText.text.toString(),binding.createPasswordEditText.text.toString())
        }

        db = Firebase.firestore
    }

    fun createUser(email:String, password:String){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if (task.isSuccessful){
                val intent = Intent(this, ThanksActivity::class.java)
                startActivity(intent)
            }
            else {
                Snackbar.make(
                    binding.root,
                    "Enter a valid email and password",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

    }

    private fun newCustomer(){
        val customers = hashMapOf(
            "name" to binding.createFullNameEditText.text.toString().trim(),
            "city" to binding.createCityEditText.text.toString().trim(),
            "country" to binding.createCountryEditText.text.toString().trim(),
            "phone number" to binding.createPhoneEditText.text.toString().trim()
        )

        db.collection("customers")
            .add(customers)
            .addOnSuccessListener {
                documentReference ->
                Log.d("debug","Document successfully added with id ${documentReference.id}")
            }

            .addOnFailureListener {  e ->
                Log.d("debug","An error happen ${e.message}")
            }
        }

    }

