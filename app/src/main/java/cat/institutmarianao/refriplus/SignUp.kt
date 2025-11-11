package cat.institutmarianao.refriplus

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
// import FirebaseAuthentication
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {

    // Declare an instance of FirebaseAuth
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Initialize FirebaseAuth
        auth = FirebaseAuth.getInstance()

        val email = findViewById<TextInputEditText>(R.id.email)
        val password = findViewById<TextInputEditText>(R.id.password)
        val confirmPassword = findViewById<TextInputEditText>(R.id.confirmPassword)
        val errorText = findViewById<TextView>(R.id.errorText)


        val signIn = findViewById<Button>(R.id.signIn)
        val goBack = findViewById<Button>(R.id.goBack)

        // TODO create new account and sign in
        signIn.setOnClickListener {
            // TODO create new account and sign in

            // Get the email and password from the TextInputEditText fields
            // editText? cause we need to obtain the text from the TextInputEditText inside de layout
            val emailText = email.text.toString()
            val passwordText = password.text.toString()
            val confirmPasswordText = confirmPassword.text.toString()

            // Check if the email and password fields are empty
            if (emailText.isEmpty() || passwordText.isEmpty() || confirmPasswordText.isEmpty()) {
                errorText.text = "All fields are required"
                return@setOnClickListener
            }

            // Check if the passwords match
            if (passwordText != confirmPasswordText) {
                errorText.text = "Passwords do not match"
                return@setOnClickListener
            }

            // if the code get here is cause all the fields are correct
            // Create a new user with the email and password
            auth.createUserWithEmailAndPassword(emailText, passwordText).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "User successfully created!", Toast.LENGTH_SHORT).show()

                    // 1 Create a new Intent to navigate to the ClientsActivity
                    val intent = Intent(this, AIFridge::class.java)

                    // 2 Add flags to the Intent to clear the back stack and start a new task
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                    // 3 Start the ClientsActivity
                    startActivity(intent)

                    // 4 Finish the current activity to prevent the user from navigating back to it
                    // not necessary but it's good manners
                    finish()

                } else {
                    val errorMessage = task.exception?.message ?: "An unknown error occurred."
                    errorText.text = errorMessage
                }
            }
        }

        // Go back to the login screen
        goBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}