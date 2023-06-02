package chernishev.rsue.golosovaniev2.ui.dashboard

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import chernishev.rsue.golosovaniev2.ActionActivity
import chernishev.rsue.golosovaniev2.R
import chernishev.rsue.golosovaniev2.adapter.QAdapter
import chernishev.rsue.golosovaniev2.databinding.FragmentQuestionBinding
import chernishev.rsue.golosovaniev2.retrofit.ProductAPI
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuestionFragment : Fragment() {
    private lateinit var adapter: QAdapter
    lateinit var binding: FragmentQuestionBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentQuestionBinding.inflate(inflater, container,false)
        binding = FragmentQuestionBinding.inflate(layoutInflater)
        adapter=QAdapter()
        binding.rcq.layoutManager = LinearLayoutManager(getActivity())
        binding.rcq.adapter=adapter


        val buttonEdit = binding.root.findViewById<FloatingActionButton>(R.id.floatingActionButton2)
        val buttonDelete = binding.root.findViewById<FloatingActionButton>(R.id.floatingActionButton4)
        val buttonAdd = binding.root.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        val buttonRe = binding.root.findViewById<FloatingActionButton>(R.id.floatingActionButton3)


        buttonEdit.setOnClickListener{
            val index = 2
            val intent = Intent(activity, ActionActivity::class.java)
            intent.putExtra("index", index)
            startActivity(intent)
        }

        buttonDelete.setOnClickListener{
            val index = 3
            val intent = Intent(activity, ActionActivity::class.java)
            intent.putExtra("index", index)
            startActivity(intent)
        }

        buttonAdd.setOnClickListener{
            val index = 4
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
                val list = productApi.getQuestions()
                getActivity()?.runOnUiThread {
                    binding.apply {
                        adapter.submitList(list.questions)
                    }
                }
            }
        }

        return binding.root
    }

}