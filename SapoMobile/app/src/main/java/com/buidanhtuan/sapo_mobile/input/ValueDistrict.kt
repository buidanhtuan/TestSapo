package com.buidanhtuan.sapo_mobile.input

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.buidanhtuan.sapo_mobile.activity.CityActivity
import com.buidanhtuan.sapo_mobile.activity.DistrictActivity
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL
import java.net.URLConnection

class ValueDistrict(var listDistrict: ArrayList<DistrictActivity.District>,
                    var rv_district: RecyclerView
) {
    @SuppressLint("StaticFieldLeak")
    inner class Json : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg p0: Void?): Void? {
            val str = "https://raw.githubusercontent.com/sapo-tech/home_test_mobile/master/Districts.json"
            val urlConn: URLConnection
            var bufferedReader: BufferedReader? = null
            try {
                val url = URL(str)
                urlConn = url.openConnection()
                bufferedReader = BufferedReader(InputStreamReader(urlConn.getInputStream()))
                val line: String = bufferedReader.readLine()
                val json = JSONObject(line)
                val jArray: JSONArray = json.getJSONArray("Districts")
                val length: Int = jArray.length() - 1
                for (i in 0..length) {
                    val jsonData: JSONObject = jArray.getJSONObject(i)
                    val district: DistrictActivity.District = DistrictActivity.District()
                    district.districtName=jsonData.getString("Name")
                    district.cityCode=jsonData.getInt("CityCode")
                    district.districtCode=jsonData.getInt("DistrictCode")
                    if (district.cityCode == CityActivity.cityCode){
                        listDistrict.add(district)
                    }
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
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
            rv_district.adapter?.notifyDataSetChanged()
        }
    }
}