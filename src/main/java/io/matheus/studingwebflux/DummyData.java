package io.matheus.studingwebflux;

import io.matheus.studingwebflux.document.Playlist;
import io.matheus.studingwebflux.repository.PlaylistRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Component
public class DummyData implements CommandLineRunner {
    private final PlaylistRepository playListRepository;

    public DummyData(PlaylistRepository playlistRepository) {
        this.playListRepository = playlistRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        playListRepository.deleteAll()
                .thenMany(
                        Flux.just("API REST Spring Boot", "Deploy de uma aplicação java no IBM Cloud", "Java 8",
                                        "Github", "Spring Security", "Web Service RESTFULL", "Bean no Spring Framework"
                                )
                                .map(name -> new Playlist(name, UUID.randomUUID().toString()))
                                .flatMap(playListRepository::save
                                )).subscribe(System.out::println);
    }
}
