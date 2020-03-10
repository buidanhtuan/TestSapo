package com.buidanhtuan.sapo_mobile.input

import android.os.AsyncTask
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.buidanhtuan.sapo_mobile.activity.CityActivity
import com.buidanhtuan.sapo_mobile.activity.DistrictActivity
import kotlinx.android.synthetic.main.activity_district.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL
import java.net.URLConnection

class ValueDistrict(listDistrict : ArrayList<DistrictActivity.District>,rv_district : RecyclerView) {
    var listDistrict = listDistrict
    var rv_district = rv_district
    inner class Json : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg p0: Void?): Void? {
            var str =
                "https://raw.githubusercontent.com/sapo-tech/home_test_mobile/master/Districts.json"
            var urlConn: URLConnection? = null
            var bufferedReader: BufferedReader? = null
            try {
                var url: URL = URL(str)
                urlConn = url.openConnection()
                bufferedReader = BufferedReader(InputStreamReader(urlConn.getInputStream()))
                var line: String = ""
                line = bufferedReader.readLine()
                var json: JSONObject = JSONObject(line)
                var jArray: JSONArray = json.getJSONArray("Districts")
                var length: Int = jArray.length() - 1
                for (i in 0..length) {
                    var json_data: JSONObject = jArray.getJSONObject(i)
                    var district: DistrictActivity.District = DistrictActivity.District()
                    district.districtName=json_data.getString("Name")
                    district.cityCode=json_data.getInt("CityCode")
                    district.districtCode=json_data.getInt("DistrictCode")
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