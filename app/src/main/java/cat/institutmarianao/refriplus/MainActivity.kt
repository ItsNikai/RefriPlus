package cat.institutmarianao.refriplus

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
        val emailText = findViewById<TextInputEditText>(R.id.emailLayout)
        val passwordLayout = findViewById<TextInputLayout>(R.id.passwordLayout)
        val passwordText = findViewById<TextInputEditText>(R.id.passwordLayout)
        val loginButton = findViewById<Button>(R.id.login)


        loginButton.setOnClickListener {

            if (emailText.text.isNullOrEmpty()) {
                emailLayout.error = "Email is required"
            }

            if (passwordText.text.isNullOrEmpty()) {
                passwordLayout.error = "Password is required"
            }
        }

    }
}
