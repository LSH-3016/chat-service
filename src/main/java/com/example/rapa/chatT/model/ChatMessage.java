//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.rapa.chatT.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class ChatMessage {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String sender;
    private String content;
    private LocalDateTime timestamp;

    public static ChatMessageBuilder builder() {
        return new ChatMessageBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getSender() {
        return this.sender;
    }

    public String getContent() {
        return this.content;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setSender(final String sender) {
        this.sender = sender;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public void setTimestamp(final LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public ChatMessage() {
    }

    public ChatMessage(final Long id, final String sender, final String content, final LocalDateTime timestamp) {
        this.id = id;
        this.sender = sender;
        this.content = content;
        this.timestamp = timestamp;
    }

    public static class ChatMessageBuilder {
        private Long id;
        private String sender;
        private String content;
        private LocalDateTime timestamp;

        ChatMessageBuilder() {
        }

        public ChatMessageBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        public ChatMessageBuilder sender(final String sender) {
            this.sender = sender;
            return this;
        }

        public ChatMessageBuilder content(final String content) {
            this.content = content;
            return this;
        }

        public ChatMessageBuilder timestamp(final LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ChatMessage build() {
            return new ChatMessage(this.id, this.sender, this.content, this.timestamp);
        }

        public String toString() {
            return "ChatMessage.ChatMessageBuilder(id=" + this.id + ", sender=" + this.sender + ", content=" + this.content + ", timestamp=" + this.timestamp + ")";
        }
    }
}
