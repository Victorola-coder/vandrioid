import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.androiddrawingpad.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appName: TextView = findViewById(R.id.appName)
        val startButton: Button = findViewById(R.id.startButton)

        // Animate the app name
        val animator = ObjectAnimator.ofFloat(appName, "translationX", -500f, 500f)
        animator.duration = 2000
        animator.repeatCount = ObjectAnimator.INFINITE
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.start()

        // Set button click listener to start drawing activity
        startButton.setOnClickListener {
            val intent = Intent(this@MainActivity, DrawingActivity::class.java)
            startActivity(intent)
        }
    }
}
