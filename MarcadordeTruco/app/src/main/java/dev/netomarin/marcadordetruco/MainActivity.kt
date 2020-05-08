package dev.netomarin.marcadordetruco

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import dev.netomarin.marcadordetruco.databinding.ActivityMainBinding
import dev.netomarin.marcadordetruco.model.DuplaDAO

class MainActivity : AppCompatActivity() {

    private lateinit var dupla1: DuplaDAO
    private lateinit var dupla2: DuplaDAO
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Criando duplas diretamente para ganhar tempo
        dupla1 = DuplaDAO("Dupla 1")
        dupla2 = DuplaDAO("Dupla 2")

        // Exibindo nome e placar
        binding.nomeDupla1.text = dupla1.nome
        binding.placarDupla1TextView.text = dupla1.pontuacao.toString()
        binding.nomeDupla2.text = dupla2.nome
        binding.placarDupla2TextView.text = dupla2.pontuacao.toString()

        // Definindo listeners
        binding.tentoDupla1Button.setOnClickListener {
            somaPontos(it as Button, dupla1)
            binding.placarDupla1TextView.text = dupla1.pontuacao.toString()
        }

        binding.trucoDupla1Button.setOnClickListener {
            somaPontos(it as Button, dupla1)
            binding.placarDupla1TextView.text = dupla1.pontuacao.toString()
        }

        binding.seisDupla1Button.setOnClickListener {
            somaPontos(it as Button, dupla1)
            binding.placarDupla1TextView.text = dupla1.pontuacao.toString()
        }

        binding.noveDupla1Button.setOnClickListener {
            somaPontos(it as Button, dupla1)
            binding.placarDupla1TextView.text = dupla1.pontuacao.toString()
        }

        binding.dozeDupla1Button.setOnClickListener {
            somaPontos(it as Button, dupla1)
            binding.placarDupla1TextView.text = dupla1.pontuacao.toString()
        }

        binding.tentoDupla2Button.setOnClickListener {
            somaPontos(it as Button, dupla2)
            binding.placarDupla2TextView.text = dupla2.pontuacao.toString()
        }

        binding.trucoDupla2Button.setOnClickListener {
            somaPontos(it as Button, dupla2)
            binding.placarDupla2TextView.text = dupla2.pontuacao.toString()
        }

        binding.seisDupla2Button.setOnClickListener {
            somaPontos(it as Button, dupla2)
            binding.placarDupla2TextView.text = dupla2.pontuacao.toString()
        }

        binding.noveDupla2Button.setOnClickListener {
            somaPontos(it as Button, dupla2)
            binding.placarDupla2TextView.text = dupla2.pontuacao.toString()
        }

        binding.dozeDupla2Button.setOnClickListener {
            somaPontos(it as Button, dupla2)
            binding.placarDupla2TextView.text = dupla2.pontuacao.toString()
        }

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
        dupla1.pontuacao = 0
        binding.placarDupla1TextView.text = dupla1.pontuacao.toString()
        dupla2.pontuacao = 0
        binding.placarDupla2TextView.text = dupla2.pontuacao.toString()
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
