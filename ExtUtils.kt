package com.omx1000.util

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar


fun Context.toast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_LONG ).show()
}

fun View.show(){
    visibility = View.VISIBLE
}

fun View.hide(){
    visibility = View.GONE
}
fun View.invisible(){
    visibility = View.INVISIBLE
}

fun View.snackBar(message: String)
{
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}

fun View.snackBarAction(message: String){
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setAction("Ok") {
            snackbar.dismiss()
        }
    }.show()
}
