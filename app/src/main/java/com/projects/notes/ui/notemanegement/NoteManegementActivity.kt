package com.projects.notes.ui.notemanegement

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.projects.notes.databinding.NoteManegementActivityBinding
import com.projects.notes.databinding.NotesListActivityBinding
import com.projects.notes.ui.customview.uikit.colorpicker.ColorPicker
import com.projects.notes.ui.notelist.model.ColorBackground
import com.projects.notes.ui.notelist.model.ColorItem

class NoteManegementActivity: AppCompatActivity() {

    private lateinit var binding: NoteManegementActivityBinding

    // region Binding layout views
    private val toolbar: Toolbar
        get() = binding.toolbar

    private val colorPicker: ColorPicker
        get() = binding.colorPiker
    // endregion

    // region Constants
    private val listOfColors: List<ColorItem>
        get() = listOf(
            ColorItem(ColorBackground.ORANGE, false),
            ColorItem(ColorBackground.MAGENTA, false),
            ColorItem(ColorBackground.YELLOW, false),
            ColorItem(ColorBackground.GREEN_AQUA, false),
            ColorItem(ColorBackground.LIGHT, false),
            ColorItem(ColorBackground.BLUE, false),
            ColorItem(ColorBackground.DARK, true),
        )
    // endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NoteManegementActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupColorPicker()
    }


    private fun setupColorPicker(){
        colorPicker.setupColorPicker(
            colorList = listOfColors,
            clickColorListener = {
                binding.root.setBackgroundResource(it.setBackgroundRes())
            }
        )
    }

}