package com.example.netcracker_lab.view;

import com.example.netcracker_lab.pojo.Genre;
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
