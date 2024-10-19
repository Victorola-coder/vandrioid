import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException
import java.lang.reflect.Type

class NewsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val region = intent.getStringExtra("region")
        val topic = intent.getStringExtra("topic")

        fetchNews(region ?: "us", topic ?: "general")
    }

    private fun fetchNews(region: String, topic: String) {
        val apiKey = "YOUR_API_KEY" // Replace with your actual API key
        val url = "https://newsapi.org/v2/top-headlines?country=$region&category=$topic&apiKey=$apiKey"

        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("NewsActivity", "Failed to fetch news", e)
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val jsonResponse = response.body?.string()
                    parseNews(jsonResponse ?: "")
                }
            }
        })
    }

    private fun parseNews(jsonResponse: String) {
        val gson = Gson()
        val newsListType: Type = object : TypeToken<NewsResponse>() {}.type
        val newsResponse: NewsResponse = gson.fromJson(jsonResponse, newsListType)
        val newsItems = newsResponse.articles

        runOnUiThread {
            newsAdapter = NewsAdapter(newsItems, this)
            recyclerView.adapter = newsAdapter
        }
    }
}
