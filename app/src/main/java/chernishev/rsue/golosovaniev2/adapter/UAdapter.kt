package chernishev.rsue.golosovaniev2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import chernishev.rsue.golosovaniev2.R
import chernishev.rsue.golosovaniev2.databinding.ListItemBinding
import chernishev.rsue.golosovaniev2.retrofit.user.User

class UAdapter: ListAdapter<User, UAdapter.Holder>(Comparator()){
    class Holder(view: View):RecyclerView.ViewHolder(view){
        private val binding= ListItemBinding.bind(view)

        fun bind(user: User) = with(binding){
            tvIdU.text = user.id.toString()
            tvLn.text=user.fio
            tvP.text=user.phone
            tvE.text=user.email
            //tvS.text=user.status
        }
    }

    class Comparator:DiffUtil.ItemCallback<User>(){
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
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
