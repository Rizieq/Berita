package com.rizieq.berita

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.rizieq.berita.model.News
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val keys = "b4ad03a115f04d83bd26923f97e0f668"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadJson()


    }


    private fun loadJson() {
        val country = Utils.getCountry()
        val category = "business"
        Utils.getClient().getNews(country, category, keys).enqueue(object : Callback<News> {
            override fun onResponse(
                call: Call<News>,
                response: Response<News>
            ) {

                if (response.body() != null) {
                    val articles = response.body()!!.articles
                    rcv_daftarberita.hasFixedSize()
                    rcv_daftarberita.layoutManager = LinearLayoutManager(this@MainActivity)
                    rcv_daftarberita.adapter = articles?.let { NewsAdapter(it, this@MainActivity) }
                }

            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()

            }
        })

    }


}
