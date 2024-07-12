package com.example.rewardservice.user.domain;

import com.example.rewardservice.common.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.example.rewardservice.user.domain.MemberState.ACTIVE;
import static jakarta.persistence.EnumType.STRING;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_name")
    private String name;

    @Column(name = "user_Nick_Name")
    private String nickname;

    @Column(name = "user_total_point")
    private long totalPoint;

    @Column(name = "profile_image_url", nullable = false)
    private String profileImageUrl;

    @Enumerated(value = STRING)
    private MemberState memberState;

    @Column(name = "user_social")
    private boolean userSocial;

    @Column(name = "last_update_date")
    private LocalDateTime lastUpdateDate;

    @Column(name = "last_modified_date")
    private LocalDateTime modifiedDate;


    public User(final String email, final String userPassword,
                final String userName, final long totalPoint, final String profileImageUrl, final String nickname ) {
        this.email = email;
        this.password = userPassword;
        this.name = userName;
        this.totalPoint = totalPoint;
        this.profileImageUrl = profileImageUrl;
        this.nickname = nickname;
        this.memberState = ACTIVE;
        this.userSocial = false;
        this.lastUpdateDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
    }

    public void earnPoints(long points) {
        this.totalPoint += points;
        this.lastUpdateDate = LocalDateTime.now();
    }

    public void usePoints(long points) {
        if(this.totalPoint < points) {
            throw new IllegalArgumentException("insufficient points");
        }
        this.totalPoint -= points;
        this.lastUpdateDate = LocalDateTime.now();
    }

    public void changePassword(String upw) {
        this.password = upw;
    }

    public User updateProfileImageUrl(String newProfileImageUrl) {
        return new User(this.email, this.password, this.name,this.totalPoint,this.nickname, newProfileImageUrl);
    }

}
