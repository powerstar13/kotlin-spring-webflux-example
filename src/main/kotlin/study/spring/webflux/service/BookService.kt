package study.spring.webflux.service

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux
import reactor.kotlin.core.publisher.toMono
import study.spring.webflux.service.dto.Book
import java.util.concurrent.atomic.AtomicInteger

@Service
class BookService {

    private final val nextId = AtomicInteger(0)

    val books: MutableList<Book> = mutableListOf(
        Book(id = nextId.incrementAndGet(), name = "코틀린 인 액션", price = 30000),
        Book(id = nextId.incrementAndGet(), name = "HTTP 완벽 가이드", price = 40000),
    )

    fun getAll(): Flux<Book> =
//        Flux.fromIterable(books)
        books.toFlux() // Flux의 확장함수로 간결하게 처리할 수도 있다.

    fun get(id: Int): Mono<Book> =
//        Mono.justOrEmpty(books.find { it.id == id })
        books.find { it.id == id }.toMono() // Mono의 확장함수로 간결하게 처리할 수 있다.

    fun add(request: Map<String, Any>): Mono<Book> =
        Mono.just(request)
            .map { map ->
                val book = Book(
                    id = nextId.incrementAndGet(),
                    name = map["name"].toString(),
                    price = map["price"] as Int
                )
                books.add(book)

                book
            }

    fun delete(id: Int): Mono<Void> =
        Mono.justOrEmpty(books.find { it.id == id })
            .map { books.remove(it) }
            .then()
}