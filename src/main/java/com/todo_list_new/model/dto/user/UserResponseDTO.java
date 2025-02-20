package com.todo_list_new.model.dto.user;

public class UserResponseDTO {
    private int id;
    private String name;
    private String email;

    public UserResponseDTO(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
    }

    public UserResponseDTO() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private int id;
        private String name;
        private String email;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }
        public UserResponseDTO build() {

            return new UserResponseDTO(this);
        }

        @Override
        public String toString() {
            return "Builder{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
    }


}
