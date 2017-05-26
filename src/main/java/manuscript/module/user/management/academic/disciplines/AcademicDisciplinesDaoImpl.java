package manuscript.module.user.management.academic.disciplines;

import java.util.HashMap;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import manuscript.module.user.management.academic.disciplines.mapper.AcademicDisciplinesMapper;
import manuscript.module.user.management.bean.AcademicDisciplines;

@Repository
public class AcademicDisciplinesDaoImpl implements AcademicDisciplinesDao {

	AcademicDisciplinesMapper academicDisciplinesMapper;

	public AcademicDisciplinesDaoImpl(AcademicDisciplinesMapper academicDisciplinesMapper) {
		this.academicDisciplinesMapper = academicDisciplinesMapper;
	}

	@Override
	@Cacheable(AcademicDisciplinesDao.GET_DISICPLINES_AS_MAP_CACHE)
	public HashMap<String, AcademicDisciplines> getDisciplinesAsMap() {
		return academicDisciplinesMapper.getDisciplinesAsMap();
	}

	@Override
	@Cacheable(AcademicDisciplinesDao.GET_DISICPLINES_AS_LIST_CACHE)
	public List<AcademicDisciplines> getDisciplinesAsList() {
		return academicDisciplinesMapper.getDisciplinesAsList();
	}

	@Override
	@CacheEvict(value = { GET_DISICPLINES_AS_LIST_CACHE, GET_DISICPLINES_AS_MAP_CACHE }, allEntries = true)
	public void updateOrInsertDisciplines(AcademicDisciplines academicDisciplines) {
		academicDisciplinesMapper.updateOrInsertDisciplines(academicDisciplines);
	}

	@Override
	public AcademicDisciplines getSingleDisciplinesById(String disciplinesId) {
		return academicDisciplinesMapper.getSingleDisciplinesById(disciplinesId);
	}

	@Override
	@CacheEvict(value = { GET_DISICPLINES_AS_LIST_CACHE, GET_DISICPLINES_AS_MAP_CACHE }, allEntries = true)
	public void removeDisciplinesById(String disciplinesId) {
		academicDisciplinesMapper.removeDisciplinesById(disciplinesId);
	}
}
