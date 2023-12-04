package aoc2023.day3

import java.io.File

fun main() {
	val lines = File("assets/2023/day3/input.txt").readLines()
	val engineSchematic = EngineSchematic.fromLines(lines)
	var gearRatioSums = 0
	engineSchematic.`⭐s`.map {
		val numbersAdjacentTo = mutableListOf<Number>()
		for (number in engineSchematic.numbers) {
			if (((number.first.first.first - 1) .. (number.first.first.last + 1)).contains(it.first) && ((number.first.second - 1) .. (number.first.second + 1)).contains(it.second)) {
				numbersAdjacentTo.add(number.second)
			}
		}
		if (numbersAdjacentTo.size == 2) {
			gearRatioSums += numbersAdjacentTo.first().value * numbersAdjacentTo.last().value
		}
	}
	print(gearRatioSums)
}