package cooking.depromeet.github.com.bangguseokcookking_android.ui.mypage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import cooking.depromeet.github.com.bangguseokcookking_android.R
import cooking.depromeet.github.com.bangguseokcookking_android.domain.MyRecipe

class MyRecipeAdapter : RecyclerView.Adapter<MyRecipeViewHolder>() {

    private val items: MutableList<MyRecipe> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecipeViewHolder =
        MyRecipeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_my_recipe, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MyRecipeViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun addItems(items: MutableList<MyRecipe>) {
        this@MyRecipeAdapter.items.addAll(items)
        notifyDataSetChanged()
    }
}

class MyRecipeViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private val title: AppCompatTextView = view.findViewById(R.id.my_recipe_tv_title)
    private val date: AppCompatTextView = view.findViewById(R.id.my_recipe_tv_date)

    fun bind(recipe: MyRecipe) {
        title.text = recipe.title
        date.text = recipe.date
    }

}