package chernishev.rsue.golosovaniev2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import chernishev.rsue.golosovaniev2.R
import chernishev.rsue.golosovaniev2.retrofit.question.Question

class QSAdapter(ctx: Context, list: List<Question>): ArrayAdapter<Question>(ctx, 0, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return myView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return myView(position, convertView, parent)
    }
    private fun myView(position: Int, convertView: View?, parent: ViewGroup): View {
        val list = getItem(position)
        val view = convertView?: LayoutInflater.from(context).inflate(R.layout.list_item_question, parent, false)
        list.let {
            val tvIdS = view.findViewById<TextView>(R.id.tv_id_q)
            val tvQS = view.findViewById<TextView>(R.id.tv_q)
            val tvQD = view.findViewById<TextView>(R.id.tv_d_q)

            tvIdS.text= list!!.id.toString()
            tvQS.text= list!!.content.toString()
            tvQD.text= list!!.dateVote
        }
        return view
    }
}