package aoc2023.day8

import java.io.File

fun main() {
	val lines = File("assets/2023/day8/input.txt").readLines()
	val map = Map.fromLines(lines)
	while (!map.takeStep()) {
		// Do nothing, let the while loop condition call takeStep method
	}
	print(map.stepsTaken)
}