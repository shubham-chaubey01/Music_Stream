package com.example.musicstream.models

data class categorymodel(
    val name: String,
    val coverUrl: String,
    var songs : List<String>
) {
    constructor() : this( "","", listOf())
}
