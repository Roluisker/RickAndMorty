package com.rick.and.morty.core

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.rick.and.morty.data.network.live_data.ConnectivityStatus

abstract class BaseFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ConnectivityStatus(requireContext()).observe(lifecycleOwnerOwner()) { isNetworkAvailable ->
            onNetworkChange(isNetworkAvailable)
        }
    }

    fun navController(): NavController? {
        return view?.let { Navigation.findNavController(it) }
    }

    abstract fun onNetworkChange(isNetworkAvailable: Boolean)
    abstract fun lifecycleOwnerOwner(): LifecycleOwner
}