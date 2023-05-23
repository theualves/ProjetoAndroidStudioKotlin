package com.example.meuprojeto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.meuprojeto.databinding.ActivityTelaCadastroBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class tela_cadastro : AppCompatActivity() {
    private lateinit var binding : ActivityTelaCadastroBinding
    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var edNome = binding.edNome
        var edEmail = binding.edEmail
        var edSenha = binding.edSenha
        var edDataNasc = binding.edDataNasc
        var btCadastrar =binding.buttonFinalize

        dbRef = FirebaseDatabase.getInstance().getReference("Usuário")

        btCadastrar.setOnClickListener{

            val empNome =  edNome.text.toString()
            val empEmail = edEmail.text.toString()
            val empSenha = edSenha.text.toString()
            val empDataNasc = edDataNasc.text.toString()

            if(empNome.isEmpty()){
                edNome.error = "Por favor insira seu nome"
            }
            if(empEmail.isEmpty()){
                edEmail.error = "Por favor insira um e-mail válido"
            }
            if(empSenha.isEmpty()){
                edSenha.error = "Por favor crie uma senha"
            }
            if(empDataNasc.isEmpty()){
                edDataNasc.error = "Por favor insira sua data de nascimento"
            }

            val empId = dbRef.push().key!!

            val usuario = EmpresaModelo(empId, empNome, empEmail, empSenha, empDataNasc)

            dbRef.child(empId).setValue(usuario)
                .addOnCompleteListener{
                    Toast.makeText(this, "Cadastro realizado", Toast.LENGTH_SHORT).show()
                    val navegarListaUsuario = Intent(this, ListaUsuarioActivity::class.java)
                    startActivity(navegarListaUsuario)

                    edNome.text.clear()
                    edEmail.text.clear()
                    edSenha.text.clear()
                    edDataNasc.text.clear()

                }.addOnFailureListener{err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_SHORT).show()
                }
        }


        }
    }
