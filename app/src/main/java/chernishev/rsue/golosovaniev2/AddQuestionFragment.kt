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
import chernishev.rsue.golosovaniev2.adapter.QSAdapter
import chernishev.rsue.golosovaniev2.adapter.TSAdapter
import chernishev.rsue.golosovaniev2.retrofit.ProductAPI
import chernishev.rsue.golosovaniev2.retrofit.question.AddQuestionRequest
import chernishev.rsue.golosovaniev2.retrofit.title.AddTitleRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.regex.Pattern

class AddQuestionFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val v = inflater.inflate(R.layout.fragment_add_question, container,false)

        val editTextTextTitle = v.findViewById<TextView>(R.id.editTextTextTitle)
        val editDateFinish = v.findViewById<TextView>(R.id.editDateFinish)
        val button_edit = v.findViewById<Button>(R.id.button_edit)
        val spinnerVote = v.findViewById<Spinner>(R.id.spinVote)

        val retrofit = Retrofit.Builder().baseUrl("http://45.9.42.232:8080/JavaEE-1.0-SNAPSHOT/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val productApi =retrofit.create(ProductAPI::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val list = productApi.getTitles()
            getActivity()?.runOnUiThread{
                apply {
                    Log.d(ContentValues.TAG, list.votes.toString())

                    getActivity()?.runOnUiThread {
                        val adapter = getContext()?.let { TSAdapter(it, list.votes) }
                        spinnerVote.adapter = adapter
                        val userSpin = spinnerVote.selectedItem
                    }
                }
            }
        }


        button_edit.setOnClickListener{
            val userSpin = spinnerVote.selectedItem

            val idS = arrayListOf<String>()
            val pat = Pattern.compile("[-]?[0-9]+(.[0-9]+)?")
            val findId = pat.matcher(userSpin.toString())
            while (findId.find()) {
                idS.add(findId.group())
            }
            CoroutineScope(Dispatchers.IO).launch {
                val title= productApi.postQuestion (
                    AddQuestionRequest(
                        voteid = idS[0],
                        editTextTextTitle.text.toString(),
                        editDateFinish.text.toString()
                    )
                )
            }
        }
        return v
    }
}