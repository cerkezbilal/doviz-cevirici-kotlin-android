package com.bilalkarademir.dovizcevirici

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URI
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun getRates(view:View){

        val download = Download()
        try {

            var url = "http://data.fixer.io/api/latest?access_key=c26f61e367975a00b7f0b68bc6352324&format=1"
            download.execute(url)


        }catch (e:Exception){

        }

    }

    inner class Download:AsyncTask<String,Void,String>(){
        override fun doInBackground(vararg params: String?): String {

            var result = ""

            var url: URL
            var httpURLConnection:HttpURLConnection

            try {

                url = URL(params[0])
                httpURLConnection = url.openConnection() as HttpURLConnection
                var inputStream = httpURLConnection.inputStream
                val inputStreamReader = InputStreamReader(inputStream)
                var data = inputStreamReader.read()
                while (data>0){
                    val characher = data.toChar()
                    result += characher
                    data = inputStreamReader.read()

                }
                return  result

            }catch (e:Exception){
                return  result

            }


        }

        override fun onPostExecute(result: String?) {

            //print(result)

            try {

                val jSonObject = JSONObject(result)
                val base = jSonObject.getString("base")
                //println(base)
                val rates = jSonObject.getString("rates")
               // println(rates)

                val jSonObject1 = JSONObject(rates)
                val turkishLira = jSonObject1.getString("TRY")
                val cad = jSonObject1.getString("CAD")
                val gbp = jSonObject1.getString("GBP")
                val usd = jSonObject1.getString("USD")
                val chf= jSonObject1.getString("CHF")

                tvTry.text = "TRY: "+turkishLira
                tvCanada.text = "CAD: "+cad
                tvIsvicre.text="GHF: "+chf
                tvPound.text = "GBP: "+gbp
                tvUsd.text ="USD: "+usd






            }catch (e:Exception){

            }
            super.onPostExecute(result)
        }
    }
}
