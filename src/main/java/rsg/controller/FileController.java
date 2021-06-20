package rsg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rsg.dto.DefaultDTO;
import rsg.dto.request.GameRateRequestDTO;
import rsg.dto.response.FileDTO;
import rsg.dto.response.GameDTO;
import rsg.dto.response.GameRateDTO;
import rsg.model.File;
import rsg.model.Game;
import rsg.model.GameRate;
import rsg.service.FileService;
import rsg.service.GameService;
import rsg.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/files")
public class FileController extends BaseController{
	@Autowired
	private FileService fileService;

	@PostMapping("/users/profile/photo/upload")
	public ResponseEntity<DefaultDTO> uploadUserProfilePhoto(HttpServletRequest req, @RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			fileService.uploadUserProfilePhoto(file, req);
			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			return new ResponseEntity<>(new DefaultDTO(true, message), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return new ResponseEntity<>(new DefaultDTO(true, message), HttpStatus.OK);
		}
	}

	@GetMapping("/files/{id}")
	public ResponseEntity<FileDTO> getFile(@PathVariable Long id) {
		File fileDB = fileService.getFile(id);

		return new ResponseEntity<>(new FileDTO(fileDB), HttpStatus.OK);
	}
}
