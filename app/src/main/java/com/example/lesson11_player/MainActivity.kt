package com.example.lesson11_player

import SongsDB
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.lesson11_player.databinding.ActivityMainBinding
import com.example.lesson11_player.models.SongState

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding;
    private val binding: ActivityMainBinding
        get() = _binding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this._binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(this.binding.root)
        setSupportActionBar(this.binding.appToolbar.topAppBar)
        binding.bottomNavigateLayout.bottomNavigate.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.artistBnBtn -> openArtistFragment()
                R.id.playlistBnBtn -> openHomeFragment()
                R.id.settingsBnBtn -> openSettingsFragment()
                else -> false
            }
        }
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainerView, PlayListFragment.newInstance(), "songs")
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_app_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.homeTbBtn -> openHomeFragment()
            R.id.settingsTbBtn ->openSettingsFragment()
            else -> false
        }
    }

    private fun openHomeFragment(): Boolean {
        if ( supportFragmentManager.findFragmentByTag("songs")?.isVisible != true) {
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerView, PlayListFragment.newInstance(), "songs")
                .addToBackStack("")
                .commit()
            return true
        }
        return false
    }

    private fun openSettingsFragment(): Boolean {
        if ( supportFragmentManager.findFragmentByTag("settings")?.isVisible != true) {
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerView, SettingsFragment.newInstance(), "settings")
                .addToBackStack("")
                .commit()
            return true
        }
        return false
    }

    private fun openArtistFragment(): Boolean {
        if ( supportFragmentManager.findFragmentByTag("artist")?.isVisible != true) {
            val songList = SongsDB.songs
            val activeSong = songList.find { s -> s.state == SongState.PLAY }
            activeSong?.let {
                supportFragmentManager.beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragmentContainerView, ArtistFragment.newInstance(it), "artist")
                    .addToBackStack("")
                    .commit()
                return true
            }
        }
        return false
    }
}