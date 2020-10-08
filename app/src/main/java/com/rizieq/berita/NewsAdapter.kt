package com.rizieq.berita

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rizieq.berita.model.Article
import kotlinx.android.synthetic.main.lay_berita.view.*

class NewsAdapter(
    private val articles: List<Article>,
    private val context: Context
) : RecyclerView.Adapter<NewsAdapter.MyViewHolder?>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.lay_berita,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItem(articles[position])
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvTitle: TextView = view.tvw_Title
        private val tvDesc: TextView = view.tvw_Desc
        private val imgNews: ImageView = view.img_news

        fun bindItem(article: Article) {
            tvTitle.text = article.title
            tvDesc.text = article.description
            Glide.with(itemView.context)
                .load(article.urlToImage)
                .into(imgNews)

            itemView.setOnClickListener {
                val goInput = Intent(itemView.context, DetailActivity::class.java)
                goInput.putExtra(DetailActivity.cont_AuthorNews, article.author)
                goInput.putExtra(DetailActivity.cont_DateNews, article.publishedAt)
                goInput.putExtra(DetailActivity.cont_PhotoNews, article.urlToImage)
                goInput.putExtra(DetailActivity.cont_TitleNews, article.title)
                goInput.putExtra(DetailActivity.cont_SourceNews, article.source?.name)
                goInput.putExtra(DetailActivity.cont_url, article.url)
                itemView.context.startActivity(goInput)
            }
        }

    }

}
