package com.ordersexample.mytabkotlinapp.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ordersexample.mytabkotlinapp.adapters.TrailerAdapter.MyViewHolder
import com.ordersexample.mytabkotlinapp.model.Trailer
import com.ordersexample.phonevstablet.R

/**
 * Created by delaroy on 5/24/17.
 */
class TrailerAdapter(mContext: Context,
    trailerList: List<Trailer>
) : RecyclerView.Adapter<MyViewHolder?>() {

    private val Tag=TrailerAdapter::class.java.simpleName
    private val mContext=mContext
    private val trailerList=trailerList



    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.trailer_card, viewGroup, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, i: Int) {
        viewHolder.title.setText(trailerList[i].name)
        val trailerImage="https://img.youtube.com/vi/"+trailerList.get(i).key+"/mqdefault.jpg"
        Glide.with(mContext).load(trailerImage).placeholder(R.mipmap.load).error(R.mipmap.error).
into(viewHolder.thumbnail)
    }


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView
        var thumbnail: ImageView

        init {
            title = view.findViewById<View>(R.id.trailer_title) as TextView
            thumbnail =
                view.findViewById<View>(R.id.trailer_image) as ImageView
            view.setOnClickListener { v ->
                val pos: Int = getAdapterPosition()
                if (pos != RecyclerView.NO_POSITION) {
                    val clickedDataItem: Trailer = trailerList[pos]
                    val videoId: String = trailerList[pos].key
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=$videoId")
                    )
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    intent.putExtra("VIDEO_ID", videoId)
                    mContext.startActivity(intent)
                    Toast.makeText(
                        v.context,
                        "You clicked " + clickedDataItem.name,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }


    override fun getItemCount(): Int {
        return trailerList.size    }
}