package com.example.netcracker_lab.pojo;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Track extends BaseEntity implements Serializable {
    String name;
    String author;
    String album;
    Long duration;

    @Builder
    public Track(Long id, String name, String author, String album, Long duration) {
        super(id);
        this.name = name;
        this.author = author;
        this.album = album;
        this.duration = duration;
    }
}
