package kz.digitalart.app.domain.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ErrorCodeInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val response = chain.proceed(request)

        if (response.code == 401) {
            // Не авторизован
            throw Exception("Пожалуйста, авторизуйтесь")
        } else if (response.code >= 400) {
            // Остальные ошибки
            val str = response.body?.string()
            throw Exception("Пожалуйста, авторизуйтесь")
        }

        return response
    }
}