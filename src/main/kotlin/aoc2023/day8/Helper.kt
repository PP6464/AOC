package aoc2023.day8

data class Node(val name : String, val options : List<String>)

fun List<Node>.findNode(name : String) : Node {
	return single { it.name == name }
}

data class Map(val nodes : List<Node>, val path : String) {
	var stepsTaken = 0
	var currentNode = try { nodes.findNode("AAA") } catch (e: Throwable) { nodes.first() }
	
	fun takeStep(part: Int = 1) : Boolean {
		val direction = if (path[stepsTaken % path.length] == 'L') 0 else 1
		currentNode = nodes.findNode(currentNode.options[direction])
		stepsTaken++
		return if (part == 1) currentNode.name == "ZZZ" else currentNode.name.endsWith('Z')
	}
	companion object {
		fun fromLines(lines : List<String>) : Map {
			val path = lines[0]
			val nodes = mutableListOf<Node>()
			for (i in 2..<lines.size) {
				val node = Node(
					name = lines[i].split(" ").first(),
					options = lines[i].split(" ").takeLast(2).map {
						it.trim(',', ' ', '(', ')')
					}
				)
				nodes += node
			}
			return Map(nodes = nodes, path = path)
		}
	}
}