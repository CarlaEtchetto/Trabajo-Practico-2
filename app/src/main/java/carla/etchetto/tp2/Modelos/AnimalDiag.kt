package carla.etchetto.tp2.Modelos

import java.io.Serializable

data class AnimalDiag (val diagnostico: String, val causas: String, val medicamentos: String,
                      val reposo: String,val tratamiento: String, val id: Int): Serializable