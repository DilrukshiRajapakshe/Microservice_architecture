package com.adams.zullrouting.controller.Impl;
import com.adams.zullrouting.config.AdminUserIDService;
import com.adams.zullrouting.config.JWTWebService;
import com.adams.zullrouting.controller.LoginController;
import com.adams.zullrouting.module.MoreUsers;
import com.adams.zullrouting.module.Users_Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/adams/api/v1")
public class LoginControllerImpl implements LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTWebService jwtConfig;
    @Autowired
    private AdminUserIDService adminUserIDService;
    static String jwt_Token = "";


    @PostMapping(value ="/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public ResponseEntity<?> Login(@RequestBody MoreUsers authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("The incorrect username or password", e);
        }
        final UserDetails userDetails = adminUserIDService
                .loadUserByUsername(authenticationRequest.getEmail());

        final String jwt = jwtConfig.generate_Token(userDetails);
        jwt_Token = "Bearer " + jwt;
        return ResponseEntity.ok(new Users_Authentication(jwt_Token));
    }

    @GetMapping("/correct")
    public String check(){
        return "testServer(proxy of zuul)";
    }
}
