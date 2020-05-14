package dev.netomarin.marcadordetruco.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class DuplaDAO(nome: String, pontuacao: Int = 0) : BaseObservable() {

    @get:Bindable
    var nome: String = nome
    set(value)  {
        field = value
        notifyChange()
    }

    @get:Bindable
    var pontuacao: Int = pontuacao
    set(value) {
        field = value
        notifyChange()
    }
}