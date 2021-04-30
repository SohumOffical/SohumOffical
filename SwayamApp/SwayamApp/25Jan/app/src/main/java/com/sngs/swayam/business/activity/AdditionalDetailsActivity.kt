package com.sngs.swayam.business.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.sngs.swayam.business.R
import com.sngs.swayam.business.network.WebUtlis.Links
import kotlinx.android.synthetic.main.activity_additional_details.*

class AdditionalDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        overridePendingTransition(
            R.anim.slide_in_right,
            R.anim.slide_out_left
        );
        setContentView(R.layout.activity_additional_details)

        click_fun()
    }

    private fun click_fun() {

        ivBack.setOnClickListener {
            finish()
        }

        if (select_state != null)
        {
            val arrayAdapter = ArrayAdapter(applicationContext,R.layout.spinnerlayout,R.id.txt_spinner, Links.select_state)
            select_state.adapter = arrayAdapter

            select_state.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // Code to perform some action when nothing is selected
                }
            }
        }
    }
}
