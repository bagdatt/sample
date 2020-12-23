package kz.bagdat.sample.core.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
@Table(schema = "sample", name = "users")
class User(
    @Id
    var id: Long,
    var name: String,
    var surname: String,
    var username: String,
    var password: String
)