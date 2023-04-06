package com.callmeadnaan.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.TextView
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private var tvInput: TextView? = null
    var lastNumeric : Boolean = false
    var lastDot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput = findViewById(R.id.tvInput)
    }
    fun onDigit(view : View)
    {
        tvInput?.append((view as Button).text)
        lastNumeric = true
        lastDot = false

        /* Toast.makeText(this,"Button Clicked",Toast.LENGTH_LONG).show()*/
    }
    fun onClear(view : View)
    {
        tvInput?.text = ""
    }
    fun onBack(view: View) {
        if (lastNumeric) {
           var value = tvInput?.text
            value = value?.substring(0, value.length - 1)
            tvInput?.text = value
        }
    }
    fun onDecimal(view: View)
    {
        if(lastNumeric && !lastDot)
        {
            tvInput?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }
    fun onEqual(view: View){
        if(lastNumeric) {
            var tvValue = tvInput?.text.toString()
            var prefix = ""
            try {
                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")) {

                    val splitValue = tvValue.split("-")//99-1
                    var one = splitValue[0]//99
                    val two = splitValue[1]//1
                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    tvInput?.text = removeZero((one.toDouble() - two.toDouble()).toString())
                } else if (tvValue.contains("+")) {

                    val splitValue = tvValue.split("+")//99-1
                    var one = splitValue[0]//99
                    val two = splitValue[1]//1
                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    tvInput?.text = removeZero((one.toDouble() + two.toDouble()).toString())
                } else if (tvValue.contains("*")) {

                    val splitValue = tvValue.split("*")//99-1
                    var one = splitValue[0]//99
                    val two = splitValue[1]//1
                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    tvInput?.text = removeZero((one.toDouble() * two.toDouble()).toString())
                } else if(tvValue.contains("/")) {

                    val splitValue = tvValue.split("/")//99-1
                    var one = splitValue[0]//99
                    val two = splitValue[1]//1
                    if (prefix.isNotEmpty()) {
                        one = prefix + one
                    }
                    tvInput?.text = removeZero((one.toDouble() / two.toDouble()).toString())
                }
        }
catch(e:ArithmeticException){
    e.printStackTrace()
}
        }
    }
    private fun removeZero(result : String):String{
        var value = result
        if(result.contains(".0")){
            value = result.substring(0,result.length-2)
        }
        return value
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")

        }
    }
    fun onOperator(view : View) {
        tvInput?.text.let {
            if (lastNumeric && !isOperatorAdded(it.toString())) {
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }


    }
}