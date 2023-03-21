package com.example.calculateapp

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btn_1: Button
    private lateinit var btn_2: Button
    private lateinit var btn_3: Button
    private lateinit var btn_4: Button
    private lateinit var btn_5: Button
    private lateinit var btn_6: Button
    private lateinit var btn_7: Button
    private lateinit var btn_8: Button
    private lateinit var btn_9: Button
    private lateinit var btn_0: Button
    private lateinit var btn_Del: Button
    private lateinit var btn_Result: Button
    private lateinit var btn_Multiple: Button
    private lateinit var btn_Plus: Button
    private lateinit var btn_Subtract: Button
    private lateinit var btn_Div: Button

    private lateinit var txt_Calculation: TextView
    private lateinit var txt_Result: TextView

    fun getView() {
        btn_1 = findViewById(R.id.btn_1)
        btn_2 = findViewById(R.id.btn_2)
        btn_3 = findViewById(R.id.btn_3)
        btn_4 = findViewById(R.id.btn_4)
        btn_5 = findViewById(R.id.btn_5)
        btn_6 = findViewById(R.id.btn_6)
        btn_7 = findViewById(R.id.btn_7)
        btn_8 = findViewById(R.id.btn_8)
        btn_9 = findViewById(R.id.btn_9)
        btn_0 = findViewById(R.id.btn_0)

        btn_Del = findViewById(R.id.btn_delete)
        btn_Plus = findViewById(R.id.btn_plus)
        btn_Subtract = findViewById(R.id.btn_subtract)
        btn_Div = findViewById(R.id.btn_division)
        btn_Multiple = findViewById(R.id.btn_multiple)
        btn_Result = findViewById(R.id.btn_result)

        txt_Calculation = findViewById(R.id.txt_Calculation)
        txt_Result = findViewById(R.id.txt_Result)
    }


    fun checkPrevIsSymbol(cal: String): Boolean {

        val symbol = "+-*/"
        if (cal.toString().equals(""))
            return true
        return cal.last().toString() in symbol
    }

    fun checkPrevIsSymbol_sec(cal: String): Boolean {

        val symbol = "*/"
        if (cal.toString().equals(""))
            return true
        return cal.last().toString() in symbol
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        requestWindowFeature(Window.FEATURE_NO_TITLE) //will hide the title
        supportActionBar!!.hide()
        setContentView(R.layout.activity_main)

        getView()


        txt_Calculation.text = ""

        btn_1.setOnClickListener {
            txt_Calculation.text = txt_Calculation.text.toString() + "1";
        }

        btn_2.setOnClickListener {
            txt_Calculation.text = txt_Calculation.text.toString() + "2";
        }

        btn_3.setOnClickListener {
            txt_Calculation.text = txt_Calculation.text.toString() + "3";
        }

        btn_4.setOnClickListener {
            txt_Calculation.text = txt_Calculation.text.toString() + "4";
        }

        btn_5.setOnClickListener {
            txt_Calculation.text = txt_Calculation.text.toString() + "5";
        }

        btn_6.setOnClickListener {
            txt_Calculation.text = txt_Calculation.text.toString() + "6";
        }

        btn_7.setOnClickListener {
            txt_Calculation.text = txt_Calculation.text.toString() + "7";
        }

        btn_8.setOnClickListener {
            txt_Calculation.text = txt_Calculation.text.toString() + "8";
        }

        btn_9.setOnClickListener {
            txt_Calculation.text = txt_Calculation.text.toString() + "9";
        }

        btn_0.setOnClickListener {
            if (txt_Calculation.text.toString() != "") {
                txt_Calculation.text = txt_Calculation.text.toString() + "0";
            }
        }

        btn_Del.setOnClickListener {
            txt_Calculation.text = "";
        }

        btn_Multiple.setOnClickListener {
            if (!checkPrevIsSymbol(txt_Calculation.text.toString()) && txt_Calculation.text.toString() != "") {
                txt_Calculation.text = txt_Calculation.text.toString() + "*"
            } else if (checkPrevIsSymbol(txt_Calculation.text.toString())) {
                txt_Calculation.text = txt_Calculation.text.toString().dropLast(1)
                txt_Calculation.text = txt_Calculation.text.toString() + "*"

            }

        }

        btn_Plus.setOnClickListener {
            if (!checkPrevIsSymbol(txt_Calculation.text.toString()) && txt_Calculation.text.toString() != "") {
                txt_Calculation.text = txt_Calculation.text.toString() + "+"
            } else if (checkPrevIsSymbol(txt_Calculation.text.toString())) {
                txt_Calculation.text = txt_Calculation.text.toString().dropLast(1)
                txt_Calculation.text = txt_Calculation.text.toString() + "+"

            }

        }

        btn_Subtract.setOnClickListener {
            if (!checkPrevIsSymbol(txt_Calculation.text.toString()) && txt_Calculation.text.toString() != "") {
                txt_Calculation.text = txt_Calculation.text.toString() + "-"
            } else if (checkPrevIsSymbol_sec(txt_Calculation.text.toString())) {
                txt_Calculation.text = txt_Calculation.text.toString() + "-"
            } else if (txt_Calculation.text.last().toString().equals("+")) {
                txt_Calculation.text = txt_Calculation.text.toString().dropLast(1)
                txt_Calculation.text = txt_Calculation.text.toString() + "-"

            }

        }

        btn_Div.setOnClickListener {
            if (!checkPrevIsSymbol(txt_Calculation.text.toString()) && txt_Calculation.text.toString() != "") {
                txt_Calculation.text = txt_Calculation.text.toString() + "/"
            } else if (checkPrevIsSymbol(txt_Calculation.text.toString())) {
                txt_Calculation.text = txt_Calculation.text.toString().dropLast(1)
                txt_Calculation.text = txt_Calculation.text.toString() + "/"

            }

        }

        btn_Result.setOnClickListener {


            if (checkPrevIsSymbol(txt_Calculation.text.toString()) || txt_Calculation.text.toString() == "") {
                txt_Result.text = "ERROR"
            } else {
                calcFirst(txt_Calculation.text.toString())
            }

            txt_Calculation.text = "";
        }

    }

    fun calcFirst(cal: String) {
        for (i in cal) {
            if (isSymbol(i.toString())) {
                var indexOfSymbol = cal.indexOf(i.toString())
                txt_Result.text = indexOfSymbol.toString()
            }
        }
    }

    fun calculate(cal: String): Double {
        var calResult: Double

        calResult = 0.0

        return calResult
    }

    fun isSymbol(cal: String): Boolean {
        var isSymbol = false
        var symbol = "*/"
        for (i in cal) {
            if (i in symbol) {
                return true
            }
        }
        return false
    }

    fun getNumberPrevSymbol(position: Int, cal: String): Int {
        var numberPrevSymbos: Int
        numberPrevSymbos = 0



        return numberPrevSymbos
    }


}