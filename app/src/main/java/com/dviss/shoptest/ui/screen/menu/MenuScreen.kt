package com.dviss.shoptest.ui.screen.menu

import androidx.compose.animation.core.FloatExponentialDecaySpec
import androidx.compose.animation.core.animateDecay
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dviss.shoptest.ui.AppViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Velocity
import com.dviss.shoptest.R
import com.dviss.shoptest.ui.screen.menu.collapsing.abstractions.BannerSectionState
import com.dviss.shoptest.ui.screen.menu.collapsing.abstractions.FixedScrollFlagState
import com.dviss.shoptest.ui.screen.menu.collapsing.impl.ExitUntilCollapsedState
import com.dviss.shoptest.ui.screen.menu.components.CollapsableView
import com.dviss.shoptest.ui.screen.menu.components.ProductListItem
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

private val MinBannerSectionHeight = 64.dp
private val MaxBannerSectionHeight = 200.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel = hiltViewModel(),
    onQrCodeIconClick: () -> Unit = {}
) {

    val state by viewModel.appState.collectAsState()

    //filtered list
    val filteredProducts by remember(state) {
        derivedStateOf {
            state.products.filter { it.category == state.selectedCategory || state.selectedCategory == "" }
        }
    }

    val bannerSectionHeightRange = with(LocalDensity.current) {
        MinBannerSectionHeight.roundToPx()..MaxBannerSectionHeight.roundToPx()
    }

    val bannerSectionState =
        rememberBannerSectionState(bannerSectionHeightRange = bannerSectionHeightRange)

    val listState = rememberLazyListState()

    val scope = rememberCoroutineScope()

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                bannerSectionState.scrollTopLimitReached =
                    listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset == 0
                bannerSectionState.scrollOffset = bannerSectionState.scrollOffset - available.y
                return Offset(0f, bannerSectionState.consumed)
            }

            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                bannerSectionState.scrollTopLimitReached =
                    listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset == 0
                bannerSectionState.scrollOffset = bannerSectionState.scrollOffset - available.y
                return Offset(0f, bannerSectionState.consumed)
            }

            override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
                if (available.y > 0) {
                    scope.launch {
                        animateDecay(
                            initialValue = bannerSectionState.height + bannerSectionState.offset,
                            initialVelocity = available.y,
                            animationSpec = FloatExponentialDecaySpec()
                        ) { value, velocity ->
                            bannerSectionState.scrollTopLimitReached =
                                listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset == 0
                            bannerSectionState.scrollOffset =
                                bannerSectionState.scrollOffset - (value - (bannerSectionState.height + bannerSectionState.offset))
                            if (bannerSectionState.scrollOffset == 0f) scope.coroutineContext.cancelChildren()
                        }
                    }
                }

                return super.onPostFling(consumed, available)
            }
        }
    }

    Column(modifier = modifier.fillMaxSize()) {
        //======================= TopAppBar =======================
        TopAppBar(
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = state.location,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null
                        )
                    }
                }
            },
            actions = {
                IconButton(onClick = onQrCodeIconClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_qr_code),
                        contentDescription = null
                    )
                }
            }
        )


        Box(
            modifier = modifier
                .fillMaxSize()
                .nestedScroll(nestedScrollConnection)
        ) {

            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .graphicsLayer {
                        translationY = bannerSectionState.height + bannerSectionState.offset
                    }
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onPress = { scope.coroutineContext.cancelChildren() }
                        )
                    },
                state = listState,
                contentPadding = PaddingValues(
                    bottom = if (bannerSectionState is FixedScrollFlagState) MinBannerSectionHeight else 0.dp
                )
            ) {
                items(filteredProducts) { product ->
                    ProductListItem(product = product)
                    Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f))
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            CollapsableView(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(with(LocalDensity.current) { bannerSectionState.height.toDp() }),
                progress = bannerSectionState.progress,
                categories = state.categories,
                selectedCategory = state.selectedCategory,
                onCategoryClick = { viewModel.onCategoryClick(it) }
            )
        }
    }

}

@Composable
private fun rememberBannerSectionState(
    bannerSectionHeightRange: IntRange
): BannerSectionState {
    return rememberSaveable(saver = ExitUntilCollapsedState.Saver) {
        ExitUntilCollapsedState(bannerSectionHeightRange)
    }
}