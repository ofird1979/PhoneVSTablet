package com.ordersexample.phonevstablet.fragments


import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ordersexample.mytabkotlinapp.adapters.MoviesAdapter
import com.ordersexample.phonevstablet.utils.OnMovieClickListner
import com.ordersexample.mytabkotlinapp.model.Movie
import com.ordersexample.mytabkotlinapp.model.MoviesResponse
import com.ordersexample.mytabkotlinapp.services.Client
import com.ordersexample.mytabkotlinapp.services.Service
import com.ordersexample.phonevstablet.BuildConfig
import com.ordersexample.phonevstablet.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class TopRatedFragment : Fragment(),
    OnMovieClickListner {
    private val LOGTAG = this@TopRatedFragment::class.java.simpleName
    private lateinit var mRecycler: RecyclerView
    private var movieList: List<Movie> = mutableListOf()
    private lateinit var adapter: MoviesAdapter
    private lateinit var srl1: SwipeRefreshLayout
    private var listner: OnMovieClickListner? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_top_rated, container, false)
        mRecycler = view.findViewById<View>(R.id.top_recyclerview) as RecyclerView
        srl1 = view.findViewById<View>(R.id.swipe_top_rated) as SwipeRefreshLayout
        srl1.setColorSchemeResources(android.R.color.holo_green_light)


        srl1.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                getTopRated()
                Toast.makeText(context, "Movies Refreshed", Toast.LENGTH_SHORT).show()

            }

        })
        getTopRated()
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnMovieClickListner) {
            listner = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listner=null
    }

    private fun refreshMoviesAdapter(movieList: List<Movie>) {
        context?.let {
            adapter = MoviesAdapter(it.applicationContext, movieList, this@TopRatedFragment)
            if (activity!!.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                mRecycler.setLayoutManager(GridLayoutManager(context, 2))
            } else {
                mRecycler.setLayoutManager(GridLayoutManager(context, 4))
            }
            mRecycler.setItemAnimator(DefaultItemAnimator())
            mRecycler.setAdapter(adapter)
            adapter.notifyDataSetChanged()
            if (srl1.isRefreshing) {
                srl1.isRefreshing = false
            }
        }

    }

    private fun getTopRated() {
        val client = Client.getClient()
        val service: Service = client!!.create(Service::class.java)
        val call = service.getTopRatedMovies(BuildConfig.THE_MOVIE_DB_API_TOKEN)
        Log.i(LOGTAG, call.toString())

        call.enqueue(object : Callback<MoviesResponse> {
            override fun onFailure(call: Call<MoviesResponse>, p1: Throwable) {
                Log.d("Error", p1.message.toString())
            }

            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                movieList = response.body().movies
                val comparator1 = Movie.BY_RELEASE_DATE()
                movieList.sortedWith(comparator1)
                refreshMoviesAdapter(movieList)

            }
        })
    }

    override fun onMovieClicked(movie: Movie) {
        listner?.onMovieClicked(movie)
    }


}
