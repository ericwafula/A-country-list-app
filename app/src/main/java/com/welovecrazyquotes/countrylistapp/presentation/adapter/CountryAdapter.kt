package com.welovecrazyquotes.countrylistapp.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.welovecrazyquotes.countrylistapp.databinding.CountryItemBinding
import com.welovecrazyquotes.countrylistapp.domain.model.Country

class CountryAdapter(private val context: Context, private val onItemClicked: (Country) -> Unit) :
    ListAdapter<Country, CountryAdapter.CountryViewHolder>(diffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val countryViewHolder = CountryViewHolder(
            CountryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        countryViewHolder.itemView.setOnClickListener {
            val position = countryViewHolder.bindingAdapterPosition
            onItemClicked(getItem(position))
        }
        return countryViewHolder
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {

        holder.bind(getItem(position), context)
    }

    class CountryViewHolder(private val binding: CountryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country, context: Context) {
            binding.apply {
                Glide.with(context)
                    .load(country.flags?.png)
                    .into(imvFlag)
                txvCountry.text = country.name?.common
            }
        }
    }

    companion object {
        private val diffCallBack = object : DiffUtil.ItemCallback<Country>() {
            override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
                return oldItem.name?.common == newItem.name?.common
            }

            override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
                return oldItem == newItem
            }
        }
    }


}