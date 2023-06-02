package chernishev.rsue.golosovaniev2.ui.users

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import chernishev.rsue.golosovaniev2.ActionActivity
import chernishev.rsue.golosovaniev2.AdministratorActivity
import chernishev.rsue.golosovaniev2.R
import chernishev.rsue.golosovaniev2.adapter.CAdapter
import chernishev.rsue.golosovaniev2.adapter.UAdapter
import chernishev.rsue.golosovaniev2.databinding.ActivityUserBinding
import chernishev.rsue.golosovaniev2.databinding.FragmentUserBinding
import chernishev.rsue.golosovaniev2.retrofit.ProductAPI
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserFragment : Fragment() {

    private lateinit var adapter: CAdapter
    lateinit var binding: FragmentUserBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        /*val viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        lateinit var binding: ActivityUserBinding*/
        binding = FragmentUserBinding.inflate(inflater, container,false)
        /*recyclerView=v.findViewById<RecyclerView>(R.id.rc)
        adapter=HomeAdapter()
        recyclerView.adapter=adapter
        viewModel.getUsers()
        viewModel.myUserList.observe(this, {list ->
            list.body()?.let {adapter.setList(it)}
        })*/
        /*binding.floatingActionButton.setOnClickListener{
            val intent = Intent(getContext(), ActionActivity::class.java)
            startActivity(intent)
        }*/

        binding = FragmentUserBinding.inflate(layoutInflater)
        //setContentView(binding.root)
        adapter= CAdapter()
        binding.rc.layoutManager = LinearLayoutManager(getActivity())
        binding.rc.adapter=adapter

        /*val buttonEdit = binding.root.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        val buttonDelete = binding.root.findViewById<FloatingActionButton>(R.id.floatingActionButton4)


        buttonEdit.setOnClickListener{
            val index = 0
            val intent = Intent(activity, ActionActivity::class.java)
            intent.putExtra("index", index)
            startActivity(intent)
        }

        buttonDelete.setOnClickListener{
            val index = 1
            val intent = Intent(activity, ActionActivity::class.java)
            intent.putExtra("index", index)
            startActivity(intent)
        }*/

        val buttonRe = binding.root.findViewById<FloatingActionButton>(R.id.floatingActionButton3)

        buttonRe.setOnClickListener {
            val retrofit =
                Retrofit.Builder().baseUrl("http://45.9.42.232:8080/JavaEE-1.0-SNAPSHOT/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            val productApi = retrofit.create(ProductAPI::class.java)

            CoroutineScope(Dispatchers.IO).launch {
                val list = productApi.getChoices()
                getActivity()?.runOnUiThread {
                    binding.apply {
                        adapter.submitList(list.choices)
                    }
                }
            }
        }

        return binding.root
    }
}