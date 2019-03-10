import java.io.File
import java.lang.Integer.max

class Knapsack(inFile: String) {
    var capacity = 0
    var numItems = 0
    var maxValue: Int = 0
    var items: List<Item>? = null

    init {

        File(inFile).readLines()
            .forEachIndexed { index, line ->
                when(index) {
                    0 -> capacity = line.toInt()
                    1 -> numItems = line.toInt()
                    // item lines
                    else -> {
                        // if items has not been initialized
                        if (items == null) {
                            // we now have the number of items
                            // so we initialize it
                            items = List(numItems) { Item() }
                        }

                        // split eat item into it's parts
                        val parts = line.split(' ')

                        // we only need parts 1 and 2, the name is irrelevant
                        items!![index - 2].weight = parts[1].toInt()
                        items!![index - 2].value = parts[2].toInt()
                    }
                }
            }

        // initialize grid
        val grid = Array(numItems) { IntArray(capacity) }

        // Index through the items
        items?.forEachIndexed { i, item ->
            // Index through the knapsack sizes
            val itemValues = grid[i].mapIndexed { j, _ ->
                // If we are on the first row
                if (i == 0) {
                    // if we can hold the current item in our knapsack
                    if (item.weight <= j) {
                        // return the item's value
                        item.value

                    } else {
                        // return 0
                        0
                    }
                } else {
                    // room left over
                    val spareRoom = j - item.weight

                    when {
                        // we have some spare room
                        spareRoom > 0 -> max(
                            // previous value
                            grid[i - 1][j],
                            // current item value + value of remaining space
                            item.value + grid[i - 1][j - item.weight]
                        )
                        // we have filled up our knapsack
                        spareRoom == 0  -> max(
                            // previous value
                            grid[i - 1][j],
                            // current value
                            item.value
                        )
                        // we don't have room for this item
                        else -> grid[i - 1][j]
                    }
                }
            }.toIntArray()

            grid[i] = itemValues
        }

        // max value is last cell
        maxValue = grid.last().last()
    }

    class Item {
        var weight: Int = 0
        var value: Int = 0
    }
}