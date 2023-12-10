package aoc2023.day10

data class Coord(val x : Int, val y : Int)

enum class Direction {
	UP,
	DOWN,
	LEFT,
	RIGHT;
	
	fun isValidPipeChar(pipeChar : PipeChar) : Boolean {
		return when (pipeChar) {
			PipeChar.START -> true
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
	START,
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
				'S' -> START
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
	
	fun findLoop() : List<Coord> {
		return completeLoopFor(startingCoord, startingCoord)!!
	}
	
	private fun completeLoopFor(coord: Coord, vararg exclusions: Coord) : List<Coord>? {
		val options = optionsFor(coord).filterNot { exclusions.contains(it) }.run {
			val mutable = toMutableList()
			if (coord == startingCoord) {
				mutable.removeLast()
				mutable
			} else mutable
		}
		if (options.isEmpty()) return null
		return if (options.contains(startingCoord)) listOf(startingCoord) else try {
			mutableListOf(coord).apply {
				addAll(
					options.mapNotNull {
						completeLoopFor(
							it,
							coord
						)
					}
						.maxBy { it.size }
				)
			}
		} catch (e: Throwable) {
			null
		}
	}
}