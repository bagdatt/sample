package kz.bagdat.sample.core.service

import kz.bagdat.sample.core.entity.User
import kz.bagdat.sample.core.repository.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class UserService(private var repository: UserRepository): UserDetailsService {

    fun getUsers(): List<User> {
        return repository.findAll()
    }

    fun findUserByUsername(username: String): User {
        return repository.findByUsername(username).orElseThrow{
            throw UsernameNotFoundException("User with username: $username not found!")
        }
    }


    override fun loadUserByUsername(username: String): UserDetails {
        val user = findUserByUsername(username)

        return org.springframework.security.core.userdetails.User
            .withUsername(username)
            .password(user.password)
            .accountExpired(false)
            .accountLocked(false)
            .credentialsExpired(false)
            .disabled(false)
            .build()
    }
}