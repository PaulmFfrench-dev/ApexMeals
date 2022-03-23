package ie.wit.freedomfood.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import ie.wit.freedomfood.R
import ie.wit.freedomfood.adapters.FreedomFoodAdapter
import ie.wit.freedomfood.databinding.ActivityReportBinding
import ie.wit.freedomfood.main.FreedomFoodApp

class Report : AppCompatActivity() {

    lateinit var app: FreedomFoodApp
    lateinit var reportLayout : ActivityReportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        reportLayout = ActivityReportBinding.inflate(layoutInflater)
        setContentView(reportLayout.root)

        app = this.application as FreedomFoodApp
        reportLayout.recyclerView.layoutManager = LinearLayoutManager(this)
        reportLayout.recyclerView.adapter = FreedomFoodAdapter(app.freedomfoodsStore.findAll())

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_report, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_donate -> { startActivity(Intent(this, FreedomFood::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}