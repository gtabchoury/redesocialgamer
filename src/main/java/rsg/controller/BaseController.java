package rsg.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BaseController{
	@Autowired
	protected ModelMapper modelMapper;

	protected final Integer DEFAULT_PER_PAGE=10;
	protected final String DEFAULT_SORT_BY="id";

	protected Pageable getPageable(HttpServletRequest req){
		return PageRequest.of(getPage(req), getPerPage(req), Sort.by(getSortBy(req)));
	}

	protected Integer getPage(HttpServletRequest req){
		return req.getParameter("page")!=null ? Integer.parseInt(req.getParameter("page"))-1 : 0;
	}

	protected Integer getPerPage(HttpServletRequest req){
		return req.getParameter("perPage")!=null ? Integer.parseInt(req.getParameter("perPage")) : DEFAULT_PER_PAGE;
	}

	protected String getSortBy(HttpServletRequest req){
		return req.getParameter("sortBy")!=null ? req.getParameter("sortBy") : DEFAULT_SORT_BY;
	}

	protected Map<String, Object> getPaginatedResponse(Page page, List list){
		Map<String, Object> response = new HashMap<>();
		response.put("result", list);
		response.put("currentPage", page.getNumber()+1);
		response.put("totalItems", page.getTotalElements());
		response.put("perPage", page.getSize());
		response.put("totalPages", page.getTotalPages());
		return response;
	}
}
