package com.example.elementosformulariok

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.Toast
import android.widget.ToggleButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val chipGroup_ = findViewById<ChipGroup>(R.id.chipGroup)

        // Usando o método setOnCheckedStateChangeListener para múltiplas seleções
        // necessário checkable no xml
        chipGroup_.setOnCheckedStateChangeListener { group, checkedIds ->
            val selectedChipsText = checkedIds.map { id ->
                val chip = group.findViewById<Chip>(id)
                chip.text.toString()
            }
            //joinToString junta valores de texto e separa por vírgula
            Toast.makeText(this, "Selecionado(s): ${selectedChipsText.joinToString()}", Toast.LENGTH_SHORT).show()
        }

        //... ou, com ação em um button para verificar quem está marcado
        val buttonVerif = findViewById<Button>(R.id.button).setOnClickListener {
            val selectedChips = verChipsSelecionadas(chipGroup_)
            Toast.makeText(this, "Selecionado(s): ${selectedChips.joinToString()}", Toast.LENGTH_SHORT).show()
        }

        // CheckBox
        val checkBox = findViewById<CheckBox>(R.id.checkbox)
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "Checkbox marcado" else "Checkbox desmarcado"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

        // RadioGroup
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val selectedRadio = when (checkedId) {
                R.id.radioOption1 -> "Opção 1 selecionada"
                R.id.radioOption2 -> "Opção 2 selecionada"
                else -> "Nenhuma opção selecionada"
            }
            Toast.makeText(this, selectedRadio, Toast.LENGTH_SHORT).show()
        }

        // Switch
        val switch = findViewById<Switch>(R.id.switch2)
        switch.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "Switch ligado" else "Switch desligado"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

        // ToggleButton
        val toggleButton = findViewById<ToggleButton>(R.id.toggleButton)
        toggleButton.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "ToggleButton ligado" else "ToggleButton desligado"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun verChipsSelecionadas(chipGroup: ChipGroup): List<String> {
        //mutableListOf = List dinâmica, que pode ser alterada depois de criada
        val chipsSelecionadas = mutableListOf<String>()
        for (i in 0 until chipGroup.childCount) {
            // as? é uma forma segura de realizar um cast, ou seja, ele tenta converter o
            // tipo e, caso não seja possível, retorna null em vez de lançar uma exceção
            val chip = chipGroup.getChildAt(i) as? Chip
            //A interrogação aqui é usada para acessar a propriedade isChecked de chip de
            // maneira segura. Se chip for null, a expressão inteira retorna null, e o
            // código dentro do if não será executado, ao invés de lançar um NullPointerException
            if (chip?.isChecked == true) {
                chipsSelecionadas.add(chip.text.toString())
            }
        }
        return chipsSelecionadas
    }
    /*exemplos for in ... until e for in..
        // Usando until (exclusivo de 5)
        for (i in 1 until 5) {
            println(i)
        }

        // Usando .. (inclusive de 5)
        for (i in 1..5) {
            println(i)
        }

    */
}