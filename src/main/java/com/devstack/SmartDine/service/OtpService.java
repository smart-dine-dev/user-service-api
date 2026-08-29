package com.devstack.SmartDine.service;

import com.devstack.SmartDine.dtos.req.OtpVerifyRequestDto;

public interface OtpService {
    void sendOtp(String email);
    void verifyOtp(OtpVerifyRequestDto dto);
    void validateOtp(String email);
}
