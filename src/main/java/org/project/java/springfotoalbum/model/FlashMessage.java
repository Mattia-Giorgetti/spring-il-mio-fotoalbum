package org.project.java.springfotoalbum.model;

public class FlashMessage {

    public enum FleshMessageType {
        SUCCESS, ERROR
    }

    private FleshMessageType type;
    private String text;

    public FlashMessage(FleshMessageType type, String text) {
        this.type = type;
        this.text = text;
    }

    public FleshMessageType getType() {
        return type;
    }

    public void setType(FleshMessageType type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
