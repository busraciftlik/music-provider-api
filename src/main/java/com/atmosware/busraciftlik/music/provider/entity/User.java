package com.atmosware.busraciftlik.music.provider.entity;

import com.atmosware.busraciftlik.music.provider.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET status = 'INACTIVE' WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "status <> 'INACTIVE'")
public class User extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue
    private Integer id;
    private String firstname;
    private String lastname;
    @Column(unique=true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Favorite> favorites = new HashSet<>();
    @ManyToMany
    private Set<User> followers = new HashSet<>();
    @ManyToMany
    private Set<User> followings = new HashSet<>();
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private Set<Playlist> playlists = new HashSet<>();


    public void addFollowing(User following){
        followings.add(following);
        following.followers.add(this);

    }
    public void addFollowers(User follower){
        followers.add(follower);
        follower.followings.add(this);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
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
        return true;
    }
}