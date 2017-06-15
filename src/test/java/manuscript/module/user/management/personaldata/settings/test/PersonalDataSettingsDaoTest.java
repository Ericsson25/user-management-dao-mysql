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
import manuscript.module.user.management.request.UpdatePassword;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = PersonalDataSettingsDaoContext.class)
@Transactional
public class PersonalDataSettingsDaoTest {

	@Autowired
	private PersonalDataSettingsDao personalDataSettingsDao;

	private String userIdWithoutUserData;
	private String userIdWithUserData;

	private SavePersonalDataRequest savePersonalDataRequest;
	private UpdatePassword updatePassword;

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

		updatePassword = new UpdatePassword();
		updatePassword.setEncryptedPassword("$2a$10$c37LKF/Y5tDFIMjVqp");
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

	@Test
	public void updatePersonalData_test() {
		personalDataSettingsDao.updatePersonalData(savePersonalDataRequest, userIdWithUserData);// update

		User user = personalDataSettingsDao.getUserData(userIdWithUserData);
		Assert.assertTrue(savePersonalDataRequest.getEmail().equals(user.getEmail()));
		Assert.assertTrue(savePersonalDataRequest.getFirstName().equals(user.getFirstName()));
		Assert.assertTrue(savePersonalDataRequest.getJob().equals(user.getJob()));
		Assert.assertTrue(savePersonalDataRequest.getLastName().equals(user.getLastName()));
		Assert.assertTrue(savePersonalDataRequest.getTitle().equals(user.getTitle()));
	}

	@Test
	public void getPasswordByUserId_without_result_test() {
		String password = personalDataSettingsDao.getPasswordByUserId(userIdWithoutUserData);

		Assert.assertNull(password);
	}

	@Test
	public void getPasswordByUserId_with_result_test() {
		String password = personalDataSettingsDao.getPasswordByUserId(userIdWithUserData);

		Assert.assertNotNull(password);
	}

	@Test
	public void updatePassword_with_result() {
		updatePassword.setUserId(userIdWithUserData);
		personalDataSettingsDao.updatePassword(updatePassword);// update

		String password = personalDataSettingsDao.getPasswordByUserId(userIdWithUserData);
		Assert.assertNotNull(password);
		Assert.assertTrue(password.equals(updatePassword.getEncryptedPassword()));

	}
}
