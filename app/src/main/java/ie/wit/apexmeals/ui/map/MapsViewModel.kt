package ie.wit.apexmeals.ui.map

import android.location.Location
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.GoogleMap

class MapsViewModel {

    lateinit var map : GoogleMap
    var currentLocation = MutableLiveData<Location>()
}