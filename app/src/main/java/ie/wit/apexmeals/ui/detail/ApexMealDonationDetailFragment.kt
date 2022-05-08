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
import ie.wit.apexmeals.databinding.FragmentApexMealDonationDetailBinding
import ie.wit.apexmeals.ui.auth.LoggedInViewModel
import ie.wit.apexmeals.ui.report.ReportViewModel
import timber.log.Timber

class ApexMealDonationDetailFragment : Fragment() {

    private lateinit var apexmealdonationDetailViewModel: ApexMealDonationDetailViewModel
    private val args by navArgs<ApexMealDonationDetailFragmentArgs>()
    private var _fragBinding: FragmentApexMealDonationDetailBinding? = null
    private val fragBinding get() = _fragBinding!!
    private val loggedInViewModel : LoggedInViewModel by activityViewModels()
    private val reportViewModel : ReportViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        _fragBinding = FragmentApexMealDonationDetailBinding.inflate(inflater, container, false)
        val root = fragBinding.root

        apexmealdonationDetailViewModel = ViewModelProvider(this).get(ApexMealDonationDetailViewModel::class.java)
        apexmealdonationDetailViewModel.observableApexMeal.observe(viewLifecycleOwner, Observer { render() })

        fragBinding.editApexMealDonationButton.setOnClickListener {
            apexmealdonationDetailViewModel.updateApexMeal(loggedInViewModel.liveFirebaseUser.value?.uid!!,
                args.apexmealid, fragBinding.apexmealdonationvm?.observableApexMeal!!.value!!)
            //Force Reload of list to guarantee refresh
            reportViewModel.load()
            findNavController().navigateUp()
            //findNavController().popBackStack()

        }

        fragBinding.deleteApexMealDonationButton.setOnClickListener {
            reportViewModel.delete(loggedInViewModel.liveFirebaseUser.value?.uid!!,
                apexmealdonationDetailViewModel.observableApexMeal.value?.uid!!)
            findNavController().navigateUp()
        }
        return root
    }

    private fun render() {
        fragBinding.apexmealdonationvm = apexmealdonationDetailViewModel
        Timber.i("Retrofit fragBinding.apexmealvm == $fragBinding.apexmealvm")
    }

    override fun onResume() {
        super.onResume()
        apexmealdonationDetailViewModel.getApexMeal(loggedInViewModel.liveFirebaseUser.value?.uid!!,
            args.apexmealid)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }
}