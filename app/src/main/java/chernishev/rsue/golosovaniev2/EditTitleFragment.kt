package chernishev.rsue.golosovaniev2

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import chernishev.rsue.golosovaniev2.adapter.TSAdapter
import chernishev.rsue.golosovaniev2.adapter.USAdapter
import chernishev.rsue.golosovaniev2.retrofit.IdRequest
import chernishev.rsue.golosovaniev2.retrofit.ProductAPI
import chernishev.rsue.golosovaniev2.retrofit.title.EditTitleRequest
import chernishev.rsue.golosovaniev2.retrofit.user.EditUserRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.regex.Pattern

class EditTitleFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val v = inflater.inflate(R.layout.fragment_edit_title, container,false)

        val retrofit = Retrofit.Builder().baseUrl("http://45.9.42.232:8080/JavaEE-1.0-SNAPSHOT/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val productApi =retrofit.create(ProductAPI::class.java)

        val spinner = v.findViewById<Spinner>(R.id.spin)
        val button_edit = v.findViewById<Button>(R.id.button_edit)
        val button_write = v.findViewById<Button>(R.id.button_write)
        val editTextTextTitle = v.findViewById<TextView>(R.id.editTextTextTitle)
        val editTextTextDateStart = v.findViewById<TextView>(R.id.editTextTextDateStart)
        val editDateFinish = v.findViewById<TextView>(R.id.editDateFinish)

        CoroutineScope(Dispatchers.IO).launch {
            val list = productApi.getTitles()
            getActivity()?.runOnUiThread{
                apply {
                    Log.d(ContentValues.TAG, list.votes.toString())

                    getActivity()?.runOnUiThread {
                        val adapter = getContext()?.let { TSAdapter(it, list.votes) }
                        spinner.adapter = adapter
                        val userSpin = spinner.selectedItem
                    }
                }
            }
        }

        button_write.setOnClickListener{
            val userSpin = spinner.selectedItem

            val idS = arrayListOf<String>()
            val pat= Pattern.compile("[-]?[0-9]+(.[0-9]+)?")
            val findId=pat.matcher(userSpin.toString())
            while (findId.find()) {
                idS.add(findId.group())
            }
            Log.d(ContentValues.TAG, idS[0])
            CoroutineScope(Dispatchers.IO).launch {
                val title= productApi.postTitleWrite (
                    IdRequest(
                        id = idS[0]
                    )
                )
                idS.clear()
                apply{
                    editTextTextTitle.text= title.title
                    editTextTextDateStart.text = title.datestart
                    editDateFinish.text = title.datefinish
                }
            }
        }

        button_edit.setOnClickListener{
            val userSpin = spinner.selectedItem

            val idS = arrayListOf<String>()
            val pat= Pattern.compile("[-]?[0-9]+(.[0-9]+)?")
            val findId=pat.matcher(userSpin.toString())
            while (findId.find()) {
                Log.d(ContentValues.TAG, findId.group())
                idS.add(findId.group())
            }
            Log.d(ContentValues.TAG, idS[0])
            CoroutineScope(Dispatchers.IO).launch {
                val title= productApi.postModTitle (
                    EditTitleRequest(
                        id = idS[0],
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