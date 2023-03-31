package com.uikit.colorpicker

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.uikit.colorpicker.R

data class ColorItem(
    val color: ColorBackground,
    val isEnabled: Boolean = false
) {

    @ColorRes
    fun setBackgroundColorRes(): Int {
        return color.colorRes
    }

    @DrawableRes
    fun setBackgroundRes(): Int {
        return color.backgroundRes
    }

}

enum class ColorBackground(@ColorRes val colorRes: Int, @DrawableRes val backgroundRes: Int) {
    BLUE(
        R.color.noteBlue,
        R.drawable.background_view_border_blue
    ),
    YELLOW(
        R.color.noteYellow,
        R.drawable.background_view_border_yellow
    ),
    ORANGE(
        R.color.noteOrange,
        R.drawable.background_view_border_orange
    ),
    GREEN_AQUA(
        R.color.noteGreenAqua,
        R.drawable.background_view_border_green_aqua
    ),
    MAGENTA(
        R.color.noteMagenta,
        R.drawable.background_view_border_magenta
    ),
    LIGHT(
        R.color.noteLight,
        R.drawable.background_view_border_light
    ),
    DARK(
        R.color.noteDark,
        R.drawable.background_view_border_dark
    )
}