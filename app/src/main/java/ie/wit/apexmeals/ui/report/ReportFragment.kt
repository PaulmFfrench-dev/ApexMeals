package ie.wit.apexmeals.ui.report

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ie.wit.apexmeals.R
import ie.wit.apexmeals.adapters.ApexMealAdapter
import ie.wit.apexmeals.adapters.ApexMealClickListener
import ie.wit.apexmeals.databinding.FragmentReportBinding
import ie.wit.apexmeals.models.ApexMealModel
import ie.wit.apexmeals.ui.auth.LoggedInViewModel
import ie.wit.apexmeals.utils.*

class ReportFragment : Fragment(), ApexMealClickListener {

    private var _fragBinding: FragmentReportBinding? = null
    private val fragBinding get() = _fragBinding!!
    lateinit var loader : AlertDialog
    private val reportViewModel: ReportViewModel by activityViewModels()
    private val loggedInViewModel : LoggedInViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragBinding = FragmentReportBinding.inflate(inflater, container, false)
        val root = fragBinding.root
        loader = createLoader(requireActivity())

        fragBinding.recyclerView.layoutManager = LinearLayoutManager(activity)
        fragBinding.fab.setOnClickListener {
            val action = ReportFragmentDirections.actionReportFragmentToApexmealdonateFragment()
            findNavController().navigate(action)
        }
        showLoader(loader, "Downloading ApexMeals")
        reportViewModel.observableApexMealsList.observe(viewLifecycleOwner, Observer { apexmeals ->
            apexmeals?.let {
                render(apexmeals as ArrayList<ApexMealModel>)
                hideLoader(loader)
                checkSwipeRefresh()
            }
        })

        setSwipeRefresh()

        val swipeDeleteHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                showLoader(loader, "Deleting ApexMeal")
                val adapter = fragBinding.recyclerView.adapter as ApexMealAdapter
                adapter.removeAt(viewHolder.adapterPosition)
                reportViewModel.delete(
                    reportViewModel.liveFirebaseUser.value?.uid!!,
                    (viewHolder.itemView.tag as ApexMealModel).uid!!
                )
                hideLoader(loader)
            }
        }
        val itemTouchDeleteHelper = ItemTouchHelper(swipeDeleteHandler)
        itemTouchDeleteHelper.attachToRecyclerView(fragBinding.recyclerView)

        val swipeEditHandler = object : SwipeToEditCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                onApexMealClick(viewHolder.itemView.tag as ApexMealModel)
            }
        }
        val itemTouchEditHelper = ItemTouchHelper(swipeEditHandler)
        itemTouchEditHelper.attachToRecyclerView(fragBinding.recyclerView)

        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_report, menu)

        val item = menu.findItem(R.id.toggleApexMealDonations) as MenuItem
        item.setActionView(R.layout.togglebutton_layout)
        val toggleApexMeals: SwitchCompat = item.actionView.findViewById(R.id.toggleButton)
        toggleApexMeals.isChecked = false

        toggleApexMeals.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) reportViewModel.loadAll()
            else reportViewModel.load()
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController()
        ) || super.onOptionsItemSelected(item)
    }

    private fun render(apexmealsList: ArrayList<ApexMealModel>) {
        fragBinding.recyclerView.adapter = ApexMealAdapter(apexmealsList, this,
            reportViewModel.readOnly.value!!)
        if (apexmealsList.isEmpty()) {
            fragBinding.recyclerView.visibility = View.GONE
            fragBinding.apexmealdonationsNotFound.visibility = View.VISIBLE
        } else {
            fragBinding.recyclerView.visibility = View.VISIBLE
            fragBinding.apexmealdonationsNotFound.visibility = View.GONE
        }
    }

    override fun onApexMealClick(apexmeal: ApexMealModel) {
        val action = ReportFragmentDirections.actionReportFragmentToApexmealdonationDetailFragment(apexmeal.uid!!)

        if(!reportViewModel.readOnly.value!!)
            findNavController().navigate(action)
    }

    private fun setSwipeRefresh() {
        fragBinding.swiperefresh.setOnRefreshListener {
            fragBinding.swiperefresh.isRefreshing = true
            showLoader(loader, "Downloading ApexMeals")
            if(reportViewModel.readOnly.value!!)
                reportViewModel.loadAll()
            else
                reportViewModel.load()
        }
    }

    private fun checkSwipeRefresh() {
        if (fragBinding.swiperefresh.isRefreshing)
            fragBinding.swiperefresh.isRefreshing = false
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

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }
}