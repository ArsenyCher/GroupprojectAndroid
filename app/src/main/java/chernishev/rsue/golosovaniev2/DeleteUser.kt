package chernishev.rsue.golosovaniev2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import chernishev.rsue.golosovaniev2.retrofit.user.DeleteUserRequest
import chernishev.rsue.golosovaniev2.retrofit.ProductAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DeleteUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_user)
        val retrofit = Retrofit.Builder().baseUrl("http://45.9.42.232:8080/JavaEE-1.0-SNAPSHOT/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val productApi =retrofit.create(ProductAPI::class.java)

        val edit_b_u = findViewById<Button>(R.id.button_delete_u)
        val edit_id = findViewById<TextView>(R.id.edit_id)

        edit_b_u.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                val duser= productApi.postDelUser (
                    DeleteUserRequest(
                        edit_id.text.toString()
                    )
                )
            }
        }
    }
}