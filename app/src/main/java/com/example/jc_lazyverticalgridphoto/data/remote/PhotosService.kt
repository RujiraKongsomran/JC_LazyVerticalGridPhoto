package com.example.jc_lazyverticalgridphoto.data.remote

import com.example.jc_lazyverticalgridphoto.model.Photo
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface PhotosService {
    suspend fun getPhotos(): List<Photo>

    companion object {
        fun create(): PhotosService =
            PhotosServiceImpl(client = HttpClient(Android) {
                install(Logging) {
                    level = LogLevel.ALL
                }
                install(JsonFeature) {
                    serializer = KotlinxSerializer()
                }
            })
    }
}