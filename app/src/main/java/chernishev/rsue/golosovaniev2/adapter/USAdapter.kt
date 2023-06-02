package chernishev.rsue.golosovaniev2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import chernishev.rsue.golosovaniev2.R
import chernishev.rsue.golosovaniev2.retrofit.user.User

class USAdapter(ctx:Context, list: List<User>):ArrayAdapter<User>(ctx, 0, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return myView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return myView(position, convertView, parent)
    }
    private fun myView(position: Int, convertView: View?, parent: ViewGroup): View {
        val list = getItem(position)
        val view = convertView?: LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        list.let {
            val tvIdU = view.findViewById<TextView>(R.id.tv_id_u)
            val tvLn = view.findViewById<TextView>(R.id.tv_ln)
            val tvP = view.findViewById<TextView>(R.id.tv_p)
            val tvE = view.findViewById<TextView>(R.id.tv_e)
            //val tvS = view.findViewById<TextView>(R.id.tv_s)

            tvIdU.text = list!!.id.toString()
            tvLn.text= list.fio
            tvP.text=list.phone
            tvE.text=list.email
            //tvS.text=list.status
        }
        return view
    }
}