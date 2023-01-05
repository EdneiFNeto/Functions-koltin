package extensions

fun String.removeCharacterArray(): String {
    return this.replace("[\\[\\]]".toRegex(), "")
}

fun String.convertFromNumber(): String {
    return this.replace("\\D".toRegex(), "")
}

fun String.removeLastCharacter(): String {
    return this.substring(1)
}