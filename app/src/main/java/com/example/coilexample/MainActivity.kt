package com.example.coilexample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.coilexample.ui.theme.CoilExampleTheme
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isLoading = remember { mutableStateOf(true) }
            CoilExampleTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Image(
                            rememberImagePainter(
                                data = IMAGE_URL,
                                builder = {
                                    listener(
                                        onSuccess = { _, _ ->
                                            isLoading.value = false
                                            Log.d(TAG, "onSuccess called")
                                        },
                                        onError = { _, _ ->
                                            isLoading.value = false
                                            Log.d(TAG, "onError called")
                                        },
                                        onStart = {
                                            Log.d(TAG, "onStart called")
                                        },
                                        onCancel = {
                                            Log.d(TAG, "onCancel called")
                                        }
                                    )
                                }
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .placeholder(
                                    visible = isLoading.value,
                                    color = MaterialTheme.colors.primary,
                                    highlight = PlaceholderHighlight.fade(
                                        MaterialTheme.colors.onPrimary,
                                        InfiniteRepeatableSpec(
                                            tween(PLACEHOLDER_ANIMATION_DURATION)
                                        )
                                    ),
                                )
                                // Uncomment the following line for making it work
                                //.clickable(enabled = false) {}
                        )
                    }
                }
            }
        }
    }

    companion object {
        const val TAG = "COIL_CALLBACK"
        const val IMAGE_URL = "https://storage.googleapis.com/gd-wagtail-prod-assets/original_images/evolving_google_identity_2x1.jpg"
        const val PLACEHOLDER_ANIMATION_DURATION = 1000
    }
}

