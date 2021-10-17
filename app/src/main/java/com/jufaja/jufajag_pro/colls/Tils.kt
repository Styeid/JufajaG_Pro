package com.jufaja.jufajag_pro.colls

import com.google.firebase.firestore.PropertyName

data class Pils(
    var id: String = "",
    @get:PropertyName("image_url") @set:PropertyName("image_url") var imageUrl: String = "",
    var code: String = "",
    var value: Int = 0,
    var number: Int = 0,
    var color: String = "",
    var user: User? = null,
)
