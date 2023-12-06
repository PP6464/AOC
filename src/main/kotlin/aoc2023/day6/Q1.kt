package aoc2023.day6

import java.io.File

fun main() {
	val lines = File("assets/2023/day6/input.txt").readLines()
	val races = Race.fromLines(lines)
	println(races.map(Race::errorMargin).reduce { a, b ->
		a * b
	})
}