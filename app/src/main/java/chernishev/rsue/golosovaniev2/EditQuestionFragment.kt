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
import chernishev.rsue.golosovaniev2.retrofit.IdRequest
import chernishev.rsue.golosovaniev2.retrofit.ProductAPI
import chernishev.rsue.golosovaniev2.retrofit.question.EditQuestionRequest
import chernishev.rsue.golosovaniev2.retrofit.user.EditUserRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.regex.Pattern

class EditQuestionFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val v = inflater.inflate(R.layout.fragment_edit_question, container,false)

        val retrofit = Retrofit.Builder().baseUrl("http://45.9.42.232:8080/JavaEE-1.0-SNAPSHOT/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val productApi =retrofit.create(ProductAPI::class.java)

        val spinner = v.findViewById<Spinner>(R.id.spin)
        val spinnerVote = v.findViewById<Spinner>(R.id.spinVote)
        val button_edit = v.findViewById<Button>(R.id.button_edit)
        val button_write = v.findViewById<Button>(R.id.button_write)
        val editTextTextContent = v.findViewById<TextView>(R.id.editTextTextContent)
        val editTextTextDateVote = v.findViewById<TextView>(R.id.editTextTextDateVote)

        CoroutineScope(Dispatchers.IO).launch {
            val list = productApi.getQuestions()
            getActivity()?.runOnUiThread{
                apply {
                    Log.d(ContentValues.TAG, list.questions.toString())
                    var array = list.questions.get(0).toString()
                    var array2 = list.questions.elementAt(0)
                    Log.d(ContentValues.TAG, array.toString())
                    Log.d(ContentValues.TAG, array2.toString())

                    getActivity()?.runOnUiThread {
                        val adapter = getContext()?.let { QSAdapter(it, list.questions) }
                        spinner.adapter = adapter
                        val userSpin = spinner.selectedItem
                    }
                }
            }
        }

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

        button_write.setOnClickListener{
            val userSpin = spinner.selectedItem


            val idS = arrayListOf<String>()
            val pat= Pattern.compile("[-]?[0-9]+(.[0-9]+)?")
            val findId=pat.matcher(userSpin.toString())
            while (findId.find()) {
                Log.d(ContentValues.TAG, findId.group())
                idS.add(findId.group())
            }
            Log.d(ContentValues.TAG, idS[0])
            Log.d(ContentValues.TAG, idS[0])
            CoroutineScope(Dispatchers.IO).launch {
                val question= productApi.postQuestionWrite (
                    IdRequest(
                        id = idS[0]
                    )
                )
                apply{
                    //spinnerVote.setSelection(question.voteid.toInt()-1)
                    editTextTextDateVote.text = question.datevote
                    editTextTextContent.text = question.content
                }
            }
        }

        button_edit.setOnClickListener {
            val userSpin = spinner.selectedItem

            val idS = arrayListOf<String>()
            val pat = Pattern.compile("[-]?[0-9]+(.[0-9]+)?")
            val findId = pat.matcher(userSpin.toString())
            while (findId.find()) {
                idS.add(findId.group())
            }

            val titleSpin = spinnerVote.selectedItem

            val idSV = arrayListOf<String>()
            val findIdV = pat.matcher(titleSpin.toString())
            while (findIdV.find()) {
                idSV.add(findIdV.group())
                CoroutineScope(Dispatchers.IO).launch {
                    val question = productApi.postModQuestion(
                        EditQuestionRequest(
                            id = idS[0],
                            voteid = idSV[0],
                            editTextTextContent.text.toString(),
                            editTextTextDateVote.text.toString()
                        )
                    )
                }
            }
        }
        return v
    }
}