// NewsItem.kt
import com.google.gson.annotations.SerializedName

data class NewsItem(
    val title: String,
    val description: String,
    @SerializedName("urlToImage") val imageUrl: String?,  
    val url: String,
    val author: String?,
    val content: String?
)
