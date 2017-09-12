package com.kevinjanvier.book.model

import com.google.gson.GsonBuilder

/**
 * Created by kevinjanvier on 12/09/2017.
 */
class Book (
        var bookId: Int,
        var name:String,
        var price:String,
        var inStock:Int



) {
    override fun toString(): String {
        return toStringGson.toJson(this)
    }

    companion object {
        internal var toStringGson = GsonBuilder().setPrettyPrinting().create()


    }
}



