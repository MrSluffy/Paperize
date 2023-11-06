package com.anthonyla.paperize.feature.wallpaper.presentation.album.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anthonyla.paperize.feature.wallpaper.domain.model.Album
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumItem(
    album: Album,
    onAlbumViewClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val bgColor = MaterialTheme.colorScheme.surfaceColorAtElevation(10.dp)
    Card(
        elevation = CardDefaults.cardElevation(5.dp),
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, bgColor),
        onClick = onAlbumViewClick
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            tonalElevation = 10.dp,
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box {
                    GlideImage(
                        imageModel = { album.coverUri },
                        imageOptions = ImageOptions(
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center
                        ),
                        requestBuilder = {
                            Glide
                                .with(LocalContext.current)
                                .asBitmap()
                                .transition(BitmapTransitionOptions.withCrossFade())
                        },
                        loading = {
                            Box(modifier = Modifier.matchParentSize()) {
                                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                            }
                        },
                        modifier = Modifier
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(16.dp))
                    )
                }

                Spacer(modifier = Modifier.padding(6.dp))
                Text(
                    text = album.displayedAlbumName,
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .align(Alignment.Start),
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.padding(8.dp))
            }
        }
    }
}