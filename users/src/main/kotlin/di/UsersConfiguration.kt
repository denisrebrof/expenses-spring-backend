package di

import data.UsersRepositoryImpl
import domain.IUsersRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
interface UsersConfiguration {

    @Bean
    fun provideRepository(repository: IUsersRepository): UsersRepositoryImpl
}