package com.example.amphibiansproject.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibiansproject.R
import com.example.amphibiansproject.model.Amphibian
import com.example.amphibiansproject.ui.theme.AmphibiansProjectTheme

@Composable
fun HomeScreen(
     amphibiansUiState: AmphibiansUiState,
     retryAction:() -> Unit,
     modifier: Modifier = Modifier,
     contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    when (amphibiansUiState) {
        is AmphibiansUiState.Loading -> LoadingScreen()
        is AmphibiansUiState.Success -> AmphibiansGridScreen(amphibiansUiState.amphibians)

        is AmphibiansUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
    }
}

    @Composable
    fun LoadingScreen(modifier: Modifier = Modifier) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(64.dp), // Adjust the size as needed
                color = Color.Gray, // Customize the color as needed
                strokeWidth = 4.dp // Adjust the strokeWidth as needed
            )
        }
    }

    @Composable
    fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Image(
                painter = painterResource(R.drawable.ic_connection_error),
                contentDescription = stringResource(R.string.loading_failed)

            )
            Text(
                stringResource(R.string.loading_failed)

            )
            Button(onClick = retryAction) {
                Text(stringResource(R.string.retry))

            }

        }

    }

    @Composable
    fun AmphibiansCard(amphibian: Amphibian,modifier: Modifier = Modifier) {
        Card(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .height(450.dp),
            shape= RoundedCornerShape(4.dp),


        ) {
            Column {
                Row (
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp)
                ){
                    Text(
                        text = stringResource(R.string.amphibian_title,amphibian.name,amphibian.type),
                        style = MaterialTheme.typography.titleMedium ,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,



                    )

                }

                AsyncImage(
                    modifier=Modifier.fillMaxWidth(),
                    model= ImageRequest.Builder(context = LocalContext.current)
                        .data(amphibian.imgSrc)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    error = painterResource(id = R.drawable.ic_broken_image),
                    placeholder = painterResource(id = R.drawable.loading_img)

                )

                Text(
                    text = amphibian.description,
                    textAlign = TextAlign.Justify,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))


                )

            }

        }

    }

@Composable
fun AmphibiansGridScreen(
    amphibians: List<Amphibian>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 16.dp),
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(items = amphibians, key = { amphibian -> amphibian.name }) { amphibian ->
            AmphibiansCard(
                amphibian,
                modifier = modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AmphibiansGridScreenPreview() {
    AmphibiansProjectTheme {
        val mockData =List(10) {Amphibian("$it","","","")}
        AmphibiansGridScreen(mockData,Modifier.fillMaxSize())
    }
}


@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    AmphibiansProjectTheme {
        ErrorScreen({})
    }
}









