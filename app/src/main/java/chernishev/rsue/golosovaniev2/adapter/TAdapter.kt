package chernishev.rsue.golosovaniev2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import chernishev.rsue.golosovaniev2.R
import chernishev.rsue.golosovaniev2.databinding.ListItemTitleBinding
import chernishev.rsue.golosovaniev2.retrofit.title.Title

class TAdapter: ListAdapter<Title, TAdapter.Holder>(Comparator()){
    class Holder(view: View): RecyclerView.ViewHolder(view){
        private val binding= ListItemTitleBinding.bind(view)

        fun bind(title: Title) = with(binding){
            tvId.text = title.id.toString()
            tvT.text=title.title
            tvDn.text=title.datestart
            tvDc.text=title.datefinish
        }
    }

    class Comparator: DiffUtil.ItemCallback<Title>(){
        override fun areItemsTheSame(oldItem: Title, newItem: Title): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Title, newItem: Title): Boolean {
            return oldItem==newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_title, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

}