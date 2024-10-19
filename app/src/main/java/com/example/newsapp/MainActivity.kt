import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var spinnerRegion: Spinner
    private lateinit var spinnerTopic: Spinner
    private lateinit var buttonGetNews: Button
    private var selectedRegion: String = "us" // Default region
    private var selectedTopic: String = "general" // Default topic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerRegion = findViewById(R.id.spinnerRegion)
        spinnerTopic = findViewById(R.id.spinnerTopic)
        buttonGetNews = findViewById(R.id.buttonGetNews)

        setupSpinners()
        setupButtonListener()
    }

    private fun setupSpinners() {
        val regionAdapter = ArrayAdapter.createFromResource(this,
            R.array.regions, android.R.layout.simple_spinner_item)
        regionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRegion.adapter = regionAdapter

        spinnerRegion.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedRegion = parent.getItemAtPosition(position).toString().lowercase()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        val topicAdapter = ArrayAdapter.createFromResource(this,
            R.array.topics, android.R.layout.simple_spinner_item)
        topicAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTopic.adapter = topicAdapter

        spinnerTopic.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedTopic = parent.getItemAtPosition(position).toString().lowercase()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun setupButtonListener() {
        buttonGetNews.setOnClickListener {
            val intent = Intent(this, NewsActivity::class.java)
            intent.putExtra("region", selectedRegion)
            intent.putExtra("topic", selectedTopic)
            startActivity(intent)
        }
    }
}
