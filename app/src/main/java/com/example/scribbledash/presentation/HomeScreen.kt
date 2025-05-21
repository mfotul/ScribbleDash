package com.example.scribbledash.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.scribbledash.R
import com.example.scribbledash.ui.theme.MediumAquamarine
import com.example.scribbledash.ui.theme.ScribbleDashTheme

@Composable
fun HomeScreen(
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp)
            )
        }
        Spacer(modifier = Modifier.height(128.dp))
        Text(
            text = stringResource(R.string.start_drawing),
            style = MaterialTheme.typography.displayLarge,
        )
        Text(
            text = stringResource(R.string.select_game_mode),
            style = MaterialTheme.typography.bodyLarge,
        )

        Card(
            border = BorderStroke(8.dp, MediumAquamarine),
            modifier = Modifier
                .padding(16.dp)
                .clickable(
                    onClick = onCardClick
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.one_round_wonder),
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 22.dp)
                )
                Image(
                    painter = painterResource(R.drawable.one_round_wonder),
                    contentDescription = null,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    ScribbleDashTheme {
        HomeScreen({})
    }
}