package com.reapairsduniya.unorgassingment.util

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Activity.showToast(value : String){
    Toast.makeText(applicationContext, value, Toast.LENGTH_SHORT).show()
}