package com.jufaja.jufajag_pro

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import com.jufaja.jufajag_pro.colls.Pils
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.activity_jufaja.*
import kotlinx.android.synthetic.main.item_tils.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.tasks.await
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
        //btnRB.setOnClickListener {
        //    val code = tvWCode.text.toString()
        //    val color = tvWColor.text.toString()
        //    val imageUrl = tvWImageU.text.toString()
        //    val dataWrap = Pils(code, color, imageUrl)
        //    saveGAB(dataWrap)
        //}

        btnJU.setOnClickListener {
            clockwiseRight()
        }

        btnFA.setOnClickListener {
            rightDown()
        }

        btnJA.setOnClickListener {
            restartFromBegining()
        }


    }
    //>>>>>>> Twist-Function's <<<<<<<

    private fun clockwiseRight()= CoroutineScope(IO).launch {
        val posAdd11 = 40; val posAdd12 = 42; val posAdd13 = 66; val posAdd14 = 64
        val posAdd21 = 39; val posAdd22 = 30; val posAdd23 = 67; val posAdd24 = 76
        val posAdd31 = 28; val posAdd32 = 43; val posAdd33 = 78; val posAdd34 = 63
        val posAdd41 = 41; val posAdd42 = 54; val posAdd43 = 65; val posAdd44 = 52
        val posAdd51 = 29; val posAdd52 = 55; val posAdd53 = 77; val posAdd54 = 51
        val dataRapW = blokW(); val dataRapX = blokX()
        val dataRapY = blokY(); val dataRapZ = blokZ()
        val dataRapW2 = blokW2(); val dataRapX2 = blokX2()
        val dataRapY2 = blokY2(); val dataRapZ2 = blokZ2()
        val dataRapW3 = blokW3(); val dataRapX3 = blokX3()
        val dataRapY3 = blokY3(); val dataRapZ3 = blokZ3()
        val dataRapW4 = blokW4(); val dataRapX4 = blokX4()
        val dataRapY4 = blokY4(); val dataRapZ4 = blokZ4()
        val dataRapW5 = blokW5(); val dataRapX5 = blokX5()
        val dataRapY5 = blokY5(); val dataRapZ5 = blokZ5()
        try {

            delay(200L)
            switchData(posAdd11, dataRapZ); switchData(posAdd12, dataRapW)
            switchData(posAdd13, dataRapY); switchData(posAdd14, dataRapX)
            switchData(posAdd21, dataRapZ2); switchData(posAdd22, dataRapW2)
            switchData(posAdd23, dataRapY2); switchData(posAdd24, dataRapX2)
            switchData(posAdd31, dataRapZ3); switchData(posAdd32, dataRapW3)
            switchData(posAdd33, dataRapY3); switchData(posAdd34, dataRapX3)
            switchData(posAdd41, dataRapZ4); switchData(posAdd42, dataRapW4)
            switchData(posAdd43, dataRapY4); switchData(posAdd44, dataRapX4)
            switchData(posAdd51, dataRapZ5); switchData(posAdd52, dataRapW5)
            switchData(posAdd53, dataRapY5); switchData(posAdd54, dataRapX5)
            delay(1000L)
            hoek1(posAdd11, posAdd12, posAdd13, posAdd14)
            delay(1000L)
            hoek2(posAdd21, posAdd22, posAdd23, posAdd24)
            delay(1000L)
            hoek3(posAdd31, posAdd32, posAdd33, posAdd34)
            delay(1000L)
            hoek4(posAdd41, posAdd42, posAdd43, posAdd44)
            delay(1000L)
            hoek5(posAdd51, posAdd52, posAdd53, posAdd54)
            //hoek1(posAdd11, posAdd12, posAdd13, posAdd14)
            //hoek2(posAdd21, posAdd22, posAdd23, posAdd24)
            //hoek3(posAdd31, posAdd32, posAdd33, posAdd34)
            //hoek4(posAdd41, posAdd42, posAdd43, posAdd44)
            //hoek5(posAdd51, posAdd52, posAdd53, posAdd54)
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(this@JufajaActivity, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun rightDown()= CoroutineScope(IO).launch {
        val posAdd11 = 37; val posAdd12 = 39; val posAdd13 = 63; val posAdd14 = 61
        val posAdd21 = 48; val posAdd22 = 28; val posAdd23 = 64; val posAdd24 = 100
        val posAdd31 = 4; val posAdd32 = 40; val posAdd33 = 76; val posAdd34 = 72
        val posAdd41 = 38; val posAdd42 = 51; val posAdd43 = 62; val posAdd44 = 49
        val posAdd51 = 16; val posAdd52 = 52; val posAdd53 = 88; val posAdd54 = 60
        val dataRapW = blokW(); val dataRapX = blokX()
        val dataRapY = blokY(); val dataRapZ = blokZ()
        val dataRapW2 = blokW2(); val dataRapX2 = blokX2()
        val dataRapY2 = blokY2(); val dataRapZ2 = blokZ2()
        val dataRapW3 = blokW3(); val dataRapX3 = blokX3()
        val dataRapY3 = blokY3(); val dataRapZ3 = blokZ3()
        val dataRapW4 = blokW4(); val dataRapX4 = blokX4()
        val dataRapY4 = blokY4(); val dataRapZ4 = blokZ4()
        val dataRapW5 = blokW5(); val dataRapX5 = blokX5()
        val dataRapY5 = blokY5(); val dataRapZ5 = blokZ5()
            try {
                delay(100L)
                hoek1(posAdd11, posAdd12, posAdd13, posAdd14)
                delay(200L)
                hoek2(posAdd21, posAdd22, posAdd23, posAdd24)
                delay(300L)
                hoek3(posAdd31, posAdd32, posAdd33, posAdd34)
                delay(400L)
                hoek4(posAdd41, posAdd42, posAdd43, posAdd44)
                delay(500L)
                hoek5(posAdd51, posAdd52, posAdd53, posAdd54)
                delay(600L)
                //switchData(posAdd11, dataRapZ); switchData(posAdd12, dataRapW)
                //switchData(posAdd13, dataRapY); switchData(posAdd14, dataRapX)
                //switchData(posAdd21, dataRapZ2); switchData(posAdd22, dataRapW2)
                //switchData(posAdd23, dataRapY2); switchData(posAdd24, dataRapX2)
                //switchData(posAdd31, dataRapZ3); switchData(posAdd32, dataRapW3)
                //switchData(posAdd33, dataRapY3); switchData(posAdd34, dataRapX3)
                //switchData(posAdd41, dataRapZ4); switchData(posAdd42, dataRapW4)
                //switchData(posAdd43, dataRapY4); switchData(posAdd44, dataRapX4)
                //switchData(posAdd51, dataRapZ5); switchData(posAdd52, dataRapW5)
                //switchData(posAdd53, dataRapY5); switchData(posAdd54, dataRapX5)
                //hoek1(posAdd11, posAdd12, posAdd13, posAdd14)
                //hoek2(posAdd21, posAdd22, posAdd23, posAdd24)
                //hoek3(posAdd31, posAdd32, posAdd33, posAdd34)
                //hoek4(posAdd41, posAdd42, posAdd43, posAdd44)
                //hoek5(posAdd51, posAdd52, posAdd53, posAdd54)
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@JufajaActivity, e.message, Toast.LENGTH_LONG).show()
                }
            }

    }

    private fun hoek1(pos1: Int, pos2: Int, pos3: Int, pos4: Int) = CoroutineScope(IO).launch {
        val tilsQuer1 = tilsCollectionRef
            .whereEqualTo("number", pos1)
            .get()
            .await()
        if (tilsQuer1.documents.isNotEmpty()) {
            for (tils1 in tilsQuer1) {
                val dataBlok1 = firebaseDb.collection("tils").document(tils1.id)
                val blok_Code1 = findViewById<TextView>(R.id.tvWCode)
                val blok_Color1 = findViewById<TextView>(R.id.tvWColor)
                val blok_ImageU1 = findViewById<TextView>(R.id.tvWImageU)
                try {
                    dataBlok1.get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
                                blok_Code1.text = document.getString("code")
                                blok_Color1.text = document.getString("color")
                                blok_ImageU1.text = document.getString("image_url")
                            } else {
                                Log.i(TAG, "-1-NON DATA")
                            }
                        }
                        .addOnFailureListener {
                            Log.i(TAG, "-1-Fetching Failure")
                        }
                } catch (e: Exception) {
                    Log.i(TAG, "-1-ERROR Fetching DATA")
                }
        val tilsQuer2 = tilsCollectionRef
            .whereEqualTo("number", pos2)
            .get()
            .await()
        if (tilsQuer2.documents.isNotEmpty()) {
            for (tils2 in tilsQuer2) {
                val dataBlok2 = firebaseDb.collection("tils").document(tils2.id)
                val blok_Code2 = findViewById<TextView>(R.id.tvYCode)
                val blok_Color2 = findViewById<TextView>(R.id.tvYColor)
                val blok_ImageU2 = findViewById<TextView>(R.id.tvYImageU)
                try {
                    dataBlok2.get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
                                blok_Code2.text = document.getString("code")
                                blok_Color2.text = document.getString("color")
                                blok_ImageU2.text = document.getString("image_url")
                            } else {
                                Log.i(TAG, "-2-NON DATA")
                            }
                        }
                        .addOnFailureListener {
                            Log.i(TAG, "-2-Fetching Failure")
                        }
                } catch (e: Exception) {
                    Log.i(TAG, "-2-ERROR Fetching DATA")
                }
                val tilsQuer3 = tilsCollectionRef
                    .whereEqualTo("number", pos3)
                    .get()
                    .await()
        if (tilsQuer3.documents.isNotEmpty()) {
            for (tils3 in tilsQuer3) {
                val dataBlok3 = firebaseDb.collection("tils").document(tils3.id)
                val blok_Code3 = findViewById<TextView>(R.id.tvXCode)
                val blok_Color3 = findViewById<TextView>(R.id.tvXColor)
                val blok_ImageU3 = findViewById<TextView>(R.id.tvXImageU)
                try {
                    dataBlok3.get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
                                blok_Code3.text = document.getString("code")
                                blok_Color3.text = document.getString("color")
                                blok_ImageU3.text = document.getString("image_url")
                            } else {
                                Log.i(TAG, "-3-NON DATA")
                            }
                        }
                        .addOnFailureListener {
                            Log.i(TAG, "-3-Fetching Failure")
                        }
                } catch (e: Exception) {
                    Log.i(TAG, "-3-ERROR Fetching DATA")
                }
        val tilsQuer4 = tilsCollectionRef
            .whereEqualTo("number", pos4)
            .get()
            .await()
        if (tilsQuer4.documents.isNotEmpty()) {
            for (tils4 in tilsQuer4) {
                val block_data4 = firebaseDb.collection("tils").document(tils4.id)
                val blok_Code4 = findViewById<TextView>(R.id.tvZCode)
                val blok_Color4 = findViewById<TextView>(R.id.tvZColor)
                val blok_ImageU4 = findViewById<TextView>(R.id.tvZImageU)
                try {
                    block_data4.get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
                                blok_Code4.text = document.getString("code")
                                blok_Color4.text = document.getString("color")
                                blok_ImageU4.text = document.getString("image_url")
                            } else {
                                Log.i(TAG, "-4-NON DATA")
                            }
                        }
                        .addOnFailureListener {
                            Log.i(TAG, "-4-Fetching Failure")
                        }
                } catch (e: Exception) {
                    Log.i(TAG, "-4-ERROR Fetching DATA")
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
                }
            }
        }
            }
        }
    }
    private fun hoek2(pos1: Int, pos2: Int, pos3: Int, pos4: Int) = CoroutineScope(IO).launch {
        val tilsQuer1 = tilsCollectionRef
            .whereEqualTo("number", pos1)
            .get()
            .await()
        if (tilsQuer1.documents.isNotEmpty()) {
            for (tils1 in tilsQuer1) {
                val dataBlok1 = firebaseDb.collection("tils").document(tils1.id)
                val blok_Code1 = findViewById<TextView>(R.id.tvWCode2)
                val blok_Color1 = findViewById<TextView>(R.id.tvWColor2)
                val blok_ImageU1 = findViewById<TextView>(R.id.tvWImageU2)
                try {
                    dataBlok1.get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
                                blok_Code1.text = document.getString("code")
                                blok_Color1.text = document.getString("color")
                                blok_ImageU1.text = document.getString("image_url")
                            } else {
                                Log.i(TAG, "-1-NON DATA")
                            }
                        }
                        .addOnFailureListener {
                            Log.i(TAG, "-1-Fetching Failure")
                        }
                } catch (e: Exception) {
                    Log.i(TAG, "-1-ERROR Fetching DATA")
                }
                val tilsQuer2 = tilsCollectionRef
                    .whereEqualTo("number", pos2)
                    .get()
                    .await()
        if (tilsQuer2.documents.isNotEmpty()) {
            for (tils2 in tilsQuer2) {
                val dataBlok2 = firebaseDb.collection("tils").document(tils2.id)
                val blok_Code2 = findViewById<TextView>(R.id.tvYCode2)
                val blok_Color2 = findViewById<TextView>(R.id.tvYColor2)
                val blok_ImageU2 = findViewById<TextView>(R.id.tvYImageU2)
                try {
                    dataBlok2.get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
                                blok_Code2.text = document.getString("code")
                                blok_Color2.text = document.getString("color")
                                blok_ImageU2.text = document.getString("image_url")
                            } else {
                                Log.i(TAG, "-2-NON DATA")
                            }
                        }
                        .addOnFailureListener {
                            Log.i(TAG, "-2-Fetching Failure")
                        }
                } catch (e: Exception) {
                    Log.i(TAG, "-2-ERROR Fetching DATA")
                }
                val tilsQuer3 = tilsCollectionRef
                    .whereEqualTo("number", pos3)
                    .get()
                    .await()
        if (tilsQuer3.documents.isNotEmpty()) {
            for (tils3 in tilsQuer3) {
                val dataBlok3 = firebaseDb.collection("tils").document(tils3.id)
                val blok_Code3 = findViewById<TextView>(R.id.tvXCode2)
                val blok_Color3 = findViewById<TextView>(R.id.tvXColor2)
                val blok_ImageU3 = findViewById<TextView>(R.id.tvXImageU2)
                try {
                    dataBlok3.get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
                                blok_Code3.text = document.getString("code")
                                blok_Color3.text = document.getString("color")
                                blok_ImageU3.text = document.getString("image_url")
                            } else {
                                Log.i(TAG, "-3-NON DATA")
                            }
                        }
                        .addOnFailureListener {
                            Log.i(TAG, "-3-Fetching Failure")
                        }
                } catch (e: Exception) {
                    Log.i(TAG, "-3-ERROR Fetching DATA")
                }
                val tilsQuer4 = tilsCollectionRef
                    .whereEqualTo("number", pos4)
                    .get()
                    .await()
        if (tilsQuer4.documents.isNotEmpty()) {
            for (tils4 in tilsQuer4) {
                val block_data4 = firebaseDb.collection("tils").document(tils4.id)
                val blok_Code4 = findViewById<TextView>(R.id.tvZCode2)
                val blok_Color4 = findViewById<TextView>(R.id.tvZColor2)
                val blok_ImageU4 = findViewById<TextView>(R.id.tvZImageU2)
                try {
                    block_data4.get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
                                blok_Code4.text = document.getString("code")
                                blok_Color4.text = document.getString("color")
                                blok_ImageU4.text = document.getString("image_url")
                            } else {
                                Log.i(TAG, "-4-NON DATA")
                            }
                        }
                        .addOnFailureListener {
                            Log.i(TAG, "-4-Fetching Failure")
                        }
                } catch (e: Exception) {
                    Log.i(TAG, "-4-ERROR Fetching DATA")
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
                }
            }
        }
            }
        }
    }
    private fun hoek3(pos1: Int, pos2: Int, pos3: Int, pos4: Int) = CoroutineScope(IO).launch {
        val tilsQuer1 = tilsCollectionRef
            .whereEqualTo("number", pos1)
            .get()
            .await()
        if (tilsQuer1.documents.isNotEmpty()) {
            for (tils1 in tilsQuer1) {
                val dataBlok1 = firebaseDb.collection("tils").document(tils1.id)
                val blok_Code1 = findViewById<TextView>(R.id.tvWCode3)
                val blok_Color1 = findViewById<TextView>(R.id.tvWColor3)
                val blok_ImageU1 = findViewById<TextView>(R.id.tvWImageU3)
                try {
                    dataBlok1.get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
                                blok_Code1.text = document.getString("code")
                                blok_Color1.text = document.getString("color")
                                blok_ImageU1.text = document.getString("image_url")
                            } else {
                                Log.i(TAG, "-1-NON DATA")
                            }
                        }
                        .addOnFailureListener {
                            Log.i(TAG, "-1-Fetching Failure")
                        }
                } catch (e: Exception) {
                    Log.i(TAG, "-1-ERROR Fetching DATA")
                }
                val tilsQuer2 = tilsCollectionRef
                    .whereEqualTo("number", pos2)
                    .get()
                    .await()
        if (tilsQuer2.documents.isNotEmpty()) {
            for (tils2 in tilsQuer2) {
                val dataBlok2 = firebaseDb.collection("tils").document(tils2.id)
                val blok_Code2 = findViewById<TextView>(R.id.tvYCode3)
                val blok_Color2 = findViewById<TextView>(R.id.tvYColor3)
                val blok_ImageU2 = findViewById<TextView>(R.id.tvYImageU3)
                try {
                    dataBlok2.get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
                                blok_Code2.text = document.getString("code")
                                blok_Color2.text = document.getString("color")
                                blok_ImageU2.text = document.getString("image_url")
                            } else {
                                Log.i(TAG, "-2-NON DATA")
                            }
                        }
                        .addOnFailureListener {
                            Log.i(TAG, "-2-Fetching Failure")
                        }
                } catch (e: Exception) {
                    Log.i(TAG, "-2-ERROR Fetching DATA")
                }
                val tilsQuer3 = tilsCollectionRef
                    .whereEqualTo("number", pos3)
                    .get()
                    .await()
        if (tilsQuer3.documents.isNotEmpty()) {
            for (tils3 in tilsQuer3) {
                val dataBlok3 = firebaseDb.collection("tils").document(tils3.id)
                val blok_Code3 = findViewById<TextView>(R.id.tvXCode3)
                val blok_Color3 = findViewById<TextView>(R.id.tvXColor3)
                val blok_ImageU3 = findViewById<TextView>(R.id.tvXImageU3)
                try {
                    dataBlok3.get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
                                blok_Code3.text = document.getString("code")
                                blok_Color3.text = document.getString("color")
                                blok_ImageU3.text = document.getString("image_url")
                            } else {
                                Log.i(TAG, "-3-NON DATA")
                            }
                        }
                        .addOnFailureListener {
                            Log.i(TAG, "-3-Fetching Failure")
                        }
                } catch (e: Exception) {
                    Log.i(TAG, "-3-ERROR Fetching DATA")
                }
                val tilsQuer4 = tilsCollectionRef
                    .whereEqualTo("number", pos4)
                    .get()
                    .await()
        if (tilsQuer4.documents.isNotEmpty()) {
            for (tils4 in tilsQuer4) {
                val block_data4 = firebaseDb.collection("tils").document(tils4.id)
                val blok_Code4 = findViewById<TextView>(R.id.tvZCode3)
                val blok_Color4 = findViewById<TextView>(R.id.tvZColor3)
                val blok_ImageU4 = findViewById<TextView>(R.id.tvZImageU3)
                try {
                    block_data4.get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
                                blok_Code4.text = document.getString("code")
                                blok_Color4.text = document.getString("color")
                                blok_ImageU4.text = document.getString("image_url")
                            } else {
                                Log.i(TAG, "-4-NON DATA")
                            }
                        }
                        .addOnFailureListener {
                            Log.i(TAG, "-4-Fetching Failure")
                        }
                } catch (e: Exception) {
                    Log.i(TAG, "-4-ERROR Fetching DATA")
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
                }
            }
        }
            }
        }
    }
    private fun hoek4(pos1: Int, pos2: Int, pos3: Int, pos4: Int) = CoroutineScope(IO).launch {
        val tilsQuer1 = tilsCollectionRef
            .whereEqualTo("number", pos1)
            .get()
            .await()
        if (tilsQuer1.documents.isNotEmpty()) {
            for (tils1 in tilsQuer1) {
                val dataBlok1 = firebaseDb.collection("tils").document(tils1.id)
                val blok_Code1 = findViewById<TextView>(R.id.tvWCode4)
                val blok_Color1 = findViewById<TextView>(R.id.tvWColor4)
                val blok_ImageU1 = findViewById<TextView>(R.id.tvWImageU4)
                try {
                    dataBlok1.get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
                                blok_Code1.text = document.getString("code")
                                blok_Color1.text = document.getString("color")
                                blok_ImageU1.text = document.getString("image_url")
                            } else {
                                Log.i(TAG, "-1-NON DATA")
                            }
                        }
                        .addOnFailureListener {
                            Log.i(TAG, "-1-Fetching Failure")
                        }
                } catch (e: Exception) {
                    Log.i(TAG, "-1-ERROR Fetching DATA")
                }
                val tilsQuer2 = tilsCollectionRef
                    .whereEqualTo("number", pos2)
                    .get()
                    .await()
        if (tilsQuer2.documents.isNotEmpty()) {
            for (tils2 in tilsQuer2) {
                val dataBlok2 = firebaseDb.collection("tils").document(tils2.id)
                val blok_Code2 = findViewById<TextView>(R.id.tvYCode4)
                val blok_Color2 = findViewById<TextView>(R.id.tvYColor4)
                val blok_ImageU2 = findViewById<TextView>(R.id.tvYImageU4)
                try {
                    dataBlok2.get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
                                blok_Code2.text = document.getString("code")
                                blok_Color2.text = document.getString("color")
                                blok_ImageU2.text = document.getString("image_url")
                            } else {
                                Log.i(TAG, "-2-NON DATA")
                            }
                        }
                        .addOnFailureListener {
                            Log.i(TAG, "-2-Fetching Failure")
                        }
                } catch (e: Exception) {
                    Log.i(TAG, "-2-ERROR Fetching DATA")
                }
                val tilsQuer3 = tilsCollectionRef
                    .whereEqualTo("number", pos3)
                    .get()
                    .await()
        if (tilsQuer3.documents.isNotEmpty()) {
            for (tils3 in tilsQuer3) {
                val dataBlok3 = firebaseDb.collection("tils").document(tils3.id)
                val blok_Code3 = findViewById<TextView>(R.id.tvXCode4)
                val blok_Color3 = findViewById<TextView>(R.id.tvXColor4)
                val blok_ImageU3 = findViewById<TextView>(R.id.tvXImageU4)
                try {
                    dataBlok3.get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
                                blok_Code3.text = document.getString("code")
                                blok_Color3.text = document.getString("color")
                                blok_ImageU3.text = document.getString("image_url")
                            } else {
                                Log.i(TAG, "-3-NON DATA")
                            }
                        }
                        .addOnFailureListener {
                            Log.i(TAG, "-3-Fetching Failure")
                        }
                } catch (e: Exception) {
                    Log.i(TAG, "-3-ERROR Fetching DATA")
                }
                val tilsQuer4 = tilsCollectionRef
                    .whereEqualTo("number", pos4)
                    .get()
                    .await()
        if (tilsQuer4.documents.isNotEmpty()) {
            for (tils4 in tilsQuer4) {
                val block_data4 = firebaseDb.collection("tils").document(tils4.id)
                val blok_Code4 = findViewById<TextView>(R.id.tvZCode4)
                val blok_Color4 = findViewById<TextView>(R.id.tvZColor4)
                val blok_ImageU4 = findViewById<TextView>(R.id.tvZImageU4)
                try {
                    block_data4.get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
                                blok_Code4.text = document.getString("code")
                                blok_Color4.text = document.getString("color")
                                blok_ImageU4.text = document.getString("image_url")
                            } else {
                                Log.i(TAG, "-4-NON DATA")
                            }
                        }
                        .addOnFailureListener {
                            Log.i(TAG, "-4-Fetching Failure")
                        }
                } catch (e: Exception) {
                    Log.i(TAG, "-4-ERROR Fetching DATA")
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
                }
            }
        }
            }
        }
    }
    private fun hoek5(pos1: Int, pos2: Int, pos3: Int, pos4: Int) = CoroutineScope(IO).launch {
        val tilsQuer1 = tilsCollectionRef
            .whereEqualTo("number", pos1)
            .get()
            .await()
        if (tilsQuer1.documents.isNotEmpty()) {
            for (tils1 in tilsQuer1) {
                val dataBlok1 = firebaseDb.collection("tils").document(tils1.id)
                val blok_Code1 = findViewById<TextView>(R.id.tvWCode5)
                val blok_Color1 = findViewById<TextView>(R.id.tvWColor5)
                val blok_ImageU1 = findViewById<TextView>(R.id.tvWImageU5)
                try {
                    dataBlok1.get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
                                blok_Code1.text = document.getString("code")
                                blok_Color1.text = document.getString("color")
                                blok_ImageU1.text = document.getString("image_url")
                            } else {
                                Log.i(TAG, "-1-NON DATA")
                            }
                        }
                        .addOnFailureListener {
                            Log.i(TAG, "-1-Fetching Failure")
                        }
                } catch (e: Exception) {
                    Log.i(TAG, "-1-ERROR Fetching DATA")
                }
                val tilsQuer2 = tilsCollectionRef
                    .whereEqualTo("number", pos2)
                    .get()
                    .await()
        if (tilsQuer2.documents.isNotEmpty()) {
            for (tils2 in tilsQuer2) {
                val dataBlok2 = firebaseDb.collection("tils").document(tils2.id)
                val blok_Code2 = findViewById<TextView>(R.id.tvYCode5)
                val blok_Color2 = findViewById<TextView>(R.id.tvYColor5)
                val blok_ImageU2 = findViewById<TextView>(R.id.tvYImageU5)
                try {
                    dataBlok2.get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
                                blok_Code2.text = document.getString("code")
                                blok_Color2.text = document.getString("color")
                                blok_ImageU2.text = document.getString("image_url")
                            } else {
                                Log.i(TAG, "-2-NON DATA")
                            }
                        }
                        .addOnFailureListener {
                            Log.i(TAG, "-2-Fetching Failure")
                        }
                } catch (e: Exception) {
                    Log.i(TAG, "-2-ERROR Fetching DATA")
                }
                val tilsQuer3 = tilsCollectionRef
                    .whereEqualTo("number", pos3)
                    .get()
                    .await()
        if (tilsQuer3.documents.isNotEmpty()) {
            for (tils3 in tilsQuer3) {
                val dataBlok3 = firebaseDb.collection("tils").document(tils3.id)
                val blok_Code3 = findViewById<TextView>(R.id.tvXCode5)
                val blok_Color3 = findViewById<TextView>(R.id.tvXColor5)
                val blok_ImageU3 = findViewById<TextView>(R.id.tvXImageU5)
                try {
                    dataBlok3.get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
                                blok_Code3.text = document.getString("code")
                                blok_Color3.text = document.getString("color")
                                blok_ImageU3.text = document.getString("image_url")
                            } else {
                                Log.i(TAG, "-3-NON DATA")
                            }
                        }
                        .addOnFailureListener {
                            Log.i(TAG, "-3-Fetching Failure")
                        }
                } catch (e: Exception) {
                    Log.i(TAG, "-3-ERROR Fetching DATA")
                }
                val tilsQuer4 = tilsCollectionRef
                    .whereEqualTo("number", pos4)
                    .get()
                    .await()
        if (tilsQuer4.documents.isNotEmpty()) {
            for (tils4 in tilsQuer4) {
                val block_data4 = firebaseDb.collection("tils").document(tils4.id)
                val blok_Code4 = findViewById<TextView>(R.id.tvZCode5)
                val blok_Color4 = findViewById<TextView>(R.id.tvZColor5)
                val blok_ImageU4 = findViewById<TextView>(R.id.tvZImageU5)
                try {
                    block_data4.get()
                        .addOnSuccessListener { document ->
                            if (document != null) {
                                blok_Code4.text = document.getString("code")
                                blok_Color4.text = document.getString("color")
                                blok_ImageU4.text = document.getString("image_url")
                            } else {
                                Log.i(TAG, "-4-NON DATA")
                            }
                        }
                        .addOnFailureListener {
                            Log.i(TAG, "-4-Fetching Failure")
                        }
                } catch (e: Exception) {
                    Log.i(TAG, "-4-ERROR Fetching DATA")
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
                }
            }
        }
            }
        }
    }

    private fun blokZero(): Map<String, Any> {
        val dataUrl = ""
        val dataCode = ""
        val dataColor = ""
        val newWrap = mutableMapOf<String, Any>()
        if (dataCode.isNotEmpty()) {
            newWrap["code"] = dataCode
        }
        if (dataColor.isNotEmpty()) {
            newWrap["color"] = dataColor
        }
        if (dataUrl.isNotEmpty()) {
            newWrap["image_url"] = dataUrl
        }
        return newWrap
    }
    private fun blokW(): Map<String, Any> {
        val dataUrl = tvWImageU.text.toString()
        val dataCode = tvWCode.text.toString()
        val dataColor = tvWColor.text.toString()
        val newWrap = mutableMapOf<String, Any>()
        if (dataCode.isNotEmpty()) {
            newWrap["code"] = dataCode
        }
        if (dataColor.isNotEmpty()) {
            newWrap["color"] = dataColor
        }
        if (dataUrl.isNotEmpty()) {
            newWrap["image_url"] = dataUrl
        }
        return newWrap
    }
    private fun blokX(): Map<String, Any> {
        //val dataId = ""
        val dataUrl = tvXImageU.text.toString()
        val dataCode = tvXCode.text.toString()
        //val dataValue = -1
        //val dataNumber = -1

        val dataColor = tvXColor.text.toString()
        //val dataUser = null
        val newWrap = mutableMapOf<String, Any>()
        if (dataCode.isNotEmpty()) {
            newWrap["code"] = dataCode
        }
        if (dataColor.isNotEmpty()) {
            newWrap["color"] = dataColor
        }
        if (dataUrl.isNotEmpty()) {
            newWrap["image_url"] = dataUrl
        }
        return newWrap
    }
    private fun blokY(): Map<String, Any> {
        val dataUrl = tvYImageU.text.toString()
        val dataCode = tvYCode.text.toString()
        val dataColor = tvYColor.text.toString()
        val newWrap = mutableMapOf<String, Any>()
        if (dataCode.isNotEmpty()) {
            newWrap["code"] = dataCode
        }
        if (dataColor.isNotEmpty()) {
            newWrap["color"] = dataColor
        }
        if (dataUrl.isNotEmpty()) {
            newWrap["image_url"] = dataUrl
        }
        return newWrap
    }
    private fun blokZ(): Map<String, Any> {
        val dataUrl = tvZImageU.text.toString()
        val dataCode = tvZCode.text.toString()
        val dataColor = tvZColor.text.toString()
        val newWrap = mutableMapOf<String, Any>()
        if (dataCode.isNotEmpty()) {
            newWrap["code"] = dataCode
        }
        if (dataColor.isNotEmpty()) {
            newWrap["color"] = dataColor
        }
        if (dataUrl.isNotEmpty()) {
            newWrap["image_url"] = dataUrl
        }
        return newWrap
    }
    private fun blokW2(): Map<String, Any> {
        val dataUrl = tvWImageU2.text.toString()
        val dataCode = tvWCode2.text.toString()
        val dataColor = tvWColor2.text.toString()
        val newWrap = mutableMapOf<String, Any>()
        if (dataCode.isNotEmpty()) {
            newWrap["code"] = dataCode
        }
        if (dataColor.isNotEmpty()) {
            newWrap["color"] = dataColor
        }
        if (dataUrl.isNotEmpty()) {
            newWrap["image_url"] = dataUrl
        }
        return newWrap
    }
    private fun blokX2(): Map<String, Any> {
        val dataUrl = tvXImageU2.text.toString()
        val dataCode = tvXCode2.text.toString()
        val dataColor = tvXColor2.text.toString()
        val newWrap = mutableMapOf<String, Any>()
        if (dataCode.isNotEmpty()) {
            newWrap["code"] = dataCode
        }
        if (dataColor.isNotEmpty()) {
            newWrap["color"] = dataColor
        }
        if (dataUrl.isNotEmpty()) {
            newWrap["image_url"] = dataUrl
        }
        return newWrap
    }
    private fun blokY2(): Map<String, Any> {
        val dataUrl = tvYImageU2.text.toString()
        val dataCode = tvYCode2.text.toString()
        val dataColor = tvYColor2.text.toString()
        val newWrap = mutableMapOf<String, Any>()
        if (dataCode.isNotEmpty()) {
            newWrap["code"] = dataCode
        }
        if (dataColor.isNotEmpty()) {
            newWrap["color"] = dataColor
        }
        if (dataUrl.isNotEmpty()) {
            newWrap["image_url"] = dataUrl
        }
        return newWrap
    }
    private fun blokZ2(): Map<String, Any> {
        val dataUrl = tvZImageU2.text.toString()
        val dataCode = tvZCode2.text.toString()
        val dataColor = tvZColor2.text.toString()
        val newWrap = mutableMapOf<String, Any>()
        if (dataCode.isNotEmpty()) {
            newWrap["code"] = dataCode
        }
        if (dataColor.isNotEmpty()) {
            newWrap["color"] = dataColor
        }
        if (dataUrl.isNotEmpty()) {
            newWrap["image_url"] = dataUrl
        }
        return newWrap
    }
    private fun blokW3(): Map<String, Any> {
        val dataUrl = tvWImageU3.text.toString()
        val dataCode = tvWCode3.text.toString()
        val dataColor = tvWColor3.text.toString()
        val newWrap = mutableMapOf<String, Any>()
        if (dataCode.isNotEmpty()) {
            newWrap["code"] = dataCode
        }
        if (dataColor.isNotEmpty()) {
            newWrap["color"] = dataColor
        }
        if (dataUrl.isNotEmpty()) {
            newWrap["image_url"] = dataUrl
        }
        return newWrap
    }
    private fun blokX3(): Map<String, Any> {
        val dataUrl = tvXImageU3.text.toString()
        val dataCode = tvXCode3.text.toString()
        val dataColor = tvXColor3.text.toString()
        val newWrap = mutableMapOf<String, Any>()
        if (dataCode.isNotEmpty()) {
            newWrap["code"] = dataCode
        }
        if (dataColor.isNotEmpty()) {
            newWrap["color"] = dataColor
        }
        if (dataUrl.isNotEmpty()) {
            newWrap["image_url"] = dataUrl
        }
        return newWrap
    }
    private fun blokY3(): Map<String, Any> {
        val dataUrl = tvYImageU3.text.toString()
        val dataCode = tvYCode3.text.toString()
        val dataColor = tvYColor3.text.toString()
        val newWrap = mutableMapOf<String, Any>()
        if (dataCode.isNotEmpty()) {
            newWrap["code"] = dataCode
        }
        if (dataColor.isNotEmpty()) {
            newWrap["color"] = dataColor
        }
        if (dataUrl.isNotEmpty()) {
            newWrap["image_url"] = dataUrl
        }
        return newWrap
    }
    private fun blokZ3(): Map<String, Any> {
        val dataUrl = tvZImageU3.text.toString()
        val dataCode = tvZCode3.text.toString()
        val dataColor = tvZColor3.text.toString()
        val newWrap = mutableMapOf<String, Any>()
        if (dataCode.isNotEmpty()) {
            newWrap["code"] = dataCode
        }
        if (dataColor.isNotEmpty()) {
            newWrap["color"] = dataColor
        }
        if (dataUrl.isNotEmpty()) {
            newWrap["image_url"] = dataUrl
        }
        return newWrap
    }
    private fun blokW4(): Map<String, Any> {
        val dataUrl = tvWImageU4.text.toString()
        val dataCode = tvWCode4.text.toString()
        val dataColor = tvWColor4.text.toString()
        val newWrap = mutableMapOf<String, Any>()
        if (dataCode.isNotEmpty()) {
            newWrap["code"] = dataCode
        }
        if (dataColor.isNotEmpty()) {
            newWrap["color"] = dataColor
        }
        if (dataUrl.isNotEmpty()) {
            newWrap["image_url"] = dataUrl
        }
        return newWrap
    }
    private fun blokX4(): Map<String, Any> {
        val dataUrl = tvXImageU4.text.toString()
        val dataCode = tvXCode4.text.toString()
        val dataColor = tvXColor4.text.toString()
        val newWrap = mutableMapOf<String, Any>()
        if (dataCode.isNotEmpty()) {
            newWrap["code"] = dataCode
        }
        if (dataColor.isNotEmpty()) {
            newWrap["color"] = dataColor
        }
        if (dataUrl.isNotEmpty()) {
            newWrap["image_url"] = dataUrl
        }
        return newWrap
    }
    private fun blokY4(): Map<String, Any> {
        val dataUrl = tvYImageU4.text.toString()
        val dataCode = tvYCode4.text.toString()
        val dataColor = tvYColor4.text.toString()
        val newWrap = mutableMapOf<String, Any>()
        if (dataCode.isNotEmpty()) {
            newWrap["code"] = dataCode
        }
        if (dataColor.isNotEmpty()) {
            newWrap["color"] = dataColor
        }
        if (dataUrl.isNotEmpty()) {
            newWrap["image_url"] = dataUrl
        }
        return newWrap
    }
    private fun blokZ4(): Map<String, Any> {
        val dataUrl = tvZImageU4.text.toString()
        val dataCode = tvZCode4.text.toString()
        val dataColor = tvZColor4.text.toString()
        val newWrap = mutableMapOf<String, Any>()
        if (dataCode.isNotEmpty()) {
            newWrap["code"] = dataCode
        }
        if (dataColor.isNotEmpty()) {
            newWrap["color"] = dataColor
        }
        if (dataUrl.isNotEmpty()) {
            newWrap["image_url"] = dataUrl
        }
        return newWrap
    }
    private fun blokW5(): Map<String, Any> {
        val dataUrl = tvWImageU5.text.toString()
        val dataCode = tvWCode5.text.toString()
        val dataColor = tvWColor5.text.toString()
        val newWrap = mutableMapOf<String, Any>()
        if (dataCode.isNotEmpty()) {
            newWrap["code"] = dataCode
        }
        if (dataColor.isNotEmpty()) {
            newWrap["color"] = dataColor
        }
        if (dataUrl.isNotEmpty()) {
            newWrap["image_url"] = dataUrl
        }
        return newWrap
    }
    private fun blokX5(): Map<String, Any> {
        val dataUrl = tvXImageU5.text.toString()
        val dataCode = tvXCode5.text.toString()
        val dataColor = tvXColor5.text.toString()
        val newWrap = mutableMapOf<String, Any>()
        if (dataCode.isNotEmpty()) {
            newWrap["code"] = dataCode
        }
        if (dataColor.isNotEmpty()) {
            newWrap["color"] = dataColor
        }
        if (dataUrl.isNotEmpty()) {
            newWrap["image_url"] = dataUrl
        }
        return newWrap
    }
    private fun blokY5(): Map<String, Any> {
        val dataUrl = tvYImageU5.text.toString()
        val dataCode = tvYCode5.text.toString()
        val dataColor = tvYColor5.text.toString()
        val newWrap = mutableMapOf<String, Any>()
        if (dataCode.isNotEmpty()) {
            newWrap["code"] = dataCode
        }
        if (dataColor.isNotEmpty()) {
            newWrap["color"] = dataColor
        }
        if (dataUrl.isNotEmpty()) {
            newWrap["image_url"] = dataUrl
        }
        return newWrap
    }
    private fun blokZ5(): Map<String, Any> {
        val dataUrl = tvZImageU5.text.toString()
        val dataCode = tvZCode5.text.toString()
        val dataColor = tvZColor5.text.toString()
        val newWrap = mutableMapOf<String, Any>()
        if (dataCode.isNotEmpty()) {
            newWrap["code"] = dataCode
        }
        if (dataColor.isNotEmpty()) {
            newWrap["color"] = dataColor
        }
        if (dataUrl.isNotEmpty()) {
            newWrap["image_url"] = dataUrl
        }
        return newWrap
    }

    private fun switchData(number: Int, dataWrapMap: Map<String, Any>) = CoroutineScope(IO).launch {
        val tilsQuery = tilsCollectionRef
            .whereEqualTo("number", number)
            //.whereEqualTo("code", tile.code)
            //.whereEqualTo("color", tile.color)
            //.whereEqualTo("imageUrl", tile.imageUrl)
            .get()
            .await()
        if (tilsQuery.documents.isNotEmpty()) {
            for (vile in tilsQuery) {
                try {
                    tilsCollectionRef.document(vile.id).set(
                        dataWrapMap,
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

    //private fun clearTils() {
    //    tvWImageU.text.toString()
    //    tvWCode.text.toString()
    //    tvWColor.text.toString()
    //}


    private fun restartFromBegining() {
        tilsCollectionRef.document("PsZ6hhwDzA8DwOZHRvme")
            .update("code", "A1","color", "amb", "image_url",
                "https://firebasestorage.googleapis.com/v0/b/jufajag-pro.appspot.com/o/images%2Fff6c24_amber.png?alt=media&token=6d2bb536-6832-4e24-a6a0-5ba5932cf2fe")
        tilsCollectionRef.document("GPN7S4GUhXt9tZXlSn1u")
            .update("code", "A2","color", "blue", "image_url",
                "https://firebasestorage.googleapis.com/v0/b/jufajag-pro.appspot.com/o/images%2F29ADFF_blauw.png?alt=media&token=e76e9e99-95f2-438f-a2d4-e9c82c42f652")
        tilsCollectionRef.document("ugf4TQzQRVHL3khVDMvL")
            .update("code", "A3","color", "green", "image_url",
                "https://firebasestorage.googleapis.com/v0/b/jufajag-pro.appspot.com/o/images%2F00e43b_groen.png?alt=media&token=f73f07de-7d7d-488d-b9aa-a83b11089acb")
        tilsCollectionRef.document("i9MeugZItvOl3mJvkGtu")
            .update("code", "B1","color", "amb", "image_url",
                "https://firebasestorage.googleapis.com/v0/b/jufajag-pro.appspot.com/o/images%2Fff6c24_amber.png?alt=media&token=6d2bb536-6832-4e24-a6a0-5ba5932cf2fe")
        tilsCollectionRef.document("n8t1NXmPL5MhsqWz4qVS")
            .update("code", "B3","color", "yelow", "image_url",
            "https://firebasestorage.googleapis.com/v0/b/jufajag-pro.appspot.com/o/images%2FffEC27_geel.png?alt=media&token=7c09ec3c-fb9a-4dda-acba-1a735bd62fa7")
        tilsCollectionRef.document("l6m9TcGSWoFq9RyOyKOJ")
            .update("code", "B2","color", "blue", "image_url",
            "https://firebasestorage.googleapis.com/v0/b/jufajag-pro.appspot.com/o/images%2F29ADFF_blauw.png?alt=media&token=e76e9e99-95f2-438f-a2d4-e9c82c42f652")
        tilsCollectionRef.document("bmq81WMmOEbDjHAEAA5N")
            .update("code", "D1","color", "amb", "image_url",
                "https://firebasestorage.googleapis.com/v0/b/jufajag-pro.appspot.com/o/images%2Fff6c24_amber.png?alt=media&token=6d2bb536-6832-4e24-a6a0-5ba5932cf2fe")
        tilsCollectionRef.document("cH6VI634ysl9Z6zomEkZ")
            .update("code", "D2","color", "grey", "image_url",
            "https://firebasestorage.googleapis.com/v0/b/jufajag-pro.appspot.com/o/images%2FC2C3C7_silver.png?alt=media&token=94dec370-d3ad-4122-9cdf-7ba9d2957504")
        tilsCollectionRef.document("KWVYfyi6am6cbYP6jwyv")
            .update("code", "D3","color", "green", "image_url",
            "https://firebasestorage.googleapis.com/v0/b/jufajag-pro.appspot.com/o/images%2F00e43b_groen.png?alt=media&token=f73f07de-7d7d-488d-b9aa-a83b11089acb")
        tilsCollectionRef.document("d4p6JghPLu2elHvprEQ4")
            .update("code", "C1","color", "amb", "image_url",
                "https://firebasestorage.googleapis.com/v0/b/jufajag-pro.appspot.com/o/images%2Fff6c24_amber.png?alt=media&token=6d2bb536-6832-4e24-a6a0-5ba5932cf2fe")
        tilsCollectionRef.document("xSSlUAvX7DKYQ4lsLaxC")
            .update("code", "C2","color", "yelow", "image_url",
            "https://firebasestorage.googleapis.com/v0/b/jufajag-pro.appspot.com/o/images%2FffEC27_geel.png?alt=media&token=7c09ec3c-fb9a-4dda-acba-1a735bd62fa7")
        tilsCollectionRef.document("bNKpJ5SVqgwIXExmVeWr")
            .update("code", "C3","color", "grey", "image_url",
            "https://firebasestorage.googleapis.com/v0/b/jufajag-pro.appspot.com/o/images%2FC2C3C7_silver.png?alt=media&token=94dec370-d3ad-4122-9cdf-7ba9d2957504")
        tilsCollectionRef.document("MYmrIa0e2oTVxIZdljst")
            .update("code", "E1","color", "red", "image_url",
                "https://firebasestorage.googleapis.com/v0/b/jufajag-pro.appspot.com/o/images%2Fff004D_rood.png?alt=media&token=86ae4d9c-7023-44da-a89d-12f4e8eb7c8f")
        tilsCollectionRef.document("7B33FWEbM0U5D2d1PELJ")
            .update("code", "E2","color", "green", "image_url",
                "https://firebasestorage.googleapis.com/v0/b/jufajag-pro.appspot.com/o/images%2F00e43b_groen.png?alt=media&token=f73f07de-7d7d-488d-b9aa-a83b11089acb")
        tilsCollectionRef.document("693CKJog85yCfG6Chtdx")
            .update("code", "E3","color", "blue", "image_url",
                "https://firebasestorage.googleapis.com/v0/b/jufajag-pro.appspot.com/o/images%2F29ADFF_blauw.png?alt=media&token=e76e9e99-95f2-438f-a2d4-e9c82c42f652")
        tilsCollectionRef.document("YEIuhGuqGxJjRpVaJWvd")
            .update("code", "G1","color", "red", "image_url",
                "https://firebasestorage.googleapis.com/v0/b/jufajag-pro.appspot.com/o/images%2Fff004D_rood.png?alt=media&token=86ae4d9c-7023-44da-a89d-12f4e8eb7c8f")
        tilsCollectionRef.document("UYei8B7NNf3BylzPoYki")
            .update("code", "G2","color", "green", "image_url",
                "https://firebasestorage.googleapis.com/v0/b/jufajag-pro.appspot.com/o/images%2F00e43b_groen.png?alt=media&token=f73f07de-7d7d-488d-b9aa-a83b11089acb")
        tilsCollectionRef.document("D07OqmxYVX5oYxhvxv14")
            .update("code", "G3","color", "grey", "image_url",
                "https://firebasestorage.googleapis.com/v0/b/jufajag-pro.appspot.com/o/images%2FC2C3C7_silver.png?alt=media&token=94dec370-d3ad-4122-9cdf-7ba9d2957504")
        tilsCollectionRef.document("EovELD76CPgpyVx26aAe")
            .update("code", "I1","color", "amb", "image_url",
                    "https://firebasestorage.googleapis.com/v0/b/jufajag-pro.appspot.com/o/images%2Fff6c24_amber.png?alt=media&token=6d2bb536-6832-4e24-a6a0-5ba5932cf2fe")
        tilsCollectionRef.document("8DiS1H4jtLIjTVRRiReT")
            .update("code", "I2","color", "green", "image_url",
                    "https://firebasestorage.googleapis.com/v0/b/jufajag-pro.appspot.com/o/images%2F00e43b_groen.png?alt=media&token=f73f07de-7d7d-488d-b9aa-a83b11089acb")
        tilsCollectionRef.document("8WPoa7xcKdarDxctj863")
            .update("code", "J1","color", "amb", "image_url",
                    "https://firebasestorage.googleapis.com/v0/b/jufajag-pro.appspot.com/o/images%2Fff6c24_amber.png?alt=media&token=6d2bb536-6832-4e24-a6a0-5ba5932cf2fe")
        tilsCollectionRef.document("Auw5iPvSrGsNSFZVEwwv")
            .update("code", "J2","color", "blue", "image_url",
                    "https://firebasestorage.googleapis.com/v0/b/jufajag-pro.appspot.com/o/images%2F29ADFF_blauw.png?alt=media&token=e76e9e99-95f2-438f-a2d4-e9c82c42f652")
        tilsCollectionRef.document("m1Q4Wi4bnXd70uILMsa9")
            .update("code", "K1","color", "amb", "image_url",
                    "https://firebasestorage.googleapis.com/v0/b/jufajag-pro.appspot.com/o/images%2Fff6c24_amber.png?alt=media&token=6d2bb536-6832-4e24-a6a0-5ba5932cf2fe")
        tilsCollectionRef.document("HnznZfliXjQk2Z59an45")
            .update("code", "K2","color", "yelow", "image_url",
                    "https://firebasestorage.googleapis.com/v0/b/jufajag-pro.appspot.com/o/images%2FffEC27_geel.png?alt=media&token=7c09ec3c-fb9a-4dda-acba-1a735bd62fa7")
        tilsCollectionRef.document("9jf9waIh9caFEk81esSM")
            .update("code", "L1","color", "amb", "image_url",
                    "https://firebasestorage.googleapis.com/v0/b/jufajag-pro.appspot.com/o/images%2Fff6c24_amber.png?alt=media&token=6d2bb536-6832-4e24-a6a0-5ba5932cf2fe")
        tilsCollectionRef.document("DYhtvK4cGH80DhjLEjRb")
            .update("code", "L2","color", "grey", "image_url",
                "https://firebasestorage.googleapis.com/v0/b/jufajag-pro.appspot.com/o/images%2FC2C3C7_silver.png?alt=media&token=94dec370-d3ad-4122-9cdf-7ba9d2957504")
        tilsCollectionRef.document("JnLkcUabFb5bX7IeqLlH")
            .update("code", "M1","color", "green", "image_url",
                "https://firebasestorage.googleapis.com/v0/b/jufajag-pro.appspot.com/o/images%2F00e43b_groen.png?alt=media&token=f73f07de-7d7d-488d-b9aa-a83b11089acb")
        tilsCollectionRef.document("UNvw6el7RZXFtJIririg")
            .update("code", "M2","color", "blue", "image_url",
                "https://firebasestorage.googleapis.com/v0/b/jufajag-pro.appspot.com/o/images%2F29ADFF_blauw.png?alt=media&token=e76e9e99-95f2-438f-a2d4-e9c82c42f652")
        tilsCollectionRef.document("7gvb6eyuSnq2WfkFP86U")
            .update("code", "P1","color", "green", "image_url",
                "https://firebasestorage.googleapis.com/v0/b/jufajag-pro.appspot.com/o/images%2F00e43b_groen.png?alt=media&token=f73f07de-7d7d-488d-b9aa-a83b11089acb")
        tilsCollectionRef.document("17KRzCS8hCZk8N82v80k")
            .update("code", "P2","color", "grey", "image_url",
                "https://firebasestorage.googleapis.com/v0/b/jufajag-pro.appspot.com/o/images%2FC2C3C7_silver.png?alt=media&token=94dec370-d3ad-4122-9cdf-7ba9d2957504")
        tilsCollectionRef.document("giLR6L4C66ptpjFwfXfg")
            .update("code", "S1","color", "red", "image_url",
                "https://firebasestorage.googleapis.com/v0/b/jufajag-pro.appspot.com/o/images%2Fff004D_rood.png?alt=media&token=86ae4d9c-7023-44da-a89d-12f4e8eb7c8f")
        tilsCollectionRef.document("7XKU1WSiVoyK9jW25ghf")
            .update("code", "S2","color", "green", "image_url",
                "https://firebasestorage.googleapis.com/v0/b/jufajag-pro.appspot.com/o/images%2F00e43b_groen.png?alt=media&token=f73f07de-7d7d-488d-b9aa-a83b11089acb")

    }

    //>>>>>>> begin Non-used Functions("twists move") <<<<<<<
    //private fun oldP40P42(): Pils {
    //    val code = tvWCode.text.toString()
    //    val color = tvWColor.text.toString()
    //    val imageUrl = tvWImageU.text.toString()
        //val id = ""
        //val number = -1
        //val value = -1
        //val user = null
     //   return Pils(imageUrl, code, color)
    //}
    //private fun saveGAB(dataWrap: Pils) = CoroutineScope(IO).launch {
    //    try {
    //        tilsCollectionRef.add(dataWrap).await()
    //    }catch (e: Exception) {
    //        withContext(Dispatchers.Main) {
    //            Toast.makeText(this@JufajaActivity, e.message,Toast.LENGTH_LONG).show()
    //        }
    //    }
    //}
    //>>>>>>> end Non-used Functions("twists move") <<<<<<<



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
    //}

    //private fun lesto(tile: Pils) = CoroutineScope(IO).launch {
    //    val moveTileA= 40
    //    val tilsQuery = tilsCollectionRef
            //.whereEqualTo("number", 42)
    //        .whereEqualTo("id", "42-15")
    //        .get()
    //        .await()
    //   if (tilsQuery.documents.isNotEmpty()) {
    //        for (tils in tilsQuery) {
    //             try {
    //                 tilsCollectionRef.document(tils.id).update("number", moveTileA)
    //                    .await()
    //            } catch (e: Exception) {
    //                withContext(Dispatchers.Main) {
    //                    Toast.makeText(
    //                        this@JufajaActivity, e.message, Toast.LENGTH_LONG).show()
    //                }
    //            }
    //        }
    //    } else {
    //        withContext(Dispatchers.Main) {
    //            Toast.makeText(
    //                this@JufajaActivity, "No Tile matched the Query",
    //                Toast.LENGTH_LONG).show()
    //        }
    //    }
    //}

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



