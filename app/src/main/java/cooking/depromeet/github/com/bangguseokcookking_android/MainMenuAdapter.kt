package cooking.depromeet.github.com.bangguseokcookking_android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class MainMenuAdapter(
    private val clickEvent: (String) -> Unit
) : RecyclerView.Adapter<MainMenuViewHolder>(), MenuEventListener {

    private val items: MutableList<String> = mutableListOf()
    private var clickPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainMenuViewHolder {
        return MainMenuViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_main_menu, parent, false),
            clickEvent,
            this
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MainMenuViewHolder, position: Int) {
        holder.bind(items[position], clickPosition, position)
    }

    fun addItems(items: MutableList<String>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onClicked(position: Int) {
        clickPosition = position
        notifyDataSetChanged()
    }

}

class MainMenuViewHolder(
    private val view: View,
    private val clickEvent: (String) -> Unit,
    private val menuListener: MenuEventListener
) : RecyclerView.ViewHolder(view) {

    private val text: AppCompatTextView = view.findViewById(R.id.item_menu_tv_text)

    fun bind(item: String, clickPosition: Int, currentPosition: Int) {

        text.text = item

        if (clickPosition == currentPosition) {
            view.background = ContextCompat.getDrawable(view.context, R.drawable.menu_orange)
            text.setTextColor(ContextCompat.getColor(text.context, R.color.menu_white))
        } else {
            view.background = ContextCompat.getDrawable(view.context, R.drawable.menu_white)
            text.setTextColor(ContextCompat.getColor(text.context, R.color.menu_gray))
        }

        view.setOnClickListener {

            menuListener.onClicked(currentPosition)
            clickEvent(item)
        }
    }
}

interface MenuEventListener {

    fun onClicked(position: Int)
}
