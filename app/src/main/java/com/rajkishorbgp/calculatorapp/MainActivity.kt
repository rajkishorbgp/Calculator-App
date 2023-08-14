package com.rajkishorbgp.calculatorapp

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rajkishorbgp.calculatorapp.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var inputData: TextView
    private lateinit var currentResult: TextView

    private var prevResult = ""
    private var currentOperation = ""
    private var inputList = ""
    private var currentResultValue = ""
    private var equ =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        inputData = findViewById(R.id.input_data)
        currentResult = findViewById(R.id.currentResult)

        val numberButtons = listOf(
            binding.btn0, binding.btn1,binding.btn2, binding.btn3, binding.btn4, binding.btn5,
            binding.btn6, binding.btn7,binding.btn8, binding.btn9, binding.btnPoint )

        val operationButtons = listOf(
            binding.btnAdd, binding.btnSub, binding.btnMultiply, binding.btnDivider, binding.btnModulo
        )

        for (button in numberButtons) {
            button.setOnClickListener {
                inputList += button.text.toString()
                binding.inputData.text=inputList
                binding.currentResult.text= calculateResult()
            }
        }

        for (button in operationButtons) {
            button.setOnClickListener {
                inputList += button.text.toString()
                binding.inputData.text=inputList
                    //    binding.currentResult.text= calculateResult()
            }
        }

        binding.btnEquale.setOnClickListener {
            calculateResult()
        }
    }

    private fun calculateResult(): String{
        val ope ="+-*/%"
        var num1 =""
        var num2 =""
        var operation=""
        var flog1 =true
        val flog2 =true
        var operation2 =""
        var calculate=true
        for ( char in inputList){
            if (char in ope && num1.isEmpty() ){
                num1 = char.toString()
            }
            if (flog1 && char !in ope){
                num1 += char.toString()
            }else if (char in ope && num2.isEmpty()){
                operation = char.toString()
                flog1=false
            }else if (num1.isNotEmpty() && flog2 && char !in ope){
                num2 += char.toString()
            }
//            else if (char in ope && operation.isNotEmpty()){
//                operation2 = char.toString()
//                flog2 = false
//            }
//            if (num1.isNotEmpty() && num2.isNotEmpty() && operation.isNotEmpty() && !flog1 && !flog2){
//                num1 = calculate(num1.toDouble(),num2.toDouble(),operation).toString()
//                num2 =""
//                calculate= false
//                operation=operation2
//                operation2 = ""
//            }
        }
//        if (calculate && operation.isNotEmpty()){
//            num1 = calculate(num1.toDouble(),num2.toDouble(),operation).toString()
//        }
        Toast.makeText(this@MainActivity,"$num1 -> $num2",Toast.LENGTH_SHORT).show()
        return num1
    }

    private fun calculate(num1: Double, num2: Double, operation: String): Double {
        val result =when(operation){
            "+" -> num1+num2
            "-" -> num1-num2
            "*" -> num1*num2
            "/" -> num1/num2
            else -> 0.0
        }
        return result
    }
}
