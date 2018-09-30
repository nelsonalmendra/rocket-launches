package com.nelsonalmendra.rocketlaunches.ui.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nelsonalmendra.rocketlaunches.R
import com.nelsonalmendra.rocketlaunches.data.database.Rocket
import kotlinx.android.synthetic.main.fragment_rocket_detail.view.*

class RocketFragmentDetail : Fragment() {

    companion object {

        const val ROCKET = "rocket"

        fun newInstance(): RocketFragmentDetail {
            return RocketFragmentDetail()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rocket = arguments?.getParcelable(ROCKET) as Rocket
        val view: View = inflater.inflate(R.layout.fragment_rocket_detail, container, false)
        view.name.text = rocket.name
        view.country.text = rocket.country
        view.engines.text = rocket.engines.number.toString()
        return view
    }
}