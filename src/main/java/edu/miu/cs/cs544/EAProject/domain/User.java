package edu.miu.cs.cs544.EAProject.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Entity @NoArgsConstructor
@Table(name = "`User`")
public class User implements UserDetails {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private int id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @JoinTable(name = "user_role")
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

    private boolean isActive = true;

    @Embedded
    private Audit audit;

    public User(String username, String password, Collection<Role> roles, boolean isActive) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.isActive = isActive;
    }

    public Collection<Role> getRoles() {
        return Optional.ofNullable(roles).orElse(List.of());
    }

    public void addRole(Role role) {

        List<Role> roles = new ArrayList<>(getRoles());
        roles.add(role);
        setRoles(roles);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Optional.ofNullable(roles).orElse(List.of()).stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
    }

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
        return isActive;
    }
}
