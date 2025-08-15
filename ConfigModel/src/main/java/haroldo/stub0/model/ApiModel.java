package haroldo.stub0.model;

import java.util.ArrayList;
import java.util.List;

public class ApiModel {
    List<MessageModel> messages = new ArrayList<>();
    private String uri;
    private String name;
    private int maxConcurrency;

    public void addMessage(MessageModel message) {
        messages.add(message);
    }

    public List<MessageModel> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageModel> messages) {
        this.messages = messages;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxConcurrency() {
        return maxConcurrency;
    }

    public void setMaxConcurrency(int maxConcurrency) {
        this.maxConcurrency = maxConcurrency;
    }
}
