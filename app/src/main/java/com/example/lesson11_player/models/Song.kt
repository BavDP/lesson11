package com.example.lesson11_player.models

data class Song(private var _id: Int = -1,
                private var _songName: String = "",
                private var _artistName: String = "",
                private var _likes: Int = 0,
                private var _dislikes: Int = 0,
                var state: SongState = SongState.PAUSE) {
    val songName: String
        get() = _songName
    val artistName: String
        get() = _artistName
    val likes: Int
        get() = _likes
    val dislikes: Int
        get() = _dislikes
    val id: Int
        get() = _id

    public fun like() {
        this._likes++
    }

    public fun dislike() {
        this._dislikes++
    }

    constructor(src: Song) : this(
        src.id,
        src.songName,
        src.artistName,
        src.likes,
        src.dislikes,
        src.state
    ) {
    }
}