package com.buidanhtuan.sapo_mobile.input

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.buidanhtuan.sapo_mobile.activity.CityActivity
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL
import java.net.URLConnection

class ValueCity(var listCity: ArrayList<CityActivity.City>, var rv_city: RecyclerView) {
    @SuppressLint("StaticFieldLeak")
    inner class Json : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg p0: Void?): Void? {
            val str = "https://raw.githubusercontent.com/sapo-tech/home_test_mobile/master/Cities.json"
            val urlConn: URLConnection
            var bufferedReader: BufferedReader? = null
            try {
                val url = URL(str)
                urlConn = url.openConnection()
                bufferedReader = BufferedReader(InputStreamReader(urlConn.getInputStream()))
                val line : String = bufferedReader.readLine()
                val json = JSONObject(line)
                val jArray: JSONArray = json.getJSONArray("Cities")
                val length: Int = jArray.length() - 1
                for (i in 0..length) {
                    val jsonData: JSONObject = jArray.getJSONObject(i)
                    val city : CityActivity.City = CityActivity.City()
                    city.cityCode = jsonData.getInt("CityCode")
                    city.cityName = jsonData.getString("Name")
                    listCity.add(city)
                }
            } catch (ex: Exception) {
                Log.e("App", "yourDataTask", ex)
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
            return null
        }
        override fun onPostExecute(result: Void?) {
            rv_city.adapter?.notifyDataSetChanged()
        }
    }
}