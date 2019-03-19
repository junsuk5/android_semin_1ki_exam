package com.example.recyclerviewexam.eventbus.events;

public class ItemClickEvent {
    public int color;

    public ItemClickEvent(int color) {
        this.color = color;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ItemClickEvent{");
        sb.append("color=").append(color);
        sb.append('}');
        return sb.toString();
    }
}
