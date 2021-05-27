package rsg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rsg.dto.DefaultDTO;
import rsg.dto.request.CreateGroupDTO;
import rsg.dto.response.GroupDTO;
import rsg.dto.response.GroupMemberDTO;
import rsg.model.Group;
import rsg.model.GroupMember;
import rsg.model.User;
import rsg.service.GroupMemberService;
import rsg.service.GroupService;
import rsg.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/groups")
public class GroupController extends BaseController{
	@Autowired
	private GroupService groupService;

	@Autowired
	private GroupMemberService groupMemberService;

	@Autowired
	private UserService userService;

	@PostMapping("")
	public ResponseEntity<GroupDTO> create(HttpServletRequest req, @RequestBody @Valid CreateGroupDTO groupRequest) {
		Group group = groupService.create(groupRequest, userService.getByRequest(req));
		return new ResponseEntity<>(new GroupDTO(group), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<GroupDTO> get(HttpServletRequest req, @PathVariable Long id) {
		Group group = groupService.getById(id);
		return new ResponseEntity<>(new GroupDTO(group), HttpStatus.OK);
	}

	@PatchMapping("/{id}/join")
	public ResponseEntity<GroupMemberDTO> join(HttpServletRequest req, @PathVariable Long id) {
		Group group = groupService.getById(id);
		GroupMember groupMember = groupService.join(group, userService.getByRequest(req));
		return new ResponseEntity<>(new GroupMemberDTO(groupMember), HttpStatus.OK);
	}

	@GetMapping("/{id}/requests")
	public ResponseEntity<List<GroupMemberDTO>> getJoinRequests(HttpServletRequest req, @PathVariable Long id) {
		Group group = groupService.getById(id);
		List<GroupMemberDTO> groupMemberDTOs = groupMemberService.getJoinRequests(group).stream().map(GroupMemberDTO::new).collect(Collectors.toList());
		return new ResponseEntity<>(groupMemberDTOs, HttpStatus.OK);
	}

	@GetMapping("/{id}/members")
	public ResponseEntity<List<GroupMemberDTO>> getMembers(HttpServletRequest req, @PathVariable Long id) {
		Group group = groupService.getById(id);
		List<GroupMemberDTO> groupMemberDTOs = groupMemberService.getMembers(group).stream().map(GroupMemberDTO::new).collect(Collectors.toList());
		return new ResponseEntity<>(groupMemberDTOs, HttpStatus.OK);
	}

	@PatchMapping("/{id}/requests/{idRequest}/approve")
	public ResponseEntity<GroupMemberDTO> approveRequest(HttpServletRequest req, @PathVariable Long id, @PathVariable Long idRequest) {
		Group group = groupService.getById(id);
		GroupMember groupMember = groupMemberService.getById(idRequest);
		groupMember = groupService.approve(userService.getByRequest(req), group, groupMember);
		return new ResponseEntity<>(new GroupMemberDTO(groupMember), HttpStatus.OK);
	}

	@PatchMapping("/{id}/requests/{idRequest}/decline")
	public ResponseEntity<DefaultDTO> declineRequest(HttpServletRequest req, @PathVariable Long id, @PathVariable Long idRequest) {
		Group group = groupService.getById(id);
		GroupMember groupMember = groupMemberService.getById(idRequest);
		groupService.decline(userService.getByRequest(req), group, groupMember);
		DefaultDTO defaultDTO = new DefaultDTO(true, "Request declined successfully.");
		return new ResponseEntity<>(defaultDTO, HttpStatus.OK);
	}

	@PatchMapping("/{id}/members/{idMember}/remove")
	public ResponseEntity<DefaultDTO> removeMember(HttpServletRequest req, @PathVariable Long id, @PathVariable Long idMember) {
		Group group = groupService.getById(id);
		User member = userService.getById(idMember);
		groupService.removeMember(userService.getByRequest(req), group, member);
		DefaultDTO defaultDTO = new DefaultDTO(true, "User removed successfully.");
		return new ResponseEntity<>(defaultDTO, HttpStatus.OK);
	}
}
