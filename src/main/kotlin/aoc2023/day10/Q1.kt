package aoc2023.day10

import java.io.File

fun main() {
	val lines = File("assets/2023/day10/debug-input.txt").readLines()
	val pipes = Pipes(lines)
	val largestLoop = pipes.findLargestLoop()
	println(largestLoop[largestLoop.size / 2])
}
