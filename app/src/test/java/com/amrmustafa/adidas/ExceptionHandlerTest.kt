package com.amrmustafa.casestudy

import com.amrmustafa.adidas.R
import com.amrmustafa.adidas.utils.ExceptionHandler
import com.google.common.truth.Truth
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.net.UnknownHostException

@RunWith(JUnit4::class)
class ExceptionHandlerTest {

    @Test
    fun unknown_host_exception_then_return_no_internet() {
        val message = ExceptionHandler.parse(UnknownHostException())
        Truth.assertThat(message).isEqualTo(R.string.error_check_internet_connection)
    }

    @Test
    fun unknown_exception_default_error() {
        val message = ExceptionHandler.parse(Exception())
        Truth.assertThat(message).isEqualTo(R.string.error_oops_error_occured)
    }
}