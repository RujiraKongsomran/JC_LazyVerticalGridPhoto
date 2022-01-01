package com.example.jc_lazyverticalgridphoto

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.jc_lazyverticalgridphoto.data.remote.PhotosService
import com.example.jc_lazyverticalgridphoto.model.Photo
import com.example.jc_lazyverticalgridphoto.repository.PhotoRepository
import com.example.jc_lazyverticalgridphoto.ui.theme.JC_LazyVerticalGridPhotoTheme

@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {
    private val service = PhotosService.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            val personRepository = PhotoRepository()
//            val getAllData = personRepository.getAllData()
//            PhotoGrid(photos = getAllData)
            val photos = produceState<List<Photo>>(
                initialValue = emptyList(),
                producer = {
                    value = service.getPhotos()
                }
            )

            JC_LazyVerticalGridPhotoTheme() {
                LazyVerticalGrid(
                    cells = GridCells.Adaptive(minSize = 128.dp),
                ) {
                    itemsIndexed(items = photos.value) { index, photo ->
                        Log.d("MainActivity", index.toString())
                        PhotoItem(photo = photo)
                    }
                }
            }
        }
    }
}

@Composable
fun PhotoItem(photo: Photo) {
    Image(
        painter = rememberImagePainter(
            data = photo.imageUrl,
            builder = {
                crossfade(true)
            }
        ),
        contentDescription = null,
        modifier = Modifier.size(128.dp),
        contentScale = ContentScale.Crop,
    )
}

@ExperimentalFoundationApi
@Composable
fun PhotoGrid(photos: List<Photo>) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(minSize = 128.dp),
    ) {
        itemsIndexed(items = photos) { index, photo ->
            Log.d("MainActivity", index.toString())
            PhotoItem(photo = photo)
        }
    }
}