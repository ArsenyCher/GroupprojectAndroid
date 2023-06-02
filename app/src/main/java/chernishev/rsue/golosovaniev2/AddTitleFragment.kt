package chernishev.rsue.golosovaniev2

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import chernishev.rsue.golosovaniev2.retrofit.ProductAPI
import chernishev.rsue.golosovaniev2.retrofit.title.AddTitleRequest
import chernishev.rsue.golosovaniev2.retrofit.title.EditTitleRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.regex.Pattern

class AddTitleFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val v = inflater.inflate(R.layout.fragment_add_title, container,false)

        val editTextTextTitle = v.findViewById<TextView>(R.id.editTextTextTitle)
        val editTextTextDateStart = v.findViewById<TextView>(R.id.editTextTextDateStart)
        val editDateFinish = v.findViewById<TextView>(R.id.editDateFinish)
        val button_edit = v.findViewById<Button>(R.id.button_edit)

        val retrofit = Retrofit.Builder().baseUrl("http://45.9.42.232:8080/JavaEE-1.0-SNAPSHOT/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val productApi =retrofit.create(ProductAPI::class.java)

        button_edit.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                val title= productApi.postTitle (
                    AddTitleRequest(
                        editTextTextTitle.text.toString(),
                        editTextTextDateStart.text.toString(),
                        editDateFinish.text.toString(),
                        status = "true"
                    )
                )
            }
        }
        return v
    }
}