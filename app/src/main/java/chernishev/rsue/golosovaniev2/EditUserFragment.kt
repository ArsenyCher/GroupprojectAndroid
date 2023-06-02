package chernishev.rsue.golosovaniev2

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import chernishev.rsue.golosovaniev2.adapter.USAdapter
import chernishev.rsue.golosovaniev2.retrofit.*
import chernishev.rsue.golosovaniev2.retrofit.user.EditUserRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.regex.*;

class EditUserFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val v = inflater.inflate(R.layout.fragment_edit_user, container,false)

        val retrofit = Retrofit.Builder().baseUrl("http://45.9.42.232:8080/JavaEE-1.0-SNAPSHOT/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val productApi =retrofit.create(ProductAPI::class.java)

        val spinner = v.findViewById<Spinner>(R.id.spin)
        val button_edit = v.findViewById<Button>(R.id.button_edit)
        val button_write = v.findViewById<Button>(R.id.button_write)
        val editTextTextEmailAddress = v.findViewById<TextView>(R.id.editTextTextEmailAddress)
        val editTextTextPersonName = v.findViewById<TextView>(R.id.editTextTextPersonName)
        val editTextPhone = v.findViewById<TextView>(R.id.editTextPhone)
        val editTextTextPassword = v.findViewById<TextView>(R.id.editTextTextPassword)
        val editTextTextStatus = v.findViewById<TextView>(R.id.editTextTextStatus)

        CoroutineScope(Dispatchers.IO).launch {
            val list = productApi.getUsers()
            getActivity()?.runOnUiThread{
                apply {
                    Log.d(TAG, list.users.toString())
                    var array = list.users.get(1).toString()
                    var array2 = list.users.elementAt(1)
                    Log.d(TAG, array.toString())
                    Log.d(TAG, array2.toString())

                    getActivity()?.runOnUiThread {
                        val adapter = getContext()?.let { USAdapter(it, list.users) }
                        spinner.adapter = adapter
                        val userSpin = spinner.selectedItem
                    }
                }
            }
        }

        button_write.setOnClickListener{
            val userSpin = spinner.selectedItem

            val idS = arrayListOf<String>()
            val pat=Pattern.compile("[-]?[0-9]+(.[0-9]+)?")
            val findId=pat.matcher(userSpin.toString())
            while (findId.find()) {
                Log.d(TAG, findId.group())
                idS.add(findId.group())
            }
            Log.d(TAG, idS[0])
            CoroutineScope(Dispatchers.IO).launch {
                val user= productApi.postUserWrite (
                    IdRequest(
                        id = idS[0]
                    )
                )
                apply{
                    editTextTextEmailAddress.text= user.email
                    editTextTextPersonName.text = user.fio
                    editTextTextPassword.text = user.password
                    editTextPhone.text = user.phone
                    editTextTextStatus.text= user.status
                }
            }
        }

        button_edit.setOnClickListener{
            val userSpin = spinner.selectedItem

            val idS = arrayListOf<String>()
            val pat=Pattern.compile("[-]?[0-9]+(.[0-9]+)?")
            val findId=pat.matcher(userSpin.toString())
            while (findId.find()) {
                Log.d(TAG, findId.group())
                idS.add(findId.group())
            }
            Log.d(TAG, idS[0])
            CoroutineScope(Dispatchers.IO).launch {
                val user= productApi.postModUser (
                    EditUserRequest(
                        id = idS[0],
                        editTextTextPersonName.text.toString(),
                        editTextTextPassword.text.toString(),
                        editTextPhone.text.toString(),
                        editTextTextEmailAddress.text.toString(),
                        status = "false"
                    )
                )
            }
        }
        return v
    }
}