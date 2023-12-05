package aoc2023.day5

data class Almanac(
	val seeds : List<Long>,
	val seedToSoilMap : Map<Long, Long>,
	val soilToFertilizerMap : Map<Long, Long>,
	val fertilizerToWaterMap : Map<Long, Long>,
	val waterToLightMap : Map<Long, Long>,
	val lightToTemperatureMap : Map<Long, Long>,
	val temperatureToHumidityMap : Map<Long, Long>,
	val humidityToLocationMap : Map<Long, Long>,
) {
	companion object {
		fun fromLines(lines : List<String>) : Almanac {
			val seeds = lines[0]
				.substring(7)
				.split(" ")
				.map(String::trim)
				.map(String::toLong)
			val seedToSoilMap = mutableMapOf<Long, Long>()
			val soilToFertilizerMap = mutableMapOf<Long, Long>()
			val fertilizerToWaterMap = mutableMapOf<Long, Long>()
			val waterToLightMap = mutableMapOf<Long, Long>()
			val lightToTemperatureMap = mutableMapOf<Long, Long>()
			val temperatureToHumidityMap = mutableMapOf<Long, Long>()
			val humidityToLocationMap = mutableMapOf<Long, Long>()
			
			val entireFile = lines.joinToString("\n")
			
			for (
				line in entireFile
					.split("seed-to-soil map:")
					.last()
					.split("soil-to-fertilizer map:")
					.first()
					.split("\n")
					.filterNot { it.isBlank() }
			) {
				val destStart = line.split(" ").first().trim().toLong()
				val sourceStart = line.split(" ")[1].trim().toLong()
				val rangeLength = line.split(" ").last().trim().toLong()
				for (i in 0..<rangeLength) {
					seedToSoilMap[sourceStart + i] = destStart + i
				}
			}
			for (
				line in entireFile
					.split("soil-to-fertilizer map:")
					.last()
					.split("fertilizer-to-water map:")
					.first()
					.split("\n")
					.filterNot { it.isBlank() }
			) {
				val destStart = line.split(" ").first().trim().toLong()
				val sourceStart = line.split(" ")[1].trim().toLong()
				val rangeLength = line.split(" ").last().trim().toLong()
				for (i in 0..<rangeLength) {
					soilToFertilizerMap[sourceStart + i] = destStart + i
				}
			}
			for (
				line in entireFile
					.split("fertilizer-to-water map:")
					.last()
					.split("water-to-light map:")
					.first()
					.split("\n")
					.filterNot { it.isBlank() }
			) {
				val destStart = line.split(" ").first().trim().toLong()
				val sourceStart = line.split(" ")[1].trim().toLong()
				val rangeLength = line.split(" ").last().trim().toLong()
				for (i in 0..<rangeLength) {
					fertilizerToWaterMap[sourceStart + i] = destStart + i
				}
			}
			for (
				line in entireFile
					.split("water-to-light map:")
					.last()
					.split("light-to-temperature map:")
					.first()
					.split("\n")
					.filterNot { it.isBlank() }
			) {
				val destStart = line.split(" ").first().trim().toLong()
				val sourceStart = line.split(" ")[1].trim().toLong()
				val rangeLength = line.split(" ").last().trim().toLong()
				for (i in 0..<rangeLength) {
					waterToLightMap[sourceStart + i] = destStart + i
				}
			}
			for (
				line in entireFile
					.split("light-to-temperature map:")
					.last()
					.split("temperature-to-humidity map:")
					.first()
					.split("\n")
					.filterNot { it.isBlank() }
			) {
				val destStart = line.split(" ").first().trim().toLong()
				val sourceStart = line.split(" ")[1].trim().toLong()
				val rangeLength = line.split(" ").last().trim().toLong()
				for (i in 0..<rangeLength) {
					lightToTemperatureMap[sourceStart + i] = destStart + i
				}
			}
			for (
				line in entireFile
					.split("temperature-to-humidity map:")
					.last()
					.split("humidity-to-location map:")
					.first()
					.split("\n")
					.filterNot { it.isBlank() }
			) {
				val destStart = line.split(" ").first().trim().toLong()
				val sourceStart = line.split(" ")[1].trim().toLong()
				val rangeLength = line.split(" ").last().trim().toLong()
				for (i in 0..<rangeLength) {
					temperatureToHumidityMap[sourceStart + i] = destStart + i
				}
			}
			for (
				line in entireFile
					.split("humidity-to-location map:")
					.last()
					.split("\n")
					.filterNot { it.isBlank() }
			) {
				val destStart = line.split(" ").first().trim().toLong()
				val sourceStart = line.split(" ")[1].trim().toLong()
				val rangeLength = line.split(" ").last().trim().toLong()
				for (i in 0..<rangeLength) {
					humidityToLocationMap[sourceStart + i] = destStart + i
				}
			}
			
			return Almanac(
				seeds,
				seedToSoilMap,
				soilToFertilizerMap,
				fertilizerToWaterMap,
				waterToLightMap,
				lightToTemperatureMap,
				temperatureToHumidityMap,
				humidityToLocationMap,
			)
		}
	}
}