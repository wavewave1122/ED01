package com.example.wave

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private var currentInput: String = ""
    private var currentOperator: String? = ""
    private var firstOperand: Double? = 0.0

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    textView = findViewById(R.id.textView)

    // เพิ่ม OnClickListener ให้กับปุ่มทุกปุ่มตัวเลข
    val numberButtons = arrayOf(
        R.id.b0,R.id.b1, R.id.b2, R.id.b3, R.id.b4,
        R.id.b5, R.id.b6, R.id.b7, R.id.b8,
        R.id.b9,
    )

    for (buttonId in numberButtons) {
        findViewById<Button>(buttonId).setOnClickListener {
            onNumberButtonClick(it)
        }
    }

    // เพิ่ม OnClickListener ให้กับปุ่มลบ
    findViewById<Button>(R.id.btnMinus).setOnClickListener {
        onDeleteButtonClick()
    }

    // เพิ่ม OnClickListener ให้กับปุ่มลบทั้งหมด
    findViewById<Button>(R.id.allClear).setOnClickListener {
        clearAll()
    }


    // เพิ่ม OnClickListener ให้กับปุ่มเพิ่ม, ลบ, คูณ, หาร, modulo
    val operatorButtons = arrayOf(
        R.id.btnPlus, R.id.btnMinus, R.id.btnMultiply,R.id.btnDivide, R.id.btnModulo
    )

    for (buttonId in operatorButtons) {
        findViewById<Button>(buttonId).setOnClickListener {
            onOperatorButtonClick(it)
        }
    }

    // เพิ่ม OnClickListener ให้กับปุ่มเท่ากับ
    findViewById<Button>(R.id.btnEqual).setOnClickListener {
        onEqualsButtonClick()
    }

    Log.e("cs", "Result: $currentInput")
    Log.e("cc", "Result: $currentOperator")
    Log.e("we", "Result: $textView")
    Log.e("rd", "Result: $firstOperand")
}

private fun clearAll() {
    currentInput = "0"
    currentOperator = null
    firstOperand = null
    updateResult()
}


private fun onNumberButtonClick(view: View) {
    val buttonText = (view as Button).text.toString()

    if (currentInput == "0") {
        // กรณีที่เป็นเลข 0 เลขเดียวบนหน้าจอ ไม่เพิ่มจำนวนเลข 0
        currentInput = buttonText
    } else {
        currentInput += buttonText
    }

    updateResult()
}

private fun onDeleteButtonClick() {
    if (currentInput.length > 1) {
        // ลบตัวเลขที่พิมพ์ล่าสุดทีละ 1 หลัก
        currentInput = currentInput.substring(0, currentInput.length - 1)
    } else {
        // หากเหลือเลขหลักเดียว กดลบซ้ำเป็นเลข 0
        currentInput = "0"
    }

    updateResult()
}

private fun onOperatorButtonClick(view: View) {
    currentOperator = (view as Button).text.toString()
    firstOperand = currentInput.toDouble()
    currentInput = "0"
}

private fun onEqualsButtonClick() {
    if (currentOperator != null && firstOperand != null) {
        val secondOperand = currentInput.toDouble()
        val result = when (currentOperator) {
            "+" -> firstOperand!! + secondOperand
            "-" -> firstOperand!! - secondOperand
            "X" -> firstOperand!! * secondOperand
            "/" -> firstOperand!! / secondOperand
            "%" -> firstOperand!! % secondOperand
            else -> throw IllegalArgumentException("Invalid operator")
        }

        currentInput = result.toString()
        currentOperator = null
        firstOperand = null

        updateResult()
    }
}

private fun updateResult() {
    textView.text = currentInput
}
}