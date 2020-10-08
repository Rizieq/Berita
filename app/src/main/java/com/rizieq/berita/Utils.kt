@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.rizieq.berita

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.rizieq.berita.api.ApiClient
import com.rizieq.berita.api.ApiInterface
import org.ocpsoft.prettytime.PrettyTime
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    private var vibrantLightColorList = arrayOf(
        ColorDrawable(Color.parseColor("#ffeead")),
        ColorDrawable(Color.parseColor("#93cfb3")),
        ColorDrawable(Color.parseColor("#fd7a7a")),
        ColorDrawable(Color.parseColor("#faca5f")),
        ColorDrawable(Color.parseColor("#1ba798")),
        ColorDrawable(Color.parseColor("#6aa9ae")),
        ColorDrawable(Color.parseColor("#ffbf27")),
        ColorDrawable(Color.parseColor("#d93947"))
    )

    fun getClient(): ApiInterface {
        return ApiClient.create().create(ApiInterface::class.java)

    }

    fun getRandomDrawbleColor(): ColorDrawable? {
        val idx = Random().nextInt(vibrantLightColorList.size)
        return vibrantLightColorList[idx]
    }

    fun dateToTimeFormat(oldstringDate: String?): String? {
        val p = PrettyTime(Locale(getCountry()))
        var isTime: String? = null
        try {
            val sdf = SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss'Z'",
                Locale.ENGLISH
            )
            val date = sdf.parse(oldstringDate)
            isTime = p.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return isTime
    }

    @SuppressLint("SimpleDateFormat")
    fun dateFormat(oldstringDate: String?): String? {
        val newDate: String
        val dateFormat =
            SimpleDateFormat("E, d MMM yyyy", Locale(getCountry()))
        newDate = try {
            val date =
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(oldstringDate)
            dateFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
            oldstringDate!!
        }
        return newDate
    }

    fun getCountry(): String? {
        val locale = Locale.getDefault()
        val country = locale.country.toString()
        return country.toLowerCase(Locale.ROOT)
    }

}