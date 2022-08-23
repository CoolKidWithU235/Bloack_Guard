package me.coolkidwithu235.bordermanager.events

import org.bukkit.ChatColor
import org.bukkit.World
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import java.io.File

// Brds refers to borders, essentially a collection of 4 Ints x1, z1, x2, z2 that represent the corners of the area
// x2 > x1 and z2 > z1

class Events : Listener {

    val pathToBrds = "./plugins/BorderManager/borders.brd"
    // Allows for a dynamic amount of a predefined amount af ints, in the form of a MutList of IntArray
    var listOfBorders: MutableList<IntArray> = mutableListOf(intArrayOf(0, 0))

    init {

        listOfBorders = readBrdsFromFile()
    }

    fun updateBrdsFromFile() {
        listOfBorders = readBrdsFromFile()
    }

    private fun readBrdsFromFile(): MutableList<IntArray> {

        // TODO: Sanitize input from file better against white spacing

        val borders: MutableList<IntArray> = mutableListOf()
        val dump = File(pathToBrds).readLines()

        for (line in dump) {

            val border = intArrayOf(0, 0, 0, 0)
            val y = line.split(',')

            y.forEachIndexed { i, str ->
                border[i] = str.toInt()
            }

            borders.add(element = border)

        }

        return borders

    }

    @EventHandler
    fun onBlockBrokenCheck(event: BlockBreakEvent) {

        // Makes it so the checks only apply in the overworld, possibly let this be user configured ?
        if (event.block.world.environment == World.Environment.NORMAL) {


            var outsideBorder = true

            // This right here sets a practical limit on how many areas can be defined, too many will result in to long loop time
            for (border in listOfBorders) {

                if ((event.block.x > border[0] && event.block.z > border[1] && event.block.x < border[2] && event.block.z < border[3])) {

                    outsideBorder = false
                }
            }

            if (outsideBorder) {

                event.isDropItems = false
                event.player.sendMessage(ChatColor.RED.toString().plus("You are breaking a block outside the border"))
            }
        }
    }
}