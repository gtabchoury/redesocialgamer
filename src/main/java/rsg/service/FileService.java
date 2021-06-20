package rsg.service;

import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import rsg.exception.CustomException;
import rsg.model.File;
import rsg.model.User;
import rsg.repository.FileRepository;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

@Service
public class FileService {
	@Autowired
	private FileRepository fileRepository;

	@Autowired
	private UserService userService;

	public File uploadUserProfilePhoto(MultipartFile file, HttpServletRequest req) throws IOException {
		User user = userService.getByRequest(req);
		String contentType = file.getContentType();
		if (contentType.startsWith("image/")){
			String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
			String newFileName = String.valueOf(new Date().getTime())+"_"+user.getId();
			file.transferTo(new java.io.File(req.getServletContext().getRealPath("/images/"+newFileName+"."+extension)));

			File fileModel = new File(newFileName, contentType, extension);
			fileRepository.save(fileModel);
			user.setProfilePhoto(fileModel);
			userService.save(user);
			return fileModel;
		}else{
			throw new CustomException("Invalid image type", HttpStatus.BAD_REQUEST);
		}
	}

	public File getFile(Long id) {
		return fileRepository.getById(id);
	}
}
