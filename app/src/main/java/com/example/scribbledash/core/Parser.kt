package com.example.scribbledash.core

import android.content.Context

import android.graphics.RectF
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.asAndroidPath

import androidx.compose.ui.graphics.vector.PathParser
import org.xmlpull.v1.XmlPullParser

fun extractPathDataFromDrawableXml(context: Context, drawableResId: Int): List<Path> {
    val pathList = mutableListOf<Path>()
    val parser = context.resources.getXml(drawableResId)
    val androidNameSpace = "http://schemas.android.com/apk/res/android"

    var eventType = parser.eventType
    while (eventType != XmlPullParser.END_DOCUMENT) {
        if (eventType == XmlPullParser.START_TAG && parser.name == "path") {
            val pathData = parser.getAttributeValue(androidNameSpace, "pathData")
            if (!pathData.isNullOrEmpty()) {
                pathList.add(PathParser().parsePathString(pathData).toPath())
            }
        }
        eventType = parser.next()
    }
    return pathList
}

@Suppress("DEPRECATION")
fun getComposePathBounds(composePath: Path): RectF {
    val bounds = RectF()
    val androidPath = composePath.asAndroidPath()
    androidPath.computeBounds(bounds, true)
    return bounds
}

fun calculateTotalBounds(paths: List<Path>): RectF {
    val totalBounds = RectF()
    for ((index, path) in paths.withIndex()) {
        val bounds = getComposePathBounds(path)
        if (index == 0) {
            totalBounds.set(bounds)
        } else {
            totalBounds.union(bounds)
        }
    }
    return totalBounds
}


/*val pathList = extractPathDataFromDrawableXml(
                        context = context,
                        drawableResId = R.drawable.whale
                    )
                    Canvas(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        val matrix = Matrix().apply {
                            scale(5f, 5f)

                        }

                        pathList.map { path -> path.transform(matrix) }
                        val rectF = calculateTotalBounds(pathList)

                        val rect = Rect(rectF.left, rectF.top, rectF.right, rectF.bottom)
                        drawRect(
                            topLeft = rect.topLeft,
                            size = rect.size,
                            color = Color.Red
                        )
                        pathList.fastForEach { path ->
                            drawPath(
                                path = path,
                                color = Color.Black,
                                style = Stroke(
                                    width = 10f,
                                    cap = StrokeCap.Round,
                                    join = StrokeJoin.Round
                                )
                            )
                        }
                    }*/