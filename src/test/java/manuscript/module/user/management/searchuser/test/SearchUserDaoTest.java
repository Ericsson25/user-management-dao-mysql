package manuscript.module.user.management.searchuser.test;

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

import manuscript.module.user.management.bean.Roles;
import manuscript.module.user.management.bean.SearchUser;
import manuscript.module.user.management.bean.User;
import manuscript.module.user.management.searchuser.SearchUserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = SearchUserDaoContext.class)
@Transactional
public class SearchUserDaoTest {

	@Autowired
	private SearchUserDao searchUserDao;

	private String firstName;
	private String lastName;
	private String email;

	@Before
	public void before() {
		firstName = "Dummy";
		lastName = "Dummy";
		email = "Dummy@gmail.com";
	}

	@Test
	public void getAllUserWithAuthorRole_test() {
		SearchUser searchUser = new SearchUser();
		searchUser.setRole(Roles.AUTHOR_ROLE);
		List<User> userList = searchUserDao.searchUsers(searchUser);
		Assert.assertNotNull(userList);
		Assert.assertTrue(userList.size() == 1);
	}

	@Test
	public void getAllUserUsingAllSearchParameter_test() {
		SearchUser searchUser = new SearchUser();
		searchUser.setRole(Roles.AUTHOR_ROLE);
		searchUser.setEmail(email);
		searchUser.setFirstName(firstName);
		searchUser.setLastName(lastName);
		List<User> userList = searchUserDao.searchUsers(searchUser);
		Assert.assertNotNull(userList);
		Assert.assertTrue(userList.size() == 1);
	}

	@Test
	public void getUserWithAdminRole_test() {
		SearchUser searchUser = new SearchUser();
		searchUser.setRole(Roles.ADMIN_ROLE);
		searchUser.setEmail(email);
		searchUser.setFirstName(firstName);
		searchUser.setLastName(lastName);
		List<User> userList = searchUserDao.searchUsers(searchUser);
		Assert.assertNotNull(userList);
		Assert.assertTrue(userList.size() == 0);
	}

}
