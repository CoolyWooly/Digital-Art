package kz.digitalart.app.data.restful

import kz.digitalart.app.domain.model.About
import kz.digitalart.app.domain.model.Exhibit
import kz.digitalart.app.domain.model.Rating
import retrofit2.http.*

interface ApiService {

    @GET("api/exhibit/{id}")
    suspend fun getExhibitAsync(
        @Path("id") id: Int?,
        @Query("lang") lang: String?
    ): Exhibit

    @GET("api/exhibits")
    suspend fun getExhibitsAsync(
        @Query("page") page: Int?,
        @Query("limit") limit: Int?,
        @Query("search") search: String?,
        @Query("lang") lang: String?,
        @Query("by_date") by_date: String?
    ): List<Exhibit>

    @GET("api/exhibits/popular")
    suspend fun getPopularAsync(
        @Query("page") page: Int?,
        @Query("limit") limit: Int?,
        @Query("search") search: String?,
        @Query("lang") lang: String?
    ): List<Exhibit>

    @GET("api/app/info")
    suspend fun getAboutAsync(
        @Query("lang") lang: String?
    ): About

    @FormUrlEncoded
    @POST("api/exhibit/rate")
    suspend fun setExhibitRateAsync(
        @Field("id") id: Int?,
        @Field("rate") rate: Double?
    ): Rating
}