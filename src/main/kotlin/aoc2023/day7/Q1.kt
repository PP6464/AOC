package aoc2023.day7

import java.io.File

// 248199909
fun main() {
	val lines = File("assets/2023/day7/input.txt").readLines()
	var hands = Hand.fromLines(lines)
	hands = hands.sorted()
	println(hands.withIndex().map { (index, hand) -> hand.value(rank = index + 1) }.sumOf { it })
}
