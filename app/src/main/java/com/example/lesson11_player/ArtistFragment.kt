package com.example.lesson11_player

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lesson11_player.databinding.FragmentArtistBinding
import com.example.lesson11_player.models.Song

class ArtistFragment : Fragment() {

    private lateinit var _binding: FragmentArtistBinding;
    private val binding: FragmentArtistBinding
        get() = _binding
    private lateinit var song: Song
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainArtistNameTextView.text = song.artistName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArtistBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(song: Song) = ArtistFragment().apply {
            this.song = song
        }
    }
}