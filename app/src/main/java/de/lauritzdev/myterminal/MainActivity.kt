package de.lauritzdev.myterminal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LayoutController.init(this)
        btn_1.setOnClickListener { Btn.btnClick(this, et_1) }
        btn_2.setOnClickListener { Btn.btnClick(this, et_2) }
    }
}