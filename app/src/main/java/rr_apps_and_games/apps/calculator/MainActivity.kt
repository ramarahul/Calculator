package rr_apps_and_games.apps.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import rr_apps_and_games.apps.calculator.databinding.ActivityMainBinding
import java.lang.Exception
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.clear.setOnClickListener {
            binding.input.text = ""
            binding.output.text = ""
        }
        binding.bracketLeft.setOnClickListener {
            binding.input.text = addToInputText("(")
        }
        binding.bracketRight.setOnClickListener {
            binding.input.text = addToInputText(")")
        }
        binding.button0.setOnClickListener {
            binding.input.text = addToInputText("0")
        }
        binding.button1.setOnClickListener {
            binding.input.text = addToInputText("1")
        }
        binding.button2.setOnClickListener {
            binding.input.text = addToInputText("2")
        }
        binding.button3.setOnClickListener {
            binding.input.text = addToInputText("3")
        }
        binding.button4.setOnClickListener {
            binding.input.text = addToInputText("4")
        }
        binding.button5.setOnClickListener {
            binding.input.text = addToInputText("5")
        }
        binding.button6.setOnClickListener {
            binding.input.text = addToInputText("6")
        }
        binding.button7.setOnClickListener {
            binding.input.text = addToInputText("7")
        }
        binding.button8.setOnClickListener {
            binding.input.text = addToInputText("8")
        }
        binding.button9.setOnClickListener {
            binding.input.text = addToInputText("9")
        }
        binding.buttonDot.setOnClickListener {
            binding.input.text = addToInputText(".")
        }
        binding.division.setOnClickListener {
            binding.input.text = addToInputText("/")
        }
        binding.multiply.setOnClickListener {
            binding.input.text = addToInputText("x")
        }
        binding.subtract.setOnClickListener {
            binding.input.text = addToInputText("-")
        }
        binding.add.setOnClickListener {
            binding.input.text = addToInputText("+")
        }
        binding.equals.setOnClickListener {
            showResult()
        }

    }

    private fun showResult() {
        try {
            val expression = getInputExpression()
            var result = evaluateExpression(expression)
            if(result==null){
                binding.output.text = "Invalid Expression"
                binding.output.setTextColor(ContextCompat.getColor(this,R.color.red))
            } else{
                binding.output.text = result
                binding.output.setTextColor(ContextCompat.getColor(this,R.color.green))
            }

        } catch (e: Exception){
            binding.output.text = "Error"
            binding.output.setTextColor(ContextCompat.getColor(this,R.color.red))
        }
    }

    private fun evaluateExpression(expression: String): String {
        var result = ""
        var numbers = ArrayDeque<Int>()
        var operators = ArrayDeque<Char>()
        for (c: Char in expression){
            if(c=='('){
                operators.addLast(c)
            } else if(c.isDigit()){
                numbers.addLast(c-'0');
            } else if(c==')'){
                while (operators.last()!='('){
                    var operator = operators.removeLast()
                    var v2 = numbers.removeLast()
                    var v1 = numbers.removeLast()
                    var number = operation(v1,v2,operator)
                    numbers.addLast(number)

                }
                operators.removeLast()
            } else if(c=='+' || c=='-' || c=='*' || c=='/'){
                while (!operators.isEmpty() && operators.last()!='(' && precedence(c) <= precedence(operators.last())){
                    var operator = operators.removeLast()
                    var v2 = numbers.removeLast()
                    var v1 = numbers.removeLast()
                    var number = operation(v1,v2,operator)
                    numbers.addLast(number)
                }

                operators.addLast(c)
            }
        }

        while (!operators.isEmpty()){
            var operator = operators.removeLast()
            var v2 = numbers.removeLast()
            var v1 = numbers.removeLast()
            var number = operation(v1,v2,operator)
            numbers.addLast(number)
        }
        result = numbers.removeLastOrNull().toString()
        return result
    }

    private fun precedence(operator : Char): Int {
        return when (operator) {
            '+' -> {
                1;
            }
            '-' -> {
                1;
            }
            '*' -> {
                2;
            }
            '/' -> {
                2;
            }
            else -> 0
        }
    }

    private fun operation(v1: Int,v2:Int, operator: Char): Int {
        return when (operator) {
            '+' -> {
                v1 + v2;
            }
            '-' -> {
                v1 - v2;
            }
            '*' -> {
                v1 * v2;
            }
            '/' -> {
                v1 / v2;
            }
            else -> 0
        }
    }

    private fun getInputExpression():String{
        var expression = binding.input.text.replace(Regex("/"),"/").replace(Regex("x"),"*")
        return expression
    }

    private fun addToInputText(buttonValue: String): String{
        return "${binding.input.text}$buttonValue"
    }
}