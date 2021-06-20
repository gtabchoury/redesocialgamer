package rsg.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import rsg.model.File;
import rsg.model.Game;

import java.util.List;

public interface FileRepository extends PagingAndSortingRepository<File, Integer> {
    File getById(Long id);
}
