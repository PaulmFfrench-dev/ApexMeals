package ie.wit.apexmeals.ui.report

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ie.wit.apexmeals.R
import ie.wit.apexmeals.adapters.ApexMealsAdapter
import ie.wit.apexmeals.adapters.DonationClickListener
import ie.wit.apexmeals.databinding.FragmentReportBinding
import ie.wit.apexmeals.main.ApexMealsApp
import ie.wit.apexmeals.models.ApexMealsModel
import ie.wit.apexmeals.ui.detail.DonationDetailFragmentArgs

class ReportFragment : Fragment(), DonationClickListener {

    lateinit var app: ApexMealsApp
    private var _fragBinding: FragmentReportBinding? = null
    private val fragBinding get() = _fragBinding!!
    private lateinit var reportViewModel: ReportViewModel
    private val args by navArgs<DonationDetailFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.donation_detail_fragment, container, false)

        Toast.makeText(context,"Donation ID Selected : ${args.donationid}", Toast.LENGTH_LONG).show()

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_report, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
            requireView().findNavController()) || super.onOptionsItemSelected(item)
    }

    private fun render(donationsList: List<ApexMealsModel>) {
        fragBinding.recyclerView.adapter = ApexMealsAdapter(donationsList,this)
        if (donationsList.isEmpty()) {
            fragBinding.recyclerView.visibility = View.GONE
            fragBinding.donationsNotFound.visibility = View.VISIBLE
        } else {
            fragBinding.recyclerView.visibility = View.VISIBLE
            fragBinding.donationsNotFound.visibility = View.GONE
        }
    }

    override fun onDonationClick(donation: ApexMealsModel) {
        val action = ReportFragmentDirections.actionReportFragmentToDonationDetailFragment(donation.id)
        findNavController().navigate(action)
    }

    override fun onResume() {
        super.onResume()
        reportViewModel.load()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }
}