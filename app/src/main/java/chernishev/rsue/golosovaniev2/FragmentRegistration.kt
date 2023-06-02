package chernishev.rsue.golosovaniev2

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import chernishev.rsue.golosovaniev2.retrofit.user.AddUserRequest
import chernishev.rsue.golosovaniev2.retrofit.ProductAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FragmentRegistration : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val v = inflater.inflate(R.layout.fragment_registration, container,false)
        val button_registration = v.findViewById<Button>(R.id.button_registration)
        val t = v.findViewById<TextView>(R.id.textView)
        val editTextTextEmailAddress = v.findViewById<TextView>(R.id.editTextTextEmailAddress)
        val editTextTextPersonName = v.findViewById<TextView>(R.id.editTextTextPersonName)
        val editTextPhone = v.findViewById<TextView>(R.id.editTextPhone)
        val editTextTextPassword = v.findViewById<TextView>(R.id.editTextTextPassword)

        val retrofit = Retrofit.Builder().baseUrl("http://45.9.42.232:8080/JavaEE-1.0-SNAPSHOT/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val productApi =retrofit.create(ProductAPI::class.java)

        button_registration.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                val user= productApi.postUser (
                    AddUserRequest(
                        editTextTextPersonName.text.toString(),
                        editTextTextPassword.text.toString(),
                        editTextPhone.text.toString(),
                        editTextTextEmailAddress.text.toString()
                    )
                )
            }
        }
        return v
    }
}
