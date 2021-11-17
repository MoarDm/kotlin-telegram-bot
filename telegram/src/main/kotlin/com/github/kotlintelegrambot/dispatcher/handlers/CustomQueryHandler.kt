package com.github.kotlintelegrambot.dispatcher.handlers

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.entities.Update
import com.github.kotlintelegrambot.entities.payments.provider.CustomQuery

data class CustomQueryHandlerEnvironment(
    val bot: Bot,
    val update: Update,
    val customQuery: CustomQuery
)

internal class CustomQueryHandler(
    private val handleCustomQuery: HandleCustomQuery
) : Handler {

    override fun checkUpdate(update: Update): Boolean {
        return update.customQuery != null
    }

    override fun handleUpdate(bot: Bot, update: Update) {
        checkNotNull(update.customQuery)

        val customQueryHandlerEnv = CustomQueryHandlerEnvironment(
            bot,
            update,
            update.customQuery
        )
        handleCustomQuery(customQueryHandlerEnv)
    }
}
