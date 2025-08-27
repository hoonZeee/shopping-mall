package com.example.demo.repository.user.entity;

import com.example.demo.repository.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    private Integer id;

    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    private String name;
    private LocalDateTime createdAt;

    @Transient
    private List<SimpleGrantedAuthority> authorities = SIMPLE_ROLES;
    public final static SimpleGrantedAuthority ROLE_USER = new SimpleGrantedAuthority("ROLE_USER");
    public final static SimpleGrantedAuthority ROLE_ADMIN = new SimpleGrantedAuthority("ROLE_ADMIN");
    public final static List<SimpleGrantedAuthority> SIMPLE_ROLES = List.of(ROLE_USER);
    public final static List<SimpleGrantedAuthority> ADMIN_ROLES = List.of(ROLE_USER, ROLE_ADMIN);


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Product> products;

    public static User create(String username, String password, String name){
        return new User(
                null,
                username,
                password,
                name,
                LocalDateTime.now(),
                SIMPLE_ROLES,
                null

        );
    }


}
