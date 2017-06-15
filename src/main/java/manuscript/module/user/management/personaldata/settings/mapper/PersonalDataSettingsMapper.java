package manuscript.module.user.management.personaldata.settings.mapper;

import org.apache.ibatis.annotations.Param;

import manuscript.module.user.management.bean.User;
import manuscript.module.user.management.request.SavePersonalDataRequest;
import manuscript.module.user.management.request.UpdatePassword;

/**
 * 
 * @author Bal�zs Kov�cs
 *
 */
public interface PersonalDataSettingsMapper {

	/**
	 * A param�terben megadott felhaszn�l� id alapj�n visszaadja a felhaszn�l� adatait.
	 * 
	 * @param userId
	 * @return {@link User}
	 */
	public User getUserData(@Param("userId") String userId);

	/**
	 * A param�terben megkapott (felhaszn�l� �ltal megadott) adatokkal friss�ti a felhaszn�l� adatait
	 * 
	 * @param request
	 * @param userId
	 */
	public void updatePersonalData(@Param("personalData") SavePersonalDataRequest request, @Param("userId") String userId);

	/**
	 * Visszaadja a userid szerint a jelsz�t.
	 * 
	 * @param userId
	 * @return
	 */
	public String getPasswordByUserId(@Param("userId") String userId);

	/**
	 * Updateli a USR_PASSWORD t�bl�t a megadott �j jelsz�val az adott felhaszn�l�hoz.
	 * 
	 * @param updatePassword
	 */
	public void updatePasswordByUserId(@Param("password") UpdatePassword updatePassword);
}
