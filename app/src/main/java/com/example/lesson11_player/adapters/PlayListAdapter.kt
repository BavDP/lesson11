package com.example.lesson11_player.adapters


import SongsDB
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson11_player.R
import com.example.lesson11_player.models.Song
import com.example.lesson11_player.models.SongState


class PlayListDiffUtilCallback(private val newPlaylist: List<Song>, private val oldPlayList: List<Song>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldPlayList.size
    }

    override fun getNewListSize(): Int {
        return newPlaylist.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldPlayList[oldItemPosition]
        val newItem = newPlaylist[newItemPosition]
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldPlayList[oldItemPosition]
        val newItem = newPlaylist[newItemPosition]
        return oldItem == newItem
    }
}

class PlayListAdapter(private val songs: MutableList<Song>,
                      private val artistClickHandler: (song: Song) -> Unit): RecyclerView.Adapter<PlayListAdapter.PlayListViewHolder>() {

    class PlayListViewHolder(private val view: View, private val songs: List<Song>): RecyclerView.ViewHolder(view) {
        fun bind(song: Song, artistClickHandler: (Song) -> Unit, playSongClick: (List<Song>, Song)->Unit) {

            val songListItem = view.findViewById<ConstraintLayout>(R.id.songItem)
            val artistNameTextView = view.findViewById<TextView>(R.id.artistNameTextView)
            val songNameTextView = view.findViewById<TextView>(R.id.songNameTextView)
            val playBtn = view.findViewById<ImageButton>(R.id.playPauseBtn)

            artistNameTextView.text = song.artistName
            artistNameTextView.setOnClickListener { _ -> artistClickHandler(song) }
            when(song.state) {
                SongState.PLAY -> {
                    playBtn.setImageDrawable(ResourcesCompat.getDrawable(view.resources, R.drawable.pause, null))
                    songListItem.setBackgroundColor(view.resources.getColor(androidx.appcompat.R.color.abc_color_highlight_material, null))
                }
                SongState.PAUSE -> {
                    playBtn.setImageDrawable(ResourcesCompat.getDrawable(view.resources, R.drawable.play_circle, null))
                    songListItem.setBackgroundColor(view.resources.getColor(R.color.white, null))
                }
            }
            playBtn.setOnClickListener {_ -> playButtonHandler(song, playSongClick) }
            songNameTextView.text = song.songName
        }

        private fun playButtonHandler(song: Song, playSongClick: (List<Song>, Song)->Unit) {
            val newSongs = mutableListOf<Song>()
            songs.forEach{s -> newSongs.add(Song(s))}
            newSongs.forEach{s ->
                s.state = SongState.PAUSE
            }
            val newSong = newSongs.find { it.id == song.id }
            if (newSong != null) {
                when (newSong.state) {
                    SongState.PLAY -> newSong.state = SongState.PAUSE
                    SongState.PAUSE -> newSong.state = SongState.PLAY
                }
                playSongClick(newSongs, newSong)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.play_list_item, parent, false)
        return PlayListViewHolder(view, songs)
    }

    override fun onBindViewHolder(holder: PlayListViewHolder, position: Int) {
        holder.bind(songs[position], artistClickHandler) { songs, _ ->
            setSongsList(
                songs
            )
        }
        return
    }

    override fun getItemCount(): Int {
        return songs.size
    }

    private fun setSongsList(songs: List<Song>) {
        val diff = DiffUtil.calculateDiff(PlayListDiffUtilCallback(songs, this.songs))
        this.songs.clear()
        this.songs.addAll(songs)
        SongsDB.setSongsStates(songs)
        diff.dispatchUpdatesTo(this)
    }
}