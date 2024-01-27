package hu.varsanyi.punkapiapp.features.beermatcher.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun BeerMatcherCard(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    name: String,
    tagline: String,
    dismissClicked: () -> Unit,
    likeClicked: () -> Unit
) {
    Card(modifier = modifier.fillMaxSize()) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp),
            model = imageUrl,
            contentDescription = "Beer image"
        )

        Text(text = name)
        Text(text = tagline)

        Row {
            Button(onClick = dismissClicked) {
                Text(text = "Dismiss")
            }

            Button(onClick = likeClicked) {
                Text(text = "Like")
            }
        }
    }
}

@Preview
@Composable
private fun BeerMatcherCardPreview() {
    BeerMatcherCard(
        imageUrl = "",
        name = "Beer name",
        tagline = "Beer tagline",
        dismissClicked = {},
        likeClicked = {}
    )
}