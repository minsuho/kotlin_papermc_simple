package com.suho.test

import net.kyori.adventure.text.Component.text
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import xyz.icetang.lib.kommand.getValue
import xyz.icetang.lib.kommand.kommand


class Main : JavaPlugin() {

    override fun onEnable() {
        logger.info("플러그인이 활성화되었습니다.")
        this.kommand {
            register("테스트", "test") {
                requires { isPlayer && isOp }
                executes {
                    sender.sendMessage(text("Hello World!"))
                }
                then("foo") {
                    executes {
                        player.sendMessage(text("Hello Foo!"))
                    }
                    then("my" to player()) {
                        executes {
                            val my: Player by it
                            sender.sendMessage(my.name())
                            my.sendMessage("ok")
                        }
                    }
                }
            }
        }
    }

    override fun onDisable() {
        logger.info("플러그인이 비활성화되었습니다.")
    }
}