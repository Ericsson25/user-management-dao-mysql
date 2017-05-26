package manuscript.module.user.management.academic.disciplines.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import manuscript.module.user.management.academic.disciplines.AcademicDisciplinesDao;
import manuscript.module.user.management.academic.disciplines.AcademicDisciplinesDaoImpl;
import manuscript.module.user.management.academic.disciplines.mapper.AcademicDisciplinesMapper;
import manuscript.test.dao.context.AbstractDaoTestContext;

@Configuration
@MapperScan("manuscript.module.user.management.academic.disciplines.mapper")
public class AcademicDisciplinesDaoContext extends AbstractDaoTestContext {

	@Autowired
	private AcademicDisciplinesMapper academicDisciplinesMapper;

	@Bean
	public AcademicDisciplinesDao getAcademicDisciplinesDao() {
		return new AcademicDisciplinesDaoImpl(academicDisciplinesMapper);
	}
}
