package kz.digitalart.app.data.cloud.rest

import kz.digitalart.app.domain.model.AboutModel
import kz.digitalart.app.domain.model.ExhibitModel
import kz.digitalart.app.domain.model.RatingModel
import retrofit2.http.*

interface ApiService {

    @GET("api/exhibits/{id}")
    suspend fun getExhibitAsync(
        @Path("id") id: Int?,
        @Query("lang") lang: String?
    ): ExhibitModel

    @GET("api/exhibits")
    suspend fun getExhibitsAsync(
        @Query("page") page: Int?,
        @Query("limit") limit: Int?,
        @Query("search") search: String?,
        @Query("lang") lang: String?,
        @Query("by_date") by_date: String?
    ): List<ExhibitModel>

    @GET("api/exhibits/popular")
    suspend fun getPopularAsync(
        @Query("page") page: Int?,
        @Query("limit") limit: Int?,
        @Query("search") search: String?,
        @Query("lang") lang: String?
    ): List<ExhibitModel>

    @GET("api/info")
    suspend fun getAboutAsync(
        @Query("lang") lang: String?
    ): AboutModel

    @FormUrlEncoded
    @POST("api/exhibit/rate")
    suspend fun setExhibitRateAsync(
        @Field("id") id: Int?,
        @Field("rate") rate: Double?
    ): RatingModel
}