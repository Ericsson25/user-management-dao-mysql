package manuscript.module.user.management.personaldata.settings.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import manuscript.module.user.management.bean.User;
import manuscript.module.user.management.exception.UserNotFoundException;
import manuscript.module.user.management.personaldata.settings.PersonalDataSettingsDao;
import manuscript.module.user.management.request.SavePersonalDataRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = PersonalDataSettingsDaoContext.class)
@Transactional
public class PersonalDataSettingsDaoTest {

	@Autowired
	private PersonalDataSettingsDao personalDataSettingsDao;

	private String userIdWithoutUserData;
	private String userIdWithUserData;

	private SavePersonalDataRequest savePersonalDataRequest;

	@Before
	public void before() {
		userIdWithoutUserData = "2";
		userIdWithUserData = "1";

		savePersonalDataRequest = new SavePersonalDataRequest();
		savePersonalDataRequest.setEmail("MsDummy@gmail.com");
		savePersonalDataRequest.setFirstName("MsDummy");
		savePersonalDataRequest.setJob("MsDummy dummy things");
		savePersonalDataRequest.setLastName("MsDummy");
		savePersonalDataRequest.setTitle("Ms");
	}

	@Test(expected = UserNotFoundException.class)
	public void getUserData_wtih_UserNotFoundException_result() {
		personalDataSettingsDao.getUserData(userIdWithoutUserData);
	}

	@Test
	public void getUserData_wtih_normal_result() {
		User user = personalDataSettingsDao.getUserData(userIdWithUserData);

		Assert.assertNotNull("User must not be null", user);
		Assert.assertTrue("The userName must be dummmy", ("dummmy").equals(user.getUserName()));
	}
}
