package kz.digitalart.app.data.restful

import kotlinx.coroutines.Deferred
import kz.digitalart.app.domain.model.About
import kz.digitalart.app.domain.model.Exhibit
import retrofit2.http.*

interface ApiService {

    @GET("api/exhibit/{id}")
    fun getExhibitAsync(
        @Path("id") id: Int?,
        @Query("lang") lang: String?
    ): Deferred<Exhibit>

    @GET("api/exhibits")
    fun getExhibitsAsync(
        @Query("page") page: Int?,
        @Query("limit") limit: Int?,
        @Query("search") search: String?,
        @Query("lang") lang: String?
    ): Deferred<List<Exhibit>>

    @GET("api/exhibits/popular")
    fun getPopularAsync(
        @Query("page") page: Int?,
        @Query("limit") limit: Int?,
        @Query("search") search: String?,
        @Query("lang") lang: String?
    ): Deferred<List<Exhibit>>

    @GET("api/app/info")
    fun getAboutAsync(
        @Query("lang") lang: String?
    ): Deferred<About>

    @POST("exhibit/rate")
    fun setExhibitRateAsync(
        @Field("id") id: Int?,
        @Field("rate") rate: Double?
    ): Deferred<About>
}