package com.finwin.finwinproject.view.home_screen.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.finwin.finwinproject.R
import com.finwin.finwinproject.call_back.RetrofitCallback
import com.finwin.finwinproject.config.ApplicationConstants
import com.finwin.finwinproject.database.RegisterDatabase
import com.finwin.finwinproject.database.RegisterRepository
import com.finwin.finwinproject.databinding.FragmentDetailsBinding
import com.finwin.finwinproject.view.home_screen.model.DetailsResponseModel
import com.finwin.finwinproject.view.home_screen.model.DetailsResponseModelItem
import com.finwin.finwinproject.view.home_screen.view_model.UserDetailsViewModel
import com.finwin.finwinproject.view.home_screen.view_model.UserDetalisFactory
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class DetailsFragment : Fragment() {

    private lateinit var detailsViewModel: UserDetailsViewModel
    private lateinit var binding: FragmentDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_details, container, false
        )
        detailsViewModel = ViewModelProvider(this).get(UserDetailsViewModel::class.java)
        binding.viewModel = detailsViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailsViewModel.getDetailsPhotos(getDetailsRetrofitCallBack, arguments?.getInt("id",0)!!)
    }
    private val getDetailsRetrofitCallBack= object :
        RetrofitCallback<DetailsResponseModelItem> {
        override fun onSuccessfulResponse(responseBody: Response<DetailsResponseModelItem>) {
            if (activity != null && isAdded){
//                        if (!responseBody.body()?.thumbnailUrl.isNullOrEmpty()) {
                            //catType=responseBody.body()?.Result!!.Headers.get(0).Details[0].DetailTypeId

                                try {

                                    context?.let { it1 ->
                                        Glide.with(it1)
                                            .load(responseBody.body()?.thumbnailUrl)
                                            .thumbnail(0.5f)
                                            .apply(
                                                RequestOptions.placeholderOf(R.drawable.ic_launcher_foreground)
                                                    .error(R.drawable.ic_launcher_foreground)
                                            )
                                            .into( binding.imageToShowImageView)
                                    }

                                } catch (e: IOException) {
                                    System.out.println(e)
                                }

//                            }

                    }

                        if (!responseBody.body()?.title!!.isNullOrEmpty()) {
                            binding?.firstNameDetailsTextView?.text = responseBody.body()?.title
                        }




                }

        override fun onBadRequest(errorBody: Response<DetailsResponseModelItem>?) {
        }

        override fun onServerError(response: Response<*>?) {
        }

        override fun onUnAuthorized() {
        }

        override fun onForbidden() {
        }

        override fun onFailure(s: String?) {
        }

        override fun onEverytime() {
        }
    }
}