package com.upreality.users.di

import com.upreality.users.data.UsersRepositoryImpl
import com.upreality.users.domain.IUsersRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
interface UsersConfiguration {

    @Bean
    fun provideRepository(repository: IUsersRepository): UsersRepositoryImpl
}