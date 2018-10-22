package cooking.depromeet.github.com.bangguseokcookking_android.ui.mypage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import cooking.depromeet.github.com.bangguseokcookking_android.R
import cooking.depromeet.github.com.bangguseokcookking_android.domain.MyLike

class MyLikeAdapter : RecyclerView.Adapter<MyLikeViewHolder>() {

    private val items: MutableList<MyLike> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyLikeViewHolder =
        MyLikeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_like, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MyLikeViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun addItems(items: MutableList<MyLike>) {
        this@MyLikeAdapter.items.addAll(items)
        notifyDataSetChanged()
    }
}

class MyLikeViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private val title: AppCompatTextView = view.findViewById(R.id.item_like_iv_title)
    private val thumbnail: AppCompatImageView = view.findViewById(R.id.item_like_iv_thumbnail)

    private val roundOptions = RequestOptions()
        .circleCrop()
        .diskCacheStrategy(DiskCacheStrategy.NONE)

    fun bind(like: MyLike) {

        title.text = like.title

        Glide.with(thumbnail.context)
            .load(like.thumbnail)
            .apply(roundOptions)
            .into(thumbnail)
    }

}