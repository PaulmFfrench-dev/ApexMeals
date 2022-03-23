package ie.wit.apexmeals.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ie.wit.apexmeals.R

class DonationDetailFragment : Fragment() {

    companion object {
        fun newInstance() = DonationDetailFragment()
    }

    private lateinit var viewModel: DonationDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.donation_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DonationDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}