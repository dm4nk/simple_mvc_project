package com.example.simple_mvc_project.pojo;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Genre extends BaseEntity implements Serializable {
    String name;

    @Builder
    public Genre(Integer id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
