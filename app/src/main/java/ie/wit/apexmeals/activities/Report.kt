package ie.wit.apexmeals.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import ie.wit.apexmeals.R
import ie.wit.apexmeals.adapters.ApexMealsAdapter
import ie.wit.apexmeals.databinding.ActivityReportBinding
import ie.wit.apexmeals.main.ApexMealsApp

class Report : AppCompatActivity() {

    lateinit var app: ApexMealsApp
    lateinit var reportLayout : ActivityReportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        reportLayout = ActivityReportBinding.inflate(layoutInflater)
        setContentView(reportLayout.root)

        app = this.application as ApexMealsApp
        reportLayout.recyclerView.layoutManager = LinearLayoutManager(this)
        reportLayout.recyclerView.adapter = ApexMealsAdapter(app.apexmealsStore.findAll())

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
            R.id.action_donate -> { startActivity(Intent(this, Donate::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}