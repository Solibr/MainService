package ru.gashev.test.advenjineering.projectmanager.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@NoArgsConstructor
public class Authority implements GrantedAuthority {

    @Id
    @Enumerated(EnumType.STRING)
    private AuthorityType authorityType;

    public Authority(AuthorityType authorityType) {
        this.authorityType = authorityType;
    }

    @Override
    public String getAuthority() {
        return authorityType.toString();
    }

    public AuthorityType getAuthorityType() {
        return authorityType;
    }

    @Override
    public int hashCode() {
        return authorityType.toString().hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other instanceof Authority)
            return ((Authority) other).authorityType.toString().equals(this.authorityType.toString());
        return false;
    }
}
