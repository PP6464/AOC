package aoc2023.day11

import kotlin.math.abs

data class Point(val x : Int, val y : Int)

data class Universe(val lines : List<String>) {
	private val emptyColumnsAt = (lines[0].indices).filter { !lines.any { l -> l[it] == '#' } }
	private val emptyRowsAt = lines.indices.filter { lines[it].all { x -> x == '.' } }
	
	private val galaxies : List<Point>
		get() = lines.withIndex().filter { (_, value) -> value.contains("#") }
			.flatMap { (index, value) -> value.withIndex().filter { (_, v) -> v == '#' }.map { (i, _) -> Point(i, index) } }
	
	fun shortestDistances(part: Int = 1) : List<Int>  {
			val distances = mutableListOf<Int>()
			for (i in galaxies.indices) {
				for (j in i..<galaxies.size) {
					distances.add(
						abs(galaxies[i].x - galaxies[j].x) + emptyColumnsAt.filter {
							it in galaxies[i].x..galaxies[j].x
						}
							.size * (if (part == 1) 2 else 1_000_000)
								+ abs(galaxies[i].y - galaxies[j].y) + emptyRowsAt.filter {
									it in galaxies[i].y..galaxies[j].y
								}
									.size * (if (part == 1) 2 else 1_000_000)
					)
				}
			}
			return distances
		}
	
}