package io.matheus.studingwebflux.service.impl;

import io.matheus.studingwebflux.document.Playlist;
import io.matheus.studingwebflux.repository.PlaylistRepository;
import io.matheus.studingwebflux.service.PlaylistService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlaylistServiceImpl implements PlaylistService {
    private final PlaylistRepository playlistRepository;

    public PlaylistServiceImpl(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @Override
    public Flux<Playlist> findAll() {
        return this.playlistRepository.findAll();
    }

    @Override
    public Mono<Playlist> findById(String id) {
        return this.playlistRepository.findById(id);
    }

    @Override
    public Mono<Playlist> save(Playlist playlist) {
        return this.playlistRepository.save(playlist);
    }
}
