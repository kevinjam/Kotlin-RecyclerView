package com.kevinjanvier.book.service

import com.kevinjanvier.book.model.Book
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by kevinjanvier on 12/09/2017.
 */
interface BookService {
    @GET("/bins/pklpp")
    fun getBooks() : Call<ArrayList<Book>>
}