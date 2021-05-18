package com.sohumngs.uapps.dw

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.Nullable
import android.view.View

/**
 * Base view interface to implement by all views.
 *
 * @author Antonina
 */
interface BaseView {
    @LayoutRes
    fun getContentResource(): Int

    fun init(view: View, @Nullable state: Bundle?)
}