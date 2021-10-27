package com.upreality.expensesspringbackend

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
@EnableAutoConfiguration(exclude = [DataSourceAutoConfiguration::class])
@ComponentScan(basePackages = ["com.upreality.expensesspringbackend"])
class ExpensesSpringBackendApplication

fun main(args: Array<String>) {
    runApplication<ExpensesSpringBackendApplication>(*args)
}
