package ru.sergeykamyshov.rostovtransport.presentation.menu

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class MenuOption

class SimpleOption(
        @DrawableRes val iconId: Int,
        @StringRes val textId: Int
) : MenuOption()