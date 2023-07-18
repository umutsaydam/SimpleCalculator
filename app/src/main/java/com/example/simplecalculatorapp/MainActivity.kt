package com.example.simplecalculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.EditText
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

class MainActivity : AppCompatActivity() {
    private lateinit var expression: EditText
    private lateinit var result: EditText
    private lateinit var btnClear: Button
    private lateinit var btnBackSpace: Button
    private lateinit var btnPercent: Button
    private lateinit var btnDivide: Button
    private lateinit var btnTimes: Button
    private lateinit var btnPlus: Button
    private lateinit var btnMinus: Button
    private lateinit var btnEquals: Button
    private lateinit var btnDot: Button
    private lateinit var btnDoubleZero: Button
    private lateinit var btnZero: Button
    private lateinit var btnOne: Button
    private lateinit var btnTwo: Button
    private lateinit var btnThree: Button
    private lateinit var btnFour: Button
    private lateinit var btnFive: Button
    private lateinit var btnSix: Button
    private lateinit var btnSeven: Button
    private lateinit var btnEight: Button
    private lateinit var btnNine: Button
    private lateinit var str: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        expression = findViewById(R.id.expression)
        result = findViewById(R.id.result)
        btnClear = findViewById(R.id.btnClear)
        btnBackSpace = findViewById(R.id.btnLess)
        btnPercent = findViewById(R.id.btnPercent)
        btnDivide = findViewById(R.id.btnDivide)
        btnTimes = findViewById(R.id.btnTimes)
        btnPlus = findViewById(R.id.btnPlus)
        btnMinus = findViewById(R.id.btnMinus)
        btnEquals = findViewById(R.id.btnEquals)
        btnDot = findViewById(R.id.btnDot)
        btnDoubleZero = findViewById(R.id.btnDoubleZero)
        btnZero = findViewById(R.id.btnZero)
        btnOne = findViewById(R.id.btnOne)
        btnTwo = findViewById(R.id.btnTwo)
        btnThree = findViewById(R.id.btnThree)
        btnFour = findViewById(R.id.btnFour)
        btnFive = findViewById(R.id.btnFive)
        btnSix = findViewById(R.id.btnSix)
        btnSeven = findViewById(R.id.btnSeven)
        btnEight = findViewById(R.id.btnEight)
        btnNine = findViewById(R.id.btnNine)

        expression.movementMethod = ScrollingMovementMethod()
        expression.isActivated = true
        expression.isPressed = true

        btnClear.setOnClickListener {
            expressionText("0")
            expression.textSize = 60F
            result.textSize = 30F
            resultText()
        }

        btnBackSpace.setOnClickListener {
            if (expression.text.toString().isNotEmpty()) {
                val lastIndex = expression.text.toString().lastIndex
                str = expression.text.toString().substring(0, lastIndex)
                expressionText(str)
                resultText()
            }
        }

        btnPercent.setOnClickListener { specialCharacter("%") }

        btnTimes.setOnClickListener { specialCharacter("*") }

        btnPlus.setOnClickListener { specialCharacter("+") }

        btnMinus.setOnClickListener { specialCharacter("-") }

        btnEquals.setOnClickListener {
            expression.textSize = 30F
            result.textSize = 60F
        }

        btnDot.setOnClickListener { specialCharacter(".") }

        btnDoubleZero.setOnClickListener { numbers("00") }

        btnZero.setOnClickListener { numbers("0") }

        btnOne.setOnClickListener { numbers("1") }

        btnTwo.setOnClickListener { numbers("2") }

        btnThree.setOnClickListener { numbers("3") }

        btnFour.setOnClickListener { numbers("4") }

        btnFive.setOnClickListener { numbers("5") }

        btnSix.setOnClickListener { numbers("6") }

        btnSeven.setOnClickListener { numbers("7") }

        btnEight.setOnClickListener { numbers("8") }

        btnNine.setOnClickListener { numbers("9") }


    }

    private fun numbers(num: String) {
        val strExpr = expression.text.toString()
        str = if (strExpr.startsWith("0")) {
            strExpr.replace("0", "") + num
        } else {
            strExpr + num
        }
        expressionText(str)
        resultText()
    }

    private fun specialCharacter(character: String) {
        var strExpr = expression.text.toString()
        if (strExpr.endsWith("%") || strExpr.endsWith("*") || strExpr.endsWith("+") ||
            strExpr.endsWith("-") || strExpr.endsWith(".") || strExpr.endsWith("/")
        ) {
            strExpr = strExpr.substring(0, strExpr.length - 1) + character
        } else {
            strExpr += character
        }
        expression.setText(strExpr)
        resultText()
    }

    private fun expressionText(strExpr: String) {
        expression.setText(strExpr)
    }

    private fun resultText() {
        val exp = expression.text.toString()
        val engine: ScriptEngine = ScriptEngineManager().getEngineByName("rhino")

        try {
            val res = engine.eval(exp).toString()
            if (res.endsWith("0")) {
                result.setText("="+res.replace(".0", ""))
            }else {
                result.setText("=$res")
            }
        }catch (e: Exception) {
            expression.setText(expression.text.toString())
            result.setText(expression.text.toString())
        }
    }
}