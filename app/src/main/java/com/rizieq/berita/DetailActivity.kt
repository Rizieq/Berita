package com.rizieq.berita

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    private var mUrl: String? = null
    private var mImg: String? = null
    private var mTitle: String? = null
    private var mDate: String? = null
    private var mSource: String? = null
    private var mAuthor: String? = null
    private var author: String? = null


    companion object {
        const val cont_TitleNews = "cont_TitleNews"
        const val cont_PhotoNews = "cont_PhotoNews"
        const val cont_url = "cont_UrlNews"
        const val cont_DateNews = "cont_DateNews"
        const val cont_SourceNews = "cont_SourceNews"
        const val cont_AuthorNews = "cont_AuthorNews"
    }

    @SuppressLint("CheckResult", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        val intent: Intent = intent
        mUrl = intent.getStringExtra(cont_url)
        mImg = intent.getStringExtra(cont_PhotoNews)
        mTitle = intent.getStringExtra(cont_TitleNews)
        mDate = intent.getStringExtra(cont_DateNews)
        mSource = intent.getStringExtra(cont_SourceNews)
        mAuthor = intent.getStringExtra(cont_AuthorNews)

        val requiresOptIn = RequestOptions()
        requiresOptIn.error(Utils.getRandomDrawbleColor())

        Glide.with(this)
            .load(mImg)
            .apply(requiresOptIn)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(img_toolbar)

        txt_FotoDesc.text = mUrl
        txt_PostTime.text = Utils.dateFormat(mDate)
        txt_TitleNews.text = mTitle


        if (author != null || mAuthor != "") {
            mAuthor = " \u2022 $mAuthor"
        } else {
            author = ""
        }

        txt_Reporter.text = mSource + author + "\u2022" + Utils.dateToTimeFormat(mDate)
        initWebView(mUrl)






        btn_Back.setOnClickListener {
            val intentMain = Intent(
                this@DetailActivity,
                MainActivity::class.java
            )
            startActivity(intentMain)
            finish()
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView(mUrl: String?) {
        webView.settings.loadsImagesAutomatically = true
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.setSupportZoom(true)
        webView.settings.builtInZoomControls = true
        webView.settings.displayZoomControls = false
        webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        webView.webViewClient = WebViewClient()
        webView.loadUrl(mUrl)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFinishAfterTransition()
    }


}

