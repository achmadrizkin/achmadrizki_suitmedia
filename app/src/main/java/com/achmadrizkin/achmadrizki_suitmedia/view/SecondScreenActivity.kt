package com.achmadrizkin.achmadrizki_suitmedia.view

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.achmadrizkin.achmadrizki_suitmedia.R


class SecondScreenActivity : AppCompatActivity() {
    private lateinit var tvName: TextView
    private lateinit var btnChooseUser: TextView
    private lateinit var tvSelectedName: TextView
    private lateinit var ivBack: ImageView

    lateinit var name: String
    var getName: String = "Selected User Name"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_screen)

        init()

        btnChooseUser.setOnClickListener {
            val i = Intent(this, ThirdScreenActivity::class.java)
            startActivityForResult(i, 1)
        }

        ivBack.setOnClickListener {
            finish()
        }
    }

    private fun init() {
        name = intent.getStringExtra("name").toString()

        tvName = findViewById(R.id.tvName)
        btnChooseUser = findViewById(R.id.btnChooseUser)
        tvSelectedName = findViewById(R.id.tvSelectedName)
        ivBack = findViewById(R.id.ivBack)

        tvName.text = name
        tvSelectedName.text = getName
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === 1) {
            if (resultCode === RESULT_OK) {
                getName = data?.getStringExtra("getName").toString()
                tvSelectedName.text = "Selected User: $getName"
            }
        }
    }
}