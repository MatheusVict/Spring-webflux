package io.matheus.studingwebflux.controller;

import io.matheus.studingwebflux.document.Playlist;
import io.matheus.studingwebflux.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;

import static reactor.core.publisher.Flux.interval;

@RestController
@RequestMapping("/playlist")
public class PlayListController {

    private final PlaylistService service;

    public PlayListController(PlaylistService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Flux<Playlist>> getPlayList() {
        return ResponseEntity.ok(this.service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<Playlist>> getPlayListById(@PathVariable String id) {
        return ResponseEntity.ok(this.service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Mono<Playlist>> savePlayList(@RequestBody Playlist playlist) {
        return ResponseEntity.ok(this.service.save(playlist));
    }

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Tuple2<Long, Playlist>> getPlaylistByEvents() {

        Flux<Long> interval = interval(Duration.ofSeconds(10));
        Flux<Playlist> events = service.findAll();
        System.out.println("Passou aqui events");
        return Flux.zip(interval, events);
    }
}
