package com.example.meuprojeto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.meuprojeto.databinding.ActivityMainBinding
import com.example.meuprojeto.databinding.ActivityTela2Binding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val edNome = binding.edUsuario
        val btLogin = binding.btLogin

        btLogin.setOnClickListener {
            if(edNome.text.toString()=="Matheus") {
                val inten = Intent(this, tela2::class.java)
                val texto = edNome.text.toString()
                inten.putExtra("nome", texto)
                startActivity(inten)
            }
            else{
                Toast.makeText(this, R.string.msgError, Toast.LENGTH_SHORT).show()
            }
        }

        binding.btCreateAccount.setOnClickListener {
            val navegarTelaCadatro = Intent(this, tela_cadastro::class.java)
            startActivity(navegarTelaCadatro)
        }

        //setContentView(R.layout.activity_main)
    }
}