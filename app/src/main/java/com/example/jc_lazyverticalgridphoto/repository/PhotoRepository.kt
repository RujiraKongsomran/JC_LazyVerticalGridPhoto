package com.example.jc_lazyverticalgridphoto.repository

import com.example.jc_lazyverticalgridphoto.model.Photo

class PhotoRepository {
    fun getAllData() =
        listOf(
            Photo(
                imageUrl = "https://cdn.pixabay.com/photo/2021/02/13/16/33/cookies-6012076_960_720.jpg"
            ),
            Photo(
                imageUrl = "https://cdn.pixabay.com/photo/2021/08/10/14/11/donut-6536082_960_720.jpg"
            ),
        )
}