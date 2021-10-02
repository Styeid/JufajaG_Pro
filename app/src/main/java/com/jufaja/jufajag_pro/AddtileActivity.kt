package com.jufaja.jufajag_pro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

private const val TAG = "AddtileActivity"
class AddtileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addtile)
        supportActionBar?.title = "Add"

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_addtile, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.backToGameA) {
            Log.i(TAG, "Back to base 'Constructor'")
            val intent = (Intent(this, GameActivity::class.java))
            finish()
            Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}