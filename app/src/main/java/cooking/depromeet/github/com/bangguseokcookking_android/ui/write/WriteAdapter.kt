package cooking.depromeet.github.com.bangguseokcookking_android.ui.write

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import cooking.depromeet.github.com.bangguseokcookking_android.R

class WriteAdapter(
    private val eventListener: WriteRecipeEventListener
) : RecyclerView.Adapter<WriteViewHolder>() {

    private val items: MutableList<WriteRecipe> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WriteViewHolder {

        return WriteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_write, parent, false),
            eventListener
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: WriteViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun addItem(item: WriteRecipe) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun changeImage(url: String, position: Int) {
        items[position].image = url
        notifyItemChanged(position)
    }
}

class WriteViewHolder(
    private val view: View,
    private val eventListener: WriteRecipeEventListener
) : RecyclerView.ViewHolder(view) {

    private val image: AppCompatImageView = view.findViewById(R.id.item_write_iv_image)
    private val number: AppCompatTextView = view.findViewById(R.id.item_write_tv_number)
    private val content: AppCompatEditText = view.findViewById(R.id.item_write_tv_content)

    fun bind(item: WriteRecipe) {

        if (item.image.isNotEmpty()) {
            Glide.with(image.context)
                .load(item.image)
                .into(image)
        }

        image.setOnClickListener { eventListener.onImageSelected(item.number) }
        number.text = "${item.number + 1}"

        content.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                eventListener.onTextWatcher(s.toString(), item.number)
            }

        })


    }
}