// FullNewsActivity.kt
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
        val authorTextView = findViewById<TextView>(R.id.newsAuthor)
        val contentTextView = findViewById<TextView>(R.id.newsContent)
        val webView = findViewById<WebView>(R.id.newsWebView)

        authorTextView.text = author ?: "Unknown Author" // Handle null case
        contentTextView.text = content ?: "No Content Available" // Handle null case

        // Load the full article in a WebView
        if (url != null) {
            webView.loadUrl(url)
        }
    }
}
`