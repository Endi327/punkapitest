package hu.varsanyi.punkapiapp.features.beermatcher.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import hu.varsanyi.punkapiapp.features.R

@Composable
fun BeerMatcherCard(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    name: String,
    tagline: String,
    dismissClicked: () -> Unit,
    likeClicked: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp),
                model = imageUrl,
                fallback = painterResource(id = R.drawable.beer),
                contentDescription = "Beer image"
            )

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                modifier = Modifier.padding(horizontal = 18.dp),
                text = name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                modifier = Modifier.padding(horizontal = 12.dp),
                text = tagline,
                fontStyle = FontStyle.Italic
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp), horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = dismissClicked, colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
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