package hr.fer.ruazosa.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import hr.fer.ruazosa.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // ActivityMainBinding class is created automaticly by andorid based on the xml layout name for the activity
    lateinit var binding: ActivityMainBinding

    // refrence to the CalculatorViewModel class
    private lateinit var viewModel: CalculatorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // .infalte creates an instance of class ActivityMainBinding, parameter layoutInflates sets the views in the xml in a tree like hiearchy (only the ones with an id)
        val binding = ActivityMainBinding.inflate(layoutInflater) // binding has acces to every view in the layout
        setContentView(binding.root) // bidning.root is the layout

        viewModel = ViewModelProvider(this)[CalculatorViewModel::class.java] // connects to the viewmodel

        // observe triggers when LIVE DATA changes
        viewModel.calculationResult.observe(this) {
            binding.resultViewTextView.text = it
        }
        val addToViewModelExpression = {button: Button ->
            viewModel.addToExpression(button.text.toString())
        }
        val evaluateViewModel = {
            viewModel.evaluate()
        }

        // the setOnClickListener expects an argument => the lambda expresion is the argument
        binding.numberOneButton.setOnClickListener {
            addToViewModelExpression(it as Button)
        }

        binding.numberTwoButton.setOnClickListener {
            addToViewModelExpression(it as Button)
        }

        binding.numberThreeButton.setOnClickListener {
            addToViewModelExpression(it as Button)
        }

        binding.numberFourButton.setOnClickListener {
            addToViewModelExpression(it as Button)
        }

        binding.numberFiveButton.setOnClickListener {
            addToViewModelExpression(it as Button)
        }

        binding.numberSixButton.setOnClickListener {
            addToViewModelExpression(it as Button)
        }

        binding.numberSevenButton.setOnClickListener {
            addToViewModelExpression(it as Button)
        }

        binding.numberEightButton.setOnClickListener {
            addToViewModelExpression(it as Button)
        }
        binding.numberNineButton.setOnClickListener {
            addToViewModelExpression(it as Button)
        }
        binding.numberZeroButton.setOnClickListener {
            addToViewModelExpression(it as Button)
        }

        binding.plusButton.setOnClickListener {
            addToViewModelExpression(it as Button)
        }

        binding.minusButton.setOnClickListener {
            addToViewModelExpression(it as Button)
        }

        binding.mutiplyButton.setOnClickListener {
            addToViewModelExpression(it as Button)
        }

        binding.divideButton.setOnClickListener {
            addToViewModelExpression(it as Button)
        }

        binding.equalsButton.setOnClickListener { viewModel.evaluate() }

        binding.cancelButton.setOnClickListener { viewModel.resetCalculator() }
    }
}