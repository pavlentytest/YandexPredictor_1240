package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import com.example.myapplication.api.YandexAPI
import com.example.myapplication.model.Answer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    val BASE_URL = "https://predictor.yandex.net"
    val KEY = "pdct.1.1.20230408T095716Z.a59fae57c5a7b804.24520269af377e887590b937941df66acc887d6a"
    val LANG = "en"
    val LIMIT = 5
    lateinit var editText: EditText
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.editText)
        textView = findViewById(R.id.textView)
        editText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
               doRequest()
            }
        })
    }

    fun doRequest() {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api: YandexAPI = retrofit.create(YandexAPI::class.java)
        api.getComplete(KEY, editText.text.toString(), LANG, LIMIT).enqueue(object: Callback<Answer>{
            override fun onResponse(call: Call<Answer>, response: Response<Answer>) {
                if(response.code() == 200) {
                    val result: List<String>? = response.body()?.text
                    if (result != null) {
                        textView.text = result.joinToString("\n")
                    }
                }
            }
            override fun onFailure(call: Call<Answer>, t: Throwable) {
                Log.d("RRR",t.message.toString())
            }

        })
    }

}