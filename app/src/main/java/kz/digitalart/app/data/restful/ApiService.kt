package kz.digitalart.app.data.restful

import kotlinx.coroutines.Deferred
import kz.digitalart.app.domain.model.Exhibit
import retrofit2.http.GET

interface ApiService {

    @GET("api/")
    fun getExhibitsAsync(
    ): Deferred<List<Exhibit>>
}