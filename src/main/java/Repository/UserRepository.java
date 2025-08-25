package Repository;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

  interface UserRepository  extends JpaRepository<User, Long> {
}
