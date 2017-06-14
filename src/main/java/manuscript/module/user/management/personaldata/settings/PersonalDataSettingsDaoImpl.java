package manuscript.module.user.management.personaldata.settings;

import org.springframework.stereotype.Repository;

import manuscript.module.user.management.bean.User;
import manuscript.module.user.management.exception.UserNotFoundException;
import manuscript.module.user.management.personaldata.settings.mapper.PersonalDataSettingsMapper;
import manuscript.module.user.management.request.SavePersonalDataRequest;
import manuscript.module.user.management.request.UpdatePassword;

/**
 * 
 * @author Balazs Kovacs
 *
 */
@Repository
public class PersonalDataSettingsDaoImpl implements PersonalDataSettingsDao {

	private PersonalDataSettingsMapper personalDataSettingsMapper;

	public PersonalDataSettingsDaoImpl(PersonalDataSettingsMapper personalDataSettingsMapper) {
		this.personalDataSettingsMapper = personalDataSettingsMapper;
	}

	@Override
	public User getUserData(String userId) {
		User user = personalDataSettingsMapper.getUserData(userId);
		if (user == null) {
			throw new UserNotFoundException("User is not found!");
		}
		return user;
	}

	@Override
	public void updatePersonalData(SavePersonalDataRequest request, String userId) {
		personalDataSettingsMapper.updatePersonalData(request, userId);
	}

	@Override
	public String getPasswordByUserId(String userId) {
		return personalDataSettingsMapper.getPasswordByUserId(userId);
	}

	@Override
	public void updatePassword(UpdatePassword updatePassword) {
		personalDataSettingsMapper.updatePasswordByUserId(updatePassword);
	}

}
