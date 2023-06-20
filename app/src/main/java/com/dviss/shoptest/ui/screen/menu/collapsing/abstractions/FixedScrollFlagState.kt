package com.dviss.shoptest.ui.screen.menu.collapsing.abstractions

abstract class FixedScrollFlagState(heightRange: IntRange) : ScrollFlagState(heightRange) {

    final override val offset: Float = 0f

}