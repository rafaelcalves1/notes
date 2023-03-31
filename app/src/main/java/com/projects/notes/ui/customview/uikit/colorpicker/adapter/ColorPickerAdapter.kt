package com.projects.notes.ui.customview.uikit.colorpicker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.projects.notes.databinding.ColorPickItemBinding
import com.projects.notes.ui.notelist.model.ColorItem


internal class ColorPickerAdapter(
    private val listenner: (ColorItem) -> Unit
) : ListAdapter<ColorItem, ColorPickerViewHolder>(
    object : DiffUtil.ItemCallback<ColorItem>() {
        override fun areItemsTheSame(oldItem: ColorItem, newItem: ColorItem): Boolean {
            return true
        }

        override fun areContentsTheSame(oldItem: ColorItem, newItem: ColorItem): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorPickerViewHolder {
        val binding = ColorPickItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ColorPickerViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ColorPickerViewHolder, position: Int) {
        holder.bindItem(currentList[position], listenner)
    }
}

internal class ColorPickerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val colorItem = view.rootView

    fun bindItem(color: ColorItem, listenner: (ColorItem) -> Unit) {
        colorItem.setBackgroundResource(color.setBackgroundRes())
        colorItem.setOnClickListener {
            listenner(color.copy(isEnabled = color.isEnabled.not()))
        }
    }
}