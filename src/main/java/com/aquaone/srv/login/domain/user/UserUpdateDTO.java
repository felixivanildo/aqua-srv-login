package com.aquaone.srv.login.domain.user;

public record UserUpdateDTO(String userId, String nome, String password, String description, String cargo, String email, UserRole admin ) {
}