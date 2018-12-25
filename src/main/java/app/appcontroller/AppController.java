package app.appcontroller;

import app.entity.CompPart;
import app.service.CompPartsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.support.PagedListHolder;

import org.jboss.logging.Logger;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
/*
@RequestMapping(value = "/search", method = RequestMethod.POST)
public String search(@RequestParam Integer idProvider,
        @RequestParam String department,
        @RequestParam String carNumber,
        @RequestParam ("arrivalDate") @DateTimeFormat(iso = ISO.DATE) LocalDate startDate,
        @RequestParam("arrivalDate") @DateTimeFormat(iso = ISO.DATE) LocalDate endDate,
        @ModelAttribute("idAttribute") Supply supply, Model model) throws DaoException {

    List<Supply> supplyList = supplyDao.searchByCriteria(idProvider, department, carNumber, startDate, endDate);
    model.addAttribute("supplyList", supplyList);

    return "searchList";
 */

@Controller
public class AppController {
	private static final Logger logger = Logger.getLogger(AppController.class);
	private final CompPartsService compPartsService;

	@Autowired
	public AppController(CompPartsService service) {
		this.compPartsService = service;

	}

	@RequestMapping("index")
	public ModelAndView createPart() {
		logger.info("Display index.jsp ");
		return new ModelAndView("index");
	}

	@RequestMapping("createCompPart")
	public ModelAndView createPart(@ModelAttribute CompPart part) {
		logger.info("Creating CompPart. Data: " + part);
		return new ModelAndView("newcomppart");
	}

	@RequestMapping("editCompPart")
	public ModelAndView editPart(@RequestParam int id, @ModelAttribute CompPart part) {
		logger.info("Updating the CompPart for the Id "+id);
		part = compPartsService.getCompPart(id);
		return new ModelAndView("newcomppart", "partObject", part);
	}

	@RequestMapping("saveCompPart")
	public ModelAndView savePart(@ModelAttribute CompPart part) {
		logger.info("Saving the CompPart. Data : "+part);
		if(part.getId() == 0){ // if part id is 0 then creating part other updating part
			compPartsService.createCompPart(part);
		} else {
			compPartsService.updateCompPart(part);
		}
		return new ModelAndView("redirect:/");
	}

	@RequestMapping("deleteCompPart")
	public ModelAndView deletePart(@RequestParam int id)
	{
		logger.info("Deleting the CompPart. Id : " + id);
		compPartsService.deleteCompPart(id);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping("getAllCompParts")
	public ModelAndView getAllParts(){
		logger.info("Getting all CompParts.");
		List<CompPart> partList = compPartsService.getAllCompParts();
		ModelAndView modelAndView = new ModelAndView("main", "list", partList);
		modelAndView.addObject("computers", compPartsService.computers());
		return modelAndView;
	}

	@RequestMapping("searchCompPart")
	public ModelAndView searchPart(@RequestParam("searchDescription") String searchDescription){
		logger.info("Searching the CompPart. CompPart Descriptions: "+searchDescription);
		List<CompPart> partsList = compPartsService.getAllCompParts(searchDescription);
		ModelAndView modelAndView = new ModelAndView("main", "list", partsList);
		modelAndView.addObject("computers", compPartsService.computers());
		return modelAndView;
	}

	@RequestMapping("searchRequired")
	public ModelAndView searchRequired(@RequestParam("requirement") String requirement) {
		logger.info("Testing boolean search with param: " +requirement);
		List<CompPart> requiredList = compPartsService.getRequired(requirement);
		ModelAndView modelAndView = new ModelAndView("main", "list", requiredList);
		modelAndView.addObject("computers", compPartsService.computers());
		return modelAndView;
	}

	@RequestMapping("/")
	public ModelAndView listOfParts(@RequestParam(required = false) Integer page) {
		logger.info("Show all components");

		ModelAndView modelAndView = new ModelAndView("main");

		List<CompPart> parts = compPartsService.getAllCompParts();
		PagedListHolder<CompPart> pagedListHolder = new PagedListHolder<>(parts);
		pagedListHolder.setPageSize(10);
		modelAndView.addObject("maxPages", pagedListHolder.getPageCount());

		if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
			page=1;
			pagedListHolder.setPage(0);
			modelAndView.addObject("list", pagedListHolder.getPageList());
		}
		else if(page <= pagedListHolder.getPageCount()) {
			pagedListHolder.setPage(page-1);
			modelAndView.addObject("list", pagedListHolder.getPageList());
		}
		modelAndView.addObject("page", page);
		modelAndView.addObject("computers", compPartsService.computers());
		return modelAndView;
	}
}
