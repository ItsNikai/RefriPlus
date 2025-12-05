package cat.institutmarianao.refriplus

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class AIFridge : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ai_fridge)

        // Initialize the toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        // Set the toolbar as the action bar for the activity
        setSupportActionBar(toolbar)

        val ingredientName= findViewById<TextInputEditText>(R.id.newIngredient)
        val quantity= findViewById<TextInputEditText>(R.id.quantity)

        val btnAddIngredient: Button = findViewById(R.id.btnAddIngredient)
        val btnClear: Button = findViewById(R.id.btnClear)

        // TODO create the list of ingredients on the db on firebase

        // TODO fill the spinners

    }

    /**
     * Inflate the menu items for use in the action bar
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    /**
     * Handle action bar item clicks
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Checks which item was clicked
        return when (item.itemId) {
            R.id.action_logout -> {
                // show a toast
                Toast.makeText(this, "GoodBye!!", Toast.LENGTH_SHORT).show()
                // Logout via Firebase
                FirebaseAuth.getInstance().signOut()
                // Return to the login screen
                val intent = Intent(this, MainActivity::class.java)
                finish()
                true // Indicate that the event has been handled
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}