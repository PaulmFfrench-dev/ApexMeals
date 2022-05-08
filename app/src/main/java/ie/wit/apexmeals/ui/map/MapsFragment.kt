package ie.wit.apexmeals.ui.map

import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import ie.wit.apexmeals.R
import ie.wit.apexmeals.models.ApexMealModel
import ie.wit.apexmeals.ui.auth.LoggedInViewModel
import ie.wit.apexmeals.ui.report.ReportViewModel
import ie.wit.apexmeals.utils.createLoader
import ie.wit.apexmeals.utils.hideLoader
import ie.wit.apexmeals.utils.showLoader

class MapsFragment : Fragment() {

    private val mapsViewModel: MapsViewModel by activityViewModels()
    private val reportViewModel: ReportViewModel by activityViewModels()
    private val loggedInViewModel : LoggedInViewModel by activityViewModels()
    lateinit var loader : AlertDialog

    @SuppressLint("MissingPermission")
    private val callback = OnMapReadyCallback { googleMap ->
        mapsViewModel.map = googleMap
        mapsViewModel.map.isMyLocationEnabled = true
        mapsViewModel.currentLocation.observe(viewLifecycleOwner,{
            val loc = LatLng(mapsViewModel.currentLocation.value!!.latitude,
                mapsViewModel.currentLocation.value!!.longitude)

            mapsViewModel.map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 14f))
            mapsViewModel.map.uiSettings.isZoomControlsEnabled = true
            mapsViewModel.map.uiSettings.isMyLocationButtonEnabled = true

            reportViewModel.observableApexMealsList.observe(viewLifecycleOwner, Observer { apexmeals ->
                apexmeals?.let {
                    render(apexmeals as ArrayList<ApexMealModel>)
                    hideLoader(loader)
                }
            })
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loader = createLoader(requireActivity())

        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_map, menu)

        val item = menu.findItem(R.id.toggleApexMealDonations) as MenuItem
        item.setActionView(R.layout.togglebutton_layout)
        val toggleApexMeals: SwitchCompat = item.actionView.findViewById(R.id.toggleButton)
        toggleApexMeals.isChecked = false

        toggleApexMeals.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) reportViewModel.loadAll()
            else reportViewModel.load()
        }
    }

    private fun render(apexmealsList: ArrayList<ApexMealModel>) {
        var markerColour: Float
        if (!apexmealsList.isEmpty()) {
            mapsViewModel.map.clear()
            apexmealsList.forEach {
                if(it.email.equals(this.reportViewModel.liveFirebaseUser.value!!.email))
                    markerColour = BitmapDescriptorFactory.HUE_AZURE + 5
                else
                    markerColour = BitmapDescriptorFactory.HUE_RED

                mapsViewModel.map.addMarker(
                    MarkerOptions().position(LatLng(it.latitude, it.longitude))
                        .title("${it.paymentmethod} €${it.amount}")
                        .snippet(it.note)
                        .icon(BitmapDescriptorFactory.defaultMarker(markerColour ))
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        showLoader(loader, "Downloading ApexMeals")
        loggedInViewModel.liveFirebaseUser.observe(viewLifecycleOwner, Observer { firebaseUser ->
            if (firebaseUser != null) {
                reportViewModel.liveFirebaseUser.value = firebaseUser
                reportViewModel.load()
            }
        })
    }
}