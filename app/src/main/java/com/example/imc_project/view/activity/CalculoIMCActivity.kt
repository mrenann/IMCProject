package com.example.imc_project.view.activity

import android.content.Context
import android.content.Intent
import android.icu.number.IntegerWidth
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.imc_project.R
import kotlinx.android.synthetic.main.activity_calculoimc.*
import kotlinx.android.synthetic.main.activity_entrar.*

class CalculoIMCActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculoimc)

        val nome = intent.getStringExtra("INTENT_NOME")

        val sharedPrefs = getSharedPreferences(
                "pessoa_$nome",
                Context.MODE_PRIVATE
        )

        tvHello.text="Olá, $nome"
        val peso = sharedPrefs.getFloat("PESO", 0.0f)
        val altura = sharedPrefs.getFloat("ALTURA", 0.0f)

        if (peso!=0.0f && altura!=0.0f) {
            tietAltura.setText(altura.toString())
            tietPeso.setText(peso.toString())
        }

        btCalcular.setOnClickListener {
            val peso = tilPeso.editText?.text.toString()
            val altura = tilAlura.editText?.text.toString()
            if (peso.isEmpty() || altura.isEmpty()){
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show()
            }else{
                val pesoFloat = peso.toFloat()
                val alutraFloat = altura.toFloat()
                val sharedPrefs = getSharedPreferences("pessoa_$nome", MODE_PRIVATE)
                val editPrefs = sharedPrefs.edit()
                editPrefs.putFloat("PESO", pesoFloat)
                editPrefs.putFloat("ALTURA", alutraFloat)
                editPrefs.apply()

                val mIntent = Intent(this, IMCCalculadoActivity::class.java)
                mIntent.putExtra("INTENT_NOME", nome)
                startActivity(mIntent)
            }

        }
    }
}