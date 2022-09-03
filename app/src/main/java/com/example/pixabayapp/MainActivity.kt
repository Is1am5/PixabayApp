package com.example.pixabayapp

import android.net.DnsResolver
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pixabayapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PixaAdapter
    private var page = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClicks()
        adapter = PixaAdapter()
        val layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = this@MainActivity.adapter

        val scrollListener = EndlessScrollListener(layoutManager) { page ->
            val q = binding.editTextKey.text.toString()
            doRequest(page, q)
        }
        binding.recyclerView.addOnScrollListener(scrollListener)
    }

    private fun initClicks() {
        with(binding) {
            buttonRequest.setOnClickListener {
                val q = binding.editTextKey.text.toString()
                doRequest(page, q)
            }
        }
    }

    private fun doRequest(page: Int, q: String) {
        App.api.getImages(q = q, page = page)
            .enqueue(object : Callback<PixabayModel> {
                override fun onResponse(
                    call: Call<PixabayModel>,
                    response: Response<PixabayModel>
                ) {
                   if(response.isSuccessful) {
                        response.body()?.hits?.let {
                            adapter.setItems(it)

                        }
                    }
                }

                override fun onFailure(call: Call<PixabayModel>, t: Throwable) {
                    Log.e("ololo", "onFailure:${t.message}")
                }

            })
    }
}

private fun <E> List<E>?.let(function: () -> Unit) {

}
