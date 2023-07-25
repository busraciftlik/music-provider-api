package com.atmosware.busraciftlik.music.provider.config;

import com.atmosware.busraciftlik.music.provider.entity.Album;
import com.atmosware.busraciftlik.music.provider.entity.Artist;
import com.atmosware.busraciftlik.music.provider.entity.Music;
import com.atmosware.busraciftlik.music.provider.enums.Genre;
import com.atmosware.busraciftlik.music.provider.service.impl.ArtistServiceImpl;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Configuration
public class LoadInitialData {
    private final Faker faker = Faker.instance();
    private final Random random = new Random();

    private static final int ARTIST_NUMBER = 10;
    private static final int ALBUM_NUMBER = ARTIST_NUMBER / 2;
    private static final int MUSIC_NUMBER = ARTIST_NUMBER;


    @Bean
    CommandLineRunner initDatabase(ArtistServiceImpl artistService) {

        return args -> {
            if (artistService.count() <= 0) {
                List<Artist> artists = generateRandomArtists();
                artistService.saveAllForJpaRepository(artists);
            }
        };
    }

    private List<Artist> generateRandomArtists() {
//        log.info("generateRandomArtists is started");
        List<Artist> artistList = new ArrayList<>();
        for (int i = 0; i < ARTIST_NUMBER; i++) {
            Artist artist = new Artist();
            artist.setName(faker.artist().name());
            initRandomMusics(artist);
            initRandomAlbums(artist.getMusics(), artist);
            artistList.add(artist);
        }
        return artistList;
    }

    private void initRandomAlbums(Set<Music> musics, Artist artist) {
        for (int i = 0; i < ALBUM_NUMBER; i++) {
            Album album = new Album();
            album.setReleaseDate(faker.date().past(random.nextInt(1, 3650), TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            album.setName(faker.rickAndMorty().location());
            Set<Music> musicSet = randomSubset(musics);
            for (Music music : musicSet) {
                // Optional.ofNullable(music.getAlbum()).ifPresent(it-> album.addToMusics(music));
                if (music.getAlbum() == null) {
                    album.addToMusics(music);
                }
            }
            if (!album.getMusics().isEmpty()) {
                artist.addToAlbums(album);
            }
        }
    }

    private void initRandomMusics(Artist artist) {
        int musics = random.nextInt(MUSIC_NUMBER);
        for (int i = 0; i < musics; i++) {
            Music build = Music.builder().genre(Genre.values()[random.nextInt(Genre.values().length)])
                    .name(faker.name().lastName())
                    .build();
            artist.addToMusics(build);
        }
    }

    private Set<Music> randomSubset(Set<Music> musicSet) {
        Set<Music> randomSubset = new HashSet<>();
        if (musicSet.size() < 4) return randomSubset;
        List<Music> tempList = new ArrayList<>(musicSet);
        int subsetSize = random.nextInt(3, Math.min(10, musicSet.size()));

        while (randomSubset.size() < subsetSize) {
            int randomIndex = random.nextInt(tempList.size());
            randomSubset.add(tempList.get(randomIndex));
            tempList.remove(randomIndex);
        }
        return randomSubset;
    }

}
