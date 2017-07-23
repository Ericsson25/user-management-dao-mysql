package manuscript.module.user.management.searchuser.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import manuscript.module.user.management.searchuser.SearchUserDao;
import manuscript.module.user.management.searchuser.SearchUserDaoImpl;
import manuscript.module.user.management.searchuser.mapper.SearchUserMapper;
import manuscript.test.dao.context.AbstractDaoTestContext;

@Configuration
@MapperScan("manuscript.module.user.management.searchuser.mapper")
public class SearchUserDaoContext extends AbstractDaoTestContext {

	@Autowired
	private SearchUserMapper searchUserMapper;

	@Bean
	public SearchUserDao getSearchUserDaoImpl() {
		return new SearchUserDaoImpl(searchUserMapper);
	}
}
