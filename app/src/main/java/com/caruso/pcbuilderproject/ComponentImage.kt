package com.caruso.pcbuilderproject

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.caruso.pcbuilderproject.componentsclasses.Component
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.CPU
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.GPU
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.MOTHERBOARD
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.PSU
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.RAM
import com.caruso.pcbuilderproject.componentsclasses.ComponentType.Companion.STORAGE
import com.caruso.pcbuilderproject.utilities.GlobalData

@Composable
fun ComponentImage(
    modifier: Modifier,
    component: Component?,
    componentType: Int
) {
    if (component != null) {
        if (component.imagePainterLink != null) {
            val imagePainterLinkFull = buildString {
                append(GlobalData.ngrokServerLinkPrefix)
                append(GlobalData.ngrokServerLink)
                append(GlobalData.ngrokServerLinkSuffix)
                append(component.imagePainterLink)
            }

            val asyncPainter = rememberAsyncImagePainter(imagePainterLinkFull)

            if (asyncPainter.state !is AsyncImagePainter.State.Error) {
                Image(
                    painter = asyncPainter,
                    contentDescription = null,
                    modifier = modifier
                )
            } else {
                Log.e(
                    "Component Image",
                    "Failed to load the image at url: \"${component.imagePainterLink}\" for component: \"$component\""
                )
                Log.e("Component Image", "The placeholder will be used instead.")
                Image(
                    painter = painterResource(component.defaultImagePainterId),
                    contentDescription = null,
                    modifier = modifier
                )
            }
        } else {
            Image(
                painter = painterResource(component.defaultImagePainterId),
                contentDescription = null,
                modifier = modifier
            )
        }
    } else {
        Image(
            painter = when (componentType) {
                CPU -> painterResource(id = R.drawable.cpu_placeholder)
                MOTHERBOARD -> painterResource(id = R.drawable.motherboard_placeholder)
                RAM -> painterResource(id = R.drawable.ram_placeholder)
                GPU -> painterResource(id = R.drawable.gpu_placeholder)
                STORAGE -> painterResource(id = R.drawable.storage_placeholder)
                PSU -> painterResource(id = R.drawable.psu_placeholder)
                else -> painterResource(id = R.drawable.ic_launcher_foreground)
            },
            contentDescription = null,
            modifier = modifier
        )
    }
}