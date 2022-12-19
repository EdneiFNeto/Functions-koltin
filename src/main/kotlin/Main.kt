import kotlin.math.roundToInt

const val TAMANHO_PAPEL = 32.0

fun main(args: Array<String>) {

//    println("EMISSÃO.:12/08/2022 17:18 Ednei".alignLeftRight(".:"))
//    println("JOAO MERCANTE.:".alignLeftRight(".:"))
//    println("CPF.:999.999.999-99".alignLeftRight(".:"))
//    println("POS.:9999999999999".alignLeftRight(".:"))
//
//    println("Master Card".alignCenter())
//    println("Qtd.:1000".alignLeftRight(".:"))
//    println("Credito a vista.:R$999.999,99".alignLeftRight(".:"))

//
//    println("MasterCard".alignCenter())
//    println("Crédito a vista.:10000.:R$ 999.999,999".alignLeftCenterRight(".:"))
//    println("Aprovado.:1.:R$12,00".alignLeftCenterRight(".:"))
//
//    println("Débito.:Qtde.:Valor".alignLeftCenterRight(".:"))
    println("Aprovado.:100000.:R$12,00".alignLeftCenterRight(".:"))
//    println("Cancelado.:1.:R$-16,00".alignLeftCenterRight(".:"))
//
//    println("Visa".alignCenter())
//    println("Crédito.:1.:R$-2,00".alignLeftCenterRight(".:"))
//
//    println("Pix".alignCenter())
//    println("Aprovado.:1.:R$-2,00".alignLeftCenterRight(".:"))
//
//    println("RESUMO DAS OPERACOES".alignCenter())
//    println("QTDE".alignCenter())
//    println("APROVADO.:3.:R$999.999,99".alignLeftCenterRight(".:"))
//    println("CANCELADO.:5.:R$9.999,99".alignLeftCenterRight(".:"))
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