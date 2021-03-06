package manuscript.module.user.management.academic.disciplines.test;

import java.util.ArrayList;
import java.util.HashMap;
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

import manuscript.module.user.management.academic.disciplines.AcademicDisciplinesDao;
import manuscript.module.user.management.bean.AcademicDisciplines;
import manuscript.module.user.management.exception.DisciplinesUpdateException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = AcademicDisciplinesDaoContext.class)
@Transactional
public class AcademicDisciplinesDaoTest {

	@Autowired
	private AcademicDisciplinesDao academicDisciplinesDao;

	private AcademicDisciplines updateDisciplinesName;
	private AcademicDisciplines insertDisciplines;
	private AcademicDisciplines removeDisciplines;

	private String userIdWhoHasDisciplines;
	private String userIdWhoHasNotDisciplines;

	@Before
	public void before() {
		updateDisciplinesName = new AcademicDisciplines();
		updateDisciplinesName.setAcademicDisciplinesId("10");
		updateDisciplinesName.setAcademicDisciplinesName("updated Disciplines Name");

		insertDisciplines = new AcademicDisciplines();
		insertDisciplines.setAcademicDisciplinesId("111111111");
		insertDisciplines.setAcademicDisciplinesName("inserted Disciplines Name");

		removeDisciplines = new AcademicDisciplines();
		removeDisciplines.setAcademicDisciplinesId("10");

		userIdWhoHasDisciplines = "1";
		userIdWhoHasNotDisciplines = "2";
	}

	@Test
	public void getDisciplinesAsMap_Test() {
		HashMap<String, AcademicDisciplines> map = academicDisciplinesDao.getDisciplinesAsMap();

		Assert.assertNotNull("getDisciplinesAsMap result must be not null", map);
	}

	@Test
	public void getDisciplinesAsList_Test() {
		List<AcademicDisciplines> list = academicDisciplinesDao.getDisciplinesAsList();

		Assert.assertNotNull("getDisciplinesAsList result must be not null", list);
	}

	@Test
	public void updateOrInsertDisciplines_update_Test() {
		AcademicDisciplines actualDisiciplines = academicDisciplinesDao.getSingleDisciplinesById(updateDisciplinesName.getAcademicDisciplinesId());

		Assert.assertNotNull("actualDisiciplines must not be null", actualDisiciplines);

		academicDisciplinesDao.updateOrInsertDisciplines(updateDisciplinesName); // update
		AcademicDisciplines updatedDisciplines = academicDisciplinesDao.getSingleDisciplinesById(updateDisciplinesName.getAcademicDisciplinesId());

		Assert.assertNotNull("updatedDisciplines must not be null", updatedDisciplines);
		Assert.assertNotEquals("actualDisiciplines and updatedDisciplines must be different", actualDisiciplines, updatedDisciplines);
		Assert.assertTrue(updateDisciplinesName.getAcademicDisciplinesName().equals(updatedDisciplines.getAcademicDisciplinesName()));
	}

	@Test
	public void updateOrInsertDisciplines_insert_Test() {
		AcademicDisciplines actialDisciplines = academicDisciplinesDao.getSingleDisciplinesById(insertDisciplines.getAcademicDisciplinesId());

		Assert.assertNull("As per this disciplines is not present yet,it should be null.", actialDisciplines);

		academicDisciplinesDao.updateOrInsertDisciplines(insertDisciplines); // insert
		AcademicDisciplines insertedDisciplines = academicDisciplinesDao.getSingleDisciplinesById(insertDisciplines.getAcademicDisciplinesId());

		Assert.assertNotNull("insertedDisciplines must not be null, because it has been inserted befor", insertedDisciplines);
		Assert.assertTrue(this.insertDisciplines.getAcademicDisciplinesId().equals(insertedDisciplines.getAcademicDisciplinesId()));
		Assert.assertTrue(this.insertDisciplines.getAcademicDisciplinesName().equals(insertedDisciplines.getAcademicDisciplinesName()));

	}

	@Test
	public void removeDisciplinesById_test() {
		AcademicDisciplines actualDisiciplines = academicDisciplinesDao.getSingleDisciplinesById(removeDisciplines.getAcademicDisciplinesId());

		Assert.assertNotNull("actualDisiciplines must not be null", actualDisiciplines);

		academicDisciplinesDao.removeDisciplinesById(removeDisciplines.getAcademicDisciplinesId()); // remove
		AcademicDisciplines removedDisiciplines = academicDisciplinesDao.getSingleDisciplinesById(removeDisciplines.getAcademicDisciplinesId());

		Assert.assertNull("removedDisiciplines must be null", removedDisiciplines);
	}

	@Test
	public void getDisciplinesByUserId_test_with_result() {
		List<AcademicDisciplines> disciplines = academicDisciplinesDao.getDisciplinesByUserId(userIdWhoHasDisciplines);

		Assert.assertNotNull("disciplines must not be null", disciplines);
		Assert.assertTrue("Size of the disciplines list must be 2", disciplines.size() == 2);
	}

	@Test
	public void getDisciplinesByUserId_test_without_result() {
		List<AcademicDisciplines> disciplines = academicDisciplinesDao.getDisciplinesByUserId(userIdWhoHasNotDisciplines);

		Assert.assertNotNull("disciplines must not be null", disciplines);
		Assert.assertTrue("Size of the disciplines list must be 2", disciplines.size() == 0);
	}

	@Test(expected = DisciplinesUpdateException.class)
	public void updateDisciplinesByUserId_test_with_exception_result() {
		List<AcademicDisciplines> disciplines = new ArrayList<>();
		academicDisciplinesDao.updateDisciplinesByUserId(userIdWhoHasDisciplines, disciplines);
	}

	@Test
	public void updateDisciplinesByUserId_test_with_success_result() {
		List<AcademicDisciplines> disciplines = new ArrayList<>();
		disciplines.add(updateDisciplinesName);
		academicDisciplinesDao.updateDisciplinesByUserId(userIdWhoHasDisciplines, disciplines); // update

		List<AcademicDisciplines> modifiedDisciplinesList = academicDisciplinesDao.getDisciplinesByUserId(userIdWhoHasDisciplines);

		Assert.assertTrue("modified list must not be empty", !modifiedDisciplinesList.isEmpty());
		Assert.assertTrue("The update and the given disciplines need to be the same", modifiedDisciplinesList.size() == 1);
	}

}
