package com.ordersexample.mytabkotlinapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.AsyncListDiffer
import com.bumptech.glide.Glide
import com.ordersexample.mytabkotlinapp.adapters.MoviesAdapter.MyViewholder
import com.ordersexample.mytabkotlinapp.model.Movie
import com.ordersexample.phonevstablet.R
import com.ordersexample.phonevstablet.utils.OnMovieClickListner
import kotlinx.android.synthetic.main.movie_card_layout.view.*
import java.util.zip.Inflater

private class MoviesDiffUtilCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.poster_path == newItem.poster_path
                && oldItem.title == newItem.title
                && oldItem.overview == newItem.overview
    }
}

class MoviesAdapter(
    ctx: Context,
    movies: List<Movie>,
    private val movieClickListner: OnMovieClickListner
) :
    RecyclerView.Adapter<MyViewholder?>() {
    private val context: Context = ctx
    private val movieList = movies
//    private val layoutInflater: LayoutInflater =
//        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//    private val asyncListDiffer = AsyncListDiffer<Movie>(this, MoviesDiffUtilCallback())


//    override fun getItemCount() = asyncListDiffer.currentList.size

    override fun getItemCount(): Int {
        return movieList.size
    }
    //initiating  view
    override fun onCreateViewHolder(parent: ViewGroup, i: Int): MyViewholder {
//        val view: View =layoutInflater.inflate(R.layout.movie_card_layout, parent, false)
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.movie_card_layout, parent, false)
        return MyViewholder(view, movieClickListner)
    }

    //bindind elements of movie card
    override fun onBindViewHolder(viewholder: MyViewholder, i: Int) {
//        val movieModel = asyncListDiffer.currentList[i]
        val movieModel=movieList[i]
        viewholder.bind(movieModel)

//        viewholder.title.setText(movieList.get(i).title)
//        val vote = java.lang.Double.toString(movieList.get(i).vote_average)
//        viewholder.userRating.text = vote
//        val image = "https://image.tmdb.org/t/p/w500" + movieList.get(i).poster_path
//        Glide.with(context).load(image).placeholder(R.mipmap.load).error(R.mipmap.error)
//            .into(viewholder.thumbnail)
    }

//    fun setData(newItems: List<Movie>) {
//        asyncListDiffer.submitList(movieList)
//    }


    //inner class for recycler adapter view inside a card
    inner class MyViewholder(view: View, clickListener: OnMovieClickListner) :
        RecyclerView.ViewHolder(view) {
        private val title: TextView=view.findViewById(R.id.text_movie_title)
        private val userRating: TextView=view.findViewById(R.id.text_movie_rating)
        private val thumbnail=view.findViewById<AppCompatImageView>(R.id.image_view_movie)

        private lateinit var movieModel: Movie

        init {
            view.setOnClickListener {
                clickListener.onMovieClicked(movieModel)
            }
        }

        fun bind(movieModel: Movie) {
            title.setText(movieModel.title)
            userRating.setText((movieModel.vote_average).toString())
            val image = "https://image.tmdb.org/t/p/w500" + movieModel.poster_path
            Glide.with(context).load(image).placeholder(R.mipmap.load).error(R.mipmap.error)
                .into(thumbnail)
            this.movieModel = movieModel
        }

    }


}
