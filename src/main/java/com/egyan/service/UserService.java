package com.egyan.service;

import com.egyan.entity.User;
import com.egyan.exception.EmailAlreadyExistsException;
import com.egyan.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.egyan.service.OtpService;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final OtpService otpService;

    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder passwordEncoder,
                       EmailService emailService,
                       OtpService otpService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.otpService = otpService;
    }


    public User saveUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email already registered");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User saved = userRepository.save(user);

        if ("STUDENT".equalsIgnoreCase(saved.getRole())) {
            emailService.sendStudentWelcome(saved.getEmail(), saved.getName());
        } else if ("FACULTY".equalsIgnoreCase(saved.getRole())) {
            emailService.sendFacultyWelcome(saved.getEmail(), saved.getName());
        }

        return saved;
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    public void sendOtp(String email) {
        String otp = otpService.generateOtp(email);
        emailService.sendOtpEmail(email, otp);
    }

    public boolean verifyOtp(String email, String otp) {
        return otpService.verifyOtp(email, otp);
    }
}