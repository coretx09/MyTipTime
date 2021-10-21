package com.example.mytiptime

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
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

        binding.costOfServiceEdit.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode)
        }
    }

    // function CalculateTip
    // aussi au br, a verifier apres
    // code a reverifier apres
    // calcul de pourboire
    private fun calculateTip() {
        // TAKE RESULT (EditText----> .text )
        val stringInTextField = binding.costOfServiceEdit.text.toString()
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

    // function ECOUTEUR DE CLICK
    // clavier automatique
    // se ferme apres enter du clavier, clavier automate
    // function handleKeyEvent
    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}