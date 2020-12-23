package kz.bagdat.sample.core.utils

import kz.bagdat.sample.core.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtFilter : OncePerRequestFilter() {

    @Autowired
    private val userService: UserService? = null

    @Autowired
    private val jwtUtil: JwtUtil? = null

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        try {

            val jwt = getJwt(request)
            if (jwt != null && jwtUtil!!.validateJwtToken(jwt)) {
                val username = jwtUtil.getUserNameFromJwtToken(jwt)

                val userDetails = userService!!.loadUserByUsername(username)
                val authentication = UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities())
                authentication.setDetails(WebAuthenticationDetailsSource().buildDetails(request))

                SecurityContextHolder.getContext().setAuthentication(authentication)
            }
        } catch (e: Exception) {
            logger.error("Can NOT set user authentication -> Message: {}", e)
        }

        filterChain.doFilter(request, response)
    }

    private fun getJwt(request: HttpServletRequest): String? {
        val authHeader = request.getHeader("Authorization")

        return if (authHeader != null && authHeader.startsWith("Bearer ")) {
            authHeader.replace("Bearer ", "")
        } else null
    }
}