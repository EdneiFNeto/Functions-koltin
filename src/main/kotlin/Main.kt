import kotlin.math.roundToInt

const val TAMANHO_PAPEL = 32.0

fun main(args: Array<String>) {

    val alignCenter = "Texto".alignCenter()
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

    return "$spaceLeft$this$spaceRight"
}