package dtos;

import entities.User;

import java.util.List;
import java.util.Objects;

public class UserDTO {
    String userName;
    String userPass;
    List<RoleDTO> roleList;

    public UserDTO(User user) {
        this.userName = user.getUserName();
        this.userPass = user.getUserPass();
        this.roleList = RoleDTO.getDtos(user.getRoleList());
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public List<RoleDTO> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RoleDTO> roleList) {
        this.roleList = roleList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(userName, userDTO.userName) && Objects.equals(userPass, userDTO.userPass) && Objects.equals(roleList, userDTO.roleList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, userPass, roleList);
    }
}
