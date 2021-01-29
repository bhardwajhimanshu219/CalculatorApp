package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    var lastnumeric: Boolean = false
    var lastdot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun onDigit(view: View)
    {
        text_input.append((view as Button).text)
        lastnumeric=true
    }
    fun onClear(view: View)
    {
        text_input.text=""
        lastdot=false
        lastnumeric=false
    }
    fun onDecimalpoint(view: View)
    {
        if(lastnumeric && !lastdot)
        {
           text_input.append(".")
            lastdot=true
        }

    }

     fun Operator(view : View){
        if (lastnumeric && !isOperator(text_input.text.toString()))
        {
            text_input.append((view as Button).text)
            lastnumeric=false
            lastdot=false
        }
    }

    private fun isOperator(value:String) : Boolean
    {
        return if (value.startsWith("-")) {
            false
        }
        else
        {
            value.contains("/")|| value.contains("*") || value.contains("+")
                    || value.contains("-")

        }

    }
    fun onEqual(view: View) {
        if (lastnumeric) {
            var tvValue = text_input.text.toString()
            var prefix = ""

            try {

                if (tvValue.startsWith("-"))
                {
                    prefix="-"
                    tvValue = tvValue.substring(1)
                }

                if (tvValue.contains("-")){
                    val splitValue = tvValue.split("-")

                    var one = splitValue[0]
                    var two = splitValue[1]


                    if (!prefix.isEmpty())
                    {
                        one = prefix + one
                    }

                        text_input.text = (one.toDouble()- two.toDouble()).toString()
                }
                else if (tvValue.contains("+")){
                    val splitValue = tvValue.split("+")

                    var one = splitValue[0]
                    var two = splitValue[1]


                    if (!prefix.isEmpty())
                    {
                        one = prefix + one
                    }

                    text_input.text = (one.toDouble() + two.toDouble()).toString()
                }
                if (tvValue.contains("*")){
                    val splitValue = tvValue.split("*")

                    var one = splitValue[0]
                    var two = splitValue[1]


                    if (!prefix.isEmpty())
                    {
                        one = prefix + one
                    }

                    text_input.text = (one.toDouble() * two.toDouble()).toString()
                }
                if (tvValue.contains("/")){
                    val splitValue = tvValue.split("/")

                    var one = splitValue[0]
                    var two = splitValue[1]


                    if (!prefix.isEmpty())
                    {
                        one = prefix + one
                    }

                    text_input.text = (one.toDouble() / two.toDouble()).toString()
                }

            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }


}
