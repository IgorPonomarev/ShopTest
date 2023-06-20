package com.dviss.shoptest.ui.screen.menu.collapsing.abstractions

import androidx.compose.runtime.Stable

@Stable
interface BannerSectionState {
    val offset: Float
    val height: Float
    val progress: Float
    val consumed: Float
    var scrollTopLimitReached: Boolean
    var scrollOffset: Float
}