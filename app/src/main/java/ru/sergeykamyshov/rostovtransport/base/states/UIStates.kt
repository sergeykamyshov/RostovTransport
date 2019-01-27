package ru.sergeykamyshov.rostovtransport.base.states

sealed class UIState

object Loading : UIState()

object HasData : UIState()

object NoData : UIState()

object Error : UIState()