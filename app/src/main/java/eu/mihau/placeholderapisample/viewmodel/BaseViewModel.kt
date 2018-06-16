package eu.mihau.placeholderapisample.viewmodel

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

    val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}

fun Disposable.bind(baseViewModel: BaseViewModel) {
    baseViewModel.compositeDisposable.add(this)
}