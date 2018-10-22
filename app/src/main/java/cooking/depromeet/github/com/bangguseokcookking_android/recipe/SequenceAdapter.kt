package cooking.depromeet.github.com.bangguseokcookking_android.recipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import cooking.depromeet.github.com.bangguseokcookking_android.R
import kotlinx.android.synthetic.main.item_sequence.view.*

class SequenceAdapter : RecyclerView.Adapter<SequenceAdapter.ViewHolder>() {
    private val items: MutableList<SequenceDataModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_sequence, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.run {
            Glide.with(itemView.context)
                    .load(items[position].thumbnail)
                    .into(thumbnail)
            sequence.text = items[position].order
            descriptionView.text = items[position].comment
        }
    }

    fun addItemAll(items: MutableList<SequenceDataModel>) {
        this.items.addAll(items)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val thumbnail = view.thumbnail!!
        val sequence = view.sequence!!
        val descriptionView = view.description!!
    }
}