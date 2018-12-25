package app.dao;


import app.entity.CompPart;

import java.util.List;

public interface Dao  {
	int createCompPart(CompPart compPart);
	CompPart updateCompPart(CompPart compPart);
	void deleteCompPart(int id);
	List<CompPart> getAllCompParts();
	List<CompPart> getAllCompParts(String partDescription);
	CompPart getCompPart(int id);
	List<CompPart> getRequired(String isRequired);
	int computers();
}
