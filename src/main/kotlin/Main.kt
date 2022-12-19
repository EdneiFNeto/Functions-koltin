import kotlin.math.roundToInt

const val TAMANHO_PAPEL = 32.0

fun main(args: Array<String>) {

    val number = "123456"

    val first = number.substring(1)
    val last = number.substring(0, number.length - 1)

    println("first = $first")
    println("last = $last")

    val alignCenter = "Jose Augusto de Souza".alignCenter()
    println(alignCenter)

}


fun String.alignCenter(): String {

    val spaceLeft = StringBuilder()
    val spaceRight = StringBuilder()

    val spaceCenter = TAMANHO_PAPEL - this.length
    println("TAMANHO_PAPEL = $TAMANHO_PAPEL - ${this.length} = $spaceCenter")

    val space = (spaceCenter / 2).roundToInt()
    println("space = $space")


    for (i in 0 until space) {
        spaceLeft.append("-")
        spaceRight.append("-")
    }

    println("spaceLeft = ${spaceLeft.length}")
    println("spaceRight = ${spaceRight.length}")

    val text = "$spaceLeft$this$spaceRight"

    println("final[before]: ${text.length}")

    if (text.length > TAMANHO_PAPEL) {
        println("Final > TAMANHO_PAPEL")
        println("Final ${text.length}")
        println("TAMANHO_PAPEL $TAMANHO_PAPEL")

        println("Diff ${( text.length -TAMANHO_PAPEL).roundToInt()}")

       return  when (val diff = (text.length - TAMANHO_PAPEL).roundToInt()) {
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