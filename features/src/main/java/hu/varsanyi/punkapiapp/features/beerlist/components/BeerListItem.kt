package hu.varsanyi.punkapiapp.features.beerlist.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import hu.varsanyi.punkapiapp.features.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeerListItem(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    name: String,
    onClick: () -> Unit
) {
    Card(modifier = modifier, onClick = onClick) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                modifier = Modifier
                    .size(75.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(2.dp),
                model = imageUrl,
                contentDescription = "Beer image",
                placeholder = painterResource(id = R.drawable.beer)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(text = name)
        }
    }
}

@Preview
@Composable
private fun BeerListItemPreview() {
    BeerListItem(imageUrl = "", name = "Beer name") {

    }
}