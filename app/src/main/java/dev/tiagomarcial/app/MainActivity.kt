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
import java.util.Calendar
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
        val result: TextView = findViewById(R.id.text_result)
        val mostLuck: Button = findViewById(R.id.button_mostlucky)
        val leastLuck: Button = findViewById(R.id.button_leastlucky)
        val mostLuckMonth: Button = findViewById(R.id.button_mostluckmonth)
        val leastLuckMonth: Button = findViewById(R.id.button_leastluckymonth)

        prefs = getSharedPreferences("db", Context.MODE_PRIVATE)
        val lastApostate = prefs.getString("result", "Nenhum registro salvo!")
        if (lastApostate !=null) {
            result.text = "Último resultado: $lastApostate"
        }

        randomNumber.setOnClickListener{
            val text = inputText.text.toString()
            numbersRandom(text, result, 1)

        }
        mostLuck.setOnClickListener {
            val text = inputText.text.toString()
            numbersRandom(text, result, 2)
        }
        leastLuck.setOnClickListener {
            val text = inputText.text.toString()
            numbersRandom(text, result, 3)
        }
        mostLuckMonth.setOnClickListener {
            val text = inputText.text.toString()
            numbersRandom(text, result, 4)
        }
        leastLuckMonth.setOnClickListener {
            val text = inputText.text.toString()
            numbersRandom(text, result, 5)
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
                //BOTÃO 1 (Gerar números completamente aleatórios)
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
                    //Botão 2 (
                } else if (idButton == 2) {
                    val quantityDraw = inputNumber * 2
                    val avaliableNumbers = listOf(10,53,5,34,23,37,42,30,33,35,4,32,41,11,27,44,17,38,43,28,46,56,16,54,29,49,36,51,6,24,52,2)
                    val filteredNumbers = avaliableNumbers.take(quantityDraw)
                    val drawnNumberResult = mutableSetOf<Int>()
                    while (drawnNumberResult.size < inputNumber){
                        val drawnNumber = filteredNumbers.sorted().shuffled().take(inputNumber)
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
                    val drawnNumberResult = mutableSetOf<Int>()
                    while (drawnNumberResult.size < inputNumber){
                        val drawnNumber = filteredNumbers.sorted().shuffled().take(inputNumber)
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
                else if (idButton == 4) {
                    val quantityDraw = inputNumber * 2
                    val calendar = Calendar.getInstance()
                    val month = calendar.get(Calendar.MONTH) + 1
                    val avaliableNumbers = listMonth(month)
                    val filteredNumbers = avaliableNumbers.take(quantityDraw)
                    val drawnNumberResult = mutableSetOf<Int>()
                    while (drawnNumberResult.size < inputNumber){
                        val drawnNumber = filteredNumbers.sorted().shuffled().take(inputNumber)
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
                else if (idButton == 5 ) {
                    val quantityDraw = inputNumber * 2
                    val calendar = Calendar.getInstance()
                    val month = calendar.get(Calendar.MONTH) + 1
                    val avaliableNumbers = listMonthLeast(month)
                    val filteredNumbers = avaliableNumbers.take(quantityDraw)
                    val drawnNumberResult = mutableSetOf<Int>()
                    while (drawnNumberResult.size < inputNumber){
                        val drawnNumber = filteredNumbers.sorted().shuffled().take(inputNumber)
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
    private fun listMonth(month: Int) : List<Int> {
        return when (month) {
            1 -> listOf(46,13,21,35,44,30,25,52,41,54,28,20,36,42,24,19,59,33,18,17,56,22,12,48,29,47,16,5,38,26,32)
            2 -> listOf(1,2,5,42,8,9,11,13,44,52,24,45,46,4,10,19,51,6,43,30,12,14,20,27,22,37,23,57,25,48)
            3 -> listOf(18,3,2,35,11,31,47,46,34,54,13,19,49,17,16,6,10,60,14,4,27,39,33,53,32,41,45,42,20,24,40,30)
            4 -> listOf(38,23,42,39,36,59,30,19,17,53,5,49,58,25,50,35,10,20,44,40,7,43,46,52,9,16,28,60,34,14,57)
            5 -> listOf(23,39,36,19,46,56,53,25,27,10,37,31,32,12,50,29,7,60,14,44,8,11,30,54,52,28,47,45,41,17)
            6 -> listOf(16,53,46,37,42,32,29,41,10,12,38,20,11,48,24,51,39,49,40,25,52,19,5,54,17,21,43,50,9,33,30,57,6,44)
            7 -> listOf(36,27,44,43,38,19,46,42,59,25,14,24,52,5,39,16,13,56,23,10,58,17,31,45,8,12,18,32,40,29,37,54,48,30)
            8 -> listOf(35,9,52,39,43,45,59,41,42,25,44,36,19,13,30,48,10,29,14,16,60,31,32,21,28,12,5,6,37,50)
            9 -> listOf(29,22,48,39,53,36,32,10,37,5,38,54,9,60,43,41,55,6,16,13,11,14,15,34,40,56,17,28,18,44)
            10 -> listOf(38,18,30,17,46,29,28,49,39,44,53,10,60,57,19,56,9,41,32,5,11,23,50,16,31,26,4,13,43,22,37)
            11 -> listOf(36,56,10,53,29,39,38,60,28,25,45,6,32,5,35,11,41,24,12,27,49,13,20,42,19,40,44,33,30,23)
            12 -> listOf(32,46,41,30,19,39,36,10,23,16,45,37,20,48,29,27,28,12,40,21,52,25,38,59,33,49,15,8,42,60)
            else -> emptyList()
        }
    }
    private fun listMonthLeast (month: Int) : List<Int> {
        return when (month) {
            1 -> listOf(23,27,53,51,59,40,26,33,34,50,14,9,55,7,31,10,4,60,8,58,6,49,57,45,37,15,3,39,11,32,26)
            2 -> listOf(53,54,55,50,31,39,60,21,28,47,49,34,35,29,32,56,18,38,33,26,40,7,30,48,25,23,57,37,22,27)
            3 -> listOf(22,23,56,21,8,36,1,55,26,57,15,58,7,43,51,38,29,50,5,44,25,37,59,9,48,30,20,24,40,42)
            4 -> listOf(29,51,55,56,54,1,48,47,8,21,26,31,24,18,32,3,33,41,6,12,22,37,13,2,15,27,4,45,11,14)
            5 -> listOf(59,3,22,55,13,16,24,34,48,43,1,21,4,5,33,6,51,57,49,58,20,2,26,35,38,18,42,15,9,40)
            6 -> listOf(56,55,23,59,8,22,4,36,35,15,28,27,31,1,2,45,26,3,7,47,34,13,18,58,60,14,44,6,57,30,33,9)
            7 -> listOf(1,51,4,2,7,3,47,28,49,22,15,6,20,55,9,34,11,53,35,21,26,57,33,50,60,30,48,54,37,29,40)
            8 -> listOf(23,27,2,51,4,11,56,1,58,15,22,24,40,49,55,26,33,7,57,53,46,47,34,3,20,18,17,38,54,8)
            9 -> listOf(59,23,35,45,24,49,21,8,46,12,30,51,1,57,2,26,4,3,58,47,33,31,27,50,52,25,19,7,42,20,18)
            10 -> listOf(51,59,47,36,1,21,52,7,12,55,35,42,54,2,58,24,27,48,25,45,40,6,8,33,34,15,3,20,14,22,43,13,4)
            11 -> listOf(57,51,4,34,59,50,47,3,15,48,9,18,26,1,14,7,21,16,2,58,17,37,55,22,8,43,54,52,46,31)
            12 -> listOf(56,57,58,55,53,35,54,47,2,11,44,31,43,13,14,1,6,9,26,18,34,3,4,51,7,17,22,5,24,50)
            else -> emptyList()
        }
    }
}