package com.example.simple_mvc_project.view;

import com.example.simple_mvc_project.pojo.Genre;
import javafx.collections.ObservableListBase;

import java.util.List;

public class GenreObservableList extends ObservableListBase<Genre> {

    private final List<Genre> genres;

    public GenreObservableList(List<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public Genre get(int index) {
        return genres.get(index);
    }

    @Override
    public int size() {
        return genres.size();
    }
}
