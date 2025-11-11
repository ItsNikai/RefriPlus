package cat.institutmarianao.refriplus

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
class MainActivity : AppCompatActivity() {
    private lateinit var errorText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val emailLayout = findViewById<TextInputLayout>(R.id.emailLayout)
        val passwordLayout = findViewById<TextInputLayout>(R.id.passwordLayout)
        val emailText = findViewById<TextInputEditText>(R.id.email)
        val passwordText = findViewById<TextInputEditText>(R.id.password)
        val loginButton = findViewById<Button>(R.id.login)
        val goSingUp = findViewById<Button>(R.id.singUp)

        errorText = findViewById(R.id.errorText)

        loginButton.setOnClickListener {

            val email = emailText.text
            val password = passwordText.text

            if (emailText.text.isNullOrEmpty()) {
                emailLayout.error = "Email is required"
            }

            if (passwordText.text.isNullOrEmpty()) {
                passwordLayout.error = "Password is required"
            }

            loginForm(emailText, passwordText)

        }

        goSingUp.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

    }

    private fun loginForm(emailEditText: EditText, passwordEditText: EditText) {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        login(email, password)
    }

    private fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(
                this, "Email and password required", Toast.LENGTH_SHORT
            ).show()
            return
        }

        // * Firebase login * //
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this,
                        "Welcome ${FirebaseAuth.getInstance().currentUser?.email}",
                        Toast.LENGTH_SHORT
                    ).show()
                    val clientsActivity = Intent(this, AIFridge::class.java)
                    startActivity(clientsActivity)
                } else {
                    val errorMessage = task.exception?.message ?: "An unknown error occurred."
                    Toast.makeText(this, "Error: $errorMessage", Toast.LENGTH_SHORT).show()

                    // NUEVO: Muestra el error en el TextView
                    errorText.text = errorMessage
                }
            }
    }
}
