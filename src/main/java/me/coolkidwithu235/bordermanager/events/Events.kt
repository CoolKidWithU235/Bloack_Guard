package me.coolkidwithu235.bordermanager.events

import org.bukkit.ChatColor
import org.bukkit.World
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import java.io.File

// Brds refers to borders, essentially a collection of 4 Ints x1, y1, x2, y2 that represent the corners of the area
// x2 > x1 and y2 > y1

class Events : Listener {

    val pathToBrds = "./plugins/BorderManager/borders.brd"
    // Allows for a dynamic amount of a predefined amount af ints, in the form of a MutList of IntArray
    var lsBrds: MutableList<IntArray> = mutableListOf(intArrayOf(0, 0))

    init {

        lsBrds = readBrdsFromFile()
    }

    fun updateBrdsFromFile() {
        lsBrds = readBrdsFromFile();
    }

    private fun readBrdsFromFile(): MutableList<IntArray> {

        val brds: MutableList<IntArray> = mutableListOf()
        val dump = File(pathToBrds).readLines()

        for (line in dump) {

            val brd = intArrayOf(0, 0, 0, 0)
            val y = line.split(',')

            y.forEachIndexed { i, str ->
                brd[i] = str.toInt()
            }

            brds.add(element = brd)

        }

        return brds

    }

    @EventHandler
    fun onBlockBrokenCheck(event: BlockBreakEvent) {

        // Makes it so the checks only apply in the overworld, possibly let this be user configured ?
        if (event.block.world.environment == World.Environment.NORMAL) {


            var outsideBrd = true

            // This right here sets a practical limit on how many areas can be defined, too many will result in to long loop time
            for (brd in lsBrds) {

                if ((event.block.x > brd[0] && event.block.z > brd[1] && event.block.x < brd[2] && event.block.z < brd[3])) {

                    outsideBrd = false
                }
            }

            if (outsideBrd) {

                event.isDropItems = false
                event.player.sendMessage(ChatColor.RED.toString().plus("You are breaking a block outside the border"))
            }
        }
    }
}