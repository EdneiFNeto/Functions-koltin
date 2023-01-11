import model.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt
import kotlin.random.Random


const val TAMANHO_PAPEL = 32.0

fun main(args: Array<String>) {

    val formatDate = formatDate()
    println("formatDate = $formatDate")
    println("formatDate = ${getAno()}")

//    val milhar = Milhar(modality = Modality())
//    println("Milhar[Bets] = ${milhar.addBets(StringBuilder("123"))}")
//    println("Milhar[randBets]: ${milhar.randBets()}")
//
//    println("")
//    val centena = Centena(modality = Modality(sizeMin = 3))
//    println("Centena[Bets] = ${centena.addBets(StringBuilder("123"))}")
//    println("Centena[randBets]: ${centena.randBets()}")
//
//    println("")
//    val unidade = Unidade(modality = Modality(sizeMin = 1))
//    println("unidade[Bets] = ${unidade.addBets(StringBuilder("1"))}")
//    println("unidade[randBets]: ${unidade.randBets()}")
//
//    println("")
//    val dezena = Dezena(modality = Modality(sizeMin = 2))
//    println("dezena[Bets] = ${dezena.addBets(StringBuilder("22"))}")
//    println("dezena[randBets]: ${dezena.randBets()}")
//
//    println("")
//    val grupo = Grupo(modality = Modality(sizeMin = 2))
//    println("grupo[Bets] = ${grupo.addBets(StringBuilder("12"))}")
//    println("grupo[randBets]: ${grupo.randBets()}")
//
//    println("")
//    val duqueDeDezena = DuqueDeDezena(modality = Modality())
//    println("duqueDeDezena[Bets] = ${duqueDeDezena.addBets(StringBuilder("1245"))}")
//    println("duqueDeDezena[randBets]: ${duqueDeDezena.randBets()}")
}

fun String.alignLeftRight(character: String): String {

    if (this.contains(character)) {
        val spaceLeft = StringBuilder()
        val split = this.split(character)
        if (split.isNotEmpty()) {
            val title1 = split[0]
            val finalSplit = split[split.size - 1]

            val space = TAMANHO_PAPEL - title1.length

            for (i in 0 until space.toInt()) spaceLeft.append("-")

            val diff = spaceLeft.length - finalSplit.length
            val title2 = when {
                diff > 0 -> spaceLeft.substring(0, diff) + finalSplit
                else -> "\n" + finalSplit
            }

            return "$title1$title2"
        }
    }

    return this
}

fun String.alignCenter(): String {

    val spaceLeft = StringBuilder()
    val spaceRight = StringBuilder()
    val spaceCenter = TAMANHO_PAPEL - this.length
    val space = (spaceCenter / 2).roundToInt()

    for (i in 0 until space) {
        spaceLeft.append("-")
        spaceRight.append("-")
    }

    val text = "$spaceLeft$this$spaceRight"

    if (text.length > TAMANHO_PAPEL) {
        return when (val diff = (text.length - TAMANHO_PAPEL).roundToInt()) {
            1 -> text.substring(diff)

            else -> {
                when {
                    diff > 1 -> {
                        val roundToInt = (diff / 2)
                        text.substring(roundToInt)
                        text.substring(0, text.length - roundToInt)
                    }
                    else -> text
                }
            }
        }
    }

    return text
}

fun String.alignLeftCenterRight(character: String, drawLine: String = "-"): String {
    if (this.contains(character)) {
        val strLines = this.split(character)

        if (strLines.isNotEmpty() && strLines.size == 3) {
            val text1 = strLines[0]
            val text2 = strLines[1]
            val text3 = strLines[strLines.size - 1]

            val spaceLeft = StringBuilder()
            val spaceRight = StringBuilder()

            val diffText2WhitPaper = TAMANHO_PAPEL - text2.length

            for (i in 0 until (diffText2WhitPaper / 2).roundToInt()) {
                spaceLeft.append(drawLine)
                spaceRight.append(drawLine)
            }

            val strText1 = if (text1.length > spaceLeft.length) text1.substring(0, spaceLeft.length - 1) + " "
            else text1 + spaceLeft.substring(text1.length)

            spaceLeft.clear()
            spaceLeft.append(strText1)

            val diffSpaceWhitText3 = spaceRight.length - text3.length
            val substring = if (spaceRight.length > text3.length) spaceRight.substring(0, diffSpaceWhitText3) + text3
            else " " + text3.substring(0, spaceRight.length - 1)

            val result = if (text2.length > TAMANHO_PAPEL) "$text2\n$text1 $text3"
            else "$spaceLeft$text2$substring"

            println("spaceLeft = ${spaceLeft.length}")
            println("text2 = $text2")
            println("substring = ${substring.length}")
            println("result = ${result.length}")
            return result
        }
    }

    return this
}

private fun TypeGame.randBets(numMin: Int, numMax: Int): String {

    val size = when (this) {
        TypeGame.MILHAR -> 2
        TypeGame.CENTENA -> 1
        else -> 1
    }

    return List(size) { Random.nextInt(numMin, numMax) }
        .toString().replace("\\D".toRegex(), "")
}

private fun String.addMask(): String {
    return this.replace("\\d".toRegex(), "*")
}

private fun String.restore(digit: String): String {
    println("digit.length = ${digit.length}")
    return this.replace("[*]".toRegex(), digit).substring(0, digit.length)
}

fun formatDate(): String {
    val currentDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
    val date = formatarData("2022-03-12T00:00:00", "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss")
    return ""
}

fun formatarData(data: String?, entrada: String, retorno: String): String {
    val formatUS: DateFormat = SimpleDateFormat(entrada)
    val date: Date = formatUS.parse(data)
    val formatBR: DateFormat = SimpleDateFormat(retorno)
    return formatBR.format(date)
}

fun getAno(): String {
    val calendar = Calendar.getInstance()
    return calendar.get(Calendar.YEAR).toString()
}



