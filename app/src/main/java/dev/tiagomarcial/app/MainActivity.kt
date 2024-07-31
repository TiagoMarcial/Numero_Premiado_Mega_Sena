package dev.tiagomarcial.app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val inputText: EditText = findViewById(R.id.edit_number)
        val randomNumber: Button = findViewById(R.id.button_random)
        var result: TextView = findViewById(R.id.text_result)
        randomNumber.setOnClickListener{
            val text = inputText.text.toString()
            val inputNumber = text.toInt()
            val random = Random.Default
            var numbers = mutableSetOf<Int>()
        if (inputText.text.toString().isEmpty() || inputNumber !in 6..15){
            Toast.makeText(this, "Digite um número entre 6 e 15!", Toast.LENGTH_LONG).show()
        } else {
            while (true) {
                val number = random.nextInt(1,60)
                numbers.add(number)
                if (inputNumber == numbers.size){
                    break
                }
            }
        }
            result.setText("Resultado: $numbers")
        }
    }
}