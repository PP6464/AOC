package aoc2023.day7

import java.io.File

fun main() {
	val lines = File("assets/2023/day7/input.txt").readLines()
	var hands = Hand.fromLines(lines, part = 2)
	hands = hands.sortedWith(Hand::compareWith)
	println(hands.withIndex().map { (index, hand) -> hand.value(rank = index + 1) }.sumOf { it })
}
