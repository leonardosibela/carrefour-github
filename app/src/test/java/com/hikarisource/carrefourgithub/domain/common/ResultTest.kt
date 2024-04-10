package com.hikarisource.carrefourgithub.domain.common

import org.junit.Assert
import org.junit.Test

class ResultTest {

    private companion object {
        const val RESULT_IS_NULL = "Result is null"
        const val NAME = "Leonardo"
        const val MESSAGE = "Message"
    }

    @Test
    fun `GIVEN scope that throws WHEN resultBy called THEN must return Result_Error`() {
        val throwable = Throwable(MESSAGE)
        val result = resultBy { throw throwable }
        Assert.assertEquals(Result.Error<String>(throwable), result)
    }

    @Test
    fun `GIVEN scope that returns non null value WHEN resultBy called THEN must return Result_Success`() {
        val name = NAME
        val result = resultBy { name }
        Assert.assertEquals(Result.Success(name), result)
    }

    @Test
    fun `GIVEN scope that returns null value WHEN resultBy called THEN must return Result_Error`() {
        val nullVal: String? = null
        val result: Result<String?> = resultBy { nullVal }
        Assert.assertTrue(result is Result.Error<String?>)
        Assert.assertEquals(RESULT_IS_NULL, result.error?.message)
    }
}