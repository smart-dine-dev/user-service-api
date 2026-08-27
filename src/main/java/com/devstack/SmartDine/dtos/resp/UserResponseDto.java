package com.devstack.SmartDine.dtos.resp;

import com.devstack.SmartDine.entity.Role;
import com.devstack.SmartDine.entity.enums.AuthProvider;
import com.devstack.SmartDine.entity.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private UUID id;
    private String firstName;
    private String lastname;
    private String email;
    private String phoneNumber;
    private String profilePictureUrl;
    private UserStatus status;
    private AuthProvider provider;
    private boolean emailVerified;
    private boolean phoneVerified;
    private boolean twoFactorEnabled;
    private Set<String> roles;
}
