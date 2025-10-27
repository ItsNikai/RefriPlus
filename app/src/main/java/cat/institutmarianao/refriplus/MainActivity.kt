package cat.institutmarianao.refriplus

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val emailLayout = findViewById<TextInputLayout>(R.id.emailLayout)
        val passwordLayout = findViewById<TextInputLayout>(R.id.passwordLayout)
        val emailText = findViewById<TextInputEditText>(R.id.email)
        val passwordText = findViewById<TextInputEditText>(R.id.password)
        val loginButton = findViewById<Button>(R.id.login)
        val goSingUp = findViewById<Button>(R.id.singUp)


        loginButton.setOnClickListener {

            if (emailText.text.isNullOrEmpty()) {
                emailLayout.error = "Email is required"
            }

            if (passwordText.text.isNullOrEmpty()) {
                passwordLayout.error = "Password is required"
            }
        }

        goSingUp.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

    }
}
