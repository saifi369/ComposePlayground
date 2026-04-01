package com.u4universe.composeplayground.xml

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.u4universe.composeplayground.R
import com.u4universe.composeplayground.databinding.ActivityBurgerXmlBinding

class BurgerXmlActivity : AppCompatActivity() {

    private val binding: ActivityBurgerXmlBinding by lazy {
        ActivityBurgerXmlBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupViews()
    }

    @SuppressLint("SetTextI18n")
    private fun setupViews() {
        with(binding) {
            pattyRadioGroup.setOnCheckedChangeListener { _, checkedId ->
                calculateTotal()
            }

            cheeseCheckbox.setOnCheckedChangeListener { _, isChecked ->
                calculateTotal()
            }

            resetButton.setOnClickListener {
                pattyRadioGroup.check(R.id.radioSingle)
//                binding.cheeseCheckbox.isChecked = false

                calculateTotal()
            }
        }

    }

    @SuppressLint("SetTextI18n")
    private fun calculateTotal() {
        var total = if (binding.radioDouble.isChecked) PRICE_DOUBLE_PETTY else PRICE_SINGLE_PETTY
        if (binding.cheeseCheckbox.isChecked) total += PRICE_CHEESE
        binding.totalPriceText.text = "Total: $total"
    }
}

const val PRICE_CHEESE = 2
const val PRICE_SINGLE_PETTY = 10
const val PRICE_DOUBLE_PETTY = 15