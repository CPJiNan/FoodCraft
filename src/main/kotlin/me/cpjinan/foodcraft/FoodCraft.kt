package me.cpjinan.foodcraft

import me.cpjinan.playercamp.manager.RegisterManager.registerAll
import taboolib.common.platform.Plugin
import taboolib.common.platform.function.info

object FoodCraft : Plugin() {
    override fun onEnable() {
        registerAll()
    }
}