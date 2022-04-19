package de.lauritzdev.myterminal

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_1.setOnClickListener {
            ButtonManager.onBtnClick(this, et_1, log_recycler_view)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.connect -> {
                startActivity(Intent(this, Connect::class.java))
                true
            }
            R.id.settings -> {
                true
            }
            R.id.save -> {
                true
            }
            R.id.open -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}