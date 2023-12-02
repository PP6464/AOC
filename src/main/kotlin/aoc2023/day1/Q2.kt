package aoc2023.day1

import java.io.File

fun main() {
	val lines = File("assets/2023/day1/input.txt").readLines()
	var res = 0
	for (line in lines) {
		val digits = mutableListOf<Int>()
		var lineCopy = line
		while (lineCopy.isNotEmpty()) {
			when {
				lineCopy.startsWith("1") ||
						lineCopy.startsWith("2") ||
						lineCopy.startsWith("3") ||
						lineCopy.startsWith("4") ||
						lineCopy.startsWith("5") ||
						lineCopy.startsWith("6") ||
						lineCopy.startsWith("7") ||
						lineCopy.startsWith("8") ||
						lineCopy.startsWith("9") -> {
					digits.add(lineCopy.first().digitToInt())
					break
				}
				
				lineCopy.startsWith("one") -> {
					digits.add(1)
					break
				}
				
				lineCopy.startsWith("two") -> {
					digits.add(2)
					break
				}
				
				lineCopy.startsWith("three") -> {
					digits.add(3)
					break
				}
				
				lineCopy.startsWith("four") -> {
					digits.add(4)
					break
				}
				
				lineCopy.startsWith("five") -> {
					digits.add(5)
					break
				}
				
				lineCopy.startsWith("six") -> {
					digits.add(6)
					break
				}
				
				lineCopy.startsWith("seven") -> {
					digits.add(7)
					break
				}
				
				lineCopy.startsWith("eight") -> {
					digits.add(8)
					break
				}
				
				lineCopy.startsWith("nine") -> {
					digits.add(9)
					break
				}
				
				else -> {
					lineCopy = lineCopy.substring(1)
				}
			}
		}
		lineCopy = line
		while (lineCopy.isNotEmpty()) {
			when {
				lineCopy.endsWith("1") ||
						lineCopy.endsWith("2") ||
						lineCopy.endsWith("3") ||
						lineCopy.endsWith("4") ||
						lineCopy.endsWith("5") ||
						lineCopy.endsWith("6") ||
						lineCopy.endsWith("7") ||
						lineCopy.endsWith("8") ||
						lineCopy.endsWith("9") -> {
					digits.add(lineCopy.last().digitToInt())
					break
				}
				
				lineCopy.endsWith("one") -> {
					digits.add(1)
					break
				}
				
				lineCopy.endsWith("two") -> {
					digits.add(2)
					break
				}
				
				lineCopy.endsWith("three") -> {
					digits.add(3)
					break
				}
				
				lineCopy.endsWith("four") -> {
					digits.add(4)
					break
				}
				
				lineCopy.endsWith("five") -> {
					digits.add(5)
					break
				}
				
				lineCopy.endsWith("six") -> {
					digits.add(6)
					break
				}
				
				lineCopy.endsWith("seven") -> {
					digits.add(7)
					break
				}
				
				lineCopy.endsWith("eight") -> {
					digits.add(8)
					break
				}
				
				lineCopy.endsWith("nine") -> {
					digits.add(9)
					break
				}
				
				else -> {
					lineCopy = lineCopy.substring(0, lineCopy.length - 1)
				}
			}
		}
		res += 10 * digits.first() + digits.last()
	}
	println(res)
}