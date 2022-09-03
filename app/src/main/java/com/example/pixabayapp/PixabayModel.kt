package com.example.pixabayapp

data class PixabayModel(
    val hits: List<ImageModel>
)

data class ImageModel(
    val largeImageURL: String

)
