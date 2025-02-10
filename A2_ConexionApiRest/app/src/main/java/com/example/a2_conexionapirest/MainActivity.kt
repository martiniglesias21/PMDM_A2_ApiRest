package com.example.a2_conexionapirest

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a2_conexionapirest.databinding.ActivityMainBinding
import com.example.a2_conexionapirest.model.Series
import com.example.a2_conexionapirest.model.SeriesResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter: SerieAdapter
    private val seriesList = mutableListOf<Series>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initRecyclerView()
        getSeries("series")

    }

    private fun initRecyclerView() {
        adapter = SerieAdapter(seriesList)
        binding.rvSeries.layoutManager = LinearLayoutManager(this)
        binding.rvSeries.adapter = adapter
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://peticiones.online/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getSeries(query : String){
        CoroutineScope(Dispatchers.IO).launch{
            val call: Response<SeriesResponse> = getRetrofit()
                .create(ApiService::class.java).getAllProducts(query)

            val serie : SeriesResponse? = call.body()
            if (serie != null) {
                for(miProducto: Series in serie){
                    Log.v("Productos", miProducto.toString())
                }
            } else {
                Log.e("Error", "Mucho error")
            }

            runOnUiThread {
                if (call.isSuccessful) {
                    val seriesL = serie ?: emptyList()
                    seriesList.clear()
                    seriesList.addAll(seriesL)
                    adapter.notifyDataSetChanged()

                }else{
                    showError()
                }
            }
        }
    }

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }
}