package kz.digitalart.app.ui.liked

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kz.digitalart.app.domain.model.Exhibit
import kz.digitalart.app.domain.model.response.ErrorModel
import kz.digitalart.app.domain.usecase.GetExhibitsUseCase
import javax.inject.Inject

class LikedViewModel @Inject constructor(
    private val getExhibitsUseCase: GetExhibitsUseCase
) : ViewModel() {
    private val TAG = this::class.java.simpleName
    val exhibitsData: MutableLiveData<List<Exhibit>> by lazy { MutableLiveData<List<Exhibit>>() }
    val error: MutableLiveData<ErrorModel> by lazy { MutableLiveData<ErrorModel>() }

    init {
        val exhibits = arrayListOf<Exhibit>()
        val photos = ArrayList<String>()
        val ph = "http://egov.kz/cms/sites/default/files/news/kak_propisat.jpg"
        photos.add(ph)
        val exhibit1 = Exhibit("name", "desc", "year", "author", 5.0, photos, "audio")
        val exhibit2 = Exhibit("name2", "desc2", "year2", "author2", 5.2, photos, "audio2")
        val exhibit3 = Exhibit("name3", "desc3", "year3", "author3", 5.3, photos, "audio3")
        val exhibit4 = Exhibit("name3", "desc3", "year3", "author3", 5.3, photos, ph)
        val exhibit5 = Exhibit("name3", "desc3", "year3", "author3", 5.3, photos, "audio3")
        val exhibit6 = Exhibit("name3", "desc3", "year3", "author3", 5.3, photos, "audio3")
        val exhibit7 = Exhibit("name3", "desc3", "year3", "author3", 5.3, photos, "audio3")
        val exhibit8 = Exhibit("name3", "desc3", "year3", "author3", 5.3, photos, "audio3")
        val exhibit9 = Exhibit("name3", "desc3", "year3", "author3", 5.3, photos, "audio3")
        val exhibit10 = Exhibit("name3", "desc3", "year3", "author3", 5.3, photos, "audio3")
        val exhibit11 = Exhibit("name3", "desc3", "year3", "author3", 5.3, photos, "audio3")
        val exhibit12 = Exhibit("name3", "desc3", "year3", "author3", 5.3, photos, "audio3")
        exhibits.add(exhibit1)
        exhibits.add(exhibit2)
        exhibits.add(exhibit3)
        exhibits.add(exhibit4)
        exhibits.add(exhibit5)
        exhibits.add(exhibit6)
        exhibits.add(exhibit7)
        exhibits.add(exhibit8)
        exhibits.add(exhibit9)
        exhibits.add(exhibit10)
        exhibits.add(exhibit11)
        exhibits.add(exhibit12)
        exhibitsData.value = exhibits

//        getExhibitsUseCase.execute {
//            onComplete {
//                Log.d(TAG, it.toString())
//                exhibitsData.value = it
//            }
//
//            onError { throwable ->
//                if (throwable.errorStatus == ErrorStatus.UNAUTHORIZED) {
//                    doReshresh()
//                } else {
//                    error.value = throwable
//                }
//            }
//
//            onCancel {
//
//            }
//        }
    }

    private fun doReshresh() {

    }

    override fun onCleared() {
        super.onCleared()
        getExhibitsUseCase.unsubscribe()
    }
}