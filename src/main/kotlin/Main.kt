import kotlin.math.roundToInt

const val TAMANHO_PAPEL = 42.0

fun main(args: Array<String>) {

    println("EMISSÃO.:12/08/2022 17:18".alignLeftRight(".:"))
    println("JOAO MERCANTE.:".alignLeftRight(".:"))
    println("CPF.:999.999.999-99".alignLeftRight(".:"))
    println("POS.:9999999999999".alignLeftRight(".:"))

    println("RELATORIO CONSOLIDADO".alignCenter())
    println("PERIODO DE.:12/01/2022".alignLeftRight(".:"))
    println("        ATE.:12/01/2022".alignLeftRight(".:"))

    println("MasterCard".alignCenter())
    println("Crédito.:Qtde.:Valor".alignLeftCenterRight(".:"))
    println("Aprovado.:1.:R$12,00".alignLeftCenterRight(".:"))

    println("Débito.:Qtde.:Valor".alignLeftCenterRight(".:"))
    println("Aprovado.:1.:R$12,00".alignLeftCenterRight(".:"))
    println("Cancelado.:1.:R$-16,00".alignLeftCenterRight(".:"))

    println("Visa".alignCenter())
    println("Crédito.:1.:R$-2,00".alignLeftCenterRight(".:"))

    println("Pix".alignCenter())
    println("Aprovado.:1.:R$-2,00".alignLeftCenterRight(".:"))

    println("RESUMO DAS OPERACOES".alignCenter())
    println("QTDE".alignCenter())
    println("APROVADO.:3.:R$999.999,99".alignLeftCenterRight(".:"))
    println("CANCELADO.:5.:R$9.999,99".alignLeftCenterRight(".:"))
}

fun String.alignLeftRight(character: String): String {

    val spaceLeft = StringBuilder()
    val space = TAMANHO_PAPEL - this.length

    val split = this.split(character)

    for (i in 0 until space.toInt()) {
        spaceLeft.append("-")
    }

    return "${split[0]}--$spaceLeft${split[split.size - 1]}"
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

        if (strLines.isNotEmpty()) {
            val text1 = strLines[0]
            val text2 = strLines[1]
            val text3 = strLines[strLines.size - 1]

            val lineLeft = StringBuilder()
            val lineRight = StringBuilder()

            val lenght = text2.length
            val diff = TAMANHO_PAPEL - lenght

            for (i in 0 until (diff / 2).toInt()) {
                lineLeft.append(drawLine)
            }

            for (i in text1.indices) lineLeft[i] = text1[i]

            val sobra = TAMANHO_PAPEL - (lineLeft.length + text2.length)

            for (i in 0 until sobra.toInt()) {
                lineRight.append(drawLine)
            }

            val substring = lineRight.substring(0, lineRight.length - text3.length) + text3
            return "$lineLeft$text2$substring"
        }
    }

    return this
}