package aoc2023.day10

import java.io.File

fun main() {
	val lines = File("assets/2023/day10/debug-input.txt").readLines()
	val pipes = Pipes(lines)
	val loop = pipes.findLoop()
	println(loop.size / 2)
}
