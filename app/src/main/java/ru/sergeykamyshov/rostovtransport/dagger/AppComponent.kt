package ru.sergeykamyshov.rostovtransport.dagger

import dagger.Component
import ru.sergeykamyshov.rostovtransport.MainActivity

@Component(modules = arrayOf(NetworkModule::class))
interface AppComponent {

    fun inject(activity: MainActivity)

}