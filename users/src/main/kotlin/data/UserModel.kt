package com.upreality.users.data

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class UserModel(
        @Id
        var id: Long = 0L,
        var email: String? = null,
        var googleId: Long? = null,
        var nickname: String? = null,
        var passwordHash: String? = null,
        var token: String? = null,
        var refreshToken: String? = null,
        val createdDate: LocalDateTime = LocalDateTime.now()
)