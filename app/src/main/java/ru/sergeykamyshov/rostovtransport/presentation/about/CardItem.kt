package ru.sergeykamyshov.rostovtransport.presentation.about

sealed class CardItem {
    object HeaderCard : CardItem()
    object TextCard : CardItem()
    object ContactsCard : CardItem()
}