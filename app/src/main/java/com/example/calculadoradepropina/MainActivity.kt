package com.example.calculadoradepropina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.calculadoradepropina.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    /*
    TODO: Exiende la app con los siguiente:
    Agrega validaciones por si el usuario da un monto negativo o 0, manda TOAST de error al usuaio.
     */

    private lateinit var binding: ActivityMainBinding
    private var totalAmount: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.initUI()
    }

    private fun initUI() {
        binding.button15.setOnClickListener {
            interfaceCalculateTip(0.15)
        }
        binding.button20.setOnClickListener {
            interfaceCalculateTip(0.2)
        }
        binding.button35.setOnClickListener {
            interfaceCalculateTip(0.35)
        }
        binding.buttonRound.setOnClickListener {
            roundTotalAmount()
        }
    }

    private fun interfaceCalculateTip(tipPercent: Double) {
        val amount = binding.amountInput.text.toString().toDoubleOrNull()
        if (checkAmount(amount) != null) { return }
        Toast.makeText(this, "${getString(R.string.selected_tip)} ${tipPercent*100}%", Toast.LENGTH_SHORT).show()

        this.totalAmount = amount!! * (1 + tipPercent)
        binding.textViewTipTotal.text = "Tip: ${amount!! * tipPercent}"
        binding.textViewAmountTotal.text = "Total: ${this.totalAmount}"
    }

    private fun roundTotalAmount() {
        Toast.makeText(this, getString(R.string.selected_to_round), Toast.LENGTH_SHORT).show()
        binding.textViewAmountTotal.text = "Total: %.2f".format(this.totalAmount)
    }

    private fun checkAmount(amount: Double?): String? {
        if (amount == null) {
            binding.textViewTipTotal.text = "0.0"
            binding.textViewAmountTotal.text = "0.0"
            return "Error"
        }
        if (amount <= 0) {
            Toast.makeText(this, getString(R.string.wrong_amount), Toast.LENGTH_SHORT).show()
            return "Error"
        }
        return null
    }
}