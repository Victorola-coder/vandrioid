import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class DrawingActivity extends AppCompatActivity {

    private DrawingView drawingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);

        drawingView = findViewById(R.id.drawingView);

        // Set up color buttons
        findViewById(R.id.colorRed).setOnClickListener(v -> drawingView.setColor(Color.RED));
        findViewById(R.id.colorGreen).setOnClickListener(v -> drawingView.setColor(Color.GREEN));
        findViewById(R.id.colorBlue).setOnClickListener(v -> drawingView.setColor(Color.BLUE));

        // Set up width buttons
        findViewById(R.id.widthSmall).setOnClickListener(v -> drawingView.setStrokeWidth(5f));
        findViewById(R.id.widthMedium).setOnClickListener(v -> drawingView.setStrokeWidth(10f));
        findViewById(R.id.widthLarge).setOnClickListener(v -> drawingView.setStrokeWidth(20f));

        // Set up clear button
        findViewById(R.id.clearButton).setOnClickListener(v -> drawingView.clear());
    }
}
