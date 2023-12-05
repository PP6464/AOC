package aoc2023.day5

import java.io.File

fun main() {
	val lines = File("assets/2023/day5/input.txt").readLines()
	val almanac = Almanac.fromLines(lines)
	print(
		almanac
			.seeds
			.map {
				val soil = almanac.seedToSoilMap[it] ?: it
				val fertilizer = almanac.soilToFertilizerMap[soil] ?: soil
				val water = almanac.fertilizerToWaterMap[fertilizer] ?: fertilizer
				val light = almanac.waterToLightMap[water] ?: water
				val temperature = almanac.lightToTemperatureMap[light] ?: light
				val humidity = almanac.temperatureToHumidityMap[temperature] ?: temperature
				return@map almanac.humidityToLocationMap[humidity] ?: humidity
			}
			.reduce { a, b ->
				if (b < a) return@reduce b
				else return@reduce a
			}
	)
}
