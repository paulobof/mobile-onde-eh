package br.com.paulobof.ondeeh.presentation.main

import br.com.paulobof.ondeeh.model.Endereco

interface MainContract {
    interface MainView {
        fun mostrarEndereco(endereco: Endereco?)
        fun mostrarErro(mensagem: String)
        fun mostrarCarregando()
        fun esconderCarregando()
    }
    interface MainPresenter {
        fun pesquisar(cep: String)
    }
}
