package com.aquaone.srv.login.domain.user;

public record UserUpdateDTO(String id, String nome, String password, String description, String cargo, String email, UserRole admin ) {
}