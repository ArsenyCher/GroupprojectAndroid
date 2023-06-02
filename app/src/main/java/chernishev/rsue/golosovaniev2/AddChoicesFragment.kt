package chernishev.rsue.golosovaniev2

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import chernishev.rsue.golosovaniev2.adapter.QSAdapter
import chernishev.rsue.golosovaniev2.retrofit.AddChoiceRequest
import chernishev.rsue.golosovaniev2.retrofit.ProductAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.regex.Pattern

class AddChoicesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val v = inflater.inflate(R.layout.fragment_add_choices, container,false)

        val retrofit = Retrofit.Builder().baseUrl("http://45.9.42.232:8080/JavaEE-1.0-SNAPSHOT/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val productApi =retrofit.create(ProductAPI::class.java)

        val spinner = v.findViewById<Spinner>(R.id.spinner)
        val button_add = v.findViewById<Button>(R.id.button_edit)
        val editTextTextTitle = v.findViewById<TextView>(R.id.editTextTextTitle)

        CoroutineScope(Dispatchers.IO).launch {
            val list = productApi.getQuestions()
            getActivity()?.runOnUiThread{
                apply {
                    Log.d(ContentValues.TAG, list.questions.toString())

                    getActivity()?.runOnUiThread {
                        val adapter = getContext()?.let { QSAdapter(it, list.questions) }
                        spinner.adapter = adapter
                    }
                }
            }
        }

        button_add.setOnClickListener{
            val userSpin = spinner.selectedItem

            val idS = arrayListOf<String>()
            val pat = Pattern.compile("[-]?[0-9]+(.[0-9]+)?")
            val findId = pat.matcher(userSpin.toString())
            while (findId.find()) {
                idS.add(findId.group())
            }
            val args = arguments
            //val index = args!!.getInt("result", 0)
            CoroutineScope(Dispatchers.IO).launch {
                val title= productApi.postChoise (
                    AddChoiceRequest(
                        questionid = idS[0],
                        userid = "1",
                        editTextTextTitle.text.toString()
                    )
                )
            }
        }
        return v
    }
}