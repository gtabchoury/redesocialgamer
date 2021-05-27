package rsg.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import rsg.model.Group;
import rsg.model.GroupMember;

import java.util.Date;

@Data
@NoArgsConstructor
public class GroupMemberDTO {
	private Long id;
	private Date joinRequestDate;
	private Date joinDate;
	private Boolean approved;
	private SimpleUserDTO user;

	public GroupMemberDTO(GroupMember groupMember){
		this.id=groupMember.getId();
		this.joinRequestDate=groupMember.getJoinRequestDate();
		this.joinDate=groupMember.getJoinDate();
		this.approved=groupMember.getApproved();
		this.user=new SimpleUserDTO(groupMember.getUser());
	}
}
