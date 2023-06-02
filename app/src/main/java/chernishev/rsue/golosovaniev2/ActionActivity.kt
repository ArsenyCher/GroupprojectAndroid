package chernishev.rsue.golosovaniev2

import android.content.ContentValues
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class ActionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action)

        val f_t = supportFragmentManager.beginTransaction()
        val index = intent.getIntExtra("index", 8)
        when (index){
            0 -> {
                f_t.replace(R.id.con, EditUserFragment())
                f_t.commit()
                Log.d(TAG, index.toString())}

            1 -> {
                f_t.replace(R.id.con, DeleteUserFragment())
                f_t.commit()
                Log.d(TAG, index.toString())}

            2 -> {
                f_t.replace(R.id.con, EditQuestionFragment())
                f_t.commit()
                Log.d(TAG, index.toString())}

            3 -> {
                f_t.replace(R.id.con, DeleteQuestionFragment())
                f_t.commit()
                Log.d(TAG, index.toString())}

            4 -> {
                f_t.replace(R.id.con, AddQuestionFragment())
                f_t.commit()
                Log.d(TAG, index.toString())}

            5 -> {
                f_t.replace(R.id.con, EditTitleFragment())
                f_t.commit()
                Log.d(TAG, index.toString())}

            6 -> {
                f_t.replace(R.id.con, DeleteTitleFragment())
                f_t.commit()
                Log.d(TAG, index.toString())}

            7 -> {
                f_t.replace(R.id.con, AddTitleFragment())
                f_t.commit()
                Log.d(TAG, index.toString())}

            8 -> {
                val result = intent.getIntExtra("result", 0)
                newInstance(result)
                Log.d(ContentValues.TAG, "id3 = "+ result.toString())
                f_t.replace(R.id.con, AddChoicesFragment())
                f_t.commit()
                Log.d(TAG, index.toString())}

            /*9 -> {
                f_t.replace(R.id.con, DeleteChoicesFragment())
                f_t.commit()
                Log.d(TAG, index.toString())}

            10 -> {
                f_t.replace(R.id.con, AddChoicesFragment())
                f_t.commit()
                Log.d(TAG, index.toString())}*/

        }
    }
    fun newInstance(result: Int): AddChoicesFragment? {
        val f = AddChoicesFragment()
        // Supply index input as an argument.
        val args = Bundle()
        args.putInt("result", result)
        f.setArguments(args)
        return f
    }
}