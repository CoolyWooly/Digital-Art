package kz.digitalart.app.core

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

abstract class BaseViewModel : ViewModel() {

    private var viewModelJob = Job()
    val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    // Do work in IO
    fun <P> doWork(doOnAsyncBlock: suspend CoroutineScope.() -> P) {
        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            Log.e("Coroutine-BaseViewModel", e.toString())
        }) {
            withContext(Dispatchers.IO) {
                doOnAsyncBlock.invoke(this)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}