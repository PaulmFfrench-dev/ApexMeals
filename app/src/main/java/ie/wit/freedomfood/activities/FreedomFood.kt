package ie.wit.freedomfood.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import ie.wit.freedomfood.R
import ie.wit.freedomfood.databinding.ActivityFreedomfoodBinding
import ie.wit.freedomfood.main.FreedomFoodApp
import ie.wit.freedomfood.models.FreedomFoodModel
import timber.log.Timber

class FreedomFood : AppCompatActivity() {

    private lateinit var donateLayout : ActivityFreedomfoodBinding
    lateinit var app: FreedomFoodApp

    override fun onCreate(savedInstanceState: Bundle?) {
        app = this.application as FreedomFoodApp

        super.onCreate(savedInstanceState)
        donateLayout = ActivityFreedomfoodBinding.inflate(layoutInflater)
        setContentView(donateLayout.root)

        donateLayout.progressBar.max = 10000

        donateLayout.amountPicker.minValue = 1
        donateLayout.amountPicker.maxValue = 1000

        donateLayout.amountPicker.setOnValueChangedListener { _, _, newVal ->
            //Display the newly selected number to paymentAmount
            donateLayout.paymentAmount.setText("$newVal")
        }

        var totalDonated = 0

        donateLayout.donateButton.setOnClickListener {
            val amount = if (donateLayout.paymentAmount.text.isNotEmpty())
                donateLayout.paymentAmount.text.toString().toInt() else donateLayout.amountPicker.value
            if(totalDonated >= donateLayout.progressBar.max)
                Toast.makeText(applicationContext,"Donate Amount Exceeded!", Toast.LENGTH_LONG).show()
            else {
                val paymentmethod = if(donateLayout.paymentMethod.checkedRadioButtonId == R.id.Direct)
                    "Direct" else "Paypal"
                totalDonated += amount
                donateLayout.totalSoFar.text = "$$totalDonated"
                donateLayout.progressBar.progress = totalDonated
                app.freedomfoodsStore.create(FreedomFoodModel(paymentmethod = paymentmethod,amount = amount))
                Timber.i("Total Donated so far $totalDonated")
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_donate, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_report -> { startActivity(Intent(this, Report::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}