package manuscript.module.user.management.personaldata.settings.mapper;

import org.apache.ibatis.annotations.Param;

import manuscript.module.user.management.bean.User;
import manuscript.module.user.management.request.SavePersonalDataRequest;
import manuscript.module.user.management.request.UpdatePassword;

public interface PersonalDataSettingsMapper {

	public User getUserData(@Param("userId") String userId);

	public void updatePersonalData(@Param("personalData") SavePersonalDataRequest request,
			@Param("userId") String userId);

	public String getPasswordByUserId(@Param("userId") String userId);

	public void updatePasswordByUserId(@Param("password") UpdatePassword updatePassword);
}
