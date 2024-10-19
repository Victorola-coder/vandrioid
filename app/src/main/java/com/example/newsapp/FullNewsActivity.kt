import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class FullNewsActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_news)

        webView = findViewById(R.id.webView)
        webView.webViewClient = WebViewClient()

        val url = intent.getStringExtra("url")
        url?.let { webView.loadUrl(it) }
    }
}
