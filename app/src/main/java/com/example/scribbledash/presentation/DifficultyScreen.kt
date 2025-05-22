package com.example.scribbledash.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.scribbledash.R
import com.example.scribbledash.ui.theme.ScribbleDashTheme

@Composable
fun DifficultyScreen(
    onCloseClick: () -> Unit,
    onBeginnerClick: () -> Unit,
    onChallengingClick: () -> Unit,
    onMasterClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            IconButton(
                onClick = onCloseClick,
                modifier = Modifier
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_cancel),
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = stringResource(R.string.close),
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.start_drawing),
                    style = MaterialTheme.typography.displayMedium,

                    )
                Text(
                    text = stringResource(R.string.select_difficulty),
                    style = MaterialTheme.typography.bodyMedium,

                    )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                ) {
                    ImageWithLabel(
                        painter = painterResource(R.drawable.pen),
                        label = stringResource(R.string.beginner),
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .clickable(
                                onClick = onBeginnerClick
                            )
                    )
                    ImageWithLabel(
                        painter = painterResource(R.drawable.colors),
                        label = stringResource(R.string.challenging),
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .clickable(
                                onClick = onChallengingClick
                            )
                    )
                    ImageWithLabel(
                        painter = painterResource(R.drawable.pallete),
                        label = stringResource(R.string.master),
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .clickable(
                                onClick = onMasterClick
                            )
                    )
                }
            }
        }
    }
}

@Composable
fun ImageWithLabel(
    painter: Painter,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painter,
            contentScale = ContentScale.None,
            contentDescription = label,
            modifier = Modifier.size(100.dp)
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
        )
    }
}


@Composable
@Preview(showBackground = true)
fun ImageWithLabelPreview() {
    ScribbleDashTheme {
        ImageWithLabel(
            painter = painterResource(R.drawable.pen),
            label = "Beginner"
        )
    }
}

@Composable
@Preview(showBackground = true)
fun DifficultyScreenPreview() {
    ScribbleDashTheme {
        DifficultyScreen(
            {},
            {},
            {},
            {})
    }
}