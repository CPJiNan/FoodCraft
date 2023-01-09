package me.cpjinan.playercamp.manager

import taboolib.library.configuration.ConfigurationSection
import taboolib.module.configuration.Config
import taboolib.module.configuration.ConfigNode
import taboolib.module.configuration.Configuration

object ConfigManager {

    @Config("config.yml", autoReload = true)
    lateinit var config : Configuration
    @ConfigNode("options", "config.yml")
    lateinit var options: ConfigurationSection

    @Config("attribute/Heal.yml", autoReload = true)
    lateinit var heal : Configuration
    @Config("attribute/Feed.yml", autoReload = true)
    lateinit var feed : Configuration

}