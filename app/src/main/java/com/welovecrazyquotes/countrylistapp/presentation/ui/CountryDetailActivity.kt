package com.welovecrazyquotes.countrylistapp.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide

class CountryDetailActivity : AppCompatActivity() {

//    override fun onCreate(savedInstanceState: Bundle?) {
//
//        super.onCreate(savedInstanceState)
//        binding = ActivityCountryDetailBinding.inflate(layoutInflater)
//
//        setContentView(binding.root)
//        bind()
//    }
//
//    private fun bind() {
//        val country = intent.getParcelableExtra<Country>(Constants.COUNTRY_COMMON_NAME)
////        Log.d("TAG", "bind: $countryName ")
//        lifecycleScope.launchWhenCreated {
//            binding.apply {
//                country?.let {
//                    Log.d("TAG", "bind: $country ")
//
//                    country?.let {
//                        Glide.with(this@CountryDetailActivity)
//                            .load(country.flags.png)
//                            .into(imvFlag)
//                        tvCapital.text = "Capital city: ${country.capital[0]}"
//                        tvLangs.text = "Languages: ${country.languages}"
//                        tvOname.text = "Official name:${country.name.common}"
//                        tvPop.text = "Population: ${country.population}"
//                        tvRegion.text = "Region: ${country.region}"
//                        tvSregion.text = "Sub-region: ${country.subregion}"
//                    }
//                }
//            }
//        }
//    }
}