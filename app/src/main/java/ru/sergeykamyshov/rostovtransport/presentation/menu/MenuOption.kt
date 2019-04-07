package ru.sergeykamyshov.rostovtransport.presentation.menu

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class MenuOption

class SimpleOption(
        val type: OptionType,
        @DrawableRes val iconId: Int,
        @StringRes val textId: Int
) : MenuOption()

enum class OptionType {
    HELP,
    CARD,
    ABOUT,
    RATE,
    DEVELOPER
}