package com.example.recyclerviewexam.kotlinexam;

import java.util.Objects;

public class ItemJava {
    private String title;
    private int id;

    public ItemJava() {
        ItemKotlin itemKotlin = new ItemKotlin("제목", 1);
        itemKotlin.setTitle("제목2");
    }

    public ItemJava(String title) {
        this.title = title;
    }

    public ItemJava(int id) {
        this.id = id;
    }

    public ItemJava(String title, int id) {
        this.title = title;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ItemJava{");
        sb.append("title='").append(title).append('\'');
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemJava itemJava = (ItemJava) o;
        return id == itemJava.id &&
                Objects.equals(title, itemJava.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, id);
    }
}
