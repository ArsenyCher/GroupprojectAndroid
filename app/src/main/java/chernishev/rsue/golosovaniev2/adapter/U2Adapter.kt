package chernishev.rsue.golosovaniev2.adapter

/*class U2Adapter:RecyclerView.Adapter<U2Adapter.StartViewHolder >() {
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
