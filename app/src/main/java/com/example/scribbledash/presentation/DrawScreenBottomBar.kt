package com.example.scribbledash.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.scribbledash.R
import com.example.scribbledash.ui.theme.ScribbleDashTheme
import com.example.scribbledash.ui.theme.success

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
        RoundedCornerIconButton(
            onClick = onUndoClick,
            enabled = isUndoEnabled,
        ) {
            Icon(
                painter = painterResource(R.drawable.icon_reply),
                contentDescription = stringResource(R.string.undo),
            )
        }

        RoundedCornerIconButton(
            onClick = onRedoClick,
            enabled = isRedoEnabled,
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
                .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                .height(64.dp),
            enabled = isUndoEnabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.success,
                disabledContainerColor = MaterialTheme.colorScheme.surfaceContainerLowest
            )
        ) {
            Text(
                text = stringResource(R.string.clear_canvas),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White,
                maxLines = 1
            )
        }
    }
}

@Composable
fun RoundedCornerIconButton(
    onClick: () -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(22.dp),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
            contentColor = MaterialTheme.colorScheme.onBackground,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceContainerLow
        ),
        contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
        modifier = modifier
            .height(64.dp)
            .background(MaterialTheme.colorScheme.surfaceContainerHigh)
    ) {
        content()
    }
}

@Composable
@Preview(showBackground = true)
fun DrawScreenBottomBarPreview() {
    ScribbleDashTheme {
        DrawScreenBottomBar(
            onUndoClick = {},
            onRedoClick = {},
            onClearCanvasClick = {},
            isUndoEnabled = false,
            isRedoEnabled = true
        )
    }
}