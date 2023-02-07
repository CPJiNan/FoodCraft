package me.cpjinan.foodcraft.listener

import me.cpjinan.playercamp.manager.ConfigManager
import org.bukkit.ChatColor
import org.bukkit.event.player.PlayerItemConsumeEvent
import taboolib.common.platform.event.SubscribeEvent

object FoodEvent {

    @SubscribeEvent
    fun onFood(event : PlayerItemConsumeEvent) {

        if (event.item.itemMeta!!.lore == null) return

        val player = event.player
        val regex = Regex("[0-9]+")
        val attributeMap = HashMap<String, Double>()

        /**
         * 词条 - 回复生命
         */
        attributeMap["回复生命"] = 0.0
        event.item.itemMeta!!.lore!!.forEach {
            ConfigManager.heal.getStringList("heal.keywords").forEach { key ->
                if (ChatColor.stripColor(it)!!.contains(key)) attributeMap["回复生命"] = attributeMap["回复生命"]!! + regex.find(ChatColor.stripColor(it)!!)?.value?.toDouble()!!
            }
        }
        if (attributeMap["回复生命"] != 0.0) {
            event.isCancelled = true
            player.inventory.itemInMainHand.amount -= 1
            player.health = (player.health + attributeMap["回复生命"]!!).coerceAtMost(player.maxHealth)
        }

        /**
         * 词条 - 回复饱食
         */
        attributeMap["回复饱食"] = 0.0
        event.item.itemMeta!!.lore!!.forEach {
            ConfigManager.feed.getStringList("feed.keywords").forEach { key ->
                if (ChatColor.stripColor(it)!!.contains(key)) attributeMap["回复饱食"] = attributeMap["回复饱食"]!! + regex.find(ChatColor.stripColor(it)!!)?.value?.toDouble()!!
            }
        }
        if (attributeMap["回复饱食"] != 0.0) {
            event.isCancelled = true
            player.inventory.itemInMainHand.amount -= 1
            player.foodLevel = (player.foodLevel + attributeMap["回复饱食"]!!).coerceAtMost(20.0).toInt()
        }

    }
}