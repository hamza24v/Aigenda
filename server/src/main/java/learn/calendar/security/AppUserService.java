package learn.calendar.security;


import learn.calendar.data.AppUserRepository;
import learn.calendar.data.CalendarRepository;
import learn.calendar.data.UserCalendarRolesRepository;
import learn.calendar.domain.Result;
import learn.calendar.domain.ResultType;
import learn.calendar.domain.Validations;
import learn.calendar.models.AppUser;
import learn.calendar.models.CalType;
import learn.calendar.models.Calendar;
import learn.calendar.models.UserCalendarRoles;
import org.springframework.security.core.parameters.P;
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
    private final UserCalendarRolesRepository userCalendarRolesRepository;
    private final PasswordEncoder encoder;

    public AppUserService(AppUserRepository userRepository, CalendarRepository calendarRepository, UserCalendarRolesRepository userCalendarRolesRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.calendarRepository = calendarRepository;
        this.userCalendarRolesRepository = userCalendarRolesRepository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findByUsername(username);

        if (appUser == null || !appUser.isEnabled()) {
            throw new UsernameNotFoundException(username + "not found");
        }


        System.out.println(appUser.getRoles());
        return appUser;
    }

    public Result<AppUser> create(String username, String password, AppUser user) {
        validate(username);
        validatePassword(password);
        password = encoder.encode(password);

        user = new AppUser(0, user.getFirstName(), user.getLastName(), user.getEmail(), username, password, false, List.of("USER"));
        Result<AppUser> result = validateUser(user);
        if (!result.isSuccess()) {
            return result;
        }
        if (user.getAppUserId() != 0) {
            result.addMessage("userId cannot be set for 'add' operation", ResultType.INVALID);
            return result;
        }
        user = userRepository.add(user);
        result.setPayload(user);
        Calendar calendar = new Calendar(0,user.getFirstName() + " " + user.getLastName(), CalType.PERSONAL, user.getAppUserId());
        calendarRepository.add(calendar);
        UserCalendarRoles userCalendarRole = new UserCalendarRoles(0,user.getAppUserId(), calendar.getCalendarId(), 3);
        userCalendarRolesRepository.add(userCalendarRole);
        return result;
    }

    public boolean deleteById(int userId) {
        return userRepository.deleteUserById(userId);

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

    private Result<AppUser> validateUser(AppUser user) {
        Result<AppUser> result = new Result<>();
        if (user == null) {
            result.addMessage("user cannot be null", ResultType.INVALID);
            return result;
        }
        if (Validations.isNullOrBlank(user.getFirstName())) {
            result.addMessage("firstName is required", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(user.getLastName())) {
            result.addMessage("lastName is required", ResultType.INVALID);
        }
        if (Validations.isNullOrBlank(user.getEmail())) {
            result.addMessage("email is required", ResultType.INVALID);
        }

        return result;
    }
}
