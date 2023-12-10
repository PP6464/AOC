package aoc2023.day9

import java.io.File

fun main() {
	val lines = File("assets/2023/day9/input.txt").readLines()
	val sequences = NumberSequence.fromLines(lines)
	println(sequences.sumOf { it.findNext() })
}
