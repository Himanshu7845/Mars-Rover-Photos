package com.reapairsduniya.unorgassingment.model.roverdatamodel

data class Photo(
    val camera: Camera,
    val earth_date: String,
    val id: Int,
    val img_src: String,
    val rover: Rover,
    val sol: Int
)