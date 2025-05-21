package com.example.scribbledash.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.scribbledash.R
import com.example.scribbledash.ui.theme.LightTaupe
import com.example.scribbledash.ui.theme.MediumAquamarine
import com.example.scribbledash.ui.theme.MutedTaupe
import com.example.scribbledash.ui.theme.RusticRed

@Composable
fun DrawScreenBottomBar(
    onUndoClick: () -> Unit,
    onRedoClick: () -> Unit,
    onClearCanvasClick: () -> Unit,
    isUndoEnabled: Boolean,
    isRedoEnabled: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        IconButton(
            onClick = onUndoClick,
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = RusticRed,
                disabledContentColor = MutedTaupe
            ),
            enabled = isUndoEnabled,
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.icon_reply),
                contentDescription = stringResource(R.string.undo),
            )
        }
        IconButton(
            onClick = onRedoClick,
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = RusticRed,
                disabledContentColor = MutedTaupe
            ),
            enabled = isRedoEnabled,
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.icon_forward),
                contentDescription = stringResource(R.string.redo),
            )
        }
        Button(
            onClick = onClearCanvasClick,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .height(70.dp)
                .padding(8.dp),
            enabled = isUndoEnabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = MediumAquamarine,
                disabledContainerColor = LightTaupe
            )
        ) {
            Text(
                text = stringResource(R.string.clear_canvas),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.displaySmall,
                color = Color.White,
                maxLines = 1
            )
        }
    }
}