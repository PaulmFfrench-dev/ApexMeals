package ie.wit.apexmeals

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ie.wit.apexmeals.databinding.FragmentDonateBinding
import ie.wit.apexmeals.main.ApexMealsApp
import ie.wit.apexmeals.models.ApexMealsModel

class DonateFragment : Fragment() {

    lateinit var app: ApexMealsApp
    var totalDonated = 0
    private var _fragBinding: FragmentDonateBinding? = null
    private val fragBinding get() = _fragBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as ApexMealsApp
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _fragBinding = FragmentDonateBinding.inflate(inflater, container, false)
        val root = fragBinding.root
        activity?.title = getString(R.string.action_donate)

        fragBinding.progressBar.max = 10000
        fragBinding.amountPicker.minValue = 1
        fragBinding.amountPicker.maxValue = 1000

        fragBinding.amountPicker.setOnValueChangedListener { _, _, newVal ->
            //Display the newly selected number to paymentAmount
            fragBinding.paymentAmount.setText("$newVal")
        }

        fun setButtonListener(layout: FragmentDonateBinding) {
            layout.donateButton.setOnClickListener {
                val amount = if (layout.paymentAmount.text.isNotEmpty())
                    layout.paymentAmount.text.toString().toInt() else layout.amountPicker.value
                if(totalDonated >= layout.progressBar.max)
                    Toast.makeText(context,"Donate Amount Exceeded!", Toast.LENGTH_LONG).show()
                else {
                    val paymentmethod = if(layout.paymentMethod.checkedRadioButtonId == R.id.Direct) "Direct" else "Paypal"
                    totalDonated += amount
                    layout.totalSoFar.text = "$$totalDonated"
                    layout.progressBar.progress = totalDonated
                    app.apexmealsStore.create(ApexMealsModel(paymentmethod = paymentmethod,amount = amount))
                }
            }
        }

        return root;
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }

    override fun onResume() {
        super.onResume()
        totalDonated = app.apexmealsStore.findAll().sumOf { it.amount }
        fragBinding.progressBar.progress = totalDonated
        fragBinding.totalSoFar.text = "$$totalDonated"
    }
    companion object {
        @JvmStatic
        fun newInstance() =
            DonateFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}