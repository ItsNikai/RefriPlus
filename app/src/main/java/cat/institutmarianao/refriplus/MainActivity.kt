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
    private lateinit var emailText: TextInputEditText
    private lateinit var passwordText: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO Chek the code to avoid spaghetti code (Important)

        val loginButton = findViewById<Button>(R.id.login)
        val goSingUp = findViewById<Button>(R.id.singUp)

        errorText = findViewById(R.id.errorText)

        loginButton.setOnClickListener {
            emailText = findViewById(R.id.email)
            passwordText = findViewById(R.id.password)

            // Check if the form is not empty and do the login
            if (checkForm(emailText, passwordText)) {
                loginForm(emailText, passwordText)
            }
        }

        goSingUp.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

    }

    /**
     * Check if the form is not empty
     *
     * @param emailEditText
     * @param passwordEditText
     * @return true if the form is not empty, false otherwise
     */
    private fun checkForm(emailEditText: EditText, passwordEditText: EditText): Boolean {
        val emailLayout = findViewById<TextInputLayout>(R.id.emailLayout)
        val passwordLayout = findViewById<TextInputLayout>(R.id.passwordLayout)

        if (emailText.text.isNullOrEmpty()) {
            emailLayout.error = "Email is required"
            return false
        }

        if (passwordText.text.isNullOrEmpty()) {
            passwordLayout.error = "Password is required"
            return false
        }

        return true
    }


    /**
     * Login form
     *
     * @param emailEditText
     * @param passwordEditText
     */
    private fun loginForm(emailEditText: EditText, passwordEditText: EditText) {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        login(email, password)
    }

    /**
     * Login function
     *
     * @param email
     * @param password
     */
    private fun login(email: String, password: String) {
        // Don't need to check the form because it is already checked before calling this function
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
                    Toast.makeText(
                        this,
                        "Error: $errorMessage",
                        Toast.LENGTH_SHORT
                    ).show()

                    // NUEVO: Muestra el error en el TextView
                    errorText.text = errorMessage
                }
            }
    }
}
