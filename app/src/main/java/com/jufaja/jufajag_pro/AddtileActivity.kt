package com.jufaja.jufajag_pro

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.jufaja.jufajag_pro.colls.Pils
import com.jufaja.jufajag_pro.colls.User
import kotlinx.android.synthetic.main.activity_addtile.*
import kotlinx.android.synthetic.main.item_tils.*

private const val TAG = "AddtileActivity"
private const val ADD_IMAGE_DATA = 1234
class AddtileActivity : AppCompatActivity() {
        private var signInNick: User? = null
        private var tileUri: Uri? = null
        private lateinit var firebaseDb: FirebaseFirestore
        private lateinit var storageReference: StorageReference

    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addtile)
        //supportActionBar?.title = "Add"
        storageReference = FirebaseStorage.getInstance().reference
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


        btnAddTile.setOnClickListener {
            Log.i(TAG, "Open up image picker on device")
            val tileAddIntent = Intent(Intent.ACTION_GET_CONTENT)
            tileAddIntent.type = "image/*"
            if (tileAddIntent.resolveActivity(packageManager) != null) {
                startActivityForResult(tileAddIntent, ADD_IMAGE_DATA)
            }
        }

        btnSubmit.setOnClickListener {
            handleSubmitButtonClick()

        }

    }



    private fun handleSubmitButtonClick() {
        if (tileUri == null) {
            Log.i(TAG, "No tile/image selected")
            Toast.makeText(this, "No tile/image selected", Toast.LENGTH_LONG).show()
            return
        }
        if (etNum.text.isBlank() || etId.text.isBlank() || etValue.text.isBlank()) {
            Log.i(TAG, "Fields cannot be empty")
            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_LONG).show()
            return
        }
        if (signInNick == null) {
            Log.i(TAG, "No signed in user, please wait")
            Toast.makeText(this, "No signed in user, please wait", Toast.LENGTH_LONG).show()
            return
        }
        //>>>>>>> Building tile(+data/info), store in firebase and return tile-build to (p.)Base


        btnSubmit.isEnabled = false
        val tileUploadUri = tileUri as Uri
        val photoReference = storageReference.child("erseve/${System.currentTimeMillis()}-photo.jpg")

        // Upload photo to Firebase storage

        photoReference.putFile(tileUploadUri)
            .continueWithTask { photoUploadTask ->
                Log.i(TAG, "uploaded bytes: ${photoUploadTask.result?.bytesTransferred}")


                // Retrieve image Url of the uploaded image

                photoReference.downloadUrl
            }.continueWithTask { downloadUrlTask ->


                // Create a post object with the image Url and add that to the post collection

                val post = Pils(
                    etId.text.toString(),
                    downloadUrlTask.result.toString(),
                    etCode.text.toString(),
                    etValue.text.toString().toInt(),
                    etNum.text.toString().toInt(),
                    etColor.text.toString(),
                    signInNick)
                firebaseDb.collection("tils").add(post)
            }.addOnCompleteListener { postCreatoinTask ->
                btnSubmit.isEnabled = true
                if (!postCreatoinTask.isSuccessful) {
                    Log.e(
                        TAG,
                        "Exception during Firebase operations",
                        postCreatoinTask.exception
                    )
                    Toast.makeText(this, "Failure adding content", Toast.LENGTH_SHORT).show()
                }
                etId.text.clear()
                ivImageUrli.setImageResource(0)
                Toast.makeText(this, "Content added", Toast.LENGTH_LONG).show()
                val profileIntent = Intent(this, GameActivity::class.java)
                profileIntent.putExtra(EXTRA_NICKNAME, signInNick?.nickname)
                startActivity(profileIntent)
                finish()
                //>>>>>>>


        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_IMAGE_DATA) {
            if (resultCode == Activity.RESULT_OK) {
                tileUri = data?.data
                Log.i(TAG, "tileUri $tileUri")
                ivImageUrli.setImageURI(tileUri)
            }else{
                Toast.makeText(this, "Add tile/image canceled", Toast.LENGTH_LONG).show()
            }
        }
    }
}
