package kz.bagdat.sample.web

import kz.bagdat.sample.core.dto.LoginDTO
import kz.bagdat.sample.core.service.UserService
import kz.bagdat.sample.core.utils.JwtUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(path = ["auth"])
@CrossOrigin(origins = ["*"], maxAge = 3600)
class AuthController(private val userService: UserService,
                     private val  authenticationManager: AuthenticationManager,
                     private val jwtUtil: JwtUtil) {


    @PostMapping("signin")
    fun authenticateUser(@RequestBody dto: LoginDTO): ResponseEntity<String> {
        val user = userService.findUserByUsername(dto.username)
        if(!BCrypt.checkpw(dto.password, user.password)) {
            return ResponseEntity(HttpStatus.UNAUTHORIZED)
        }
        val jwt: String = jwtUtil.generateJwtToken(user.username)
        return ResponseEntity.ok("Bearer $jwt")
    }

}