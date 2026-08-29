package com.devstack.SmartDine.service;

import com.devstack.SmartDine.dtos.req.ForgotPasswordRequestDto;
import com.devstack.SmartDine.dtos.req.ResetPasswordRequestDto;

public interface PasswordService {
    void forgotPassword(ForgotPasswordRequestDto dto);
    void resetPassword(ResetPasswordRequestDto dto);
}
