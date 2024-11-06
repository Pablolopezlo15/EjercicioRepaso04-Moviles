package com.example.ejerciciorepaso04

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.ejerciciorepaso04.databinding.FragmentFigurasGeometricasBinding
import com.google.android.material.snackbar.Snackbar

class FigurasGeometricasFragment : Fragment() {

    private lateinit var binding: FragmentFigurasGeometricasBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inicializarBinding(inflater, container)
        inicializarViewModel()
        inicializarBotones()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val figuraSeleccionada = binding.spnFiguras.selectedItem.toString()
        mostrarCampos(figuraSeleccionada)
    }

    private fun inicializarBinding(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentFigurasGeometricasBinding.inflate(inflater, container, false)
    }

    private fun inicializarViewModel() {
        viewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
    }

    private fun inicializarBotones() {
        binding.btnseleccionarFigura.setOnClickListener {
            val figura = binding.spnFiguras.selectedItem.toString()
            mostrarCampos(figura)
        }

        binding.calcularArea.setOnClickListener {
            val figura = binding.spnFiguras.selectedItem.toString()
            calcularArea(figura)
        }

        binding.calcularVolumen.setOnClickListener {
            val figura = binding.spnFiguras.selectedItem.toString()
            calcularVolumen(figura)
        }
    }



    private fun mostrarCampos(figura: String) {
        esconderEditText()

        when (figura) {
            "Cubo" -> {
                binding.txtLado.isVisible = true
                binding.imageViewFigura.setImageResource(R.drawable.cubo)
            }
            "Prisma" -> {
                binding.txtAreaBase.isVisible = true
                binding.txtPerimetroBase.isVisible = true
                binding.txtAltura.isVisible = true
                binding.imageViewFigura.setImageResource(R.drawable.prisma)
            }
            "Pirámide" -> {
                binding.txtAreaBase.isVisible = true
                binding.txtPerimetroBase.isVisible = true
                binding.txtApotema.isVisible = true
                binding.imageViewFigura.setImageResource(R.drawable.piramide)
            }
            "Cilindro" -> {
                binding.txtRadio.isVisible = true
                binding.txtAltura.isVisible = true
                binding.imageViewFigura.setImageResource(R.drawable.cilindro)
            }
            "Cono" -> {
                binding.txtRadio.isVisible = true
                binding.txtGeneratriz.isVisible = true
                binding.imageViewFigura.setImageResource(R.drawable.cono)
            }
            "Esfera" -> {
                binding.txtRadio.isVisible = true
                binding.imageViewFigura.setImageResource(R.drawable.esfera)
            }
            else -> {
                binding.imageViewFigura.setImageResource(R.drawable.sinimagen)
            }
        }
    }

    private fun esconderEditText() {
        binding.txtLado.isVisible = false
        binding.txtAreaBase.isVisible = false
        binding.txtPerimetroBase.isVisible = false
        binding.txtAltura.isVisible = false
        binding.txtApotema.isVisible = false
        binding.txtRadio.isVisible = false
        binding.txtGeneratriz.isVisible = false
    }

    private fun calcularArea(figura: String) {
        val resultado = when (figura) {
            "Cubo" -> calcularAreaCubo(binding.txtLado.text.toString().toDoubleOrNull())
            "Prisma" -> calcularAreaPrisma(
                binding.txtAreaBase.text.toString().toDoubleOrNull(),
                binding.txtPerimetroBase.text.toString().toDoubleOrNull(),
                binding.txtAltura.text.toString().toDoubleOrNull()
            )
            "Pirámide" -> calcularAreaPiramide(
                binding.txtAreaBase.text.toString().toDoubleOrNull(),
                binding.txtPerimetroBase.text.toString().toDoubleOrNull(),
                binding.txtApotema.text.toString().toDoubleOrNull()
            )
            "Cilindro" -> calcularAreaCilindro(
                binding.txtRadio.text.toString().toDoubleOrNull(),
                binding.txtAltura.text.toString().toDoubleOrNull()
            )
            "Cono" -> calcularAreaCono(
                binding.txtRadio.text.toString().toDoubleOrNull(),
                binding.txtGeneratriz.text.toString().toDoubleOrNull()
            )
            "Esfera" -> calcularAreaEsfera(binding.txtRadio.text.toString().toDoubleOrNull())
            else -> "Figura no válida"
        }
        mostrarResultado(resultado)
    }

    private fun calcularVolumen(figura: String) {
        val resultado = when (figura) {
            "Cubo" -> calcularVolumenCubo(binding.txtLado.text.toString().toDoubleOrNull())
            "Prisma" -> calcularVolumenPrisma(
                binding.txtAreaBase.text.toString().toDoubleOrNull(),
                binding.txtAltura.text.toString().toDoubleOrNull()
            )
            "Pirámide" -> calcularVolumenPiramide(
                binding.txtAreaBase.text.toString().toDoubleOrNull(),
                binding.txtAltura.text.toString().toDoubleOrNull()
            )
            "Cilindro" -> calcularVolumenCilindro(
                binding.txtRadio.text.toString().toDoubleOrNull(),
                binding.txtAltura.text.toString().toDoubleOrNull()
            )
            "Cono" -> calcularVolumenCono(
                binding.txtRadio.text.toString().toDoubleOrNull(),
                binding.txtAltura.text.toString().toDoubleOrNull()
            )
            "Esfera" -> calcularVolumenEsfera(binding.txtRadio.text.toString().toDoubleOrNull())
            else -> "Figura no válida"
        }
        mostrarResultado(resultado)
    }

    private fun mostrarResultado(mensaje: String) {
        Snackbar.make(binding.root, mensaje, Snackbar.LENGTH_LONG).show()
    }

    fun calcularAreaCubo(lado: Double?): String {
        return if (lado != null) {
            val area = 6 * (lado * lado)
            "El área del cubo es: $area"
        } else {
            "Por favor, introduce un valor válido para el lado del cubo"
        }
    }

    fun calcularAreaPrisma(areaBase: Double?, perimetroBase: Double?, altura: Double?): String {
        return if (areaBase != null && perimetroBase != null && altura != null) {
            val area = 2 * areaBase + perimetroBase * altura
            "El área del prisma es: $area"
        } else {
            "Por favor, introduce valores válidos para el prisma"
        }
    }

    fun calcularAreaPiramide(areaBase: Double?, perimetroBase: Double?, apotema: Double?): String {
        return if (areaBase != null && perimetroBase != null && apotema != null) {
            val area = areaBase + (perimetroBase * apotema) / 2
            "El área de la pirámide es: $area"
        } else {
            "Por favor, introduce valores válidos para la pirámide"
        }
    }

    fun calcularAreaCilindro(radio: Double?, altura: Double?): String {
        return if (radio != null && altura != null) {
            val area = 2 * Math.PI * radio * (radio + altura)
            "El área del cilindro es: $area"
        } else {
            "Por favor, introduce valores válidos para el cilindro"
        }
    }

    fun calcularAreaCono(radio: Double?, generatriz: Double?): String {
        return if (radio != null && generatriz != null) {
            val area = Math.PI * radio * (radio + generatriz)
            "El área del cono es: $area"
        } else {
            "Por favor, introduce valores válidos para el cono"
        }
    }

    fun calcularAreaEsfera(radio: Double?): String {
        return if (radio != null) {
            val area = 4 * Math.PI * (radio * radio)
            "El área de la esfera es: $area"
        } else {
            "Por favor, introduce un valor válido para el radio de la esfera"
        }
    }

    fun calcularVolumenCubo(lado: Double?): String {
        return if (lado != null) {
            val volumen = lado * lado * lado
            "El volumen del cubo es: $volumen"
        } else {
            "Por favor, introduce un valor válido para el lado del cubo"
        }
    }

    fun calcularVolumenPrisma(areaBase: Double?, altura: Double?): String {
        return if (areaBase != null && altura != null) {
            val volumen = areaBase * altura
            "El volumen del prisma es: $volumen"
        } else {
            "Por favor, introduce valores válidos para el prisma"
        }
    }

    fun calcularVolumenPiramide(areaBase: Double?, altura: Double?): String {
        return if (areaBase != null && altura != null) {
            val volumen = (areaBase * altura) / 3
            "El volumen de la pirámide es: $volumen"
        } else {
            "Por favor, introduce valores válidos para la pirámide"
        }
    }

    fun calcularVolumenCilindro(radio: Double?, altura: Double?): String {
        return if (radio != null && altura != null) {
            val volumen = Math.PI * radio * radio * altura
            "El volumen del cilindro es: $volumen"
        } else {
            "Por favor, introduce valores válidos para el cilindro"
        }
    }

    fun calcularVolumenCono(radio: Double?, altura: Double?): String {
        return if (radio != null && altura != null) {
            val volumen = (Math.PI * radio * radio * altura) / 3
            "El volumen del cono es: $volumen"
        } else {
            "Por favor, introduce valores válidos para el cono"
        }
    }

    fun calcularVolumenEsfera(radio: Double?): String {
        return if (radio != null) {
            val volumen = (4 / 3.0) * Math.PI * Math.pow(radio, 3.0)
            "El volumen de la esfera es: $volumen"
        } else {
            "Por favor, introduce un valor válido para el radio de la esfera"
        }
    }



}
