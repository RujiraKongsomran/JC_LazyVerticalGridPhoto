package com.example.jc_lazyverticalgridphoto.data.remote

import com.example.jc_lazyverticalgridphoto.model.Photo

interface PhotosService {
    suspend fun getPhotos(): List<Photo>
}