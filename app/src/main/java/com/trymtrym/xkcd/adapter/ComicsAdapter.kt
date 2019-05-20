package com.trymtrym.xkcd.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.trymtrym.xkcd.R
import com.trymtrym.xkcd.data.Comic

class ComicsAdapter(private var myDataset: Array<Comic>) :
    RecyclerView.Adapter<ComicsAdapter.ComicViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    class ComicViewHolder(val layout: LinearLayout) : RecyclerView.ViewHolder(layout)


    public fun replaceDataset(newDataset: List<Comic>) {
        this.myDataset = newDataset.toTypedArray();
        this.notifyDataSetChanged()
    }
    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ComicsAdapter.ComicViewHolder {
        // create a new view
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.comic_item_view, parent, false) as LinearLayout
        // set the view's size, margins, paddings and layout parameters

        return ComicViewHolder(textView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.layout.findViewById<TextView>(R.id.comicItemText).text = myDataset[position].title;
        val imageView = holder.layout.findViewById<ImageView>(R.id.comicItemImage);
        Picasso.get()
            .load(myDataset[position].img)
            .into(imageView);
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size
}