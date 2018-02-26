package com.amidezcod.impulse2k18.modal;


public class CardItem {

    private int imageId;
    private String text;
    private String quotes;

    public CardItem(int imageId, String text, String quotes, EventDetailsInfo eventDetailsInfo) {
        this.imageId = imageId;
        this.text = text;
        this.quotes = quotes;
        this.eventDetailsInfo = eventDetailsInfo;
    }

    public EventDetailsInfo getEventDetailsInfo() {
        return eventDetailsInfo;
    }

    public void setEventDetailsInfo(EventDetailsInfo eventDetailsInfo) {
        this.eventDetailsInfo = eventDetailsInfo;
    }

    private EventDetailsInfo eventDetailsInfo;


    public int getImageId() {
        return this.imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getQuotes() {
        return quotes;
    }

    public void setQuotes(String quotes) {
        this.quotes = quotes;
    }

}
