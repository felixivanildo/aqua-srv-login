package com.aquaone.srv.login.domain.user;

public record UserRequestDTO(String nome, String password, String description, String cargo, String email, UserRole admin ) {
}