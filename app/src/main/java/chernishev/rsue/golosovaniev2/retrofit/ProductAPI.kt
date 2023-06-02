package chernishev.rsue.golosovaniev2.retrofit

import chernishev.rsue.golosovaniev2.retrofit.question.*
import chernishev.rsue.golosovaniev2.retrofit.title.*
import chernishev.rsue.golosovaniev2.retrofit.user.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductAPI {
    @GET("usersjsontest")
    suspend fun getUsers(): Users

    @GET("votesjsontest")
    suspend fun getTitles(): Titles

    @GET("questionsjsontest")
    suspend fun getQuestions(): Questions

    @GET("choicesjsontest")
    suspend fun getChoices(): Choices


    @POST("useraddjson")
    suspend fun postUser(@Body addUserRequest: AddUserRequest):ServerResp

    @POST("voteaddjson")
    suspend fun postTitle(@Body addTitleRequest: AddTitleRequest):ServerResp

    @POST("questionaddjson")
    suspend fun postQuestion(@Body addTitleRequest: AddQuestionRequest):ServerResp

    @POST("choiceaddjson")
    suspend fun postChoise(@Body addTitleRequest: AddChoiceRequest):ServerResp

    @POST("userauthorizationjson")
    suspend fun postAuthorization(@Body authorization: Authorization):ServerResp



    @POST("usergetjson")
    suspend fun postUserWrite(@Body idRequest: IdRequest): ServerRespUser

    @POST("questiongetjson")
    suspend fun postQuestionWrite(@Body idRequest: IdRequest): ServerRespQuestion

    @POST("votegetjson")
    suspend fun postTitleWrite(@Body idRequest: IdRequest): ServerRespTitle



    @POST("usereditjson")
    suspend fun postModUser(@Body editUserRequest: EditUserRequest):ServerResp

    @POST("questioneditjson")
    suspend fun postModQuestion(@Body editQuestioRequest: EditQuestionRequest):ServerResp

    @POST("voteeditjson")
    suspend fun postModTitle(@Body editTitleRequest: EditTitleRequest):ServerResp



    @POST("userdeletejson")
    suspend fun postDelUser(@Body editUserRequest: DeleteUserRequest):ServerResp

    @POST("questiondeletejson")
    suspend fun postDelQuestion(@Body deleteQuestionRequest: DeleteQuestionRequest):ServerResp

    @POST("votedeletejson")
    suspend fun postDelTitle(@Body deleteTitleRequest: DeleteTitleRequest):ServerResp
}