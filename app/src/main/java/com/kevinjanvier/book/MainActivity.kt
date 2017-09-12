package com.kevinjanvier.book

import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import com.kevinjanvier.book.adapters.BookAdapter
import com.kevinjanvier.book.model.Book
import com.kevinjanvier.book.service.BookService

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var progress: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        recycler_view.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        recycler_view.setHasFixedSize(true)

        if (isNetworkConnected()){
            progress = ProgressDialog(this)
            progress?.setMessage("Please wait...")
            progress?.setCancelable(false)
            progress?.show()


            getData()

        }else{
            AlertDialog.Builder(this).setTitle("No Internet Connection")
                    .setMessage("Please check your internet connection and try again")
                    .setPositiveButton("OK"){ dialog, which->}
                    .setIcon(android.R.drawable.ic_dialog_alert).show()
                    }

    }

    fun getData(){
        val retrofit = Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(BookService::class.java)
        val call = service.getBooks()
        call.enqueue(object :Callback<ArrayList<Book>>{
            override fun onResponse(call: Call<ArrayList<Book>>?, response: Response<ArrayList<Book>>?) {

                log("Response " + response?.body())
                downloadComplete(response?.body())

            }

            override fun onFailure(call: Call<ArrayList<Book>>?, t: Throwable?) {
                log("On Failure " + t?.message)
            }
        })

    }

    private fun downloadComplete(body: ArrayList<Book>?) {

        if (true){
            progress.hide()
        }

        log("====== $body")
        val adapter = BookAdapter(body!!)
        recycler_view.adapter = adapter
    }


    companion object{
        val ROOT_URL= "https://api.myjson.com/"
    }

    fun log(msg:String){
        Log.e(this@MainActivity.javaClass.simpleName, msg)
    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}
