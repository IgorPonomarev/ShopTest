package com.dviss.shoptest.ui.screen.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.dviss.shoptest.ui.AppViewModel

@Composable
fun ProfileScreen(
    viewModel: AppViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "This is screen Profile")
    }
}