package aoc2023.day6

import kotlin.math.*

fun customCeil(x : Double) : Long {
	if (x.toString().split(".")[1] == "0") return x.toLong() + 1
	return ceil(x).toLong()
}

fun customFloor(x : Double) : Long {
	if (x.toString().split(".")[1] == "0") return x.toLong() - 1
	return floor(x).toLong()
}

data class Race(val time : Long, val distance : Long) {
	private val discriminant = sqrt(time.toDouble().pow(2.0) - 4 * distance)
	private val min = customCeil((time - discriminant) / 2.0)
	private val max = customFloor((time + discriminant) / 2.0)
	val errorMargin : Long
		get() = (max - min) + 1
	
	companion object {
		fun fromLines(lines: List<String>) : List<Race> {
			val times = lines[0].split(Regex("\\s+")).filterIndexed { index, _ -> index != 0 }.map(String::toLong)
			val distances = lines[1].split(Regex("\\s+")).filterIndexed { index, _ -> index != 0 }.map(String::toLong)
			val races = List(times.size) {
				Race(
					time = times[it],
					distance = distances[it],
				)
			}
			return races
		}
	}
}