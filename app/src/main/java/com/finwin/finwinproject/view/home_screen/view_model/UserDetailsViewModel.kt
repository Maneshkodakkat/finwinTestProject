package com.finwin.finwinproject.view.home_screen.view_model

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.finwin.finwinproject.application.FinwinApplication
import com.finwin.finwinproject.call_back.RetrofitCallback
import com.finwin.finwinproject.call_back.RetrofitMainCallback
import com.finwin.finwinproject.database.RegisterRepository
import com.finwin.finwinproject.view.home_screen.model.DetailsResponseModel
import com.finwin.finwinproject.utils.Utils
import com.finwin.finwinproject.view.home_screen.model.DetailsResponseModelItem
import com.google.gson.Gson
import kotlinx.coroutines.launch

class UserDetailsViewModel (application: Application):AndroidViewModel(application) {
    var progressDialog: MutableLiveData<Int?>? = MutableLiveData()

    init {
        progressDialog?.value = 8

    }

    fun getPhotos(
        getPhotosRetrofitCallBack: RetrofitCallback<DetailsResponseModel>
    ) {
        if (!Utils.isNetworkConnected(getApplication())) {
            Toast.makeText(
                getApplication<Application>(),
                "No Internet Connection",
                Toast.LENGTH_SHORT
            ).show()
            return
        } else {
            progressDialog?.value = 0
        val aiiceApplication = FinwinApplication.get(getApplication())
        val apiService = aiiceApplication.getRetrofitService()

        apiService?.getPhotos(
        )?.enqueue(RetrofitMainCallback(getPhotosRetrofitCallBack))
    }
}

    fun getDetailsPhotos(
        getDetailsRetrofitCallBack: RetrofitCallback<DetailsResponseModelItem>,
        id: Int
    ) {
        if (!Utils.isNetworkConnected(getApplication())) {
            Toast.makeText(getApplication<Application>(),"No Internet Connection",Toast.LENGTH_SHORT).show()
            return
        }
        progressDialog?.value = 0
        val aiiceApplication = FinwinApplication.get(getApplication())
        val apiService = aiiceApplication.getRetrofitService()

        apiService?.getDetailsPhotos(id
        )?.enqueue(RetrofitMainCallback(getDetailsRetrofitCallBack))
    }

}