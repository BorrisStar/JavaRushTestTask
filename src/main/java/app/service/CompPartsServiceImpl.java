package app.service;


import app.dao.CompPartDao;
import app.dao.Dao;
import app.entity.CompPart;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CompPartsServiceImpl implements CompPartsService {
	@Autowired
	private  Dao DAO;
//	@Autowired
//	private  CompPartDao compPartDao;
@Autowired
SessionFactory sessionFactory;
	@Autowired
	public CompPartsServiceImpl() {
//		this.DAO = DAO;
//		this.compPartDao = compPartDao;
	}


	@Override
	public int createCompPart(CompPart compPart) {
//		CompPart compPartsaved = compPartDao.save(compPart);
//		return compPartsaved.getId();
		return DAO.createCompPart(compPart);
	}

	@Override
	public CompPart updateCompPart(CompPart compPart) {
		return DAO.updateCompPart(compPart);
	}

	@Override
	public void deleteCompPart(int id) {
		DAO.deleteCompPart(id);
	}

	@Override
	public List<CompPart> getAllCompParts() {
//		return compPartDao.findAll();
		return DAO.getAllCompParts();
	}

	@Override
	public List<CompPart> getAllCompParts(String partName) {
		return DAO.getAllCompParts(partName);
	}

	@Override
	public CompPart getCompPart(int id) {
		return DAO.getCompPart(id);
	}

	@Override
	public List<CompPart> getRequired(String isRequired) {
		return DAO.getRequired(isRequired);
	}

	@Override
	public int computers() {
		return DAO.computers();
	}


}
