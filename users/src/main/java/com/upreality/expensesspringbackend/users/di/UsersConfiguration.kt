package com.upreality.expensesspringbackend.users.di

import com.upreality.expensesspringbackend.users.data.UsersRepositoryImpl
import com.upreality.expensesspringbackend.users.domain.IUsersRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UsersConfiguration {
    @Bean
    fun provideRepository(repository: UsersRepositoryImpl): IUsersRepository = repository
}