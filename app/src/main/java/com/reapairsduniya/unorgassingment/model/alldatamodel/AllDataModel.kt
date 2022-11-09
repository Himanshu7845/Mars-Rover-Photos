package com.reapairsduniya.unorgassingment.model.alldatamodel

import android.graphics.Camera

data class AllDataModel(
    val id: String, val sol: String,
    val camera: MyCamera,
    val img_src:
     String, val earth_date: String,
    val rover: MyRover
)

data class MyRover(
    val id: Int,
    val landing_date: String,
    val launch_date: String,
    val name: String,
    val status: String
)

data class MyCamera(
    val full_name: String,
    val id: Int,
    val name: String,
    val rover_id: Int
)