package com.dviss.shoptest.ui.screen.menu.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dviss.shoptest.R
import com.dviss.shoptest.domain.model.Product

@Composable
fun ProductListItem(
    product: Product,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        AsyncImage(
            model = product.imageUrl,
            contentDescription = null,
            modifier = Modifier.size(135.dp)
        )
        Spacer(modifier = Modifier.width(22.dp))
        Column {
            Text(
                text = product.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = product.description,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.align(Alignment.End),
                shape = RoundedCornerShape(6.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
            ) {
                Text(
                    text = stringResource(R.string.price_from, product.price.toString()),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}