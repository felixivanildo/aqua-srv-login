package com.aquaone.srv.login.domain.user;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;


import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Table(name= "users")
@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements  UserDetails{
    @Id
    @GeneratedValue
    private UUID id;

    private String nome;

    @Column(name = "password")
    private String password;
    private String description;
    private String cargo;
    private String email;
    private LocalDate date;
    private UserRole admin;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.admin == UserRole.ADMIN) return  List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }
    

    @Override
    public String getUsername() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }
    public String getNome(){return nome;}

    public void setPassword(String password){
        this.password = password;
    }


    public void setDescription(String description){
        this.description = description;
    }

    public void setCargo(String cargo){
        this.cargo = cargo;
    }

    public void setEmail(String email){
        this.email = email;
    }


    public void setDate(LocalDate date){
        this.date = date;
    }

    public void setAdmin(UserRole admin){
        this.admin = admin;
    }
}
