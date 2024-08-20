package com.example.spuelmaschine.domain.user;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class User implements UserDetails {

    @Id
    @Column(nullable = false)
    @GeneratedValue(generator = "client_id")
    private UUID id;

    @Column(unique=true)
    private String username;

    // The user's password
    private String password;

    private String name;

    public void setName(String name) {
        this.name = name.trim();
    }

    public void setUsername(String username) {
        this.username = username.trim();
    }


    /* --------------------------------- */
    /* UserDetails interface methods */

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

/*  Problem:
    getAuthorities() in der Klasse User gibt momentan null zurück. Wenn du Rollen oder Berechtigungen nutzen möchtest, solltest du eine Liste oder Sammlung von GrantedAuthority zurückgeben, andernfalls könnte es zu Problemen bei der Benutzerauthentifizierung kommen.
    Lösung:
    Falls du keine Autoritäten hast, die du verwalten möchtest, kannst du entweder eine leere Liste zurückgeben oder eine richtige Implementierung hinzufügen, die die Rollen des Benutzers zurückgibt.
    Mögliche Implementierung:
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // Wenn keine Rollen/Berechtigungen verwendet werden
    }*/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

