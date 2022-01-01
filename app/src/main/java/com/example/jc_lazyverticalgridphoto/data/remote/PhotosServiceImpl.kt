package com.example.jc_lazyverticalgridphoto.data.remote

import com.example.jc_lazyverticalgridphoto.model.Photo
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*

class PhotosServiceImpl(
    private val client: HttpClient
) : PhotosService {
    override suspend fun getPhotos(): List<Photo> = try {
        client.get { url(HttpRoutes.ADDITION) }
    } catch (e: RedirectResponseException) {
        // 3xx - response
        println("Error: ${e.response.status.description}")
        emptyList()
    } catch (e: ClientRequestException) {
        // 4xx - response
        println("Error: ${e.response.status.description}")
        emptyList()
    } catch (e: ServerResponseException) {
        // 5xx - response
        println("Error: ${e.response.status.description}")
        emptyList()
    } catch (e: Exception) {
        println("Error: ${e.message}")
        emptyList()
    }
}