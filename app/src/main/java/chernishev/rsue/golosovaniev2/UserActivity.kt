package chernishev.rsue.golosovaniev2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import chernishev.rsue.golosovaniev2.adapter.QAdapter
import chernishev.rsue.golosovaniev2.databinding.ActivityUserBinding
import chernishev.rsue.golosovaniev2.retrofit.ProductAPI
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserActivity : AppCompatActivity() {
    private lateinit var adapter: QAdapter
    lateinit var binding: ActivityUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter=QAdapter()
        binding.rc.layoutManager = LinearLayoutManager(this)
        binding.rc.adapter=adapter

        val button = findViewById<FloatingActionButton>(R.id.floatingActionButton)


        button.setOnClickListener{
            val index = 8
            val intent = Intent(this, ActionActivity::class.java)
            intent.putExtra("index", index)
            startActivity(intent)
        }

        val retrofit = Retrofit.Builder().baseUrl("http://45.9.42.232:8080/JavaEE-1.0-SNAPSHOT/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val productApi =retrofit.create(ProductAPI::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val list = productApi.getQuestions()
            runOnUiThread{
                binding.apply {
                    adapter.submitList(list.questions)
                }
            }
        }
    }

    override fun onResume(){
        super.onResume()
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter=QAdapter()
        binding.rc.layoutManager = LinearLayoutManager(this)
        binding.rc.adapter=adapter

        val button = findViewById<FloatingActionButton>(R.id.floatingActionButton)


        button.setOnClickListener{
            val index = 8
            val intent = Intent(this, ActionActivity::class.java)
            intent.putExtra("index", index)
            startActivity(intent)
        }

        val retrofit = Retrofit.Builder().baseUrl("http://45.9.42.232:8080/JavaEE-1.0-SNAPSHOT/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val productApi =retrofit.create(ProductAPI::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val list = productApi.getQuestions()
            runOnUiThread {
                binding.apply {
                    adapter.submitList(list.questions)
                }
            }
        }
    }
}