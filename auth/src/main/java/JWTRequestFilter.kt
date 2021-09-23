import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JWTRequestFilter : OncePerRequestFilter() {

    @Autowired
    var jwtUtil: JWTUtil? = null

    @Autowired
    var userDetailsService: UserDetailsServiceImpl? = null

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorizationHeader = request.getHeader("Authorization")

        val bearerToken = request.getHeader("Authorization")

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            val jwtToken = bearerToken.substring(7, bearerToken.length)
            var username = jwtUtil?.extractUsername(jwtToken)
            if (username != null && SecurityContextHolder.getContext().authentication == null) {
                val userDetails: UserDetails = userDetailsService!!.loadUserByUsername(username)
                if (jwtUtil!!.validateToken(jwtToken, userDetails) == true) {
                    val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                    usernamePasswordAuthenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                    SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
                }
            }
        }
        filterChain.doFilter(request, response)
    }
}