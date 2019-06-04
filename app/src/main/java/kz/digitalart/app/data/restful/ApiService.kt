package kz.digitalart.app.data.restful

import kotlinx.coroutines.Deferred
import kz.digitalart.app.domain.model.About
import kz.digitalart.app.domain.model.Exhibit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("api/exhibit/{id}")
    fun getExhibitAsync(
        @Path("id") id: Int?,
        @Query("lang") lang: String?
    ): Deferred<Exhibit>

    @GET("api/exhibits")
    fun getExhibitsAsync(
        @Query("page") page : Int?,
        @Query("limit") limit : Int?,
        @Query("search") search : String?,
        @Query("lang") lang: String?
    ): Deferred<List<Exhibit>>

    @GET("api/exhibits/popular")
    fun getPopularAsync(
        @Query("page") page : Int?,
        @Query("limit") limit : Int?,
        @Query("search") search : String?,
        @Query("lang") lang: String?
    ): Deferred<List<Exhibit>>

    @GET("api/app/info")
    fun getAboutAsync(): Deferred<About>
}