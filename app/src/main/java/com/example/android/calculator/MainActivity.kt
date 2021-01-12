package com.example.android.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lastNumeric = false
    var lastDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun removeZeroAfterDot(result:String):String{
        var value = result
        if(result.contains("0"))
            value = value.substring(0,result.length-2)    // 22.00
                                                          // 0  1 2
            return value                                              // it will give index o value i.e 22
    }

    fun onDigit(view: View) {
        tvInput.append((view as Button).text)
        lastNumeric = true
    }

    fun onClear(view: View) {
        tvInput.text = " "
        lastNumeric = false
        lastDot = false
    }

    fun onDecimal(view: View) {                           // Example 99-1
        if (lastNumeric && !lastDot)                      // var one = 99 ;   var two = 1
            tvInput.append(".")
        lastNumeric = false
        lastDot = true
    }
    fun onEqual(view: View){
             if(lastNumeric){
                 var tvValue = tvInput.text.toString()
                 var prefix = ""
                 try {
                     if(tvValue.startsWith("-")){
                         prefix="-"
                         tvValue = tvValue.substring(1)
                     }
                         if(tvValue.contains("-")){
                             val splitValue =  tvValue.split("-")

                             var one = splitValue[0]
                             var two = splitValue[1]

                             if(!prefix.isEmpty()){
                                 one =  prefix + one
                             }
                             tvInput.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                         }
                            // for ADDITION

                            else if(tvValue.contains("+")){
                             val splitValue =  tvValue.split("+")

                             var one = splitValue[0]
                             var two = splitValue[1]

                             if(!prefix.isEmpty()){
                                 one =  prefix + one
                             }
                             tvInput.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                         }
                        // FOR MULTIPLY
                     if(tvValue.contains("*")){
                         val splitValue =  tvValue.split("*")

                         var one = splitValue[0]
                         var two = splitValue[1]

                         if(!prefix.isEmpty()){
                             one =  prefix + one
                         }
                         tvInput.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                     }
                      //FOR DIVISION

                     if(tvValue.contains("/")){
                         val splitValue =  tvValue.split("/")

                         var one = splitValue[0]
                         var two = splitValue[1]

                         if(!prefix.isEmpty()){
                             one =  prefix + one
                         }
                         tvInput.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                     }

                 }catch (e: ArithmeticException){
                     e.printStackTrace()
                 }
             }
    }

    fun onOperator(view: View){
        if(lastNumeric && !isOperatorAdded(tvInput.text.toString())){
            tvInput.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }

    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith(prefix = "-")){
            false
        }else
        {
            value.contains(other = "/") || value.contains(other = "*") || value.contains(other = "+")
                    ||value.contains(other = "-")
        }
    }
}
