package com.finwin.finwinproject.utils

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager


fun Activity.makeStatusBarTransparent() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
            statusBarColor = Color.TRANSPARENT
        }
    }
}
fun getColor(
    color: String?
): Int {
    val splitStr = color?.substring(
        color.indexOf('(') + 1,
        color.indexOf(')')
    )
    val splitString = splitStr?.split(",".toRegex())?.toTypedArray()
    val colorValues = IntArray(splitString!!.size)
    for (i in splitString.indices) {
        colorValues[i] = splitString[i].trim().toInt()
    }
    return Color.rgb(colorValues[0], colorValues[1], colorValues[2])
}