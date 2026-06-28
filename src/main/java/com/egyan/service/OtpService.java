
package com.egyan.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpService {

    // stores email -> otp
    private final Map<String, String> otpStore = new HashMap<>();

    public String generateOtp(String email) {
        String otp = String.valueOf(new Random().nextInt(900000) + 100000); // 6 digit
        otpStore.put(email, otp);
        return otp;
    }

    public boolean verifyOtp(String email, String otp) {
        String stored = otpStore.get(email);
        if (stored != null && stored.equals(otp)) {
            otpStore.remove(email); // remove after verified
            return true;
        }
        return false;
    }
}