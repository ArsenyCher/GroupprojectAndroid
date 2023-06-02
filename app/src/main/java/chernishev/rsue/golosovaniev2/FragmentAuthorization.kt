package chernishev.rsue.golosovaniev2

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import chernishev.rsue.golosovaniev2.retrofit.Authorization
import chernishev.rsue.golosovaniev2.retrofit.ProductAPI
import chernishev.rsue.golosovaniev2.retrofit.ServerResp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FragmentAuthorization : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?, ): View? {
        val v = inflater.inflate(R.layout.fragment_authorization, container,false)
        val button = v.findViewById<Button>(R.id.button_autorization)
        val editTextTextEmailAddress = v.findViewById<TextView>(R.id.editTextTextEmailAddress)
        val editTextTextPassword = v.findViewById<TextView>(R.id.editTextTextPassword)
        val t1 = v.findViewById<TextView>(R.id.textView2)

        val retrofit = Retrofit.Builder().baseUrl("http://45.9.42.232:8080/JavaEE-1.0-SNAPSHOT/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val productApi =retrofit.create(ProductAPI::class.java)

        button.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val user= productApi.postAuthorization (
                    Authorization(
                        editTextTextPassword.text.toString(),
                        editTextTextEmailAddress.text.toString()
                    )
                )
                apply {
                    Log.d(ContentValues.TAG, user.find.toString())
                    if (user.find == true){
                        if (user.status == true) {
                            val intent = Intent(context, AdministratorActivity::class.java)
                            startActivity(intent)
                        }
                        else{
                            val intent = Intent(context, UserActivity::class.java)
                            val intentId = Intent(context, ActionActivity::class.java)
                            val result = user.id
                            intentId.putExtra("result", result.toInt())
                            Log.d(ContentValues.TAG, "id = "+ result.toString())
                            startActivity(intentId)

                            startActivity(intent)
                        }
                    }
                    else
                        t1.text= user.find.toString()
                }
            }
        }
        return v
        }
}