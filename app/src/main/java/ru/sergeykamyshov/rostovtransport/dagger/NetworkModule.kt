package ru.sergeykamyshov.rostovtransport.dagger

import dagger.Module
import dagger.Provides
import ru.sergeykamyshov.rostovtransport.App
import ru.sergeykamyshov.rostovtransport.data.network.RestService

@Module
class NetworkModule {

    @Provides
    fun providesRestService(): RestService {
        return App.retrofit.create(RestService::class.java)
    }

}