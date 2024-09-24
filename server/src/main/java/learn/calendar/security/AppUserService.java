//package learn.calendar.security;
//
//
//import learn.calendar.data.AppUserRepository;
//import learn.calendar.models.AppUser;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AppUserService implements UserDetailsService {
//    private final AppUserRepository repository;
//    private final PasswordEncoder encoder;
//
//    public AppUserService(AppUserRepository repository, PasswordEncoder encoder) {
//        this.repository = repository;
//        this.encoder = encoder;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        AppUser appUser = repository.findByUsername(username);
//
//        if (appUser == null || !appUser.isEnabled()) {
//            throw new UsernameNotFoundException(username + "not found");
//        }
//
//        return appUser;
//    }
//}
