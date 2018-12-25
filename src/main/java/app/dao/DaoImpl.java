package app.dao;

import app.entity.CompPart;
import app.repository.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class DaoImpl implements Dao {

	private final AppRepository Repository;

	List<CompPart> partList = new ArrayList<>();

	@Autowired
	public DaoImpl(AppRepository Repository) {

		this.Repository = Repository;

		partList.add(new CompPart("Computer1", true, 95));
		partList.add(new CompPart("Computer2", true, 96));
		partList.add(new CompPart("Computer3", true, 97));
		partList.add(new CompPart("Computer4", true, 98));
		partList.add(new CompPart("Computer5", true, 99));
	}

	@Override
	public int createCompPart(CompPart compPart) {
		return (int) Repository.create(compPart);
	}

	@Override
	public CompPart updateCompPart(CompPart compPart) {
		return Repository.update(compPart);
	}

	@Override
	public void deleteCompPart(int id) {
		CompPart comPart = new CompPart();
		comPart.setId(id);
		Repository.delete(comPart);
	}

	@Override
	public List<CompPart> getAllCompParts() {
		//return partList;
		return Repository.fetchAll();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CompPart> getAllCompParts(String partDescription) {
		String query = "SELECT t.* FROM part t WHERE t.description like '%" + partDescription + "%'";
		List<Object[]> partObjects = Repository.fetchAll(query);

		List<CompPart> parts = new ArrayList<>();
		createList(partObjects, parts);
		return parts;
	}

	@Override
	public CompPart getCompPart(int id) {
		return Repository.fetchById(id, CompPart.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CompPart> getRequired(String isRequired) {
		if (isRequired.isEmpty()) return getAllCompParts();
		else {
			String query = "SELECT t.* FROM part t WHERE t.required = " + isRequired;
			List<Object[]> partObjects = Repository.fetchAll(query);

			List<CompPart> parts = new ArrayList<>();
			createList(partObjects, parts);
			return parts;
		}
	}

	@Override
	public int computers() {
		List<CompPart> required = getRequired("true");
		TreeSet<Integer> set = new TreeSet<>();
		for (CompPart part : required) {
			set.add(part.getAmount());
		}
		if (set.isEmpty()) return 0;
		return set.first();
	}
	private void CreateListFromList(List<CompPart> compParts, List<CompPart> parts) {
		parts.addAll(compParts);
	}

	public static void createList(List<Object[]> partObjects, List<CompPart> parts) {
		for (Object[] partObject : partObjects) {
			CompPart part = new CompPart();
			int id = (int) partObject[0];
			String description = (String) partObject[1];
			boolean required = (boolean) partObject[2];
			int amount = (int) partObject[3];
			part.setId(id);
			part.setDescription(description);
			part.setRequired(required);
			part.setAmount(amount);
			parts.add(part);
		}
	}
}

