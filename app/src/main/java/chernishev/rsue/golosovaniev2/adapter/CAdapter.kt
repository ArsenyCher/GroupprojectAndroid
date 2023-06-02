package chernishev.rsue.golosovaniev2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import chernishev.rsue.golosovaniev2.R
import chernishev.rsue.golosovaniev2.databinding.ListItemBinding
import chernishev.rsue.golosovaniev2.retrofit.Choice
import chernishev.rsue.golosovaniev2.retrofit.user.User

class CAdapter: ListAdapter<Choice, CAdapter.Holder>(Comparator()){
    class Holder(view: View): RecyclerView.ViewHolder(view){
        private val binding= ListItemBinding.bind(view)

        fun bind(choice: Choice) = with(binding){
            tvIdU.text = choice.id.toString()
            tvLn.text=choice.questtioncontent
            tvP.text=choice.userfio
            tvE.text=choice.choiceuser
        }
    }

    class Comparator: DiffUtil.ItemCallback<Choice>(){
        override fun areItemsTheSame(oldItem: Choice, newItem: Choice): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Choice, newItem: Choice): Boolean {
            return oldItem==newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

}