package rr_apps_and_games.apps.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import rr_apps_and_games.apps.calculator.databinding.ActivityMainBinding
import java.lang.Exception

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
            if(binding.input.text.isNotEmpty())
                binding.input.text = binding.input.text.subSequence(0,binding.input.text.length -1)
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
            val result = evaluateExpression(expression)
            binding.output.text = result
            binding.output.setTextColor(ContextCompat.getColor(this,R.color.green))

        } catch (e: Exception){
            binding.output.text = getString(R.string.error)
            binding.output.setTextColor(ContextCompat.getColor(this,R.color.red))
        }
    }

    private fun evaluateExpression(expression: String): String {
        val result : String
        val numbers = ArrayDeque<Long>()
        val operators = ArrayDeque<Char>()
        var i = 0
        while (i < expression.length){
            if(expression[i]=='('){
                operators.addLast(expression[i])
            } else if(expression[i].isDigit()){
                var num = ""
                num += expression[i]
                while(i < expression.length - 1 && expression[i+1].isDigit()){
                    num += expression[i+1]
                    i += 1
                }
                numbers.addLast(num.toLong())
            } else if(expression[i]==')'){
                while (operators.last()!='('){
                    val operator = operators.removeLast()
                    val v2 = numbers.removeLast()
                    val v1 = numbers.removeLast()
                    val number = operation(v1,v2,operator)
                    numbers.addLast(number)

                }
                operators.removeLast()
            } else if(expression[i]=='+' || expression[i]=='-' || expression[i]=='*' || expression[i]=='/'){
                while (!operators.isEmpty() && operators.last()!='(' && precedence(expression[i]) <= precedence(operators.last())){
                    val operator = operators.removeLast()
                    val v2 = numbers.removeLast()
                    val v1 = numbers.removeLast()
                    val number = operation(v1,v2,operator)
                    numbers.addLast(number)
                }

                operators.addLast(expression[i])
            }
            i++
        }

        while (!operators.isEmpty()){
            val operator = operators.removeLast()
            val v2 = numbers.removeLast()
            val v1 = numbers.removeLast()
            val number = operation(v1,v2,operator)
            numbers.addLast(number)
        }
        result = numbers.removeLastOrNull().toString()
        return result
    }

    private fun precedence(operator : Char): Int {
        return when (operator) {
            '+' -> {
                1
            }
            '-' -> {
                1
            }
            '*' -> {
                2
            }
            '/' -> {
                2
            }
            else -> 0
        }
    }

    private fun operation(v1: Long,v2:Long, operator: Char): Long {
        return when (operator) {
            '+' -> {
                v1 + v2
            }
            '-' -> {
                v1 - v2
            }
            '*' -> {
                v1 * v2
            }
            '/' -> {
                v1 / v2
            }
            else -> 0
        }
    }

    private fun getInputExpression():String{
        return binding.input.text.replace(Regex("/"),"/").replace(Regex("x"),"*")
    }

    private fun addToInputText(buttonValue: String): String{
        return "${binding.input.text}$buttonValue"
    }
}