package ie.wit.apexmeals.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ie.wit.apexmeals.databinding.FragmentProfileBinding
import ie.wit.apexmeals.ui.auth.LoggedInViewModel
import ie.wit.apexmeals.ui.report.ReportViewModel
import timber.log.Timber

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private val args by navArgs<ProfileFragmentArgs>()
    private var _fragBinding: FragmentProfileBinding? = null
    private val fragBinding get() = _fragBinding!!
    private val loggedInViewModel : LoggedInViewModel by activityViewModels()
    private val reportViewModel : ReportViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        _fragBinding = FragmentProfileBinding.inflate(inflater, container, false)
        val root = fragBinding.root

        profileViewModel = ViewModelProvider(this).get(
            ProfileViewModel::class.java)
        profileViewModel.observableProfile.observe(viewLifecycleOwner, Observer { render() })
        _fragBinding!!.email.text = "hello"

        fragBinding.editProfileButton.setOnClickListener {
            profileViewModel.updateProfile(loggedInViewModel.liveFirebaseUser.value?.email!!,
                args.email, fragBinding.profilevm?.observableProfile!!.value!!)
            //Force Reload of list to guarantee refresh
            reportViewModel.load()

            findNavController().navigateUp()
            //findNavController().popBackStack()

        }

        return root
    }

    private fun render() {
        fragBinding.profilevm = profileViewModel
        Timber.i("Retrofit fragBinding.profilevm == ${fragBinding.profilevm}")
    }

    override fun onResume() {
        super.onResume()
        profileViewModel.getApexMeal(loggedInViewModel.liveFirebaseUser.value?.email!!,
            args.email)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }
}