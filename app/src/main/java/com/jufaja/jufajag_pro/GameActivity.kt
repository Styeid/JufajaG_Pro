package com.jufaja.jufajag_pro

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.api.Distribution
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.jufaja.jufajag_pro.colls.Pils
import com.jufaja.jufajag_pro.colls.User
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_tils.*



private const val TAG = "GameActivity"
const val EXTRA_NICKNAME = "EXTRA_NICKNAME"
open class GameActivity : AppCompatActivity() {

    private var signInNick: User? = null
    private lateinit var firebaseDb: FirebaseFirestore
    private lateinit var pils: MutableList<Pils>
    private lateinit var adapter: PilsAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        supportActionBar?.title = "Base"
        //>>>>>>>
        pils = mutableListOf()
        adapter = PilsAdapter(this, pils)
        rvPils.adapter = adapter
        rvPils.layoutManager = LinearLayoutManager(this)

        //>>>>>>>
        firebaseDb = FirebaseFirestore.getInstance()
        //>>>>>>> REQUEST SIGNIN USER <<<<<<<
        firebaseDb.collection("user")
            .document(FirebaseAuth.getInstance().currentUser?.uid as String)
            .get()
            .addOnSuccessListener { userSnapshot ->
                signInNick = userSnapshot.toObject(User::class.java)
                Log.i(TAG, "signed in User: $signInNick")

            }
            .addOnFailureListener { exeption ->
                Log.i(TAG, "Failure fetching signed in user", exeption)
            }


        var tilsReference = firebaseDb
            .collection("tils")
            .limit(54)
            .orderBy("number", Query.Direction.ASCENDING)
        //>>>>>>> FILTERING TILS-PROJECTS <<<<<<<
        val nickname = (intent.getStringExtra(EXTRA_NICKNAME))
        if (nickname != null) {
            supportActionBar?.title = nickname
            tilsReference = tilsReference.whereEqualTo("user.nickname", nickname)
        }

        //>>>>>>>
        tilsReference.addSnapshotListener { snapshot, exeption ->
            if (exeption != null || snapshot == null) {
                Log.e(TAG, "Failed to get tils", exeption)
                return@addSnapshotListener
            }
            val tilsList = snapshot.toObjects(Pils::class.java)
            pils.clear()
            pils.addAll(tilsList)
            adapter.notifyDataSetChanged()
            for (tils in tilsList)
                Log.i(TAG, "DOKU $tils")
            }
            fabCreate.setOnClickListener {
                val intent = Intent(this, AddtileActivity::class.java)
                startActivity(intent)
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
            Toast.makeText(this, "Restarting 'Constructor'", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, MainActivity::class.java))
        }
        //if (item.itemId == R.id.RESERVE-RESERVE) {
        //    Log.i(TAG, "LOG-LOG 'Constructor'")
        //    val intent = (Intent(this, XX-XXActivity::class.java))
        //    finish()
        //    Toast.makeText(this, "TEXT-TEXT 'Constructor'", Toast.LENGTH_LONG).show()
        //    startActivity(intent)
        //}
        if (item.itemId == R.id.profile) {
            Log.i(TAG, "Go to profile Base 'Constructor'")
            val intent = (Intent(this, ProfileActivity::class.java))
            finish()
            Toast.makeText(this, "ProfileBase 'Constructor'", Toast.LENGTH_LONG).show()
            intent.putExtra(EXTRA_NICKNAME, signInNick?.nickname)
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }
}