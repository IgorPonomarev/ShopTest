package com.dviss.shoptest.ui.screen.menu.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dviss.shoptest.R

@Composable
fun CollapsableView(
    modifier: Modifier = Modifier,
    progress: Float,
    categories: List<String>,
    selectedCategory: String,
    onCategoryClick: (String) -> Unit = { }
) {
    val maxHeight = (136 * progress).dp
    Column(modifier = modifier) {
        Box(modifier = Modifier.height(maxHeight)) {
            LazyRow(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                item {
                    Spacer(modifier = Modifier.width(16.dp))
                    BannerItem(R.drawable.banner1)
                }
                item {
                    Spacer(modifier = Modifier.width(16.dp))
                    BannerItem(R.drawable.banner2)
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        }
        LazyRow(
            modifier = Modifier.height(64.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            item {
                Spacer(modifier = Modifier.width(16.dp))
            }
            items(categories) {
                CategoryItem(
                    title = it,
                    onCategoryClick = onCategoryClick,
                    selected = it == selectedCategory
                )
                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}

@Composable
fun BannerItem(
    imgRes: Int
) {
    Image(
        painter = painterResource(id = imgRes),
        contentDescription = null,
        modifier = Modifier
            .width(300.dp)
            .height(112.dp)
            .clip(RoundedCornerShape(10.dp)),
        contentScale = ContentScale.FillWidth
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryItem(
    title: String,
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    onCategoryClick: (String) -> Unit = { }
) {
    FilterChip(
        selected = selected,
        onClick = {onCategoryClick(title)},
        label = { Text(text = title) },
        modifier = modifier
    )
}