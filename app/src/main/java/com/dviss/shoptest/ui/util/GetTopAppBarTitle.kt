package com.dviss.shoptest.ui.util

import android.content.Context
import com.dviss.shoptest.R

fun getTopAppBarTitleByRoute(context: Context, route: String): String {
    return when (route) {
        "menu" -> context.getString(R.string.title_menu)
        "profile" -> context.getString(R.string.title_profile)
        "cart" -> context.getString(R.string.title_cart)
        else -> context.getString(R.string.app_name)
    }
}