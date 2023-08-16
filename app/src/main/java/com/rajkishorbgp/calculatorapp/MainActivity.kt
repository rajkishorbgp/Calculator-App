package com.rajkishorbgp.calculatorapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.rajkishorbgp.calculatorapp.databinding.ActivityMainBinding
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var inputList = ""
    private var f = false
    private lateinit var expression: Expression

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setNumberClickListeners()
        setOperationClickListeners()

        binding.btnEquale.setOnClickListener {
            onEqual()
            binding.inputData.text=binding.currentResult.text.toString()
            binding.currentResult.text= ""
        }

        binding.btnAllClear.setOnClickListener {
            // Clear all inputs and reset the calculator
            clearInputs()
            binding.inputData.text=inputList
        }
        binding.btnBack.setOnClickListener {
            if (inputList.isNotEmpty()) {
                inputList = inputList.dropLast(1)
                binding.inputData.text = inputList
                val isOperator = "1234567890"
                if (inputList.isNotEmpty() && inputList.last() in isOperator){
                    onEqual()
                }else if(inputList.isNotEmpty() && inputList.last() in isOperator){
                    binding.currentResult.text=inputList
                }else if(inputList.isEmpty()){
                    binding.currentResult.text=inputList
                }
            }
        }
    }

    private fun setNumberClickListeners() {
        val numberButtons = listOf(
            binding.btn0, binding.btn1, binding.btn2, binding.btn3,
            binding.btn4, binding.btn5, binding.btn6, binding.btn7,
            binding.btn8, binding.btn9, binding.btnPoint
        )

        for (button in numberButtons) {
            button.setOnClickListener {
                inputList += button.text.toString()
                binding.inputData.text=inputList
                onEqual()
            }
        }
    }

    private fun setOperationClickListeners() {
        val operationButtons = listOf(
            binding.btnAdd, binding.btnSub, binding.btnMultiply,
            binding.btnDivider, binding.btnModulo,binding.btnsqr
        )

        for (button in operationButtons) {
            button.setOnClickListener {
                inputList += button.text.toString()
                binding.inputData.text=inputList
            }
        }
    }



    private fun clearInputs() {
        inputList =""
        binding.inputData.text=""
        binding.currentResult.text=""
    }

    private fun onEqual() {
        val txt = binding.inputData.text.toString()
        val replaced = txt.replace("x", "*")
        expression = ExpressionBuilder(replaced).build()
        try {
            val result = expression.evaluate()
            binding.currentResult.text = result.toString()
        } catch (ex: ArithmeticException) {
            Log.e("EvaluateError", ex.toString())
            binding.currentResult.text = "Error"

        }
    }
}