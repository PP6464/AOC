package aoc2023.day5

data class Almanac(
	val seeds: List<Long>,
	val seedToSoilMap: Map<Long, Long>,
	val soilToFertilizerMap: Map<Long, Long>,
	val fertilizerToWaterMap: Map<Long, Long>,
	val waterToLightMap: Map<Long, Long>,
	val lightToTemperatureMap: Map<Long, Long>,
	val temperatureToHumidityMap: Map<Long, Long>,
	val humidityToLocationMap: Map<Long, Long>
) {
	companion object {
		fun fromLines(lines: List<String>): Almanac {
			val seeds = lines[0].substring(7).split(" ").map(String::trim).map(String::toLong)
			val seedToSoilMap = populateMap(lines, "seed-to-soil map:")
			val soilToFertilizerMap = populateMap(lines, "soil-to-fertilizer map:")
			val fertilizerToWaterMap = populateMap(lines, "fertilizer-to-water map:")
			val waterToLightMap = populateMap(lines, "water-to-light map:")
			val lightToTemperatureMap = populateMap(lines, "light-to-temperature map:")
			val temperatureToHumidityMap = populateMap(lines, "temperature-to-humidity map:")
			val humidityToLocationMap = populateMap(lines, "humidity-to-location map:")
			
			return Almanac(
				seeds,
				seedToSoilMap,
				soilToFertilizerMap,
				fertilizerToWaterMap,
				waterToLightMap,
				lightToTemperatureMap,
				temperatureToHumidityMap,
				humidityToLocationMap
			)
		}
		
		private fun populateMap(lines: List<String>, key: String): Map<Long, Long> {
			val map = mutableMapOf<Long, Long>()
			val keyLine = lines.indexOf(key)
			val section = mutableListOf<String>()
			var index = keyLine + 1
			while (true) {
				if (try { lines[index].isBlank() } catch (e: Throwable) { true }) break
				section.add(lines[index])
				index++
			}
			for (line in section.filterNot { it.isBlank() }) {
				val destStart = line.split(" ").first().trim().toLong()
				val sourceStart = line.split(" ")[1].trim().toLong()
				val rangeLength = line.split(" ").last().trim().toLong()
				(sourceStart..<sourceStart + rangeLength).forEach { i ->
					map[i] = destStart + i - sourceStart
				}
			}
			return map
		}
	}
}