package ie.wit.apexmeals.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ie.wit.apexmeals.databinding.FragmentDonationDetailBinding
import ie.wit.apexmeals.ui.auth.LoggedInViewModel
import ie.wit.apexmeals.ui.report.ReportViewModel
import timber.log.Timber

class DonationDetailFragment : Fragment() {

    private lateinit var detailViewModel: DonationDetailViewModel
    private val args by navArgs<DonationDetailFragmentArgs>()
    private var _fragBinding: FragmentDonationDetailBinding? = null
    private val fragBinding get() = _fragBinding!!
    private val loggedInViewModel : LoggedInViewModel by activityViewModels()
    private val reportViewModel : ReportViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        _fragBinding = FragmentDonationDetailBinding.inflate(inflater, container, false)
        val root = fragBinding.root

        detailViewModel = ViewModelProvider(this).get(DonationDetailViewModel::class.java)
        detailViewModel.observableDonation.observe(viewLifecycleOwner, Observer { render() })

        fragBinding.editDonationButton.setOnClickListener {
            detailViewModel.updateDonation(loggedInViewModel.liveFirebaseUser.value?.email!!,
                args.donationid.toString(), fragBinding.donationvm?.observableDonation!!.value!!)
            //Force Reload of list to guarantee refresh
            reportViewModel.load()
            findNavController().navigateUp()
            //findNavController().popBackStack()

        }

        fragBinding.deleteDonationButton.setOnClickListener {
            reportViewModel.delete(loggedInViewModel.liveFirebaseUser.value?.email!!,
                detailViewModel.observableDonation.value?._id!!)
            findNavController().navigateUp()
        }
        return root
    }

    private fun render() {
        fragBinding.donationvm = detailViewModel
        Timber.i("Retrofit fragBinding.donationvm == $fragBinding.donationvm")
    }

    override fun onResume() {
        super.onResume()
        detailViewModel.getDonation(loggedInViewModel.liveFirebaseUser.value?.email!!,
            args.donationid.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }
}