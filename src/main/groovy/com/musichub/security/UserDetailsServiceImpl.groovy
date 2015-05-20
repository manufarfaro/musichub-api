package com.musichub.security

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.transaction.annotation.Transactional

import com.musichub.MHUser;
import com.musichub.Role
import com.musichub.UserRole;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		println ("------ Trying with user: $username")
		MHUser user = MHUser.findByUsername(username)
		println ("------ User from Database: $user")
		List<GrantedAuthority> authorities = buildUserAuthority(user);
		return buildUserForAuthentication(user, authorities);

    }
	private User buildUserForAuthentication(MHUser user,
		List<GrantedAuthority> authorities) {
		if(!user){
			throw new BadCredentialsException("User/Password not found.")
		}
		return new User(user.getUsername(), user.getPassword(),
			user.isEnabled(), true, true, true, authorities);
	}
 
	private List<GrantedAuthority> buildUserAuthority(MHUser user) {
 
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		if(user) {
			for (Role role : user.getAuthorities()) {
				setAuths.add(new SimpleGrantedAuthority(role.getAuthority()));
			}
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}
}
