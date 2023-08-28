package com.example.blogsystem.Controller;


import com.example.blogsystem.ApiResponse.ApiResponse;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Service.AuthServiec;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {


    private final AuthServiec authServiec;

    @PostMapping("/regester")
    public ResponseEntity Regester(@RequestBody @Valid User user) {
        authServiec.regester(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added"));

    }

    @PostMapping("/logout")
    public ResponseEntity Logoout() {
        return ResponseEntity.status(200).body(new ApiResponse("Log out successful"));

    }

}
