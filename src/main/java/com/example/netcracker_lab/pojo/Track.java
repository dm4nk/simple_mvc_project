package com.example.netcracker_lab.pojo;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Track extends BaseEntity implements Serializable {
    String name;
    String author;
    String album;
    Double duration;
    Genre genre;

    @Builder
    public Track(Integer id, String name, String author, String album, Double duration, Genre genre) {
        super(id);
        this.name = name;
        this.author = author;
        this.album = album;
        this.duration = duration;
        this.genre = genre;
    }
}
