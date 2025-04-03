package org.alexandre.forms;

public class Client {

    private Long id;            //Identificador único para o cliente
    private String name;        //Nome do cliente
    private String cardToken;   //Token do cartão
    private Long value;         //Valor do saldo

    //Construtor vazio
    public Client() {}

    //Construtor privado - padrão Builder
    private Client(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.cardToken = builder.cardToken;
        this.value = builder.value;
    }
    //Classe estática - padrão Builder
    public static class Builder {

        private Long id;            //Identificador para o cliente
        private String name;        //Nome do cliente no builder
        private String cardToken;   //Token do cartão no builder
        private Long value;         //Valor de saldo no builder

        //Define o identificador do cliente no builder
        public Builder setId(Long id) {
            this.id = id;
            return this;    //Retorna o próprio builder para encadeamento
        }
        //Define o nome do cliente no builder
        public Builder setName(String name) {
            this.name = name;
            return this;    //Retorna o próprio builder para encadeamento
        }
        //Define o token do cartão associado ao cliente no builder
        public Builder setCardToken(String cardToken) {
            this.cardToken = cardToken;
            return this;    //Retorna o próprio builder para encadeamento
        }
        //Define o saldo associado ao cliente no builder
        public Builder setValue(long value) {
            this.value = value;
            return this;    //Retorna o próprio builder para encadeamento
        }

        //Método final que retorna uma instância de Client
        public Client build() {
            //Passa os atributos para o construtor privado
            return new Client(this);
        }
    }

    //Getter e Setter para o ID do cliente
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    //Getter e Setter para o nome do cliente
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    //Getter e Setter para o token do cartão
    public String getCardToken() {
        return cardToken;
    }
    public void setCardToken(String cardToken) {
        this.cardToken = cardToken;
    }

    //Getter e Setter para o saldo associado ao cliente
    public Long getValue() {
        return value;
    }
    public void setValue(Long value) {
        this.value = value;
    }
}
