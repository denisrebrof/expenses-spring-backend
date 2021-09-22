package com.upreality.expensesspringbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication


@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
class ExpensesSpringBackendApplication

fun main(args: Array<String>) {
    runApplication<ExpensesSpringBackendApplication>(*args)
}