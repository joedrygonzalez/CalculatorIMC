package com.example.calculadoraimc

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.calculadoraimc.MainActivity.Companion.IMC_KEY

class ResultIMCActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnRecalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result_imcactivity)
        val result = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        initComponents()
        initUI(result)
        initListeners()
    }

    private fun initListeners() {
        btnRecalculate.setOnClickListener { onBackPressed() }
    }

    private fun initUI(result: Double) {
        tvIMC.text = result.toString()
        when(result) {
            in 0.00..18.50 -> {
                tvResult.text = getString(R.string.title_low_weight)
                tvResult.setTextColor(getColor(R.color.low_result_text))
                tvDescription.text = getString(R.string.description_low_weight)
            } in 18.51..24.90 -> {
                tvResult.text = getString(R.string.title_normal_weight)
                tvResult.setTextColor(getColor(R.color.normal_result_text))
                tvDescription.text = getString(R.string.description_normal_weight)
            } in 25.00..29.90 -> {
                tvResult.text = getString(R.string.title_overweight)
                tvResult.setTextColor(getColor(R.color.over_result_text))
                tvDescription.text = getString(R.string.description_overweight)
            } in 30.0..99.99 -> {
                tvResult.text = getString(R.string.title_obese_weight)
                tvResult.setTextColor(getColor(R.color.danger_result_text))
                tvDescription.text = getString(R.string.description_obese_weight)
            } else -> {
                tvResult.text = getString(R.string.error)
                tvIMC.text = getString(R.string.error)
            tvResult.setTextColor(getColor(R.color.danger_result_text))
                tvDescription.text = getString(R.string.error)
            }
        }
    }

    private fun setColor(result: Double) : Int {
        return ContextCompat.getColor(this, R.color.danger_result_text)
    }

    private fun initComponents() {
        tvResult = findViewById(R.id.tvResult)
        tvIMC = findViewById(R.id.tvIMC)
        tvDescription = findViewById(R.id.tvDescription)
        btnRecalculate = findViewById(R.id.btnRecalculate)
    }
}
