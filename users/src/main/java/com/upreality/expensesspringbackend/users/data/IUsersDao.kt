package com.upreality.expensesspringbackend.users.data

import org.springframework.data.mongodb.repository.MongoRepository

interface IUsersDao : MongoRepository<UserModel, String> {
    fun getById(id: Long): UserModel

}