package com.jufaja.jufajag_pro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

private const val TAG = "ProfileActivity"
class ProfileActivity : GameActivity() {


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_profile, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.backToGameB) {
            Log.i(TAG, "Back to base 'Constructor'")
            val intent = (Intent(this, GameActivity::class.java))
            finish()
            Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }
}