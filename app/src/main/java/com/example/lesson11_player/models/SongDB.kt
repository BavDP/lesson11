import com.example.lesson11_player.models.Song

object SongsDB {
    private val _songs = getSongList()
    val songs: List<Song>
        get() = _songs
    fun setSongsStates(songs: List<Song>) {
        songs.forEach{s -> this._songs.find { s2 -> s2.id == s.id }?.state = s.state}
    }
}

private fun getSongList(): MutableList<Song> = mutableListOf(
    Song(1, "Песня 1", "Автор 1"),
    Song(2, "Песня 2", "Автор 1"),
    Song(3, "Песня 3", "Автор 1"),

    Song(4, "Песня 1", "Автор 2"),
    Song(5, "Песня 2", "Автор 2"),

    Song(6, "Песня 1", "Автор 3"),

    Song(7, "Песня 1", "Автор 4"),

    Song(8, "Песня 1", "Автор 5"),
    Song(9, "Песня 2", "Автор 5"),
    Song(10, "Песня 3", "Автор 5"),
    Song(11, "Песня 4", "Автор 5"),
    Song(12, "Песня 5", "Автор 5"),

    Song(13, "Песня 1", "Автор 6"),
    Song(14, "Песня 2", "Автор 6"),

    Song(15, "Песня 1", "Автор 7"),
    Song(16, "Песня 2", "Автор 7"),
    Song(17, "Песня 3", "Автор 7"),
    Song(18, "Песня 4", "Автор 7"),
    Song(19, "Песня 5", "Автор 7"),

    Song(20, "Песня 1", "Автор 8"),
    Song(21, "Песня 2", "Автор 8"),

    Song(22, "Песня 1", "Автор 9"),
    Song(23, "Песня 2", "Автор 9"),
    Song(24, "Песня 3", "Автор 9"),

    Song(25, "Песня 1", "Автор 10"),


    Song(26, "Песня 1", "Автор 11"),

    Song(27, "Песня 1", "Автор 12"),

    Song(28, "Песня 1", "Автор 13"),
)