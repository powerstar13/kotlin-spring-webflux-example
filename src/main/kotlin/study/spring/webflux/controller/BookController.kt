package study.spring.webflux.controller

import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import study.spring.webflux.service.BookService
import study.spring.webflux.service.dto.Book

@RestController
class BookController(
    private val bookService: BookService,
) {
    @GetMapping("/books")
    fun getAll(): Flux<Book> =
        bookService.getAll()

    @GetMapping("/books/{id}")
    fun get(@PathVariable id: Int): Mono<Book> =
        bookService.get(id)

    @PostMapping("/books")
    fun get(@RequestBody request: Map<String, Any>): Mono<Book> =
        bookService.add(request)

    @DeleteMapping("/books/{id}")
    fun delete(@PathVariable id: Int): Mono<Void> =
        bookService.delete(id)
}