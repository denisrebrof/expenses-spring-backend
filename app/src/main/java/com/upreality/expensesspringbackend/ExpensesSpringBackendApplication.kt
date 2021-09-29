package com.upreality.expensesspringbackend

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@EnableAutoConfiguration(exclude = [DataSourceAutoConfiguration::class])
@ComponentScan(basePackages = ["com.upreality"])
class ExpensesSpringBackendApplication

fun main(args: Array<String>) {
    runApplication<ExpensesSpringBackendApplication>(*args)
}