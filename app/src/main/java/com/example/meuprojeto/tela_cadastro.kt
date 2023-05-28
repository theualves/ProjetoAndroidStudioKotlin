package com.example.meuprojeto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.meuprojeto.databinding.ActivityTelaCadastroBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import android.app.DatePickerDialog
import android.widget.DatePicker
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

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

        var camposPreenchidos = false

        btCadastrar.setOnClickListener{

            val empNome =  edNome.text.toString()
            val empEmail = edEmail.text.toString()
            val empSenha = edSenha.text.toString()
            val empDataNasc = edDataNasc.text.toString()

            camposPreenchidos = !empNome.isEmpty() && !empEmail.isEmpty() && !empSenha.isEmpty() && !empDataNasc.isEmpty()

            if(empNome.isEmpty()){
                edNome.error = getString(R.string.nameEmpty)
            }
            if(empEmail.isEmpty()){
                edEmail.error = getString(R.string.emailEmpty)
            }
            if(empSenha.isEmpty()){
                edSenha.error = getString(R.string.passwordEmpty)
            }
            if(empDataNasc.isEmpty()){
                edDataNasc.error = getString(R.string.dateBirthEmpty)
            }

            val empId = dbRef.push().key!!

            val usuario = EmpresaModelo(empId, empNome, empEmail, empSenha, empDataNasc)

            if (camposPreenchidos) {

                dbRef.child(empId).setValue(usuario)
                    .addOnCompleteListener {
                        Toast.makeText(this, getString(R.string.cadastroRealizado), Toast.LENGTH_SHORT).show()
                        val navegarListaUsuario = Intent(this, ListaUsuarioActivity::class.java)
                        startActivity(navegarListaUsuario)
                    }

            }
            else{
                Toast.makeText(this, getString(R.string.preenchaCampos), Toast.LENGTH_SHORT).show()
            }
        }


        }
    }
