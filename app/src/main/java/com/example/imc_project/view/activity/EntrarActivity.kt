package com.example.imc_project.view.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.imc_project.R
import kotlinx.android.synthetic.main.activity_entrar.*

class EntrarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrar)

        btEntrar.setOnClickListener {
            val nome = tilNome.editText?.text.toString().trim()
            if (nome.isEmpty()){
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show()
            }else{
                val sharedPrefs = getSharedPreferences("pessoa_$nome", Context.MODE_PRIVATE)
                val editPrefs = sharedPrefs.edit()
                editPrefs.putString("NOME", nome)
                editPrefs.apply()

                val mIntent = Intent(this, CalculoIMCActivity::class.java)
                mIntent.putExtra("INTENT_NOME", nome)
                startActivity(mIntent)

                finishAffinity()
            }

        }
    }



    }