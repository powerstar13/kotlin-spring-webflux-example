package study.spring.webflux.hanlder

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import study.spring.webflux.hanlder.dto.User

@Component
class UserHandler {

    val users = listOf(
        User(id = 1, email = "user1@gmail.com"),
        User(id = 2, email = "user2@gmail.com"),
    )

    fun getUser(request: ServerRequest): Mono<ServerResponse> =
        users.find { request.pathVariable("id").toLong() == it.id }
            ?.let {
                ServerResponse.ok()
                    .bodyValue(it)
            }
            ?: ServerResponse.notFound()
                .build()

    fun getAll(request: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok()
            .bodyValue(users)
}