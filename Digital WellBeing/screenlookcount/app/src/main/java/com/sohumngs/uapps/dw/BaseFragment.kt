package com.sohumngs.uapps.dw

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Base fragment class to use by all fragments.
 *
 * @author Antonina
 */
abstract class BaseFragment : Fragment(), BaseView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getContentResource(), container, false)
        init(view, savedInstanceState)

        return view
    }
}