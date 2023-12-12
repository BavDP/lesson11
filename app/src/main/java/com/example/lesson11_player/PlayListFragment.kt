package com.example.lesson11_player

import SongsDB
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lesson11_player.adapters.PlayListAdapter
import com.example.lesson11_player.databinding.FragmentPlayListBinding
import com.example.lesson11_player.models.Song
import com.example.lesson11_player.models.SongState

class PlayListFragment : Fragment() {
    private lateinit  var _binding: FragmentPlayListBinding;
    private val binding: FragmentPlayListBinding
        get() = this._binding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.playlistView.adapter = PlayListAdapter(SongsDB.songs.toMutableList()) { song ->
            openArtistFragment(song)
        }
        binding.playlistView.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this._binding = FragmentPlayListBinding.inflate(inflater);
        return binding.root
    }

    private fun openArtistFragment(song: Song) {
        parentFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.fragmentContainerView, ArtistFragment.newInstance(song))
            .addToBackStack("")
            .commit()
    }

    private fun playSong(song: Song) {
        when(song.state) {
            SongState.PLAY -> song.state = SongState.PAUSE
            SongState.PAUSE -> song.state = SongState.PLAY
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PlayListFragment()
    }
}