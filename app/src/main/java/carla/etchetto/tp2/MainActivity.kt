package carla.etchetto.tp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import carla.etchetto.tp2.Modelos.Animal
import carla.etchetto.tp2.Modelos.AnimalDiag

class MainActivity : AppCompatActivity() {
    lateinit var ingresoPacientes: Button
    lateinit var verPacientes: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniciarElementos()
        var pacientes :ArrayList<Animal> = ArrayList<Animal>()

        val extras = intent.extras
        if(extras != null)
        {
            pacientes = intent.getSerializableExtra("pacientes") as ArrayList<Animal>
        }

        var pacientesDiag: ArrayList<AnimalDiag> = ArrayList<AnimalDiag>()
        val extras2 = intent.extras
        if(extras2!=null)
        {
            pacientesDiag = intent.getSerializableExtra("pacientesDiag") as ArrayList<AnimalDiag>
        }

        ingresoPacientes.setOnClickListener(View.OnClickListener {
            val intento = Intent(this, Atencion::class.java)
            intento.putExtra("pacientes",pacientes)
            intento.putExtra("pacientesDiag",pacientesDiag)
            startActivity(intento)
        })

        verPacientes.setOnClickListener(View.OnClickListener {
            val intento2 = Intent(this, ListadoPacientes::class.java)
            intento2.putExtra("pacientes",pacientes)
            intento2.putExtra("pacientesDiag", pacientesDiag)
            startActivity(intento2)
        })

    }



    private fun iniciarElementos()
    {
        ingresoPacientes=findViewById(R.id.i_ingresoPaciente)
        verPacientes=findViewById(R.id.i_verPacientes)


    }
}