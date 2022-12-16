package com.finwin.finwinproject.application

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.finwin.finwinproject.api_service.APIService


class FinwinApplication : MultiDexApplication() {
    private var mRetrofitService: APIService? = null

    override fun onCreate() {
        super.onCreate()


    }

    fun getRetrofitService(): APIService? {
        if (mRetrofitService == null) {
            mRetrofitService = APIService.Factory.create()
        }
        return mRetrofitService
    }

    companion object {
        fun get(context: Context): FinwinApplication {
            return context.applicationContext as FinwinApplication
        }
    }
}