package com.nelsonalmendra.rocketlaunches.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nelsonalmendra.rocketlaunches.R
import com.nelsonalmendra.rocketlaunches.data.database.Rocket
import com.nelsonalmendra.rocketlaunches.ui.detail.RocketFragmentDetail

class MainActivity : AppCompatActivity(), RocketsListFragment.IRocketListFragment {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left, R.anim.slide_out_right, R.anim.slide_in_right)
                    .add(R.id.root_layout, RocketsListFragment.newInstance(), "rocketList")
                    .commit()
        }
    }

    override fun onRocketClick(rocket: Rocket) {
        val detailsFragment = RocketFragmentDetail.newInstance()
        val arguments = Bundle()
        arguments.putParcelable(RocketFragmentDetail.ROCKET, rocket)
        detailsFragment.arguments = arguments
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left, R.anim.slide_out_right, R.anim.slide_in_right)
                .replace(R.id.root_layout, detailsFragment, "rocketDetails")
                .addToBackStack(null)
                .commit()
    }
}
