import java.text.DateFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt
import kotlin.random.Random


const val TAMANHO_PAPEL = 32.0

fun main(args: Array<String>) {

    val str = "STATUS:PREMIO NAO CONFIRMADO123456789".alignLeftAndRight(":", false)
    println("currentDate = $str")
    println("currentDate = ${str.length}")

}

fun drawLine(): String {
    val str = StringBuilder()
    for (i in 1..TAMANHO_PAPEL.toInt()) str.append("-")
    return str.toString()
}

fun String.alignLeftCenterRight(character: String, drawLine: String = "-"): String {
    if (this.contains(character)) {
        val strLines = this.split(character)

        if (strLines.isNotEmpty() && strLines.size == 3) {
            val leftText = strLines[0]
            val centerText = strLines[1]
            val rightText = strLines[strLines.size - 1]

            val spaceLeft = StringBuilder()
            val spaceRight = StringBuilder()

            val diffCenterTextWithPaper = TAMANHO_PAPEL - centerText.length

            val roundToInt = (diffCenterTextWithPaper / 2).roundToInt()

            for (i in 0 until roundToInt) {
                spaceLeft.append(drawLine)
                spaceRight.append(drawLine)
            }

            val sizeTexts = "$spaceLeft$centerText$spaceRight".length

            return if (sizeTexts > TAMANHO_PAPEL) {
                val diff = sizeTexts - TAMANHO_PAPEL
                val textLeft = spaceLeft.toString().addTextLeft(leftText)
                val finalText = spaceRight.toString().addTextRight(rightText)
                "$textLeft$centerText${finalText.removeFirstCharacter(diff.roundToInt())}"
            } else {
                val textLeft = spaceLeft.toString().addTextLeft(leftText)
                val finalText = spaceRight.toString().addTextRight(rightText)
                "$textLeft$centerText${finalText}"
            }
        }
    }

    return this
}

private fun String.addTextLeft(text: String): String {
    return if (text.length < this.length)
        text + this.substring(text.length)
    else text.substring(0, this.length - 1) + " "
}

private fun String.addTextRight(text: String): String {
    return if (text.length < this.length) this.removeLastCharacter(text.length) + text
    else " " + text.substring(0, this.length - 1)
}

fun String.removeLastCharacter(index: Int) = this.substring(0, this.length - index)

fun String.removeFirstCharacter(index: Int) = this.substring(index)

fun String.formatAmount(): String {
    val numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
    val number = this.toDouble()
    return numberFormat.format(number.div(100))
}

fun Long.formatAmount(): String {
    val numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
    return numberFormat.format(this.div(100))
}

fun Double.formatAmount(): String {
    return NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
        .format(this)
        .replace("\\s+".toRegex(), "")
}

private fun printBets() {
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

fun String.alignLeftAndRight(character: String, isShowCharacter: Boolean = false): String {

    if (this.contains(character)) {
        val spaceRight = StringBuilder()
        val split = this.split(character)
        if (split.isNotEmpty()) {
            val title1 = split[0]
            val finalSplit = split[split.size - 1]

            val space = TAMANHO_PAPEL - title1.length

            for (i in 0 until space.toInt()) {
                spaceRight.append("-")
            }


            val diff = spaceRight.length - finalSplit.length
            println("diff = $diff")
            val title2 = when (diff > 0) {
                true -> spaceRight.substring(0, diff) + finalSplit
                else -> finalSplit.substring(0, spaceRight.length)
            }

            val newTitle2 = if(isShowCharacter) title2.substring(1)
            else title2

            val result = "$title1${if (isShowCharacter) character else ""}$newTitle2"

            val calc = result.length - TAMANHO_PAPEL
            println("calc = $calc")
            return result
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

fun currentTime(): String {
    val calendar = Calendar.getInstance()
    calendar.get(Calendar.YEAR)
    calendar.get(Calendar.MONTH)
    calendar.get(Calendar.DAY_OF_MONTH)
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    calendar.get(Calendar.MINUTE)
    calendar.get(Calendar.SECOND)

    return (hour - 1).toString()
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


fun String.alignLeftAndRight(character: String, drawLine: String, isShowCharacter: Boolean = false): String {

    if (this.contains(character)) {
        val cutStr = this.split(character)
        val text1 = if (!isShowCharacter) cutStr[0] else "${cutStr[0]}${character}"
        val text2 = cutStr[cutStr.size - 1]

        val diff = TAMANHO_PAPEL - (text1.length + text2.length)
        val lineLeft = StringBuilder()

        for (i in 0 until diff.toInt()) {
            lineLeft.append(drawLine)
        }

        return "$text1${lineLeft}$text2"
    }

    return this
}


