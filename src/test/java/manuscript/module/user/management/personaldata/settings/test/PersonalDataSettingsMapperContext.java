package manuscript.module.user.management.personaldata.settings.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import manuscript.module.user.management.personaldata.settings.PersonalDataSettingsDao;
import manuscript.module.user.management.personaldata.settings.PersonalDataSettingsDaoImpl;
import manuscript.module.user.management.personaldata.settings.mapper.PersonalDataSettingsMapper;
import manuscript.test.annotation.IgnoreTestContext;
import manuscript.test.dao.context.AbstractDaoTestContext;

@IgnoreTestContext
@Configuration
@MapperScan("manuscript.module.user.management.personaldata.settings.mapper")
public class PersonalDataSettingsMapperContext extends AbstractDaoTestContext{

	@Autowired
	private PersonalDataSettingsMapper personalDataSettingsMapper;
	
	@Bean
	public PersonalDataSettingsDao getPersonalDataSettingsDao(){
		return new PersonalDataSettingsDaoImpl(personalDataSettingsMapper);
	}

}
