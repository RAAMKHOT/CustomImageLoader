package com.greedygameimageloader

import Children
import com.greedygameimageloader.helper.CommonUtils
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.net.ssl.SSLHandshakeException
import kotlin.collections.ArrayList

class LandingPresenter(private val view: LandingView) {
    private val repository = CommonUtils.getSOService()
    fun getRedditImages() {
        CompositeDisposable().add(
            repository.getImages().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(
                { result ->

                    io.reactivex.Observable
                        .fromIterable(result.data.children)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.computation())
                        .filter { imageItem ->imageItem.data.url.contains(".jpg") || imageItem.data.url.contains(".png")}
                        .toList()
                        .subscribe(object : SingleObserver<List<Children>> {
                            override fun onSubscribe(d: Disposable) {
                            }


                            override fun onSuccess(images: List<Children>) {
                                if (images != null) {
                                    view.onSuccess(images as ArrayList<Children>)
                                }
                            }

                            override fun onError(e: Throwable) {
                                /* to do */
                            }
                        })
                },
                { error ->
                    if (error is SSLHandshakeException) {
                        view.onError("We are getting SSL Handshake Exception, We need to add SSL cert in Server side \n Please use the lower version device.")
                    } else {
                        view.onError("Somthing went wrong")
                    }
                })
        )
    }

}
