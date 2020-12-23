package kz.bagdat.sample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.context.support.beans
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
@EnableAsync
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args) {
        addInitializers(beans())
    }
}

fun beans() = beans {
    bean("messageSource") {
        ReloadableResourceBundleMessageSource().apply {
            setBasename("classpath:messages")
            setDefaultEncoding("UTF-8")
        }
    }
}
