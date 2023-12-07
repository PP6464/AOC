package aoc2023.day2

import java.io.File

fun main() {
	val lines = File("assets/2023/day2/input.txt").readLines()
	var res = 0
	for (line in lines) {
		val game = Game.fromLine(line)
		val lowestPossible = game.lowestPossible()
		if (lowestPossible["red"]!! <= 12 && lowestPossible["green"]!! <= 13 && lowestPossible["blue"]!! <= 14) {
			res += game.id
		}
	}
	println(res)
}