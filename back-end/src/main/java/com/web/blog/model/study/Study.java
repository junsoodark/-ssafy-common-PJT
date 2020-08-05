package com.web.blog.model.study;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.blog.model.address.Address;
import com.web.blog.model.board.Post;
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

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH })
	@JoinTable(name = "study_member", joinColumns = @JoinColumn(name = "study_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> members;

	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "study")
	// @JoinTable(name = "post", joinColumns = @JoinColumn(name = "study_id"))
	private Set<Post> posts;

	@JsonIgnore
	@ManyToOne(targetEntity = Address.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	private Address address;

	@Version
	private Long version;

	private String title;
	private String content;
	private LocalDate startDate;
	private LocalDate endDate;

	public boolean addPost(Post post) {
		if (this.posts == null) {
			this.posts = new HashSet<>();
		}
		if (!this.posts.contains(post)) {
			this.posts.add(post);
			return true;
		}
		return false;
	}

	public boolean addMember(User member) {
		if (this.members == null)
			this.members = new HashSet<>();

		if (!this.members.contains(member)) {
			this.members.add(member);
			return true;
		}
		return false;
	}

	public boolean removeMember(User member) {
		if (this.members == null)
			return false;

		if (this.members.contains(member)) {
			this.members.remove(member);
			return true;
		}
		return false;
	}

	private int maxMembers;
}
