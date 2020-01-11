package com.ordersexample.phonevstablet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import com.ordersexample.phonevstablet.utils.OnMovieClickListner
import com.ordersexample.mytabkotlinapp.model.Movie
import com.ordersexample.phonevstablet.fragments.DetailsFragment
import com.ordersexample.phonevstablet.fragments.TopRatedFragment

class MainActivity : AppCompatActivity(),
    OnMovieClickListner {

    private var isTablet: Boolean = false
    private lateinit var moviesFragment: TopRatedFragment
    private lateinit var detailsFragment: DetailsFragment
    private var listner: OnMovieClickListner? = null
    private lateinit var detailsFrame: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val moviesFrame = findViewById<FrameLayout>(R.id.activity_main_frame)


        isTablet = resources.getBoolean(R.bool.isTablet)

        moviesFragment = TopRatedFragment()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.activity_main_frame, moviesFragment)
            .commit()


    }



    override fun onMovieClicked(movie: Movie) {
        detailsFragment = DetailsFragment.newInstance(movie)

        if (!isTablet) {

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.activity_main_frame, detailsFragment)
                .commit()
        } else {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.activity_details_frame, detailsFragment)
                .commit()
        }
    }


}
