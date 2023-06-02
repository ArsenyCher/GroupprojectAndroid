package chernishev.rsue.golosovaniev2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import chernishev.rsue.golosovaniev2.R
import chernishev.rsue.golosovaniev2.databinding.ListItemQuestionBinding
import chernishev.rsue.golosovaniev2.retrofit.question.Question

class QAdapter: ListAdapter<Question, QAdapter.Holder>(Comparator()){
    class Holder(view: View): RecyclerView.ViewHolder(view){
        private val binding= ListItemQuestionBinding.bind(view)

        fun bind(question: Question) = with(binding){
            tvIdQ.text = question.id.toString()
            tvQ.text=question.content
            tvDQ.text=question.dateVote
        }
    }

    class Comparator: DiffUtil.ItemCallback<Question>(){
        override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem==newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_question, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

}