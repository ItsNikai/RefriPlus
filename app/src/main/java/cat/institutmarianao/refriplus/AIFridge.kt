package cat.institutmarianao.refriplus

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth

class AIFridge : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ai_fridge)

        // Initialize the toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        // Set the toolbar as the action bar for the activity
        setSupportActionBar(toolbar)

    }

    // method to inflate the toolbar menu
    // android does it by default
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    // Method called all the time the user clicks on an item in the toolbar
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