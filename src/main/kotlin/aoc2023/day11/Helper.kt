package aoc2023.day11

import kotlin.math.abs

data class Point(val x : Int, val y : Int)

fun String.copy(n : Int) : String {
	val sb = StringBuilder()
	repeat(n) {
		sb.append(this)
	}
	return sb.toString()
}

data class Universe(val lines : List<String>) {
	private val expanded : List<String>
		get() {
			val emptyColumnsAt = (lines[0].indices).filter { !lines.any { l -> l[it] == '#' } }
			val emptyRowsAt = lines.indices.filter { lines[it].all { x -> x == '.' } }
			val linesCopy = lines.toMutableList()
			for ((iterations, index) in emptyRowsAt.withIndex()) {
				linesCopy.add(index + iterations, ".".copy(lines[0].length))
			}
			for ((iterations, index) in emptyColumnsAt.withIndex()) {
				repeat(linesCopy.size) {
					linesCopy[it] = linesCopy[it].toMutableList().apply { add(index + iterations, '.') }.joinToString("")
				}
			}
			return linesCopy
		}
	
	private val galaxies : List<Point>
		get() = expanded.withIndex().filter { (_, value) -> value.contains("#") }
			.flatMap { (index, value) -> value.withIndex().filter { (_, v) -> v == '#' }.map { (i, _) -> Point(i, index) } }
	
	val shortestDistances : List<Int>
		get() {
			val distances = mutableListOf<Int>()
			for (i in galaxies.indices) {
				for (j in i..<galaxies.size) {
					distances.add(abs(galaxies[i].x - galaxies[j].x) + abs(galaxies[i].y - galaxies[j].y))
				}
			}
			return distances
		}
	
}