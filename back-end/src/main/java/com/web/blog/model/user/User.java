package com.web.blog.model.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull(message="이메일은 필수 항목입니다.")
	@Email(message="이메일 형식에 맞지 않습니다.")
	private String email;
	
	@NotNull(message="비밀번호는 필수 항목입니다.")
	@Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d$@$!%*#?&]{8,}$",
			 message="비밀번호는 영문과 숫자가 적어도 1자 이상씩 포함된 8자이상으로 구성되어야 합니다.")
	private String password;

	@NotNull(message="이름은 필수 항목입니다.")
	private String name;

	@NotNull(message="나이는 필수 항목입니다.")
	private int age;
	
	@NotNull(message="성별은 필수 항목입니다.")
	@Pattern(regexp="^[12]{1}$", message="성별은 1 또는 2의 값을 가져야 합니다.")
	private String sex; // 1: man, 2: woman

	@OneToMany(mappedBy="user")
	private List<StudyUser> studyUsers = new ArrayList<>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
}
