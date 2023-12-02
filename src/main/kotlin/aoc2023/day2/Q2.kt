package aoc2023.day2

import java.io.File

fun main() {
	val lines = File("assets/2023/day2/input.txt").readLines()
	var res = 0
	for (line in lines) {
		val game = Game.fromLine(line)
		val lowestPossible = game.lowestPossible()
		res += lowestPossible["red"]!! * lowestPossible["green"]!! * lowestPossible["blue"]!!
	}
	print(res)
}