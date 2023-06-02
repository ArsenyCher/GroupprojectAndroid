package chernishev.rsue.golosovaniev2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var button_reg_auth:Boolean = true
        val add_b_r_a = findViewById<Button>(R.id.button_registration_autorization)

        showAuth("Зарегистрироваться", "Еще не зарегистрированны?")

        add_b_r_a.setOnClickListener{
            if (button_reg_auth){
                showAuth("Зарегистрироваться", "Еще не зарегистрированны?")
                button_reg_auth = false
            }else{
                showReg("Авторизироваться", "Уже зарегистрированны?")
                button_reg_auth  = true
            }
        }
    }
    fun showAuth(BText:String, IText:String){
        val add_b_r_a = findViewById<Button>(R.id.button_registration_autorization)
        val text_reg_auth = findViewById<TextView>(R.id.text_reg_auth)
        val f_t = supportFragmentManager.beginTransaction()
        f_t.replace(R.id.container, FragmentAuthorization())
        f_t.commit()
        add_b_r_a.text=BText
        text_reg_auth.text=IText
    }

    fun showReg(BText:String, IText:String){
        val add_b_r_a = findViewById<Button>(R.id.button_registration_autorization)
        val text_reg_auth = findViewById<TextView>(R.id.text_reg_auth)
        val f_t = supportFragmentManager.beginTransaction()
        f_t.replace(R.id.container, FragmentRegistration())
        f_t.commit()
        add_b_r_a.text=BText
        text_reg_auth.text=IText
    }
}