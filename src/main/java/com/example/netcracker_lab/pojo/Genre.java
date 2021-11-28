package com.example.netcracker_lab.pojo;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Genre extends BaseEntity implements Serializable {
    String name;

    @Builder
    public Genre(Integer id, String name) {
        super(id);
        this.name = name;
    }
}
