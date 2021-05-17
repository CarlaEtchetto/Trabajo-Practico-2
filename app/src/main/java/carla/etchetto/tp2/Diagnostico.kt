package carla.etchetto.tp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import carla.etchetto.tp2.Modelos.Animal
import carla.etchetto.tp2.Modelos.AnimalDiag

class Diagnostico : AppCompatActivity() {
    var pacientes:ArrayList<Animal> = ArrayList<Animal>()
    var pacientesDiag :ArrayList<AnimalDiag> = ArrayList<AnimalDiag>()
    lateinit var diagnostico: EditText
    lateinit var causas: EditText
    lateinit var medicamentos: EditText
    lateinit var tratamiento: EditText
    lateinit var reposo: EditText
    lateinit var enviar: Button
    lateinit var pacientediag: AnimalDiag


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diagnostico)

        iniciarElementos()

        enviar.setOnClickListener(View.OnClickListener {

            var id:Int = pacientesDiag.size

            pacientediag = AnimalDiag (diagnostico.text.toString(),causas.text.toString(),medicamentos.text.toString(),
                reposo.text.toString(),tratamiento.text.toString(),id)

            pacientesDiag.add(pacientediag)

            val intento = Intent(this, MainActivity::class.java)
            intento.putExtra("pacientesDiag",pacientesDiag)
            intento.putExtra("pacientes", pacientes)


            startActivity(intento)
        })
    }

    private fun iniciarElementos(){
        diagnostico=findViewById(R.id.a_diagnostico)
        causas=findViewById(R.id.a_causas)
        medicamentos=findViewById(R.id.a_medicamentos)
        tratamiento=findViewById(R.id.a_tratamiento)
        reposo=findViewById(R.id.a_reposo)
        enviar=findViewById(R.id.a_agregar)

        val intent = intent
        val datos = intent.extras
        val datos2= intent.extras
        if (datos != null) {
            val ingresos = intent.getSerializableExtra("pacientes") as ArrayList<Animal>
            pacientes = ingresos
        }
        if (datos2 != null) {
            val ingresos2 = intent.getSerializableExtra("pacientesDiag") as ArrayList<AnimalDiag>
            pacientesDiag = ingresos2
        }



    }

}