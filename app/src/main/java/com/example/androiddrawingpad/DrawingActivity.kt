import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androiddrawingpad.R
import android.widget.Button
import android.widget.ImageButton

class DrawingActivity : AppCompatActivity() {

    private lateinit var drawingView: DrawingView
    private lateinit var colorRed: ImageButton
    private lateinit var colorGreen: ImageButton
    private lateinit var colorBlue: ImageButton
    private lateinit var widthSmall: Button
    private lateinit var widthMedium: Button
    private lateinit var widthLarge: Button
    private lateinit var clearButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawing)

        drawingView = findViewById(R.id.drawingView)
        colorRed = findViewById(R.id.colorRed)
        colorGreen = findViewById(R.id.colorGreen)
        colorBlue = findViewById(R.id.colorBlue)
        widthSmall = findViewById(R.id.widthSmall)
        widthMedium = findViewById(R.id.widthMedium)
        widthLarge = findViewById(R.id.widthLarge)
        clearButton = findViewById(R.id.clearButton)

        // Set up color buttons
        colorRed.setOnClickListener { drawingView.setColor(Color.RED) }
        colorGreen.setOnClickListener { drawingView.setColor(Color.GREEN) }
        colorBlue.setOnClickListener { drawingView.setColor(Color.BLUE) }

        // Set up width buttons
        widthSmall.setOnClickListener { drawingView.setStrokeWidth(5f) }
        widthMedium.setOnClickListener { drawingView.setStrokeWidth(10f) }
        widthLarge.setOnClickListener { drawingView.setStrokeWidth(20f) }

        // Set up clear button
        clearButton.setOnClickListener { drawingView.clear() }
    }
}
