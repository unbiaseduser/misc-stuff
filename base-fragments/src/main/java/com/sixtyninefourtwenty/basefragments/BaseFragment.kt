package com.sixtyninefourtwenty.basefragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null
    private val binding: VB get() = _binding!!

    protected abstract fun initBinding(inflater: LayoutInflater, container: ViewGroup?): VB
    protected abstract fun onViewCreated(binding: VB, savedInstanceState: Bundle?)
    protected open fun onDestroyView(binding: VB) {}

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = initBinding(inflater, container)
        return binding.root
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        onViewCreated(binding, savedInstanceState)
    }

    final override fun onDestroyView() {
        super.onDestroyView()
        onDestroyView(binding)
        _binding = null
    }
}
