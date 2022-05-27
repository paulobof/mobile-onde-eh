package br.com.paulobof.ondeeh.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import br.com.paulobof.ondeeh.R
import br.com.paulobof.ondeeh.data.remote.APIService
import br.com.paulobof.ondeeh.model.Endereco
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var etCEP: EditText
    private lateinit var btPesquisar: Button
    private lateinit var tvLogradouro: TextView
    private lateinit var tvBairro: TextView
    private lateinit var tvLocalidade: TextView
    private lateinit var tvUF: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpView()
        setUpListener()
    }

    private fun setUpListener() {
        btPesquisar.setOnClickListener {
            if (etCEP.toString().isEmpty()) {
                showError("CEP não informado")
            } else {
                pesquisar()
            }
        }
    }

    private fun setUpView() {
        etCEP = findViewById(R.id.etCep)
        btPesquisar = findViewById(R.id.btPesquisar)
        tvLogradouro = findViewById(R.id.tvLogradouro)
        tvBairro = findViewById(R.id.tvBairro)
        tvLocalidade = findViewById(R.id.tvLocalidade)
        tvUF = findViewById(R.id.tvUF)

    }

    private fun pesquisar() {
        APIService.instance
            ?.pesquisar(etCEP.text.toString())
            ?.enqueue(object : Callback<Endereco> {
                override fun onResponse(call: Call<Endereco>, response: Response<Endereco>
                ) {
                    if(response.isSuccessful) {
                        val endereco = response.body()
                        endereco?.let {
                            tvLogradouro.text = endereco.logradouro
                            tvBairro.text = endereco.bairro
                            tvLocalidade.text = endereco.localidade
                            tvUF.text = endereco.uf
                        }
                    }
                    else {
                        showError("Endereço não informado")
                    }
                }
                override fun onFailure(call: Call<Endereco>,
                                       t: Throwable) {
                    showError(t.message.toString())
                }
            })
    }

    private fun showError(mensagem: String) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()
    }
}