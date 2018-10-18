package cooking.depromeet.github.com.bangguseokcookking_android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MainAdapter(
    private val eventListener: MainBottomEventListener
) : RecyclerView.Adapter<MainViewHolder>() {

    private val items: MutableList<MainRecipe> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false),
            eventListener
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun addItemAll(items: MutableList<MainRecipe>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

}

class MainViewHolder(
    private val view: View,
    private val eventListener: MainBottomEventListener
) : RecyclerView.ViewHolder(view) {

    private val thumbNail = view.findViewById<AppCompatImageView>(R.id.item_main_iv_image)
    private val title = view.findViewById<AppCompatTextView>(R.id.item_main_tv_title)
    private val likeCount = view.findViewById<AppCompatTextView>(R.id.item_main_tv_like_count)
    private val commentCount = view.findViewById<AppCompatTextView>(R.id.item_main_tv_comments_count)
    private val difficult = view.findViewById<AppCompatTextView>(R.id.item_main_tv_difficult)

    fun bind(recipe: MainRecipe) {

        view.setOnClickListener {
            eventListener.onItemClick(recipe)
        }

        Glide.with(view.context)
            .load(recipe.thumbNail)
            .into(thumbNail)

        title.text = recipe.title
        likeCount.text = recipe.like.toString()
        commentCount.text = recipe.comment.toString()
        difficult.text = recipe.difficult
    }

}