package manuscript.module.user.management.searchuser;

import java.util.List;

import org.springframework.stereotype.Repository;

import manuscript.module.user.management.bean.SearchUser;
import manuscript.module.user.management.bean.User;
import manuscript.module.user.management.searchuser.mapper.SearchUserMapper;

@Repository
public class SearchUserDaoImpl implements SearchUserDao {

	private SearchUserMapper searchUserMapper;

	public SearchUserDaoImpl(SearchUserMapper searchUserMapper) {
		this.searchUserMapper = searchUserMapper;
	}

	@Override
	public List<User> searchUsers(SearchUser searchUser) {
		return searchUserMapper.getUsers(searchUser);
	}

}
