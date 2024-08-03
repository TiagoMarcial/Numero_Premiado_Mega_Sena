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
        // É BASICAMENTE A MESMA LÓGICA PARA TODOS OS BOTÕES, SÓ MUDA O ESCOPO PASSÍVEL DE SORTEIO,  DAR UM JEITO DE SÓ FAZER 1 VEZ
        val inputText: EditText = findViewById(R.id.edit_number)
        val randomNumber: Button = findViewById(R.id.button_random)
        var result: TextView = findViewById(R.id.text_result)
        var mostLuck: Button = findViewById(R.id.button_mostlucky)
        var leastLuck: Button = findViewById(R.id.button_leastlucky)
        prefs = getSharedPreferences("db", Context.MODE_PRIVATE)
        val lastApostate = prefs.getString("result", "Nenhum registro salvo!")
        if (lastApostate !=null) {
            result.text = "Último resultado: $lastApostate"
        }

        randomNumber.setOnClickListener{
            val text = inputText.text.toString()
            numbersRandom(text, result, 1)
             //APLICANDO UM RESULTADO VAZIO
        }
        mostLuck.setOnClickListener {
            val text = inputText.text.toString()
            numbersRandom(text, result, 2)
        }
        leastLuck.setOnClickListener {
            val text = inputText.text.toString()
            numbersRandom(text, result, 3)
        }
    }
    private fun numbersRandom(text: String, result: TextView, idButton: Int) {
        if (text.isEmpty()){
            Toast.makeText(this, "Digite um número entre 6 e 15", Toast.LENGTH_LONG).show()
        } else {
            val inputNumber = text.toInt()
            if(inputNumber !in 6..15) {
                Toast.makeText(this, "Digite um número entre 6 e 15", Toast.LENGTH_LONG).show()
            } else {
                if (idButton == 1) {
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
                } else if (idButton == 2) {
                    val quantityDraw = inputNumber * 2
                    val avaliableNumbers = listOf(10,53,5,34,23,37,42,30,33,35,4,32,41,11,27,44,17,38,43,28,46,56,16,54,29,49,36,51,6,24,52,2)
                    val filteredNumbers = avaliableNumbers.take(quantityDraw)
                    var drawnNumberResult = mutableSetOf<Int>()
                    while (drawnNumberResult.size < inputNumber){
                        var drawnNumber = filteredNumbers.sorted().shuffled().take(inputNumber)
                        drawnNumberResult.addAll(drawnNumber)
                        if (drawnNumberResult.size == inputNumber){
                            break
                        }
                    }
                    result.setText("Resultado: ${drawnNumberResult.joinToString ( " - " )}")
                    val lastResult = "${drawnNumberResult.joinToString ( " - " )}"
                    val editor = prefs.edit()
                    editor.putString("result", lastResult)
                    editor.commit()
                }
                else if (idButton == 3) {
                    val quantityDraw = inputNumber * 2
                    val avaliableNumbers = listOf(59,20,13,15,21,55,26,22,3,48,40,31,9,7,1,60,39,57,19,12,14,47,18,58,50,45,8,25,2,52,24,6)
                    val filteredNumbers = avaliableNumbers.take(quantityDraw)
                    var drawnNumberResult = mutableSetOf<Int>()
                    while (drawnNumberResult.size < inputNumber){
                        var drawnNumber = filteredNumbers.sorted().shuffled().take(inputNumber)
                        drawnNumberResult.addAll(drawnNumber)
                        if (drawnNumberResult.size == inputNumber){
                            break
                        }
                    }
                    result.setText("Resultado: ${drawnNumberResult.joinToString ( " - " )}")
                    val lastResult = "${drawnNumberResult.joinToString ( " - " )}"
                    val editor = prefs.edit()
                    editor.putString("result", lastResult)
                    editor.commit()
                }
            }
        }
    }
}