package com.amier.jelajahrasa.external.events

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveData <T> : MutableLiveData<T>(){
    private val mPending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasActiveObservers()){
            Log.w("SingleLiveData","Multiple Observers is On but only one will notified")
        }

        super.observe(owner, Observer {
            if (mPending.compareAndSet(true,false)){
                observer.onChanged(it)
            }
        })
    }

    @MainThread
    override fun setValue(value: T) {
        mPending.set(true)
        super.setValue(value)
    }
    @MainThread
    fun call(){
        value
    }
}