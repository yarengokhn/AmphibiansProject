package com.example.amphibiansproject.model

import com.google.gson.annotations.SerializedName

data class Amphibian (
     val name :String,
     val type :String,
     val description :String,
     @SerializedName("img_src")
     val imgSrc :String,
 )

const val name = "Great Basin Spadefoot"
const val type = " Toad "
const val description = "This toad spends most of its life underground due to the arid " +
        "desert conditions in which it lives. Spadefoot toads earn the name because of " +
        "their hind legs which are wedged to aid in digging. " +
        "They are typically grey, green, or brown with dark spots."

const val imgSrc = "img1.src"



val mockData: List<Amphibian> = listOf(
    Amphibian(
        name =name,
        type = type,
        description = description,
        imgSrc = imgSrc
    ),

    Amphibian(
        name ="name2",
        type = type,
        description = description,
         imgSrc = imgSrc

    ),

    Amphibian(
        name ="name3",
        type = type,
        description = description,
        imgSrc = imgSrc

    ),
    Amphibian(
        name ="name4",
        type = type,
        description = description,
        imgSrc = imgSrc

    ),

    Amphibian(
        name ="name5",
        type = type,
        description = description,
        imgSrc = imgSrc

    )

)

fun getMockDatas(): List<Amphibian> {
    return mockData
}