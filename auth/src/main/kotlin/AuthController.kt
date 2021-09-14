import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class AuthController {
    @Autowired
    var authenticationManager: AuthenticationManager? = null

    @Autowired
    var userDetailsService: UserDetailsServiceImpl? = null

    @Autowired
    var jwtUtil: JWTUtil? = null
    @RequestMapping("/hello")
    fun hello(): String {
        return "Nil here"
    }

    @PostMapping("/authenticate")
    fun authenticate(@RequestBody authenticationRequest: AuthenticationRequest): ResponseEntity<*> {
        val authenticate: Authentication = authenticationManager!!.authenticate(
            UsernamePasswordAuthenticationToken(
                authenticationRequest.username,
                authenticationRequest.password
            )
        )
        val userDetails: UserDetails = userDetailsService!!.loadUserByUsername(authenticationRequest.username)
        val jwt: String = jwtUtil!!.generateToken(userDetails) ?: ""
        return ResponseEntity.ok<Any>(AuthenticationResponse(jwt))
    }
}