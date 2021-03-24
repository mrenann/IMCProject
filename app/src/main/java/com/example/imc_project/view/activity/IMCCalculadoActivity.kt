package com.example.imc_project.view.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.imc_project.R
import kotlinx.android.synthetic.main.activity_imccalculado.*

class IMCCalculadoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imccalculado)

        val nome = intent.getStringExtra("INTENT_NOME")

        val sharedPrefs = getSharedPreferences(
            "pessoa_$nome",
            Context.MODE_PRIVATE
        )

        val peso = sharedPrefs.getFloat("PESO", 0.0f)
        val altura = sharedPrefs.getFloat("ALTURA", 0.0f)

        val imc = (peso/(altura*altura))
        tvIMC.text = "IMC: $imc"

        var situacao = ""
        when {
            imc<18.5 -> situacao = "Magreza"
            imc in 18.5..24.9 -> situacao = "Normal"
            imc in 25.0..29.9 -> situacao = "Sobrepeso"
            imc in 30.0..39.9 -> situacao = "Obesidade"
            imc>40 -> situacao = "Obesidade Grave"
        }

        tvSituacaoIMC.text = "Situação: $situacao"
    }
}