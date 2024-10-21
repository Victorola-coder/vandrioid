import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView appName = findViewById(R.id.appName);
        Button startButton = findViewById(R.id.startButton);

        // Animate the app name
        ObjectAnimator animator = ObjectAnimator.ofFloat(appName, "translationX", -500f, 500f);
        animator.setDuration(2000);
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.setRepeatMode(ObjectAnimator.REVERSE);
        animator.start();

        // Set button click listener to start drawing activity
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DrawingActivity.class);
                startActivity(intent);
            }
        });
    }
}