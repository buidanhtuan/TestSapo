package com.buidanhtuan.sapo_mobile.activity

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.buidanhtuan.sapo_mobile.R
import com.buidanhtuan.sapo_mobile.adapter.CityAdapter
import com.buidanhtuan.sapo_mobile.adapter.DistrictAdapter
import kotlinx.android.synthetic.main.activity_city.*
import kotlinx.android.synthetic.main.activity_district.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL
import java.net.URLConnection

class DistrictActivity : AppCompatActivity(), DistrictAdapter.OnClickItemListener{
    val listDistrict: ArrayList<District> = ArrayList()
    val listDistrictName : ArrayList<String> = ArrayList()
    class District{
        var cityCode : Int = 0
        var districtName : String = ""
        var districtCode : Int = 0
    }
    companion object {
        var cityCode : Int = 0
        var districtName : String = ""
        var districtCode : Int = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        var intent = getIntent()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_district)
        rv_district.layoutManager = LinearLayoutManager(this)
        rv_district.adapter = DistrictAdapter(listDistrictName, this)
        Json().execute()
    }
    override fun onClickItem(position: Int) {
        districtName = listDistrictName.get(position)
        val intent: Intent = Intent (this, AgeActivity::class.java)
        startActivity(intent)
    }
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
                System.out.println(line)
                var jArray: JSONArray = json.getJSONArray("Districts")
                var length: Int = jArray.length() - 1
                for (i in 0..length) {
                    var json_data: JSONObject = jArray.getJSONObject(i)
                    var district:District = District()
                    district.districtName=json_data.getString("Name")
                    district.cityCode=json_data.getInt("CityCode")
                    district.districtCode=json_data.getInt("DistrictCode")
                    listDistrict.add(district)
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
            var cityCode = CityActivity.cityCode
            for (i in 0..(listDistrict.size-1)){
                if(listDistrict.get(i).cityCode==cityCode){
                    listDistrictName.add(listDistrict.get(i).districtName)
                }
            }
            rv_district.adapter?.notifyDataSetChanged()
        }
    }
}