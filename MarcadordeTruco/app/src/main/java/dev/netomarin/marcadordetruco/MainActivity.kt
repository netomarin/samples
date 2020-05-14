package dev.netomarin.marcadordetruco

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dev.netomarin.marcadordetruco.databinding.ActivityMainBinding
import dev.netomarin.marcadordetruco.model.DuplaDAO

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Criando duplas diretamente para ganhar tempo
        this.binding.dupla1 = DuplaDAO("Dupla 1")
        this.binding.dupla2 = DuplaDAO("Dupla 2")
        this.binding.activity = this

        binding.restartGameActionButton.setOnClickListener { confirmaReinicioJogo() }
    }

    fun confirmaReinicioJogo() {
        val alertDialog: AlertDialog? = this.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton("Sim") { _, _ -> reiniciaJogo() }
                setNegativeButton("Não"){ dialog, _ -> dialog.dismiss() }
            }
            builder.setTitle("Confirmação")
                .setMessage("Deseja mesmo reiniciar a partida?")
            builder.create()
        }
        alertDialog?.show()
    }

    private fun reiniciaJogo() {
        binding.dupla1!!.pontuacao = 0
        binding.dupla2!!.pontuacao = 0
    }

    fun somaPontos(view: View, dupla: DuplaDAO) {
        val button: Button = view as Button
        var pontosParaSomar = 0
        when(button.text) {
            getString(R.string.um_tento) -> pontosParaSomar = 1
            getString(R.string.truco) -> pontosParaSomar = 3
            getString(R.string.seis) -> pontosParaSomar = 6
            getString(R.string.nove) -> pontosParaSomar = 9
            getString(R.string.doze) -> pontosParaSomar = 12
        }
        dupla.pontuacao += pontosParaSomar
        if (dupla.pontuacao >= 12) {
            encerrarPartida(dupla)
        }
    }

    fun encerrarPartida(dupla: DuplaDAO) {
        val alertDialog: AlertDialog? = this.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton(getString(R.string.label_agora)) { _, _ -> reiniciaJogo()}
                setNegativeButton(getString(R.string.label_correram)) { _, _ -> correram() }
            }
            builder.setTitle("${dupla.nome} VENCEU!")
                .setMessage(getString(R.string.label_pergunta_outra_partida))
            builder.create()
        }
        alertDialog?.show()
    }

    fun correram() {
        Toast.makeText(this,
            getString(R.string.toast_info_nova_partida),
            Toast.LENGTH_SHORT).show()
    }
}
