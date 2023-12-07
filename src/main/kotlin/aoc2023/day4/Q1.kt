package aoc2023.day4

import java.io.File

fun main() {
    val lines = File("assets/2023/day4/input.txt").readLines()
    val cards = cardsFromLines(lines)
    var totalValue = 0
    for (card in cards) {
        totalValue += card.value
    }
    println(totalValue)
}