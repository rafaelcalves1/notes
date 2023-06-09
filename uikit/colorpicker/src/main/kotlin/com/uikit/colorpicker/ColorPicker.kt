package com.uikit.colorpicker

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uikit.colorpicker.databinding.ColorPickerViewBinding
import com.uikit.colorpicker.adapter.ColorPickerAdapter

class ColorPicker(
    context: Context,
    attributeSet: AttributeSet? = null
) : FrameLayout(context, attributeSet) {

    private var binding: ColorPickerViewBinding

    private val recyclerView: RecyclerView
        get() = binding.colorSelectorRecyclerView

    private val root: View
        get() = binding.root


    private val adapter = ColorPickerAdapter(::setClickColorListener)

    private var listOfColors: List<ColorItem> = emptyList()
        set(value) {
            adapter.submitList(value)
        }

    private var colorItemListener: ((ColorItem) -> Unit)? = null

    init {
        binding = ColorPickerViewBinding.inflate(LayoutInflater.from(context), this)
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = this@ColorPicker.adapter
        }
    }

    fun setupColorPicker(
        colorList: List<ColorItem>,
        clickColorListener: (ColorItem) -> Unit
    ){
        setColorList(colorList)
        colorItemListener = clickColorListener
    }

    private fun setColorList(list: List<ColorItem>){
        listOfColors = list
    }

    private fun setClickColorListener(colorItem: ColorItem){
        colorItemListener?.invoke(colorItem)
        root.setBackgroundResource(colorItem.setBackgroundColorRes())
    }

}