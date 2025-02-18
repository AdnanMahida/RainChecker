package com.ad.rainchecker.utility.extensions

import android.content.Context
import android.widget.Toast


fun String.toToast(context: Context, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, length).show()
}