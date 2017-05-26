package manuscript.module.user.management.registration.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import manuscript.module.user.management.registration.UserRegistrationDao;
import manuscript.module.user.management.registration.UserRegistrationDaoImpl;
import manuscript.module.user.management.registration.mapper.UserRegistrationMapper;
import manuscript.test.dao.context.AbstractDaoTestContext;

@Configuration
@MapperScan("manuscript.module.user.management.registration.mapper")
public class UserRegistrationDaoContext extends AbstractDaoTestContext {

	@Autowired
	private UserRegistrationMapper userRegistrationMapper;

	@Bean
	public UserRegistrationDao getUserRegistrationDaoImpl() {
		return new UserRegistrationDaoImpl(userRegistrationMapper);
	}
}
