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
        setContentView(R.layout.activity_main)

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
            if(result.equals("Invalid")){
                binding.output.text = "Invalid Expression"
                binding.output.setTextColor(ContextCompat.getColor(this,R.color.red))
            } else{
                binding.output.text = DecimalFormat("0.######").format(result).toString()
                binding.output.setTextColor(ContextCompat.getColor(this,R.color.green))
            }

        } catch (e: Exception){
            binding.output.text = "Error"
            binding.output.setTextColor(ContextCompat.getColor(this,R.color.red))
        }
    }

    private fun evaluateExpression(expression: String): String {
        var result = ""

        return result
    }

    private fun getInputExpression():String{
        var expression = binding.input.text.replace(Regex("/"),"/").replace(Regex("x"),"*")
        return expression
    }

    private fun addToInputText(buttonValue: String): String{
        return "${binding.input.text}$buttonValue"
    }
}