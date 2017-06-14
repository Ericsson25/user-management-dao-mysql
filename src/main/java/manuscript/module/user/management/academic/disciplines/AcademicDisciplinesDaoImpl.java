package manuscript.module.user.management.academic.disciplines;

import java.util.HashMap;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import manuscript.module.user.management.academic.disciplines.mapper.AcademicDisciplinesMapper;
import manuscript.module.user.management.bean.AcademicDisciplines;
import manuscript.module.user.management.exception.DisciplinesUpdateException;

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

	@Override
	public List<AcademicDisciplines> getDisciplinesByUserId(String userId) {
		return academicDisciplinesMapper.getDisciplinesByUserId(userId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	public void updateDisciplinesByUserId(String userId, List<AcademicDisciplines> academicDisciplines) {
		academicDisciplinesMapper.removeDisciplinesByUserId(userId);
		if (academicDisciplines.isEmpty()) {
			throw new DisciplinesUpdateException("The give disciplinse list must not be empty!");
		}
		academicDisciplinesMapper.updateDisciplinesByUserId(userId, academicDisciplines);
	}

}
