package com.upreality.expensesspringbackend

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import springfox.documentation.builders.*
import springfox.documentation.schema.ModelKeyBuilder
import springfox.documentation.schema.ScalarType
import springfox.documentation.service.*
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import java.time.LocalDate

@Configuration
class SwaggerApiConfiguration {

    private val tag = Tag("Pet Service", "All apis relating to pets")

    private val responses = ResponseBuilder()
        .code("500")
        .description("500 message")
        .representation(MediaType.TEXT_XML)
        .apply { r: RepresentationBuilder ->
            r.model { m: ModelSpecificationBuilder ->
                m.referenceModel { ref: ReferenceModelSpecificationBuilder ->
                    ref.key { k: ModelKeyBuilder ->
                        k.qualifiedModelName { q: QualifiedModelNameBuilder ->
                            q.namespace("some:namespace").name("ERROR")
                        }
                    }
                }
            }
        }.build().let(::listOf)

    private val globalRequestParameters = RequestParameterBuilder()
        .name("someGlobalParameter")
        .description("Description of someGlobalParameter")
        .`in`(ParameterType.QUERY)
        .required(true)
        .query { q: SimpleParameterSpecificationBuilder ->
            q.model { m: ModelSpecificationBuilder ->
                m.scalarModel(ScalarType.STRING)
            }
        }.build().let(::listOf)

    @Bean
    fun getSwaggerApi(): Docket? = Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build()
        .pathMapping("/")
        .directModelSubstitute(LocalDate::class.java, String::class.java)
        .genericModelSubstitutes(ResponseEntity::class.java)
        .useDefaultResponseMessages(false)
        .globalResponses(HttpMethod.GET, responses)
        .securitySchemes(listOf(apiKey()))
        .securityContexts(listOf(securityContext()))
        .enableUrlTemplating(true)
        .globalRequestParameters(globalRequestParameters)
        .tags(tag)

    private fun apiKey(): ApiKey = ApiKey("mykey", "api_key", "header")

    private fun securityContext() = SecurityContext.builder()
        .securityReferences(defaultAuth())
        .forPaths(PathSelectors.regex("/anyPath.*"))
        .build()

    private fun defaultAuth(): List<SecurityReference?> {
        val scopes = AuthorizationScope("global", "accessEverything").let(::listOf).toTypedArray()
        return SecurityReference("mykey", scopes).let(::listOf)
    }
}