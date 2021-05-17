package carla.etchetto.tp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import carla.etchetto.tp2.Modelos.Animal
import carla.etchetto.tp2.Modelos.AnimalDiag

class Atencion : AppCompatActivity() {
    var pacientes:ArrayList<Animal> = ArrayList<Animal>()
    var pacientesDiag:ArrayList<AnimalDiag> = ArrayList<AnimalDiag>()
    lateinit var nombre: EditText
    lateinit var raza: EditText
    lateinit var edad: EditText
    lateinit var tipoId: RadioGroup
    lateinit var tipo: RadioButton
    lateinit var aceptar: Button
    lateinit var paciente: Animal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atencion)

        val veterinarios = arrayOf("Pedro", "Juan")
        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, veterinarios)

        iniciarElementos()

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        val spin: Spinner = findViewById(R.id.i_veterinarios)
        spin.adapter = adapter
        var veterinario: String = ""


        spin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                veterinario = parent.getItemAtPosition(position).toString()
            }
        }

        aceptar.setOnClickListener(View.OnClickListener {
            tipoId = findViewById(R.id.i_tipoAnimal)
            tipo = findViewById(tipoId.checkedRadioButtonId)

            var cantpacientes = pacientes.count()
            var pacientesPedro = pacientes.count { it.veterinario == "Pedro" }
            var turnos: Boolean = false
            var dispoVet: Boolean= false

            turnos=chequeoCantTurnos(cantpacientes)
            dispoVet=chequeoVet(tipo,veterinario,pacientesPedro)


            if(turnos&&dispoVet)
            {
                var id:Int = pacientes.size

                paciente = Animal (nombre.text.toString(),raza.text.toString(),edad.text.toString(),tipo.text.toString(),veterinario,id)
                pacientes.add(paciente)
                val intent = Intent(this,Diagnostico::class.java)
                intent.putExtra("pacientes",pacientes)
                intent.putExtra("pacientesDiag",pacientesDiag)
                startActivity(intent)
            }
        })
    }


    private fun iniciarElementos(){
        nombre=findViewById(R.id.i_nombre)
        raza=findViewById(R.id.i_raza)
        edad=findViewById(R.id.i_edad)
        aceptar=findViewById(R.id.i_aceptar)

        val intento = intent
        val datos = intent.extras
        val datos2=intent.extras
        if (datos != null) {
            val ingresos = intento.getSerializableExtra("pacientes") as ArrayList<Animal>
            pacientes = ingresos
        }
        if (datos2 != null) {
            val ingresos2 = intent.getSerializableExtra("pacientesDiag") as ArrayList<AnimalDiag>
            pacientesDiag = ingresos2

        }
    }

    private fun chequeoCantTurnos (pacientes: Int): Boolean
    {
        if (pacientes > 4)
        {
            Toast.makeText(this, "No hay mas turnos", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    private fun chequeoVet(tipoMascota: RadioButton, veterinario: String, perrosPedro: Int): Boolean
    {
        var tipoAnimal: String = tipoMascota.text.toString()
        if(tipoAnimal.equals("Perro") && veterinario.equals("Pedro"))
        {
            if(perrosPedro>2)
            {
                Toast.makeText(this, "Veterinario No Disponible", Toast.LENGTH_LONG).show()
                return false
            }
        }
        else if (tipoAnimal.equals("Gato") && veterinario.equals("Pedro")|| tipoAnimal.equals("Conejo") && veterinario.equals("Pedro"))
        {
            Toast.makeText(this, "Veterinario No Disponible", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

}