package kz.bagdat.sample.core.utils

import org.slf4j.LoggerFactory
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtEntryPoint: AuthenticationEntryPoint {

    private val logger = LoggerFactory.getLogger(JwtEntryPoint::class.java)

    @Throws(IOException::class, ServletException::class)
    override fun commence(request: HttpServletRequest,
                          response: HttpServletResponse,
                          exp: AuthenticationException) {
        logger.error("Unauthorized error. Message - {}", exp.message)
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid credentials")
    }
}