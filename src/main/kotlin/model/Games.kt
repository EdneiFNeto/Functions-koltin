package model

import TypeGame
import extensions.convertFromNumber
import extensions.removeCharacterArray
import extensions.removeLastCharacter
import kotlin.random.Random

class Milhar(modality: Modality) : ModalityFactory(modality)

class Centena(private val modality: Modality) : ModalityFactory(modality) {
    override fun randBets(): String {
        val size = modality.sizeMin / 2
        val numRand = (modality.numMin..modality.numMax).random().toString()
        val rand = if (numRand.length > 1) {
            numRand.removeLastCharacter()
        } else {
            numRand
        }

        val list = List(size) {
            Random.nextInt(modality.numMin, modality.numMax)
        }.map {
            if (it < 10) "0$it"
            else it.toString()
        }

        return list.toString().removeCharacterArray().convertFromNumber() + rand
    }
}

class Dezena(modality: Modality) : ModalityFactory(modality)

class Unidade(private val modality: Modality) : ModalityFactory(modality) {
    override fun randBets(): String {
        val numRand = (modality.numMin..modality.numMax).random().toString()
        return numRand.removeLastCharacter().convertFromNumber()
    }
}

class Grupo(modality: Modality) : ModalityFactory(modality)

class DuqueDeDezena(modality: Modality) : ModalityFactory(modality)

data class Modality(
    val sizeMin: Int = 4,
    val maxSize: Int = 4,
    val numMin: Int = 0,
    val numMax: Int = 99
)

abstract class ModalityFactory(private val modality: Modality) {
    private val listBets = arrayListOf<String>()

    open fun randBets(): String {
        val size = modality.sizeMin / 2
        val list1 = List(size) {
            Random.nextInt(modality.numMin, modality.numMax)
        }.map {
            if (it < 10)
                "0$it"
            else
                it.toString()
        }

        return list1.toString().removeCharacterArray().convertFromNumber()
    }

    open fun addBets(digit: StringBuilder): String {
        if (digit.toString().length == modality.sizeMin) {
            listBets.add(digit.toString())
            digit.clear()
        }
        return listBets.toString()
    }
}