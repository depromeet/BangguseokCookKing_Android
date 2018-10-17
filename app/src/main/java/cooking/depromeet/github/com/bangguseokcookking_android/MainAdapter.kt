package cooking.depromeet.github.com.bangguseokcookking_android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {

    private val items: MutableList<MainRecipe> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(items[position])
    }

}

class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(recipe: MainRecipe) {

    }

}