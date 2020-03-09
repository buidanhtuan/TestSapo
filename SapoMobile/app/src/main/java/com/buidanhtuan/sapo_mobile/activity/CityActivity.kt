package com.buidanhtuan.sapo_mobile.activity

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.buidanhtuan.sapo_mobile.R
import com.buidanhtuan.sapo_mobile.adapter.CityAdapter
import kotlinx.android.synthetic.main.activity_city.*
import kotlinx.android.synthetic.main.adapter_city.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL
import java.net.URLConnection

class CityActivity : AppCompatActivity(),
    CityAdapter.OnClickItemListener{

    val listCityName: ArrayList<String> = ArrayList()
    val listCityCode: ArrayList<Int> = ArrayList()
    val listCity: ArrayList<City> = ArrayList()
    class City {
        var cityName = ""
        var cityCode = 0
    }
    companion object {
        var cityName = ""
        var cityCode = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)
        rv_city.layoutManager = LinearLayoutManager(this)
        rv_city.adapter = CityAdapter(listCity, this)
        Json().execute()
    }
    override fun onClickItem(position: Int) {
        cityName  = listCityName.get(position)
        val intent: Intent = Intent (this, DistrictActivity::class.java)
        cityName = listCityName.get(position)
        cityCode = listCityCode.get(position)
        rv_city.adapter = CityAdapter(listCity,this)
        startActivity(intent)
    }
    inner class Json : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg p0: Void?): Void? {
            var str = "https://raw.githubusercontent.com/sapo-tech/home_test_mobile/master/Cities.json"
            var urlConn: URLConnection? = null
            var bufferedReader: BufferedReader? = null
            try {
                var url: URL = URL(str)
                urlConn = url.openConnection()
                bufferedReader = BufferedReader(InputStreamReader(urlConn.getInputStream()))
                //var stringBuffer: StringBuffer? = null
                var line: String = ""
                line = bufferedReader.readLine()
                var json: JSONObject = JSONObject(line)
                var jArray: JSONArray = json.getJSONArray("Cities")
                var length: Int = jArray.length() - 1
                for (i in 0..length) {
                    var json_data: JSONObject = jArray.getJSONObject(i)
                    var city : City = City()
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
            for (i in 0..(listCity.size-1)){
                listCityName.add(listCity.get(i).cityName)
                listCityCode.add(listCity.get(i).cityCode)
            }
            rv_city.adapter?.notifyDataSetChanged()
        }
    }
}
