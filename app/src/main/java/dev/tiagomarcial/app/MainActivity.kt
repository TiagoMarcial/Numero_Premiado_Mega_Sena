package dev.tiagomarcial.app

import android.content.Context
import android.content.SharedPreferences
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
    private lateinit var prefs: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // FAZER FUNÇÃO PRIVADA DO BOTÃO E COLOCAR UM NOVO IF DEPOIS DO VAZIO, POS 1 IF SÓ CONVERTE O NULL EM NUMERO E NÃO APLICA O TOAST
        val inputText: EditText = findViewById(R.id.edit_number)
        val randomNumber: Button = findViewById(R.id.button_random)
        var result: TextView = findViewById(R.id.text_result)
        prefs = getSharedPreferences("db", Context.MODE_PRIVATE)
        val lastApostate = prefs.getString("result", "Nenhum registro salvo!")
        if (lastApostate !=null) {
            result.text = "Último resultado: $lastApostate"
        }
        randomNumber.setOnClickListener{
            val text = inputText.text.toString()
            numbersRandom(text, result)
             //APLICANDO UM RESULTADO VAZIO
        }
    }
    private fun numbersRandom(text: String, result: TextView) {
        if (text.isEmpty()){
            Toast.makeText(this, "Digite um número entre 6 e 15", Toast.LENGTH_LONG).show()
        } else {
            val inputNumber = text.toInt()
            if(inputNumber !in 6..15) {
                Toast.makeText(this, "Digite um número entre 6 e 15", Toast.LENGTH_LONG).show()
            } else {
                val random = Random.Default
                var numbers = mutableSetOf<Int>()
                while (true) {
                    val number = random.nextInt(1, 60)
                    numbers.add(number)
                    if (inputNumber == numbers.size) {
                        break
                    }
                }
                result.setText("Resultado: ${numbers.joinToString ( " - " )}")
                val lastResult = "${numbers.joinToString ( " - " )}"
                val editor = prefs.edit()
                editor.putString("result", lastResult)
                editor.commit()
            }
        }
    }
}