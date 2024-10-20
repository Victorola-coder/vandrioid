package com.example.newsapp

import android.os.Bundle
import android.webkit.WebView
import android.widget.TextView
import com.example.newsapp.R
import androidx.appcompat.app.AppCompatActivity

class FullNewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_news)

        // Get the data from the Intent
        val url = intent.getStringExtra("url")
        val author = intent.getStringExtra("author")
        val content = intent.getStringExtra("content")

        // Display the author and content in TextViews
        val authorTextView: TextView = findViewById(R.id.newsAuthor)
        val contentTextView: TextView = findViewById(R.id.newsContent)
        val webView: WebView = findViewById(R.id.newsWebView)

        authorTextView.text = author ?: getString(R.string.unknown_author)
        contentTextView.text = content ?: getString(R.string.no_content_available)

        // Load the full article in a WebView
        url?.let { webView.loadUrl(it) }
    }
}
