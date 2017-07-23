package manuscript.module.user.management.searchuser.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import manuscript.module.user.management.bean.SearchUser;
import manuscript.module.user.management.bean.User;

public interface SearchUserMapper {

	List<User> getUsers(@Param("searchUser") SearchUser searchUser);

}
