import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
@Configuration
class UserDetailsServiceImpl: UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        return User("foo", "foo", arrayListOf())
    }
}