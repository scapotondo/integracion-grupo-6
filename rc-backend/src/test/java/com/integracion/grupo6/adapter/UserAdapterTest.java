package com.integracion.grupo6.adapter;

import com.integracion.grupo6.domain.User;
import com.integracion.grupo6.domain.UserRole;
import com.integracion.grupo6.dto.UserDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserAdapterTest {

    @Autowired
    private UserAdapter userAdapter;

    @Test
    public void toUser() {
        UserDTO dto = new UserDTO();
        dto.setId(9494L);
        dto.setFullName("Hombre Araña");
        dto.setUsername("spiderman");
        dto.setPassword("123123123");
        dto.setUserRole("ROLE_ADMIN");

        User user = userAdapter.toUser(dto);

        Assert.assertEquals(dto.getId(), user.getId());
        Assert.assertEquals(dto.getUsername(), user.getUsername());
        Assert.assertEquals(dto.getPassword(), user.getPassword());
        Assert.assertEquals(dto.getFullName(), user.getFullName());
        Assert.assertEquals(dto.getUserRole(), user.getUserRole().getName());
    }

    @Test
    public void toUserDTO() {
        UserRole role = new UserRole();
        role.setId(1L);
        role.setName("ROLE_ADMIN");
        User user = new User();
        user.setId(9494L);
        user.setFullName("Hombre Araña");
        user.setUsername("spiderman");
        user.setPassword("123123123");
        user.setUserRole(role);

        UserDTO dto = userAdapter.toDTO(user);

        Assert.assertEquals(user.getId(), dto.getId());
        Assert.assertEquals(user.getUsername(), dto.getUsername());
        Assert.assertEquals(user.getPassword(), dto.getPassword());
        Assert.assertEquals(user.getFullName(), dto.getFullName());
        Assert.assertEquals(user.getUserRole().getName(), dto.getUserRole());
    }
}