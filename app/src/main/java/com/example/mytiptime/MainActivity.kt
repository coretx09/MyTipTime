package com.example.mytiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mytiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ECOUTEUR DE CLICK
        binding.buttonPlay.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        // TAKE RESULT (EditText----> .text )
        val stringInTextField = binding.costOfService.text.toString()
        // Convert le text en Number decimal and stock
        // toDouble doit etre call sur un fichier String
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null) {
            binding.tipResult.text = " "
            return
        }

        // GET PERCENTAGE
        val tipPercentage = when (binding.radioSelect.checkedRadioButtonId) {
            R.id.amazing_20 -> 0.20
            R.id.good_18 -> 0.18
            else -> 0.15
        }
        // CALCULATE TIP
        var tip = tipPercentage * cost

        // SWITCH isChecked-- attribute (see if the switch is "ON")
        if (binding.rounded.isChecked) { // TRUE OR FALSE
            tip = kotlin.math.ceil(tip) // POUR ARRONDIR
        }

        // FORMAT TIP
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        // AFFICHE RESULT
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)


    }
}