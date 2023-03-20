package com.msaggik.sixthlessonnotebook.model;

public class Notebook {

    // поля сущности
    private String id; // поле идентификатора записи в блокноте
    private String title; // поле заголовка записи в блокноте
    private String description; // поле описания записи в блокноте

    // конструктор
    public Notebook(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    // геттеры и сеттеры
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
