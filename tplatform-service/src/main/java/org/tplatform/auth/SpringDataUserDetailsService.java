package org.tplatform.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by tianyi on 2017/4/7.
 */
@Service
public class SpringDataUserDetailsService implements UserDetailsService {

  @Autowired
  private SysUserService sysUserService;

  @Transactional(readOnly = true)
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    SysUser sysUser = sysUserService.findOne(username);
    if (sysUser == null) {
      throw new UsernameNotFoundException("User: " + username + " not fond");
    }

    Set<GrantedAuthority> setAuths = new HashSet<>();

    // Build user's authorities
    for (SysRole sysRole : sysUser.getRoles()) {
      setAuths.add(new SimpleGrantedAuthority("ROLE_" + sysRole.getRole()));
    }

    return new User(sysUser.getUsername(), sysUser.getPassword(), 1 == sysUser.getStatus(), true, true, true, setAuths);
  }
}
