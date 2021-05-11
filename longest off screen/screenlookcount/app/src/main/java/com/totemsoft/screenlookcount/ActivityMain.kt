package com.totemsoft.screenlookcount

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.totemsoft.screenlookcount.background.LookCounterService
import com.totemsoft.screenlookcount.db.ScreenCounterDb
import com.totemsoft.screenlookcount.fragment.about.FragmentAbout
import com.totemsoft.screenlookcount.fragment.main.FragmentMain
import com.totemsoft.screenlookcount.utils.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*





class ActivityMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)






        initView()
    }

    override fun onStart() {
        super.onStart()

        if (AppPreferences.shouldRunCountingService) {
            startService()
        }

        if (!isFragmentInBackStack(C.FRAGMENT_TAG_MAIN)) {
            addFragment(FragmentMain::class.java, C.FRAGMENT_TAG_MAIN, sv_container, false, null)
        }
    }

    private fun initView() {
        b_about.setOnClickListener {
            showOrHideAbout()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateMainView() {
        val mainFragment = getFragmentByTag(C.FRAGMENT_TAG_MAIN)
        if (mainFragment is FragmentMain) {
            val nowDateString = Date();
            mainFragment.getMainPresenter().setCountersValues(this, nowDateString, "")
        }
    }

    private fun showOrHideAbout() {
        if (isFragmentVisible(C.FRAGMENT_TAG_MAIN)) {
            addFragment(FragmentAbout::class.java, C.FRAGMENT_TAG_ABOUT, sv_container.id, true, null)
        } else {
            supportFragmentManager.popBackStack()
        }
    }




    private fun startService() {
        startService(Intent(this, LookCounterService::class.java))
    }

    private fun stopService() {
        stopService(Intent(this, LookCounterService::class.java))
    }

    private fun clearAllData() {
        ScreenCounterDb.getDatabase(this).dropDb()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showClearAllDataUndoSnackbar() {
        val undoSnackbar = Snackbar.make(sv_container, getString(R.string.snack_bar_clear_text), Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.snack_bar_clear_action_undo)) {
                    ScreenCounterDb.getDatabase(this).setDbClearFlag(false)
                    updateMainView()
                }
                .addCallback(object : Snackbar.Callback() {
                    override fun onDismissed(snackbar: Snackbar?, event: Int) {
                        super.onDismissed(snackbar, event)
                        if (event != Snackbar.Callback.DISMISS_EVENT_ACTION) {
                            clearAllData()
                        }
                    }
                })

        // styling
        undoSnackbar.setActionTextColor(ContextCompat.getColor(this, R.color.snack_bar_action))


        undoSnackbar.show()
    }

    private fun switchService(menuItem: MenuItem) {
        val startCountingTitle = getString(R.string.action_start_counting)
        val stopCountingTitle = getString(R.string.action_stop_counting)
        if (menuItem.title == startCountingTitle) {
            AppPreferences.shouldRunCountingService = true
            startService()
            menuItem.title = stopCountingTitle
        } else {
            AppPreferences.shouldRunCountingService = false
            stopService()
            menuItem.title = startCountingTitle
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun clearDataAction() {
        ScreenCounterDb.getDatabase(this).setDbClearFlag(true)
        updateMainView()
        showClearAllDataUndoSnackbar()
    }

    private fun rateAppAction() {
        val appPackageName = packageName
        try {
            val googlePlayIntent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.rate_google_play_uri, appPackageName)))
            startActivity(googlePlayIntent)
        } catch (exception: ActivityNotFoundException) {
            val googlePlayWebPageIntent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.rate_google_play_uri_browser, appPackageName)))
            startActivity(googlePlayWebPageIntent)
        }
    }

    private fun shareWithFriendsAction() {
        val shareIntent = Intent()
        with(shareIntent) {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, getString(R.string.share_text, packageName))
            type = "text/plain"
            startActivity(Intent.createChooser(this, resources.getText(R.string.share_chooser)))
        }
    }
}