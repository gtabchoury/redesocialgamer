package rsg.dto.response;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Pageable;
import rsg.dto.PageableDTO;

import java.util.List;

@Data
@AllArgsConstructor
public class PageableResponseDTO {
	private List result;
	private PageableDTO pageable;

	public PageableResponseDTO(List result, Pageable pageableRequest, Integer total){
		this.result=result;
		this.pageable = new PageableDTO(pageableRequest.getPageNumber(), pageableRequest.getPageSize(), total, (int) Math.ceil(total/pageableRequest.getPageSize()));
	}
}

