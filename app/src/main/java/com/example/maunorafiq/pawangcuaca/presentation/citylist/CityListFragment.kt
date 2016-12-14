package com.example.maunorafiq.pawangcuaca.presentation.citylist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout

import com.example.maunorafiq.pawangcuaca.R
import com.example.maunorafiq.pawangcuaca.presentation.base.BaseFragment
import com.example.maunorafiq.pawangcuaca.presentation.internal.di.component.WeatherComponent
import com.example.maunorafiq.pawangcuaca.presentation.model.CityModel
import com.example.maunorafiq.pawangcuaca.presentation.model.WeatherModel
import com.example.maunorafiq.pawangcuaca.presentation.citylist.adapter.CityListAdapter
import com.example.maunorafiq.pawangcuaca.presentation.citylist.adapter.CityListLayoutManager
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlaceAutocomplete

import javax.inject.Inject

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import com.google.android.gms.location.places.ui.PlaceAutocomplete.RESULT_ERROR

/**
 * Created by maunorafiq on 11/29/16.
 */

class CityListFragment : BaseFragment(), CityListView {

    private val TAG = javaClass.simpleName

    interface CityListListener {
        fun onCityClicked(cityModel: CityModel)
    }

    @Inject internal lateinit var cityListPresenter: CityListPresenter
    @Inject internal lateinit var cityListAdapter: CityListAdapter

    internal lateinit var rvCityList: RecyclerView
    internal lateinit var swipeRefreshLayout: SwipeRefreshLayout
    internal lateinit var rlProgress: RelativeLayout
    internal lateinit var fabAddCity: FloatingActionButton

    private var cityListListener: CityListListener? = null
    private val PLACE_AUTOCOMPLETE_REQUEST_CODE = 123

    init {
        retainInstance = true
    }

    private fun findViewById(fragmentView: View) {
        rvCityList = fragmentView.findViewById(R.id.rv_city_list) as RecyclerView
        swipeRefreshLayout = fragmentView.findViewById(R.id.srl_content_list_location) as SwipeRefreshLayout
        rlProgress = fragmentView.findViewById(R.id.rl_progress) as RelativeLayout
        fabAddCity = fragmentView.findViewById(R.id.fab_add_city) as FloatingActionButton
        fabAddCity.setOnClickListener { v ->
            try {
                val intent = PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                        .build(activity)
                startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE)
            } catch (e: GooglePlayServicesRepairableException) {
                Log.d(TAG, e.message)
            } catch (e: GooglePlayServicesNotAvailableException) {
                Log.d(TAG, e.message)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is CityListListener) {
            cityListListener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getComponent(WeatherComponent::class.java).inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val fragmentView = inflater.inflate(R.layout.activity_list_location, container, false)
        findViewById(fragmentView)
        setUpRecyclerView()
        setUpRefreshLayout()
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cityListPresenter.setView(this)
        loadCityList()
    }

    override fun onResume() {
        super.onResume()
        cityListPresenter.resume()
    }

    override fun onPause() {
        super.onPause()
        cityListPresenter.pause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        rvCityList.adapter = null
    }

    override fun onDestroy() {
        super.onDestroy()
        cityListPresenter.destroy()
    }

    override fun onDetach() {
        super.onDetach()
        cityListListener = null
    }

    override fun showLoading() {
        rlProgress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        rlProgress.visibility = View.GONE
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showRetry() {
        showToastMessage("Error Occur\nSwipe Down To Reload!")
    }

    override fun hideRetry() {
    }

    override fun renderCityList(cityModelList: List<CityModel>) {
        cityListAdapter.setCityModelList(cityModelList)
    }

    override fun viewCity(cityModel: CityModel) {
        cityListListener!!.onCityClicked(cityModel)
    }

    override fun updateCity(weatherModel: WeatherModel) {
        cityListAdapter.updateCityModel(weatherModel)
    }

    override fun addNewCity(cityModel: CityModel) {
        cityListAdapter.addNewCity(cityModel)
    }

    override fun showError(message: String) {
        Log.d(TAG, "showError: " + message)
    }

    override fun context(): Context {
        return activity.applicationContext
    }

    private fun setUpRecyclerView() {
        cityListAdapter.setOnItemClickListener(object : CityListAdapter.OnItemClickListener {
            override fun onCityItemClicked(cityModel: CityModel) {
                cityListPresenter.onCityClicked(cityModel)
            }
        })

        rvCityList.layoutManager = CityListLayoutManager(context())
        rvCityList.adapter = cityListAdapter
    }

    private fun setUpRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener { this.loadCityList() })
    }

    private fun loadCityList() {
        cityListPresenter.initialize()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        when (requestCode) {
            PLACE_AUTOCOMPLETE_REQUEST_CODE -> if (resultCode == RESULT_OK) {
                val place = PlaceAutocomplete.getPlace(context(), data)
                cityListPresenter.addCity(place.name.toString())
            } else if (resultCode == RESULT_ERROR) {
                val status = PlaceAutocomplete.getStatus(context(), data)
                Log.d(TAG, "onActivityResult: " + status.toString())
            } else if (resultCode == RESULT_CANCELED) {
                val status = PlaceAutocomplete.getStatus(context(), data)
                Log.d(TAG, "onActivityResult: " + status.toString())
            }
        }
    }
}
