package chernishev.rsue.golosovaniev2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import chernishev.rsue.golosovaniev2.retrofit.user.EditUserRequest
import chernishev.rsue.golosovaniev2.retrofit.ProductAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EditUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user)
        val retrofit = Retrofit.Builder().baseUrl("http://45.9.42.232:8080/JavaEE-1.0-SNAPSHOT/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val productApi =retrofit.create(ProductAPI::class.java)

        val edit_b_u = findViewById<Button>(R.id.button_edit_u)
        val edit_id = findViewById<TextView>(R.id.edit_id)
        val edit_fn = findViewById<TextView>(R.id.edit_fn)
        val edit_ln = findViewById<TextView>(R.id.edit_ln)
        val edit_p = findViewById<TextView>(R.id.edit_p)
        val edit_e = findViewById<TextView>(R.id.edit_e)
        val edit_s = findViewById<TextView>(R.id.edit_s)

        edit_b_u.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                val euser= productApi.postModUser (
                    EditUserRequest(
                        edit_id.text.toString(),
                        edit_fn.text.toString(),
                        edit_ln.text.toString(),
                        edit_p.text.toString(),
                        edit_e.text.toString(),
                        edit_s.text.toString()
                    )
                )
            }
        }
    }
}