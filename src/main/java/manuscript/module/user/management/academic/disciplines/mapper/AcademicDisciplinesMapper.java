package manuscript.module.user.management.academic.disciplines.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import manuscript.module.user.management.bean.AcademicDisciplines;

public interface AcademicDisciplinesMapper {

	@MapKey("academicDisciplinesId")
	public HashMap<String, AcademicDisciplines> getDisciplinesAsMap();

	public List<AcademicDisciplines> getDisciplinesAsList();

	public void updateOrInsertDisciplines(@Param("disciplines") AcademicDisciplines academicDisciplines);

	public AcademicDisciplines getSingleDisciplinesById(@Param("id") String disciplinesId);

	public void removeDisciplinesById(@Param("id") String disciplinesId);
}
