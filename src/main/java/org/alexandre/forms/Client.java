package org.alexandre.forms;

public class Client {

    private Long id;
    private String name;
    private String cardToken;
    private Long value;

    public Client() {}

    private Client(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.cardToken = builder.cardToken;
        this.value = builder.value;
    }
    public static class Builder {

        private long id;
        private String name;
        private String cardToken;
        private long value;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }
        public Builder setName(String name) {
            this.name = name;
            return this;
        }
        public Builder setCardToken(String cardToken) {
            this.cardToken = cardToken;
            return this;
        }
        public Builder setValue(long value) {
            this.value = value;
            return this;
        }

        public Client build() {
            return new Client(this);
        }
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCardToken() {
        return cardToken;
    }
    public void setCardToken(String cardToken) {
        this.cardToken = cardToken;
    }

    public Long getValue() {
        return value;
    }
    public void setValue(Long value) {
        this.value = value;
    }
}
