package com.rick.and.morty.character_video

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.databinding.DataBindingUtil
import androidx.media3.common.MediaItem
import androidx.media3.common.util.Util
import androidx.media3.exoplayer.ExoPlayer
import com.rick.and.morty.R
import com.rick.and.morty.core.BaseFragment
import com.rick.and.morty.databinding.FragmentCharacterVideoBinding

class CharacterVideoFragment : BaseFragment() {

    private lateinit var fragmentCharacterVideoBinding: FragmentCharacterVideoBinding

    private var player: ExoPlayer? = null
    private var playWhenReady = true
    private var currentItem = 0
    private var playbackPosition = 0L

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentCharacterVideoBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_character_video, container, false
            )

        fragmentCharacterVideoBinding.lifecycleOwner = this
        return fragmentCharacterVideoBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentCharacterVideoBinding.characterName =
            CharacterVideoFragmentArgs.fromBundle(requireArguments()).characterName
    }

    @androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
    private fun initializePlayer() {
        player = ExoPlayer.Builder(requireContext())
            .build()
            .also { exoPlayer ->
                fragmentCharacterVideoBinding.videoView?.player = exoPlayer
                val mediaItem =
                    MediaItem.fromUri(CharacterVideoFragmentArgs.fromBundle(requireArguments()).videoUrl)
                exoPlayer.setMediaItem(mediaItem)

                exoPlayer.playWhenReady = playWhenReady
                exoPlayer.seekTo(currentItem, playbackPosition)
                exoPlayer.prepare()

            }
    }

    private fun releasePlayer() {
        player?.let { exoPlayer ->
            playbackPosition = exoPlayer.currentPosition
            currentItem = exoPlayer.currentMediaItemIndex
            playWhenReady = exoPlayer.playWhenReady
            exoPlayer.release()
        }
        player = null
    }

    @SuppressLint("InlinedApi")
    private fun hideSystemUi() {
        WindowCompat.setDecorFitsSystemWindows(activity?.window!!, false)
        WindowInsetsControllerCompat(
            activity?.window!!,
            fragmentCharacterVideoBinding.videoView
        ).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    @androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > Build.VERSION_CODES.M) {
            initializePlayer()
        }
    }

    @androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
    override fun onResume() {
        super.onResume()
        hideSystemUi()
        if ((Util.SDK_INT <= Build.VERSION_CODES.M || player == null)) {
            initializePlayer()
        }
    }

    @androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT <= Build.VERSION_CODES.M) {
            releasePlayer()
        }
    }

    @androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > Build.VERSION_CODES.M) {
            releasePlayer()
        }
    }

}