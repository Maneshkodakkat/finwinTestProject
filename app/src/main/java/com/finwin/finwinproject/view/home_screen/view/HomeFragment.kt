package com.finwin.finwinproject.view.home_screen.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.finwin.finwinproject.R
import com.finwin.finwinproject.call_back.RetrofitCallback
import com.finwin.finwinproject.database.RegisterDatabase
import com.finwin.finwinproject.database.RegisterRepository
import com.finwin.finwinproject.databinding.HomeFragmentBinding
import com.finwin.finwinproject.view.home_screen.view_model.UserDetailsViewModel
import com.finwin.finwinproject.view.home_screen.view_model.UserDetalisFactory
import com.finwin.finwinproject.view.home_screen.model.DetailsResponseModel
import com.finwin.finwinproject.view.home_screen.model.DetailsResponseModelItem
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var userDetailsViewModel: UserDetailsViewModel
    private lateinit var binding: HomeFragmentBinding
    private var detailsViewAdapter: DetailsViewAdapter? = null
    private val items = mutableListOf<DetailsResponseModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.home_fragment, container, false
        )

        val application = requireNotNull(this.activity).application

        val dao = RegisterDatabase.getInstance(application).registerDatabaseDao

        val repository = RegisterRepository(dao)


        userDetailsViewModel =
            ViewModelProvider(this).get(UserDetailsViewModel::class.java)

        binding.userDelailsLayout = userDetailsViewModel

        binding.lifecycleOwner = this

        val manager: LinearLayoutManager
        manager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )


        binding?.detailsRecyclerView?.layoutManager = manager

        detailsViewAdapter = DetailsViewAdapter(
            requireContext(), mutableListOf()
        )
        binding?.detailsRecyclerView?.adapter = detailsViewAdapter
        userDetailsViewModel.getPhotos(getPhotoRetrofitCallBack)

        detailsViewAdapter!!.setOnItemClickListener(object :
            DetailsViewAdapter.OnItemClickListener {

            override fun onItemClick(bean: DetailsResponseModelItem) {
//                    Log.e("branchId",bean.RefId.toString())
                var bundle = bundleOf(
                    "id" to bean.id
                )
                Navigation.findNavController(view!!)
                    .navigate(R.id.action_homeFragment_to_detailsFragment, bundle)

            }

        })

        return binding.root

    }

    private val getPhotoRetrofitCallBack = object : RetrofitCallback<DetailsResponseModel> {
        override fun onSuccessfulResponse(response: Response<DetailsResponseModel>) {
            userDetailsViewModel.progressDialog?.value = 0
//          Log.e("respo",response?.body()!!.toString())
//            detailsViewAdapter?.update(response?.body()!!)
//            if (response.isSuccessful) {
//                detailsViewAdapter?.update(response?.body()!!)
////                val responseItems = response.body()
////                items.addAll(responseItems)
//                detailsViewAdapter?.notifyDataSetChanged()
//            } else {
//                // Handle error
//            }
            detailsViewAdapter?.update(response?.body()!!)
            detailsViewAdapter?.notifyDataSetChanged()
        }

        override fun onBadRequest(response: Response<DetailsResponseModel>) {

        }

        override fun onServerError(response: Response<*>?) {

        }

        override fun onUnAuthorized() {

        }

        override fun onForbidden() {

        }

        override fun onFailure(failure: String) {

        }

        override fun onEverytime() {
            userDetailsViewModel?.progressDialog?.value = 8
        }
    }

}