/*
package chernishev.rsue.golosovaniev2.ui.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import chernishev.rsue.golosovaniev2.R
import chernishev.rsue.golosovaniev2.databinding.ListItemBinding
import chernishev.rsue.golosovaniev2.retrofit.user.User

class HomeAdapter: ListAdapter<User, HomeAdapter.StartViewHolder>(HomeAdapter.Comparator()) {
    var listStart = emptyList<User>()
    class StartViewHolder(view: View):RecyclerView.ViewHolder(view){
        private val binding= ListItemBinding.bind(view)

        fun bind(user: User) = with(binding){
            tvId.text = user.id.toString()
            tvLn.text=user.fio
            tvFn.text=user.password
            tvP.text=user.phone
            tvE.text=user.email
            tvS.text= user.status.toString()
        }
    }

    class Comparator: DiffUtil.ItemCallback<User>(){
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem==newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StartViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return StartViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listStart.size
    }

    override fun onBindViewHolder(holder: StartViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    fun setList(list: List<User>){
        listStart=list
        notifyDataSetChanged()
    }
}*/
