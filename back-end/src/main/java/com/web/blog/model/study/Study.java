package com.web.blog.model.study;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.blog.model.address.Address;
import com.web.blog.model.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Study {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "study_id")
	private int studyId;

	@JsonIgnore
	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "mgr_id")
	private User user;

	@JsonBackReference
	@ManyToMany(mappedBy="studies", cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
	private Set<User> members;
	
	@JsonIgnore
	@ManyToOne(targetEntity = Address.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	private Address address;

	private String title;
	private String content;
	private LocalDate startDate;
	private LocalDate endDate;
	
	public void addMember(User member) {
		if(!this.members.contains(member))
			this.members.add(member);
		
		if(!member.getStudies().contains(this))
			member.getStudies().add(this);
	}
	
	public List<Map<String, Object>> getMemberList(){
		List<Map<String, Object>> ret = new ArrayList<>();
		for(User member : members) {
			Map<String, Object> info = new HashMap<>();
			info.put("email", member.getEmail());
			info.put("name",  member.getName());
			ret.add(info);
		}
		return ret;
	}
}