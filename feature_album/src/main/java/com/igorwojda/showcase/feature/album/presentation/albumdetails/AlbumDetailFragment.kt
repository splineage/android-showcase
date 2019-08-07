package com.igorwojda.showcase.feature.album.presentation.albumdetails

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.igorwojda.base.presentation.extension.observe
import com.igorwojda.base.presentation.fragment.BaseContainerFragment
import com.igorwojda.showcase.feature.album.R
import com.igorwojda.showcase.feature.album.domain.enum.AlbumDomainImageSize
import com.igorwojda.showcase.feature.album.domain.model.AlbumDomainModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_album_detail.*
import org.kodein.di.generic.instance

internal class AlbumDetailFragment : BaseContainerFragment() {

    private val viewModel: AlbumDetailsViewModel by instance()
    private val picasso: Picasso by instance()
    private val args: AlbumDetailFragmentArgs by navArgs()

    override val layoutResourceId = R.layout.fragment_album_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = resources.getString(R.string.album_details)

        observe(viewModel.state, ::onStateChange)

        viewModel.getAlbum(args)
    }

    private fun onStateChange(albumDomainModel: AlbumDomainModel) {
        nameTextView.text = albumDomainModel.name
        artistTextView.text = albumDomainModel.artist
        publishedTextView.text = albumDomainModel.wiki?.published

        val url = albumDomainModel.images.firstOrNull { it.size == AlbumDomainImageSize.LARGE }?.url
        if (albumDomainModel.images.isNotEmpty() && !url.isNullOrEmpty()) {
            loadImage(url)
        }
    }

    private fun loadImage(it: String) {
        val imageSize = 800

        picasso.load(it)
            .resize(imageSize, imageSize)
            .centerCrop()
            .placeholder(R.drawable.progress_animation)
            .into(coverImageView)
    }
}