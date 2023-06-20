package com.dviss.shoptest.ui.util

import com.dviss.shoptest.R

fun getNavigationIconByRoute(route: String): Int {
    return when (route) {
        "menu" -> R.drawable.ic_menu
        "profile" -> R.drawable.ic_profile
        "cart" -> R.drawable.ic_cart
        else -> R.drawable.ic_menu
    }
}