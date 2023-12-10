package aoc2023.day10

data class Coord(val x : Int, val y : Int)

enum class Direction {
	UP,
	DOWN,
	LEFT,
	RIGHT;
	
	fun isValidPipeChar(pipeChar : PipeChar) : Boolean {
		return when (pipeChar) {
			PipeChar.VERTICAL_PIPE -> when (this) {
				UP, DOWN -> true
				else -> false
			}
			PipeChar.HORIZONTAL_PIPE -> when (this) {
				LEFT, RIGHT -> true
				else -> false
			}
			PipeChar.L_BEND -> when (this) {
				LEFT, DOWN -> true
				else -> false
			}
			PipeChar.J_BEND -> when (this) {
				RIGHT, DOWN -> true
				else -> false
			}
			PipeChar.`7_BEND` -> when (this) {
				RIGHT, UP -> true
				else -> false
			}
			PipeChar.F_BEND -> when (this) {
				LEFT, UP -> true
				else -> false
			}
			PipeChar.GROUND -> false
		}
	}
}

enum class PipeChar {
	VERTICAL_PIPE,
	HORIZONTAL_PIPE,
	L_BEND,
	J_BEND,
	@Suppress("EnumEntryName")
	`7_BEND`,
	F_BEND,
	GROUND;
	
	companion object {
		fun fromChar(char : Char) : PipeChar {
			return when (char) {
				'|' -> VERTICAL_PIPE
				'-' -> HORIZONTAL_PIPE
				'L' -> L_BEND
				'J' -> J_BEND
				'7' -> `7_BEND`
				'F' -> F_BEND
				else -> GROUND
			}
		}
	}
}

data class Pipes(val lines : List<String>) {
	private val startingCoord =
		Coord(lines.single { it.contains("S") }.indexOf("S"), lines.withIndex().single { (_, v) -> v.contains("S") }.index)
	
	private fun getAtCoord(coord : Coord) : Char {
		return lines[coord.y][coord.x]
	}
	
	private fun optionsFor(coord: Coord) : List<Coord> {
		val options = mutableListOf<Coord>()
		repeat(4) {
			val direction = Direction.entries[it]
			val pipeChar = PipeChar.fromChar(
				try {
					when (direction) {
						Direction.UP -> getAtCoord(coord.copy(y = coord.y - 1))
						Direction.LEFT -> getAtCoord(coord.copy(x = coord.x - 1))
						Direction.DOWN -> getAtCoord(coord.copy(y = coord.y + 1))
						Direction.RIGHT -> getAtCoord(coord.copy(x = coord.x + 1))
					}
				} catch (e: Throwable) { '.' }
			)
			if (direction.isValidPipeChar(pipeChar)) {
				options += when (direction) {
					Direction.UP -> coord.copy(y = coord.y - 1)
					Direction.LEFT -> coord.copy(x = coord.x - 1)
					Direction.DOWN -> coord.copy(y = coord.y + 1)
					Direction.RIGHT -> coord.copy(x = coord.x + 1)
				}
			}
		}
		return options
	}
	
	fun findLargestLoop() : List<Coord> {
		val options = optionsFor(startingCoord)
		val restOfLoop = options.map {
			it to optionsFor(it).filterNot { x -> x == startingCoord }
		}.map {
			it.first to it.second.map { x -> completeLoopFor(x) }.maxBy { x -> x.size }
		}.maxBy {
			it.second.size
		}
		return mutableListOf(startingCoord, restOfLoop.first).apply {
			addAll(restOfLoop.second)
		}
	}
	
	private fun completeLoopFor(coord: Coord) : List<Coord> {
		val options = optionsFor(coord)
		return if (options.contains(startingCoord)) listOf(startingCoord) else options.map { completeLoopFor(it) }.maxBy { it.size }
	}
}