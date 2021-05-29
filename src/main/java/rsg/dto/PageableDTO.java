package rsg.dto;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Pageable;

@Data
@AllArgsConstructor
public class PageableDTO {
	private Integer currentPage;
	private Integer perPage;
	private Integer total;
	private Integer totalPages;

	public PageableDTO (Pageable pageable, Integer total){
		this.currentPage = pageable.getPageNumber()+1;
		this.perPage = pageable.getPageSize();
		this.total = total;
		this.totalPages = (int) Math.ceil(total/this.perPage);
	}
}

