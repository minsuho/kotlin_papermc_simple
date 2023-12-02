package com.suho.simple_template

import org.bukkit.plugin.java.JavaPlugin

import io.github.monun.kommand.kommand


class Main : JavaPlugin() {

    override fun onEnable() {
        logger.info("플러그인이 활성화되었습니다.")
    }

    override fun onDisable() {
        logger.info("플러그인이 비활성화되었습니다.")
    }
}