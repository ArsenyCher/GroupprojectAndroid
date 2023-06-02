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
import chernishev.rsue.golosovaniev2.adapter.QSAdapter
import chernishev.rsue.golosovaniev2.adapter.USAdapter
import chernishev.rsue.golosovaniev2.retrofit.ProductAPI
import chernishev.rsue.golosovaniev2.retrofit.question.DeleteQuestionRequest
import chernishev.rsue.golosovaniev2.retrofit.user.DeleteUserRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.regex.Pattern

class DeleteQuestionFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val v = inflater.inflate(R.layout.fragment_delete_user, container,false)

        val retrofit = Retrofit.Builder().baseUrl("http://45.9.42.232:8080/JavaEE-1.0-SNAPSHOT/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val productApi =retrofit.create(ProductAPI::class.java)

        val spinner = v.findViewById<Spinner>(R.id.spin)
        val button_delete = v.findViewById<Button>(R.id.button_delete)

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

        button_delete.setOnClickListener{
            val userSpin = spinner.selectedItem

            val idS = arrayListOf<String>()
            val pat= Pattern.compile("[-]?[0-9]+(.[0-9]+)?")
            val findId=pat.matcher(userSpin.toString())
            while (findId.find()) {
                idS.add(findId.group())
            }
            CoroutineScope(Dispatchers.IO).launch {
                val question= productApi.postDelQuestion (
                    DeleteQuestionRequest(
                        id = idS[0]
                    )
                )
            }
        }
        return v
    }
}