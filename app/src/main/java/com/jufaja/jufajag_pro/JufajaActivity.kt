package com.jufaja.jufajag_pro

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import com.jufaja.jufajag_pro.colls.Pils
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.activity_jufaja.*
import kotlinx.android.synthetic.main.item_tils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlin.Exception

private var spanCount = 12
private var fieldByOrder = "number"
private const val TAG = "JufajaActivity"

class JufajaActivity : AppCompatActivity() {

    //private var signInNick: User? = null
    private lateinit var firebaseDb: FirebaseFirestore
    private lateinit var pils: MutableList<Pils>
    private lateinit var adapter: JFJAdapter
    private val tilsCollectionRef = Firebase.firestore.collection("tils")

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jufaja)
        supportActionBar?.title = ""
        //>>>>>>> Gameplay Logic <<<<<<<


        //>>>>>>>
        //>>>>>>> Layout Logic <<<<<<<
        pils = mutableListOf()
        adapter = JFJAdapter(this, pils)
        rvJFJ.adapter = adapter
        rvJFJ.layoutManager = GridLayoutManager(this, spanCount)

        firebaseDb = FirebaseFirestore.getInstance()
        //>>>>>>> REQUEST SIGNIN USER <<<<<<<
        //firebaseDb.collection("user")
        //    .document(FirebaseAuth.getInstance().currentUser?.uid as String)
        //    .get()
        //    .addOnSuccessListener { userSnapshot ->
        //       signInNick = userSnapshot.toObject(User::class.java)
        //        Log.i(TAG, "signed in User: $signInNick")

        //    }
        //   .addOnFailureListener { exeption ->
        //        Log.i(TAG, "Failure fetching signed in user", exeption)
        //    }

        val tilsReference = firebaseDb
            .collection("tils")
            .limit(108)
            .orderBy(fieldByOrder, Query.Direction.ASCENDING)
        //>>>>>>> FILTERING TILS-PROJECTS <<<<<<<
        //val nickname = (intent.getStringExtra(EXTRA_NICKNAME))
        //if (nickname != null) {
        //    supportActionBar?.title = nickname
        //    tilsReference = tilsReference.whereEqualTo("user.nickname", nickname)
        //}

        //>>>>>>>
        tilsReference.addSnapshotListener { snapshot, exeption ->
            if (exeption != null || snapshot == null) {
                Log.e(TAG, "Failed to get tils", exeption)
                return@addSnapshotListener
            }
            val tilsList = snapshot.toObjects<Pils>()
            pils.clear()
            pils.addAll(tilsList)
            adapter.notifyDataSetChanged()
            for (tils in tilsList)
                Log.i(TAG, "DOKU $tils")
        }
        btnRB.setOnClickListener {
            val code = tvWCode.text.toString()
            val color = tvWColor.text.toString()
            val imageUrl = tvWImageU.text.toString()
            val dataWrap = Pils(code, color, imageUrl)
            saveGAB(dataWrap)
        }

        btnJU.setOnClickListener {
            //testo(tile = Pils())
            //lesto(tile = Pils())
            //oldP40P42()
            val oldie = oldP40P42()
            val newie = newP40P42()
            switchP40P42(oldie, newie)
        }
        btnFA.setOnClickListener {
            restartFromBegining()
            //    position40()
            //    tile()
        }
        btnJA.setOnClickListener {
            pos40()
            pos42()
            pos66()
            pos64()
        }


    }



    private fun pos40() = CoroutineScope(IO).launch {
        val tilsQuery = tilsCollectionRef
            .whereEqualTo("number", 40)
            .get()
            .await()
        if (tilsQuery.documents.isNotEmpty()) {
            for (tils in tilsQuery) {
                val dataBlok = firebaseDb.collection("tils").document(tils.id)
                val blok_Code = findViewById<TextView>(R.id.tvWCode)
                val blok_Color = findViewById<TextView>(R.id.tvWColor)
                val blok_ImageU = findViewById<TextView>(R.id.tvWImageU)
                try {
                    dataBlok.get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
                                blok_Code.text = document.getString("code")
                                blok_Color.text = document.getString("color")
                                blok_ImageU.text = document.getString("image_url")
                            } else {
                                Log.i(TAG, "NON DATA")
                            }
                        }
                        .addOnFailureListener { exception ->
                            Log.i(TAG, "Fetching Failure")
                        }
                } catch (e: Exception) {
                    Log.i(TAG, "ERROR Fetching DATA")
                }
            }
        } else {
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    this@JufajaActivity, "Query = Empty", Toast.LENGTH_LONG
                ).show()
            }
        }
    }
    private fun pos42() = CoroutineScope(IO).launch {
        val tilsQuery = tilsCollectionRef
            .whereEqualTo("number", 42)
            .get()
            .await()
        if (tilsQuery.documents.isNotEmpty()) {
            for (tils in tilsQuery) {
                val dataBlok = firebaseDb.collection("tils").document(tils.id)
                val blok_Code = findViewById<TextView>(R.id.tvYCode)
                val blok_Color = findViewById<TextView>(R.id.tvYColor)
                val blok_ImageU = findViewById<TextView>(R.id.tvYImageU)
                try {
                    dataBlok.get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
                                blok_Code.text = document.getString("code")
                                blok_Color.text = document.getString("color")
                                blok_ImageU.text = document.getString("image_url")
                            } else {
                                Log.i(TAG, "NON DATA")
                            }
                        }
                        .addOnFailureListener { exception ->
                            Log.i(TAG, "Fetching Failure")
                        }
                } catch (e: Exception) {
                    Log.i(TAG, "ERROR Fetching DATA")
                }
            }
        } else {
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    this@JufajaActivity, "Query = Empty", Toast.LENGTH_LONG
                ).show()
            }
        }
    }
    private fun pos66() = CoroutineScope(IO).launch {
        val tilsQuery = tilsCollectionRef
            .whereEqualTo("number", 66)
            .get()
            .await()
        if (tilsQuery.documents.isNotEmpty()) {
            for (tils in tilsQuery) {
                val dataBlok = firebaseDb.collection("tils").document(tils.id)
                val blok_Code = findViewById<TextView>(R.id.tvXCode)
                val blok_Color = findViewById<TextView>(R.id.tvXColor)
                val blok_ImageU = findViewById<TextView>(R.id.tvXImageU)
                try {
                    dataBlok.get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
                                blok_Code.text = document.getString("code")
                                blok_Color.text = document.getString("color")
                                blok_ImageU.text = document.getString("image_url")
                            } else {
                                Log.i(TAG, "NON DATA")
                            }
                        }
                        .addOnFailureListener { exception ->
                            Log.i(TAG, "Fetching Failure")
                        }
                } catch (e: Exception) {
                    Log.i(TAG, "ERROR Fetching DATA")
                }
            }
        } else {
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    this@JufajaActivity, "Query = Empty", Toast.LENGTH_LONG
                ).show()
            }
        }
    }
    private fun pos64() = CoroutineScope(IO).launch {
        val tilsQuery = tilsCollectionRef
            .whereEqualTo("number", 64)
            .get()
            .await()
        if (tilsQuery.documents.isNotEmpty()) {
            for (tils in tilsQuery) {
                val block_data = firebaseDb.collection("tils").document(tils.id)
                val blok_Code = findViewById<TextView>(R.id.tvZCode)
                val blok_Color = findViewById<TextView>(R.id.tvZColor)
                val blok_ImageU = findViewById<TextView>(R.id.tvZImageU)
                try {
                    block_data.get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
                                blok_Code.text = document.getString("code")
                                blok_Color.text = document.getString("color")
                                blok_ImageU.text = document.getString("image_url")
                            } else {
                                Log.i(TAG, "NON DATA")
                            }
                        }
                        .addOnFailureListener {
                            Log.i(TAG, "Fetching Failure")
                        }
                } catch (e: Exception) {
                    Log.i(TAG, "ERROR Fetching DATA")
                }
            }
        } else {
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    this@JufajaActivity, "Query = Empty", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun oldP40P42(): Pils {
        val dataCode = tvWCode.text.toString()
        val dataColor = tvWColor.text.toString()
        val dataUrl = tvWImageU.text.toString()
        return Pils(dataCode, dataColor, dataUrl)
    }
    private fun newP40P42(): Map<String, Any> {
        val dataCode = tvYCode.text.toString()
        val dataColor = tvYColor.text.toString()
        val dataUrl = tvYImageU.text.toString()
        val newWrap = mutableMapOf<String, Any>()
        if (dataCode.isNotEmpty()) {
            newWrap["code"] = dataCode
        }
        if (dataColor.isNotEmpty()) {
            newWrap["color"] = dataColor
        }
        if (dataUrl.isNotEmpty()) {
            newWrap["ImageUrl"] = dataCode
        }
        return newWrap
    }
    private fun switchP40P42(tile: Pils, newP40P42Map: Map<String, Any>) = CoroutineScope(IO).launch {
        val tilsQuery = tilsCollectionRef
            .whereEqualTo("code", tile.code)
            .whereEqualTo("color", tile.color)
            .whereEqualTo("imageUrl", tile.imageUrl)
            .get()
            .await()
        if (tilsQuery.documents.isNotEmpty()) {
            for (vile in tilsQuery) {
                try {
                    tilsCollectionRef.document(vile.id).set(
                        newP40P42Map,
                        SetOptions.merge()
                    ).await()
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@JufajaActivity, e.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        } else {
            withContext(Dispatchers.Main) {
                Toast.makeText(this@JufajaActivity, "Non Tile ERROR!",
                    Toast.LENGTH_LONG).show()
            }
        }
    }



    private fun saveGAB(dataWrap: Pils) = CoroutineScope(IO).launch {
        try {
            tilsCollectionRef.add(dataWrap).await()
        }catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(this@JufajaActivity, e.message,Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun restartFromBegining() {
        tilsCollectionRef.document("PsZ6hhwDzA8DwOZHRvme").update("number", 40)
        tilsCollectionRef.document("i9MeugZItvOl3mJvkGtu").update("number", 42)
        //tilsCollectionRef.document("7XKU1WSiVoyK9jW25ghf").update("number", 49)
        //tilsCollectionRef.document("sPKZnNPd2rMD1xcHD5lx").update("number", 6)


    //}
    //private fun position40() = CoroutineScope(IO).launch {
    //    try {
    //        val tilsQuerySnapshot = tilsCollectionRef.get().await()
    //        val sb = StringBuilder()
    //        for (tile in tilsQuerySnapshot.documents) {
    //            val position40 = tile.toObject<Pils>()
    //            sb.append("$position40\n")
    //            Log.i(TAG, "???-->$position40")
    //        }
    //        withContext(Dispatchers.Main) {
    //            tvTestXxxx.text = sb.toString()
    //        }

    //    } catch(e: Exception) {
    //        withContext(Dispatchers.Main) {
    //            Toast.makeText(this@JufajaActivity, e.message, Toast.LENGTH_LONG).show()
    //        }
    //    }

    }

    private fun lesto(tile: Pils) = CoroutineScope(IO).launch {
        val moveTileA= 40
        val tilsQuery = tilsCollectionRef
            //.whereEqualTo("number", 42)
            .whereEqualTo("id", "42-15")
            .get()
            .await()
        if (tilsQuery.documents.isNotEmpty()) {
            for (tils in tilsQuery) {
                 try {
                     tilsCollectionRef.document(tils.id).update("number", moveTileA)
                        .await()
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@JufajaActivity, e.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        } else {
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    this@JufajaActivity, "No Tile matched the Query",
                    Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun testo(tile: Pils) = CoroutineScope(IO).launch {
        val moveTileA =42
        //val a1A =tilsCollectionRef
        //    .whereEqualTo("tils.number", 42).get().await()
        //val moveTileb = a1A.toObjects<Ails>()
        val qilsQuery = tilsCollectionRef
            //.whereEqualTo("number", 40)
            .whereEqualTo("id", "40-13")
            .get()
            .await()
            //Log.i(TAG, "== $qilsQuery")
        if (qilsQuery.documents.isNotEmpty()) {
            for (tils in qilsQuery) {
                try {
                    tilsCollectionRef.document(tils.id).update("number", moveTileA)
                        //>>>>>>> UDATING WITH MAP OF (FIELD)DATA'S <<<<<<<
                    //tilsCollectionRef.document(document.id).update("number", moveTileB)
                    //tilsCollectionRef.document(document.id).set(
                    //    moveTile,
                    //    SetOptions.merge()
                    .await()
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@JufajaActivity, e.message,
                            Toast.LENGTH_LONG).show()
                    }
                }
            }
        } else {
            withContext(Dispatchers.Main) {
                Toast.makeText(
                    this@JufajaActivity, "No Tile matched the Query",
                    Toast.LENGTH_LONG).show()
            }
        }


        """firebaseDb = FirebaseFirestore.getInstance()
        val a1A =tilsCollectionRef
                .whereEqualTo("tils.number", 40)

        val a1B =tilsCollectionRef
                .whereEqualTo("tils.number", 42)
        val a1Ax = 40
        val a1Bx = 42
        val a1 = tilsCollectionRef
            .document("PsZ6hhwDzA8DwOZHRvme").update("number", a1B)
        val b1  = tilsCollectionRef
            .document("i9MeugZItvOl3mJvkGtu").update("number", a1A)

        Log.i(TAG, "a1 = a1")
        Log.i(TAG, "a1A = a1A / a1Ax")
        Log.i(TAG, "b1 = b1")
        Log.i(TAG, "a1B = a1B / a1Bx")"""


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
            //intent.putExtra(EXTRA_NICKNAME, signInNick?.nickname)
            startActivity(intent)
        }

            return super.onOptionsItemSelected(item)
    }

}



