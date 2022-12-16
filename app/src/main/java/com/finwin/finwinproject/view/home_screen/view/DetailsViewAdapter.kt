package com.finwin.finwinproject.view.home_screen.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.finwin.finwinproject.R
import com.finwin.finwinproject.view.home_screen.model.DetailsResponseModelItem

class DetailsViewAdapter(
    private var context:Context,
    private var result:List<DetailsResponseModelItem>
) : RecyclerView.Adapter<DetailsViewAdapter.CartOrderViewHolder>() {
    private var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartOrderViewHolder {
        var view:View = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false)

        return CartOrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartOrderViewHolder, position: Int) {
        val detailItem = result[position]
        if (!detailItem.thumbnailUrl.isNullOrEmpty()) {
            Glide.with(context)
                .load(detailItem.thumbnailUrl)
                .thumbnail(0.5f)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_launcher_foreground)
                        .error(R.drawable.ic_launcher_foreground)
                )
                .into(holder.imageView)
        }
//        else{
//            Glide.with(context).load(R.drawable.ic_launcher_background)
//                .thumbnail(0.5f)
//                .apply(
//                    RequestOptions.placeholderOf(R.drawable.ic_launcher_background)
//                        .error(R.drawable.ic_launcher_background)
//                )
//                .into(holder.imageView)
//        }

        holder.title.text="${detailItem.title}"
        holder.cardview.setOnClickListener{
            onItemClickListener?.onItemClick(detailItem)
        }

    }


    override fun getItemCount(): Int {
        return result!!.size
    }
    fun update(result:List<DetailsResponseModelItem>){
        this.result=result
        notifyDataSetChanged()
    }

    class CartOrderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        internal val imageView: ImageView = itemView.findViewById(R.id.imageToShow)
        internal val title: TextView = itemView.findViewById(R.id.Title_name_text_View)
        internal val cardview: CardView = itemView.findViewById(R.id.card_view)
    }

    interface OnItemClickListener {
        fun onItemClick(bean: DetailsResponseModelItem)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }
}