package carla.etchetto.tp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import carla.etchetto.tp2.Modelos.Animal
import carla.etchetto.tp2.Modelos.AnimalDiag
import java.lang.Exception
import kotlin.math.log

class ListadoPacientes : AppCompatActivity() {

    var pacientes: ArrayList<Animal> = ArrayList<Animal>()
    var pacientesDiag: ArrayList<AnimalDiag> = ArrayList<AnimalDiag>()
    lateinit var buscar: Button
    lateinit var nombIngresado: TextView
    lateinit var tipo: TextView
    lateinit var diag: TextView
    lateinit var reposo: TextView
    lateinit var tratamiento: TextView
    lateinit var causa: TextView
    lateinit var medicamentos: TextView
    lateinit var veterinario: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_pacientes)

        iniciarElementos()
        var existe: Boolean= false

        buscar.setOnClickListener(View.OnClickListener {
            var IDS: Int = obtenerID(pacientes)
            existe=existe(IDS)
        if(existe){
            val animVer: Animal = pacientes.get(IDS)
            val aniVerDiag: AnimalDiag = pacientesDiag.get(IDS)
            tipo.setText(animVer.tipo)
            diag.setText(aniVerDiag.diagnostico)
            reposo.setText(aniVerDiag.reposo)
            tratamiento.setText(aniVerDiag.tratamiento)
            causa.setText(aniVerDiag.causas)
            medicamentos.setText(aniVerDiag.medicamentos)
            veterinario.setText(animVer.veterinario)
        }
            else{
                LimpiarRdo()
                Toast.makeText(this, "PACIENTE NO ENCONTRADO", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun iniciarElementos() {

        buscar = findViewById(R.id.m_buscar)
        nombIngresado = findViewById(R.id.m_nombre)
        tipo = findViewById(R.id.m_tipoAnimal)
        diag = findViewById(R.id.m_diagnostico)
        reposo = findViewById(R.id.m_reposo)
        tratamiento = findViewById(R.id.m_tratamientos)
        causa = findViewById(R.id.m_causas)
        medicamentos = findViewById(R.id.m_medicamentos)
        veterinario = findViewById(R.id.m_veterinario)
        val intento = intent
        val datos = intent.extras
        val datos2 = intent.extras
        if (datos != null) {
            val ingresos = intento.getSerializableExtra("pacientes") as ArrayList<Animal>
            pacientes = ingresos
        }
        if (datos2 != null) {
            val ingresos2 = intento.getSerializableExtra("pacientesDiag") as ArrayList<AnimalDiag>
            pacientesDiag = ingresos2

        }

    }

    private fun LimpiarRdo() {
        nombIngresado.setText("")
        tipo.setText("")
        diag.setText("")
        reposo.setText("")
        tratamiento.setText("")
        causa.setText("")
        medicamentos.setText("")
        veterinario.setText("")

    }

    private fun obtenerID(paciente: ArrayList<Animal>): Int
    {
        for (animal in paciente)
        {
            if (nombIngresado.text.toString() == animal.nombre)
                return animal.id
        }
        return -1
    }
    private fun existe(id: Int):Boolean{
        if(id < 0)
        {return false}
        return true
    }
}