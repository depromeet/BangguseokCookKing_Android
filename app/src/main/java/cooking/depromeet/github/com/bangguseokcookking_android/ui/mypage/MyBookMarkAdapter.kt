package cooking.depromeet.github.com.bangguseokcookking_android.ui.mypage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import cooking.depromeet.github.com.bangguseokcookking_android.R
import cooking.depromeet.github.com.bangguseokcookking_android.domain.BookMark

class MyBookMarkAdapter : RecyclerView.Adapter<MyBookMarkViewHolder>() {

    private val items: MutableList<BookMark> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyBookMarkViewHolder =
        MyBookMarkViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_bookmark, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MyBookMarkViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun addItems(items: MutableList<BookMark>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}

class MyBookMarkViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private val number: AppCompatTextView = view.findViewById(R.id.item_bookmark_tv_number)
    private val thumbnail: AppCompatImageView = view.findViewById(R.id.item_bookmark_iv_thumbnail)

    fun bind(bookmark: BookMark) {
        number.text = bookmark.number.toString()

        Glide.with(thumbnail.context)
            .load(bookmark.thumbnail)
            .into(thumbnail)
    }
}