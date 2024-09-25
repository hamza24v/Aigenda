package learn.calendar.security;


import learn.calendar.data.AppUserRepository;
import learn.calendar.data.CalendarRepository;
import learn.calendar.models.AppUser;
import learn.calendar.models.CalType;
import learn.calendar.models.Calendar;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;

@Service
public class AppUserService implements UserDetailsService {
    private final AppUserRepository userRepository;
    private final CalendarRepository calendarRepository;
    private final PasswordEncoder encoder;

    public AppUserService(AppUserRepository userRepository, CalendarRepository calendarRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.calendarRepository = calendarRepository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findByUsername(username);

        if (appUser == null || !appUser.isEnabled()) {
            throw new UsernameNotFoundException(username + "not found");
        }

        return appUser;
    }

    public AppUser create(String username, String password, AppUser user) {
        validate(username);
        validatePassword(password);

        password = encoder.encode(password);

        user = new AppUser(0, user.getFirstName(), user.getLastName(), user.getEmail(), username, password, false, List.of("USER"));
        AppUser result = userRepository.add(user);
        Calendar calendar = new Calendar(0,result.getFirstName() + " " + result.getLastName(), CalType.PERSONAL, result.getAppUserId());
        calendarRepository.add(calendar);
        return result;
    }

    private void validate(String username) {
        if (username == null || username.isBlank()) {
            throw new ValidationException("username is required");
        }

        if (username.length() > 50) {
            throw new ValidationException("username must be less than 50 characters");
        }
    }

    private void validatePassword(String password) {
        if (password == null || password.length() < 8) {
            throw new ValidationException("password must be at least 8 characters");
        }

        int digits = 0;
        int letters = 0;
        int others = 0;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                digits++;
            } else if (Character.isLetter(c)) {
                letters++;
            } else {
                others++;
            }
        }

        if (digits == 0 || letters == 0 || others == 0) {
            throw new ValidationException("password must contain a digit, a letter, and a non-digit/non-letter");
        }
    }
}
