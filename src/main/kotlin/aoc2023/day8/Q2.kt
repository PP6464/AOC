package aoc2023.day8

import java.io.File

fun main() {
	val lines = File("assets/2023/day8/input.txt").readLines()
	val map = Map.fromLines(lines)
	val nodesEndingInA = map.nodes.filter { it.name.endsWith('A') }
	val mapCopies = (nodesEndingInA).map {
		Map.fromLines(lines).apply {
			currentNode = it
		}
	}
	var finished = false
	while (!finished) {
		finished = mapCopies.map { it.takeStep(part = 2) }.all { it }
	}
	print(mapCopies.first().stepsTaken)
}