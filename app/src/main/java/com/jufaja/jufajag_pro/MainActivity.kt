package com.jufaja.jufajag_pro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "MainActivity"
open class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val auth = FirebaseAuth.getInstance()

            if (auth.currentUser != null ) {
                goGameActivity()
            }
        btnStartGame.setOnClickListener {
            btnStartGame.isEnabled = false
            val logoOne = tvHomeLogo1.text.toString()
            val logoTwo = tvHomeLogo2.text.toString()
            val nickName = etNick.text.toString()
            if (logoOne.isBlank() || logoTwo.isBlank() || nickName.isBlank()) {
                btnStartGame.isEnabled = true
                Toast.makeText(this, "Please enter your Nick-name", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(logoOne, logoTwo)
                .addOnCompleteListener { task ->
                    btnStartGame.isEnabled = true
                    if (task.isSuccessful) {
                        btnStartGame.isEnabled = false
                        Toast.makeText(this, "$nickName Success!", Toast.LENGTH_LONG).show()
                        goGameActivity()
                    } else {
                        Log.e(TAG, "Starting game Failed", task.exception)
                        Toast.makeText(this, "Starting game Failed", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
    private fun goGameActivity() {
        Log.i(TAG, "goGameActivity")
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
        finish()
    }
}

