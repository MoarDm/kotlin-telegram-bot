package com.github.kotlintelegrambot.dispatcher.handlers

import anyCustomQuery
import anyUpdate
import com.github.kotlintelegrambot.Bot
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.jupiter.api.Test

class CustomQueryHandlerTest {

    private val handleCustomQueryMock = mockk<HandleCustomQuery>(relaxed = true)
    private val sut = CustomQueryHandler(handleCustomQuery = handleCustomQueryMock)

    @Test
    fun `checkUpdate returns false when there is no custom query`() {
        val anyUpdateWithoutCustomQuery = anyUpdate(customQuery = null)

        val checkUpdateResult = sut.checkUpdate(anyUpdateWithoutCustomQuery)

        assertFalse(checkUpdateResult)
    }

    @Test
    fun `checkUpdate returns true when there is custom query`() {
        val anyUpdateCustomQuery = anyUpdate(customQuery = anyCustomQuery())

        val checkUpdateResult = sut.checkUpdate(anyUpdateCustomQuery)

        assertTrue(checkUpdateResult)
    }

    @Test
    fun `custom query is properly dispatched to the handler function`() {
        val botMock = mockk<Bot>()
        val anyCustomQuery = anyCustomQuery()
        val anyUpdateCustomQuery = anyUpdate(customQuery = anyCustomQuery)

        sut.handleUpdate(botMock, anyUpdateCustomQuery)

        val expectedCustomQueryHandlerEnv = CustomQueryHandlerEnvironment(
            botMock,
            anyUpdateCustomQuery,
            anyCustomQuery
        )
        verify { handleCustomQueryMock.invoke(expectedCustomQueryHandlerEnv) }
    }
}
