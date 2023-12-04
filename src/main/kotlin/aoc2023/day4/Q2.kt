package aoc2023.day4

import java.io.File
import kotlin.math.log2

fun main() {
    val lines = File("assets/2023/day4/input.txt").readLines()
    val cards = cardsFromLines(lines)
    val cardCopies = mutableMapOf<Int, Int>().apply {
        for (index in cards.indices) {
            put(index, 1)
        }
    }
    for ((index, card) in cards.withIndex()) {
        val numberOfMatches = log2(card.value.toDouble()).toInt() + 1
        for (i in index + 1..index + numberOfMatches) {
            cardCopies[i] = cardCopies[i]!! + cardCopies[index]!!
        }
    }
    print(cardCopies.values.reduce(Int::plus))
}