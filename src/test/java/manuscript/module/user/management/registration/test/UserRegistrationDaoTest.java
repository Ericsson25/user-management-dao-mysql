package manuscript.module.user.management.registration.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import manuscript.module.user.management.bean.AcademicDisciplines;
import manuscript.module.user.management.bean.AdditionalData;
import manuscript.module.user.management.bean.Password;
import manuscript.module.user.management.bean.Role;
import manuscript.module.user.management.bean.User;
import manuscript.module.user.management.registration.UserRegistrationDao;
import manuscript.module.user.management.request.UserRegistrationRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = UserRegistrationDaoContext.class)
@Transactional
public class UserRegistrationDaoTest {

	@Autowired
	private UserRegistrationDao userRegistrationDao;

	private String notReservedUserName;
	private String reservedUsername;
	private User user;
	private AdditionalData additionalData;
	private Password password;
	private AcademicDisciplines disciplines1;
	private AcademicDisciplines disciplines2;

	@Before
	public void before() {
		notReservedUserName = "notReserved";
		reservedUsername = "dummmy";

		user = new User();
		user.setUserName("TestUser");
		user.setTitle("Mr");
		user.setFirstName("FirstName");
		user.setLastName("LastName");
		user.setJob("job");
		user.setEmail("teszt@mail.com");

		password = new Password();
		password.setPassword("password");
		password.setPasswordAgain("password");

		disciplines1 = new AcademicDisciplines();
		disciplines1.setAcademicDisciplinesId("01");
		disciplines1.setAcademicDisciplinesName("dummy");

		disciplines2 = new AcademicDisciplines();
		disciplines2.setAcademicDisciplinesId("01001");
		disciplines2.setAcademicDisciplinesName("dummy2");

		additionalData = new AdditionalData();
		additionalData.setDefaultRoles(userRegistrationDao.getDefaultRole());
	}

	@Test
	public void isNameReserved_notReserved_test() {
		boolean isNameReserved = userRegistrationDao.isNameReserved(notReservedUserName);
		Assert.assertFalse("isNameReserved should false", isNameReserved);
	}

	@Test
	public void isNameReserved_reserved_test() {
		boolean isNameReserved = userRegistrationDao.isNameReserved(reservedUsername);
		Assert.assertTrue("isNameReserved should true", isNameReserved);
	}

	@Test
	public void getDefaultRole_test() {
		List<Role> roleList = new ArrayList<Role>();
		roleList = userRegistrationDao.getDefaultRole();

		Assert.assertNotNull("roleList must be not null", roleList);
		Assert.assertTrue(roleList.size() >= 2);
	}

	@Test
	public void createRegistration_test() {
		UserRegistrationRequest request = new UserRegistrationRequest();
		request.setUser(user);
		request.setPassword(password);
		List<AcademicDisciplines> disciplinesList = new ArrayList<AcademicDisciplines>();
		disciplinesList.add(disciplines1);
		disciplinesList.add(disciplines2);
		request.setAcademicDisciplines(disciplinesList);

		userRegistrationDao.createRegistration(request, additionalData);
	}
}
