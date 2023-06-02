package chernishev.rsue.golosovaniev2.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import chernishev.rsue.golosovaniev2.ActionActivity
import chernishev.rsue.golosovaniev2.R
import chernishev.rsue.golosovaniev2.adapter.TAdapter
import chernishev.rsue.golosovaniev2.databinding.FragmentTitleBinding
import chernishev.rsue.golosovaniev2.retrofit.ProductAPI
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TitleFragment : Fragment() {
    private lateinit var adapter: TAdapter
    lateinit var binding: FragmentTitleBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentTitleBinding.inflate(inflater, container,false)

        binding = FragmentTitleBinding.inflate(layoutInflater)
        adapter=TAdapter()
        binding.rct.layoutManager = LinearLayoutManager(activity)
        binding.rct.adapter=adapter

        val buttonEdit = binding.root.findViewById<FloatingActionButton>(R.id.floatingActionButton2)
        val buttonDelete = binding.root.findViewById<FloatingActionButton>(R.id.floatingActionButton4)
        val buttonAdd = binding.root.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        val buttonRe = binding.root.findViewById<FloatingActionButton>(R.id.floatingActionButton3)


        buttonEdit.setOnClickListener{
            val index = 5
            val intent = Intent(activity, ActionActivity::class.java)
            intent.putExtra("index", index)
            startActivity(intent)
        }

        buttonDelete.setOnClickListener{
            val index = 6
            val intent = Intent(activity, ActionActivity::class.java)
            intent.putExtra("index", index)
            startActivity(intent)
        }

        buttonAdd.setOnClickListener{
            val index = 7
            val intent = Intent(activity, ActionActivity::class.java)
            intent.putExtra("index", index)
            startActivity(intent)
        }

        buttonRe.setOnClickListener {
            val retrofit =
                Retrofit.Builder().baseUrl("http://45.9.42.232:8080/JavaEE-1.0-SNAPSHOT/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            val productApi = retrofit.create(ProductAPI::class.java)

            CoroutineScope(Dispatchers.IO).launch {
                val list = productApi.getTitles()
                activity?.runOnUiThread {
                    binding.apply {
                        adapter.submitList(list.votes)
                    }
                }
            }
        }

        return binding.root
    }
}