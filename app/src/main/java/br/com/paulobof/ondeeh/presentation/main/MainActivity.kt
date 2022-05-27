package br.com.paulobof.ondeeh.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.com.paulobof.ondeeh.R
import br.com.paulobof.ondeeh.databinding.ActivityMainBinding
import br.com.paulobof.ondeeh.model.Endereco

class MainActivity : AppCompatActivity(), MainContract.MainView
{
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainPresenter: MainContract.MainPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root

        setContentView(view)

        mainPresenter = MainPresenter(this)
        setUpListener()
    }
    private fun setUpListener() {
        binding.btPesquisar.setOnClickListener {
            mainPresenter.pesquisar(binding.etCep.text.toString())
        }
    }
    override fun mostrarEndereco(endereco: Endereco?) {
        binding.tvLogradouro.text = endereco?.logradouro
        binding.tvBairro.text = endereco?.bairro
        binding.tvLocalidade.text = endereco?.localidade
        binding.tvUF.text = endereco?.uf
    }
    override fun mostrarErro(mensagem: String) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()
    }
    override fun mostrarCarregando (){
        binding.ContainerLoading.visibility = View.VISIBLE
    }
    override fun esconderCarregando (){
        binding.ContainerLoading.visibility = View.GONE
    }
}
