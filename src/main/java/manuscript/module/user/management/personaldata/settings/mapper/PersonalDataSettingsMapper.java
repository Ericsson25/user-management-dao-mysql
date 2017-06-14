package manuscript.module.user.management.personaldata.settings.mapper;

import org.apache.ibatis.annotations.Param;

import manuscript.module.user.management.bean.User;
import manuscript.module.user.management.request.SavePersonalDataRequest;
import manuscript.module.user.management.request.UpdatePassword;

public interface PersonalDataSettingsMapper {

	/**
	 * A paraméterben megadott felhasználó id alapján visszaadja a felhasználó adatait.
	 * 
	 * @param userId
	 * @return {@link User}
	 */
	public User getUserData(@Param("userId") String userId);

	/**
	 * A paraméterben megkapott (felhasználó által megadott) adatokkal frissíti a felhasználó adatait
	 * 
	 * @param request
	 * @param userId
	 */
	public void updatePersonalData(@Param("personalData") SavePersonalDataRequest request,
			@Param("userId") String userId);

	public String getPasswordByUserId(@Param("userId") String userId);

	public void updatePasswordByUserId(@Param("password") UpdatePassword updatePassword);
}
