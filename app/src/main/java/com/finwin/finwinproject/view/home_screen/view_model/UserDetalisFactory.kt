package com.finwin.finwinproject.view.home_screen.view_model

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.finwin.finwinproject.database.RegisterRepository
import java.lang.IllegalArgumentException

class UserDetalisFactory (
    private val application: Application
): ViewModelProvider.Factory{
    @Suppress("Unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UserDetailsViewModel::class.java)) {
            return UserDetailsViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}