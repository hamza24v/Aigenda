package learn.calendar.data;

import learn.calendar.models.AppUser;
import org.springframework.transaction.annotation.Transactional;

public interface AppUserRepository {

    AppUser add(AppUser appUser);

    @Transactional
    AppUser findByUsername(String username);

    @Transactional
    public AppUser findById(int appUserId);

    boolean deleteUserById(int userId);
}
