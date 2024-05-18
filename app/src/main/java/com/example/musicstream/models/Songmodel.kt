package com.example.musicstream.models

data class Songmodel(
    val id : String,
    val title : String,
    val subtitle :String,
    val url : String,
    val coverUrl : String,
)  {
     constructor() : this("","","","","")
}
