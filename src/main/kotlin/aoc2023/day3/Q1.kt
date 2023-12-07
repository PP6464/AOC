package aoc2023.day3

import java.io.File

fun main() {
	val lines = File("assets/2023/day3/input.txt").readLines()
	val engineSchematic = EngineSchematic.fromLines(lines)
	println(engineSchematic.numbers.filter { it.second.adjacentContainsSymbol() }.map { it.second.value }.reduce(Int::plus))
}