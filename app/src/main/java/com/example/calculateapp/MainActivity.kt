package com.example.calculateapp

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.roundToInt

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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN
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


            if (checkPrevIsSymbol(txt_Calculation.text.toString()) || txt_Calculation.text.toString() == "" || checkDivWithZero(
                    txt_Calculation.text.toString()
                )
            ) {
                txt_Result.text = "ERROR"
            } else {
                calcFirst(txt_Calculation.text.toString())
            }

            txt_Calculation.text = "";
        }

    }

    fun calcFirst(cal: String) {

        var calculate = cal

        for (i in calculate) {
            if (isSymbol(i.toString())) {
                var indexOfSymbol = calculate.indexOf(i.toString())

                var numberPrevSymbol = getNumberPrevSymbol(indexOfSymbol, calculate)
                var numberNextSymbol = getNumberNextSymbol(indexOfSymbol, calculate)
                var miniCalcNumber: Double = 0.0

                if (calculate[indexOfSymbol].toString().equals("*")) {
                    miniCalcNumber = (numberNextSymbol.toDouble() * numberPrevSymbol.toDouble())
                } else {
                    miniCalcNumber = (numberPrevSymbol.toDouble() / numberNextSymbol.toDouble())
                }

                miniCalcNumber = (miniCalcNumber * 100.0).roundToInt() / 100.0


                var calNumber = miniCalcNumber.toString()

                var new_NextCal = getNew_NextCal(indexOfSymbol, calculate)
                var new_PrevCal = getNew_PrevCal(indexOfSymbol, calculate)


                if (new_PrevCal != "" && new_PrevCal.last().toString()
                        .equals("+") && calNumber[0].toString().equals("-")
                ) {
                    new_PrevCal = new_PrevCal.replace("+", "")
                }

                calculate = new_PrevCal + calNumber + new_NextCal


            }
        }

        txt_Result.text = last_calculate(calculate).toString()

    }

    fun last_calculate(cal: String): Double {

        var calculate = cal
        var calcResult: Double = 0.0
        for (i in calculate){
            if (isSymbolPlusOrSubTract(i.toString()) && !i.toString().equals(calculate[0].toString())){
                var position = calculate.indexOf(i.toString())
                var firstVariable = getFirstVariable(position,calculate)
                var secondVariable = getSecondVariable(position,calculate)



                if (calculate[position].toString().equals("+")) {
                    calcResult = (firstVariable + secondVariable)
                } else {
                    calcResult = (firstVariable - secondVariable)
                }

            }
        }

        return calcResult
    }

    fun getFirstVariable(position: Int, cal: String): Double {

        var numberPrevSymbos: String
        numberPrevSymbos = ""

        var indexOfSymbol = position

        var index = 0

        if (cal[index].toString().equals("-")) {
            index++
            numberPrevSymbos = "-" + numberPrevSymbos
        }

        while (index < indexOfSymbol) {
            numberPrevSymbos += cal[index].toString()
            index++
        }

        return numberPrevSymbos.toDouble()

    }

    fun getSecondVariable(position: Int,cal: String):Double{

        var numberNextSymbos: String
        numberNextSymbos = ""

        var index = position + 1

        while (index < cal.length && !checkPrevIsSymbol(cal[index].toString())) {

            numberNextSymbos += cal[index].toString()
            index++

        }

        println("HAHAHA - "+numberNextSymbos)

        return numberNextSymbos.toDouble()

    }

//    fun countSymbolinCalc(cal: String): Int {
//        var count = 0
//
//        for (i in cal) {
//            if (checkPrevIsSymbol(i.toString())) {
//                count++
//            }
//        }
//
//        return count
//
//    }

    fun isSymbolPlusOrSubTract(cal: String): Boolean {
        var symbol = "+-"
        for (i in cal) {
            if (i in symbol) {
                return true
            }
        }
        return false
    }

    fun isSymbol(cal: String): Boolean {
        var symbol = "*/"
        for (i in cal) {
            if (i in symbol) {
                return true
            }
        }
        return false
    }

    fun getNumberPrevSymbol(position: Int, cal: String): Double {
        var numberPrevSymbos: String
        numberPrevSymbos = ""

        var indexOfSymbol = position

        var index = indexOfSymbol - 1

        while (index > -1 && !checkPrevIsSymbol(cal[index].toString())) {

            numberPrevSymbos += cal[index].toString()
            index--

        }
        numberPrevSymbos = numberPrevSymbos.reversed()

        if (index == 0) {
            numberPrevSymbos = "-" + numberPrevSymbos
        }

        return numberPrevSymbos.toDouble()
    }

    fun getNew_PrevCal(position: Int, cal: String): String {
        var numberPrevSymbos: String
        numberPrevSymbos = ""

        var indexOfSymbol = position

        var index = indexOfSymbol - 1

        while (index > -1 && !checkPrevIsSymbol(cal[index].toString())) {

            numberPrevSymbos += cal[index].toString()
            index--

        }

        numberPrevSymbos = numberPrevSymbos.reversed()

        if (index == 0 && cal[index].toString().equals("-")) {
            numberPrevSymbos = "-" + numberPrevSymbos
        }

        var cutStrPrevSymbol = ""
        if (index > 0) {
            cutStrPrevSymbol = cal.substring(0, index + 1)
        }


        return cutStrPrevSymbol
    }

    fun getNumberNextSymbol(position: Int, cal: String): Int {
        var numberNextSymbos: String
        numberNextSymbos = ""

        var indexOfSymbol = position

        var index = indexOfSymbol + 1

        if (cal[index].toString().equals("-")) {
            numberNextSymbos += cal[index].toString()
            index++
        }

        while (index < cal.length && !checkPrevIsSymbol(cal[index].toString())) {


            numberNextSymbos += cal[index].toString()
            index++

        }

        return numberNextSymbos.toInt()
    }

    fun getNew_NextCal(position: Int, cal: String): String {
        var numberNextSymbos: String
        numberNextSymbos = ""

        var indexOfSymbol = position

        var index = indexOfSymbol + 1

        if (cal[index].toString().equals("-")) {
            numberNextSymbos += cal[index].toString()
            index++
        }

        while (index < cal.length && !checkPrevIsSymbol(cal[index].toString())) {


            numberNextSymbos += cal[index].toString()
            index++

        }


        var cutStrNextSymbol = ""
        if (index < cal.length) {
            cutStrNextSymbol = cal.substring(index, cal.length)
        }

        return cutStrNextSymbol
    }


    fun checkPrevIsSymbol(cal: String): Boolean {

        val symbol = "+-*/"
        if (cal.equals("")) return true
        return cal.last().toString() in symbol
    }

    fun checkPrevIsSymbol_sec(cal: String): Boolean {

        val symbol = "*/"
        if (cal.equals("")) return true
        return cal.last().toString() in symbol
    }

    fun checkDivWithZero(cal: String): Boolean {
        for (i in cal) {
            if (i.toString().equals("/") && cal[cal.indexOf(i) + 1].toString().equals("0")) {
                return true
            }
        }
        return false
    }


}