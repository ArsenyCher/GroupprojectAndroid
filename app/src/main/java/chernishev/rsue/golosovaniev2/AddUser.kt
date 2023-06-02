package chernishev.rsue.golosovaniev2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import chernishev.rsue.golosovaniev2.retrofit.user.AddUserRequest
import chernishev.rsue.golosovaniev2.retrofit.ProductAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)
        val retrofit = Retrofit.Builder().baseUrl("http://45.9.42.232:8080/JavaEE-1.0-SNAPSHOT/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val productApi =retrofit.create(ProductAPI::class.java)

        val add_b_u = findViewById<Button>(R.id.button_add_u)
        val add_fn = findViewById<TextView>(R.id.edit_fn)
        val add_ln = findViewById<TextView>(R.id.edit_ln)
        val add_p = findViewById<TextView>(R.id.edit_p)
        val add_e = findViewById<TextView>(R.id.edit_e)
        val add_s = findViewById<TextView>(R.id.edit_s)

        add_b_u.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                val user= productApi.postUser (
                    AddUserRequest(
                        add_fn.text.toString(),
                        add_ln.text.toString(),
                        add_p.text.toString(),
                        add_e.text.toString()
                )
                )
            }
        }
    }
}