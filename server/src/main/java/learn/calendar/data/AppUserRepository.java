package learn.calendar.data;

import learn.calendar.models.AppUser;
import org.springframework.transaction.annotation.Transactional;

public interface AppUserRepository {
    @Transactional
    AppUser findById(int appUserId);
    @Transactional
    AppUser findByUsername(String username);

}
