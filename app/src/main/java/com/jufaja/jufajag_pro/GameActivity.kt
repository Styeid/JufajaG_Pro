package com.jufaja.jufajag_pro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.jufaja.jufajag_pro.colls.Tils
import kotlinx.android.synthetic.main.activity_main.*


private const val TAG = "GameActivity"
class GameActivity : AppCompatActivity() {

    private lateinit var firebaseDb: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        //val nickName = etNick2.text.toString()
        //var nickName = etNick.text.toString()
        supportActionBar?.title = "GAME"

        firebaseDb = FirebaseFirestore.getInstance()
        val tilsReference = firebaseDb
            .collection("tils")
            .limit(54)
            .orderBy("id", Query.Direction.ASCENDING)
        tilsReference.addSnapshotListener { snapshot, exeption ->
            if (exeption != null || snapshot == null) {
                Log.e(TAG, "Failed to get tils", exeption)
                return@addSnapshotListener
            }
            val tilsList = snapshot.toObjects(Tils::class.java)
            for (tils in tilsList)
                Log.i(TAG, "DOKU $tils")
            }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_game, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.restartGame) {
            Log.i(TAG, "restart/boot game")
            FirebaseAuth.getInstance().signOut()
            finish()
            Toast.makeText(this, "Constructor Restarting", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, MainActivity::class.java))
        }

        return super.onOptionsItemSelected(item)
    }
}