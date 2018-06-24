package ru.sergeykamyshov.rostovtransport.data.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import ru.sergeykamyshov.rostovtransport.data.network.model.about.About
import ru.sergeykamyshov.rostovtransport.data.network.model.card.CardBuy
import ru.sergeykamyshov.rostovtransport.data.network.model.card.CardDeposit
import ru.sergeykamyshov.rostovtransport.data.network.model.card.CardInfo
import ru.sergeykamyshov.rostovtransport.data.network.model.card.CardQuestions
import ru.sergeykamyshov.rostovtransport.data.network.model.help.Help
import ru.sergeykamyshov.rostovtransport.data.network.model.routes.Routes
import ru.sergeykamyshov.rostovtransport.data.network.model.schedule.Directions

interface RestService {

    // Маршруты
    @GET("/tmp/rostov-transport/test/routes/{transport}/{transport}.json")
    fun getRoutesFor(@Path("transport") transport: String): Call<Routes>

    // Расписание
    @GET("/tmp/rostov-transport/test/directions.json")
    fun getDirections(): Call<Directions>

    // Справка
    @GET("/tmp/rostov-transport/test/help/{help}.json")
    fun getHelpFor(@Path("help") help: String): Call<Help>

    // Транспортная карта
    @GET("/tmp/rostov-transport/test/card/card_info.json")
    fun getCardInfo(): Call<CardInfo>

    @GET("/tmp/rostov-transport/test/card/card_buy.json")
    fun getCardBuy(): Call<CardBuy>

    @GET("/tmp/rostov-transport/test/card/card_deposit.json")
    fun getCardDeposit(): Call<CardDeposit>

    @GET("/tmp/rostov-transport/test/card/card_questions.json")
    fun getCardQuestions(): Call<CardQuestions>

    // О проекте
    @GET("/tmp/rostov-transport/test/about.json")
    fun getAbout(): Call<About>

}