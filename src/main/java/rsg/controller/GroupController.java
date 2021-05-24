package rsg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rsg.dto.request.CreateGroupDTO;
import rsg.dto.response.GroupDTO;
import rsg.model.Group;
import rsg.service.GroupService;
import rsg.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/groups")
public class GroupController extends BaseController{
	@Autowired
	private GroupService groupService;

	@Autowired
	private UserService userService;

	@PostMapping("")
	public ResponseEntity<GroupDTO> create(HttpServletRequest req, @RequestBody @Valid CreateGroupDTO groupRequest) {
		Group group = groupService.create(groupRequest, userService.getByRequest(req));
		return new ResponseEntity<>(modelMapper.map(group, GroupDTO.class), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<GroupDTO> get(HttpServletRequest req, @PathVariable Long id) {
		Group group = groupService.getById(id);
		return new ResponseEntity<>(modelMapper.map(group, GroupDTO.class), HttpStatus.CREATED);
	}

	@PatchMapping("/{id}/join")
	public ResponseEntity<GroupDTO> join(HttpServletRequest req, @PathVariable Long id) {
		Group group = groupService.getById(id);
		group = groupService.join(group, userService.getByRequest(req));
		return new ResponseEntity<>(modelMapper.map(group, GroupDTO.class), HttpStatus.OK);
	}
}
