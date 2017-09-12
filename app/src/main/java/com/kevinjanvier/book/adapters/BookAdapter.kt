package com.kevinjanvier.book.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.kevinjanvier.book.R
import com.kevinjanvier.book.model.Book

/**
 * Created by kevinjanvier on 12/09/2017.
 */
class BookAdapter(var arraylist : ArrayList<Book>) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {



    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val books: Book = arraylist[position]
        holder?.name?.text = books.name
        holder?.price?.text = books.price
        holder?.inStock?.text = books.inStock.toString()
        holder?.bookId?.text = books.bookId.toString()

        holder?.linear?.setOnClickListener {
            log("CLICK " + position)
        }

        log("Name $books.name" )
    }

    override fun getItemCount(): Int {

        return arraylist.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view :View = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return ViewHolder(view)
    }


    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        var name:TextView = itemview.findViewById(R.id.name)
        var price:TextView = itemview.findViewById(R.id.price)
        var inStock:TextView = itemview.findViewById(R.id.inStock)
        var bookId:TextView = itemview.findViewById(R.id.bookId)
        var linear:LinearLayout = itemview.findViewById(R.id.linear)

        init {


        }
    }

    fun log(msg:String){
        Log.e(this@BookAdapter.javaClass.simpleName, msg)
    }
}