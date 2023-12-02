package aoc2023.day2

data class Game(val id : Int, val reds : List<Int>, val greens : List<Int>, val blues : List<Int>) {
	companion object {
		fun fromLine(line : String) : Game = Game(
			id = line.split(":").first().substring(5).toInt(),
			reds = line
				.split(":")
				.last()
				.split(";")
				.flatMap { it.split(",") }
				.filter { it.contains("red") }
				.map(String::trim)
				.map { it.split(" ").first().toInt() },
			greens = line
				.split(":")
				.last()
				.split(";")
				.flatMap { it.split(",") }
				.filter { it.contains("green") }
				.map(String::trim)
				.map { it.split(" ").first().toInt() },
			blues = line
				.split(":")
				.last()
				.split(";")
				.flatMap { it.split(",") }
				.filter { it.contains("blue") }
				.map(String::trim)
				.map { it.split(" ").first().toInt() },
		)
	}
	
	fun lowestPossible() : Map<String, Int> {
		return mapOf(
			"red" to reds.max(),
			"green" to greens.max(),
			"blue" to blues.max(),
		)
	}
}