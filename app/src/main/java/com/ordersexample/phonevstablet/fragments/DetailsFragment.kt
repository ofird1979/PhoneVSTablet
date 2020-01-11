package com.ordersexample.phonevstablet.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.ordersexample.mytabkotlinapp.adapters.TrailerAdapter
import com.ordersexample.mytabkotlinapp.model.Movie
import com.ordersexample.mytabkotlinapp.model.Trailer

import com.ordersexample.phonevstablet.R
import com.ordersexample.phonevstablet.db.DBHelper
import java.io.File

/**
 * A simple [Fragment] subclass.
 */
class DetailsFragment : Fragment() {
    private var plotSynopsis: TextView? = null
    private var userRating: TextView? = null
    var releaseDate: TextView? = null
    var movie_title: TextView? = null
    lateinit var imageView: ImageView
    lateinit var share: ImageView
    lateinit var edit: ImageView
    private var recyclerView: RecyclerView? = null
    private var adapter: TrailerAdapter? = null
    private var trailerList: List<Trailer> = mutableListOf()
    private var dao: DBHelper? = null
    lateinit var thumbnail: String
    lateinit var movieName: String
    lateinit var synopsis: String
    lateinit var rating: String
    lateinit var dateOfRelease: String
    private var movieId = 0
    private lateinit var mfb: ToggleButton
    private var isInDBCheck: Boolean = false
    private lateinit var file: File


    companion object {
        private const val MOVIE_BUNDLE_KEY = "unique_movie_key"

        fun newInstance(movie: Movie): DetailsFragment {
            val fragment = DetailsFragment()
            val args = Bundle()
            args.putParcelable(MOVIE_BUNDLE_KEY, movie)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_details, container, false)

        val movie:Movie?= arguments?.getParcelable(MOVIE_BUNDLE_KEY)
        edit = root.findViewById(R.id.edit_button)
        share = root.findViewById(R.id.share_button)
        imageView = root.findViewById(R.id.thumbnail_image_header)
        plotSynopsis = root.findViewById(R.id.plotsynopsis)
        userRating = root.findViewById(R.id.userrating)
        releaseDate = root.findViewById(R.id.releasedate)
        movie_title = root.findViewById(R.id.text_movie_details_title)

        thumbnail = movie!!.poster_path
        movieName = movie.title
        synopsis = movie.overview
        rating = java.lang.Double.toString(movie.vote_average)
        dateOfRelease = movie.release_date
        movieId = movie.id
        val poster = "https://image.tmdb.org/t/p/w500$thumbnail"
        Glide.with(this)
            .load(poster)
            .placeholder(R.mipmap.load).into(imageView)

        plotSynopsis!!.text = synopsis
        movie_title!!.text = movieName
        userRating!!.text = rating
        releaseDate!!.text = dateOfRelease

        dao = DBHelper(requireContext())

        mfb = root.findViewById(R.id.tb_favorite)
        isInDBCheck = dao!!.isMovieExist(movie)

        if (isInDBCheck) {
            mfb.setBackgroundResource(R.drawable.full_heart)
        } else {
            mfb.setBackgroundResource(R.drawable.empty_heart)
        }

        mfb.setOnCheckedChangeListener { _, isChecked ->
            isInDBCheck = dao!!.isMovieExist(movie)
            if (!isInDBCheck) {
                dao!!.addMovie(movie)
                Snackbar.make(
                    mfb, "Added to Favorite",
                    Snackbar.LENGTH_SHORT
                ).show()
                mfb.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.rotate))
                mfb.setBackgroundResource(R.drawable.full_heart)

            } else {

                dao!!.deleteMovie(movie)
                Snackbar.make(
                    mfb, "Removed from Favorite",
                    Snackbar.LENGTH_SHORT
                ).show()
                mfb.startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.rotate))
                mfb.setBackgroundResource(R.drawable.empty_heart)
            }
        }

        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
}
