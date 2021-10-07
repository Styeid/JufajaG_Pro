package com.jufaja.jufajag_pro

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.jufaja.jufajag_pro.colls.Pils
import com.jufaja.jufajag_pro.colls.User
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.activity_jufaja.*
import kotlinx.android.synthetic.main.item_tils.*



private const val TAG = "JufajaActivity"
class JufajaActivity : AppCompatActivity() {

    private var signInNick: User? = null
    private lateinit var firebaseDb: FirebaseFirestore
    private lateinit var pils: MutableList<Pils>
    private lateinit var adapter: JFJAdapter


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jufaja)
        supportActionBar?.title = "JUFAJA"
        //>>>>>>>
        pils = mutableListOf()
        adapter = JFJAdapter(this, pils)
        rvJFJ.adapter = adapter
        rvJFJ.layoutManager = GridLayoutManager(this, 12)

        firebaseDb = FirebaseFirestore.getInstance()
        //>>>>>>> REQUEST SIGNIN USER <<<<<<<
        //firebaseDb.collection("user")
        //    .document(FirebaseAuth.getInstance().currentUser?.uid as String)
        //    .get()
        //    .addOnSuccessListener { userSnapshot ->
        //        signInNick = userSnapshot.toObject(User::class.java)
        //        Log.i(TAG, "signed in User: $signInNick")

        //    }
        //   .addOnFailureListener { exeption ->
        //        Log.i(TAG, "Failure fetching signed in user", exeption)
        //    }

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



