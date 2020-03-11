package com.buidanhtuan.sapo_mobile.input

import android.os.AsyncTask
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.buidanhtuan.sapo_mobile.activity.CityActivity
import kotlinx.android.synthetic.main.activity_city.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL
import java.net.URLConnection

class ValueCity(listCity: ArrayList<CityActivity.City>,rv_city : RecyclerView) {
    var listCity = listCity
    var rv_city = rv_city
    inner class Json : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg p0: Void?): Void? {
            var str = "https://raw.githubusercontent.com/sapo-tech/home_test_mobile/master/Cities.json"
            var urlConn: URLConnection? = null
            var bufferedReader: BufferedReader? = null
            try {
                var url = URL(str)
                urlConn = url.openConnection()
                bufferedReader = BufferedReader(InputStreamReader(urlConn.getInputStream()))
                var line : String
                line = bufferedReader.readLine()
                var json: JSONObject = JSONObject(line)
                var jArray: JSONArray = json.getJSONArray("Cities")
                var length: Int = jArray.length() - 1
                for (i in 0..length) {
                    var json_data: JSONObject = jArray.getJSONObject(i)
                    var city : CityActivity.City = CityActivity.City()
                    city.cityCode = json_data.getInt("CityCode")
                    city.cityName = json_data.getString("Name")
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