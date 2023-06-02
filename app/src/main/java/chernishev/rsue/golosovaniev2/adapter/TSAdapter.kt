package chernishev.rsue.golosovaniev2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import chernishev.rsue.golosovaniev2.R
import chernishev.rsue.golosovaniev2.retrofit.question.Question
import chernishev.rsue.golosovaniev2.retrofit.title.Title

class TSAdapter(ctx: Context, list: List<Title>): ArrayAdapter<Title>(ctx, 0, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return myView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return myView(position, convertView, parent)
    }
    private fun myView(position: Int, convertView: View?, parent: ViewGroup): View {
        val list = getItem(position)
        val view = convertView?: LayoutInflater.from(context).inflate(R.layout.list_item_title, parent, false)
        list.let {
            val tvIdS = view.findViewById<TextView>(R.id.tv_id)
            val tvT = view.findViewById<TextView>(R.id.tv_t)
            val tvDn = view.findViewById<TextView>(R.id.tv_dn)
            val tvDc = view.findViewById<TextView>(R.id.tv_dc)

            tvIdS.text= list!!.id.toString()
            tvT.text=list!!.title
            tvDn.text=list!!.datestart
            tvDc.text=list!!.datefinish
        }
        return view
    }
}