import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.FullNewsActivity
import com.example.newsapp.R

class NewsAdapter(private val newsItems: List<NewsItem>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = newsItems[position]
        holder.title.text = newsItem.title
        holder.description.text = newsItem.description

        Glide.with(holder.itemView.context)
            .load(newsItem.imageUrl)
            .placeholder(R.drawable.placeholder_image) 
            .error(R.drawable.error_image) 
            .into(holder.image)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, FullNewsActivity::class.java).apply {
                putExtra("url", newsItem.url)
            }
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = newsItems.size

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.newsTitle)
        val description: TextView = itemView.findViewById(R.id.newsDescription)
        val image: ImageView = itemView.findViewById(R.id.newsImage)
    }
}
