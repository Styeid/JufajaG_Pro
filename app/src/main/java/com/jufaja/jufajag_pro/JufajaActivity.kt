package com.jufaja.jufajag_pro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.item_tils.*

private const val TAG = "JufajaActivity"
class JufajaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jufaja)
        supportActionBar?.title = "JUFAJA"

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_jufaja, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.restartGame) {
            Log.i(TAG, "user wants to restart game")
            FirebaseAuth.getInstance().signOut()
            finish()
            Toast.makeText(this, "Restarting", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, MainActivity::class.java))
        }
        if (item.itemId == R.id.rvBase) {
            Log.i(TAG, "Constructor mode")
            val intent = (Intent(this, GameActivity::class.java))
            finish()
            Toast.makeText(this, "Constructor mode", Toast.LENGTH_LONG).show()
            startActivity(intent)
        }

            return super.onOptionsItemSelected(item)
    }

}



