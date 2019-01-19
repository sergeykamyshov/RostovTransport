package ru.sergeykamyshov.rostovtransport.data.json

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import ru.sergeykamyshov.rostovtransport.data.models.about.About
import ru.sergeykamyshov.rostovtransport.data.models.card.CardBuy
import ru.sergeykamyshov.rostovtransport.data.models.card.CardDeposit
import ru.sergeykamyshov.rostovtransport.data.models.card.CardInfo
import ru.sergeykamyshov.rostovtransport.data.models.card.CardQuestions
import ru.sergeykamyshov.rostovtransport.data.models.help.Help
import ru.sergeykamyshov.rostovtransport.data.models.online.Transport
import ru.sergeykamyshov.rostovtransport.data.models.routes.RouteInfo
import ru.sergeykamyshov.rostovtransport.data.models.routes.Routes
import ru.sergeykamyshov.rostovtransport.data.models.schedule.Directions

interface JsonDataApi {

    // Маршруты
    @GET("routes/{transport}/{transport}.json")
    fun getRoutesFor(@Path("transport") transport: String): Call<Routes>

    @GET("routes/{transport}/{route}.json")
    fun getRoute(@Path("transport") transport: String, @Path("route") route: String): Call<RouteInfo>

    // Расписание
    @GET("schedule/directions.json")
    fun getDirections(): Call<Directions>

    // Транспорт онлайн
    @GET("online/transport_list.json")
    fun getTransportList(): Call<Transport>

    // Справка
    @GET("help/{help}.json")
    fun getHelpFor(@Path("help") help: String): Call<Help>

    // Транспортная карта
    @GET("card/card_info.json")
    fun getCardInfo(): Call<CardInfo>

    @GET("card/card_buy.json")
    fun getCardBuy(): Call<CardBuy>

    @GET("card/card_deposit.json")
    fun getCardDeposit(): Call<CardDeposit>

    @GET("card/card_questions.json")
    fun getCardQuestions(): Call<CardQuestions>

    // О проекте
    @GET("about/about.json")
    fun getAbout(): Call<About>

}