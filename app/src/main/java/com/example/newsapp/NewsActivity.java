import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String region = getIntent().getStringExtra("region");
        String topic = getIntent().getStringExtra("topic");

        fetchNews(region, topic);
    }

    private void fetchNews(String region, String topic) {
        String apiKey = "f3e92ce9f7cc4bdd806eb00d983d443e"; 
        String url = "https://newsapi.org/v2/top-headlines?country=" + region + "&category=" + topic + "&apiKey=" + apiKey;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("NewsActivity", "Failed to fetch news", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonResponse = response.body().string();
                    parseNews(jsonResponse);
                }
            }
        });
    }

    private void parseNews(String jsonResponse) {
        Gson gson = new Gson();
        Type newsListType = new TypeToken<NewsResponse>(){}.getType();
        NewsResponse newsResponse = gson.fromJson(jsonResponse, newsListType);
        List<NewsItem> newsItems = newsResponse.getArticles();

        runOnUiThread(() -> {
            newsAdapter = new NewsAdapter(newsItems, NewsActivity.this);
            recyclerView.setAdapter(newsAdapter);
        });
    }
}
