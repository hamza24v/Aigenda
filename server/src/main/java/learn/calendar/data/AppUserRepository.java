package learn.calendar.data;

import learn.calendar.models.AppUser;
import org.springframework.transaction.annotation.Transactional;

public interface AppUserRepository {

    @Transactional
    AppUser findByUsername(String username);

    @Transactional
    public AppUser findById(int appUserId);

}
