package dtos;

import entities.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RoleDTO {
    private String roleName;

    public RoleDTO(Role role) {
        this.roleName = role.getRoleName();
    }

    public static List<RoleDTO> getDtos(List<Role> roles) {
        List<RoleDTO> roleDTOS = new ArrayList();
        if(roles != null) {
            roles.forEach(role -> roleDTOS.add(new RoleDTO(role)));
        }
        return roleDTOS;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDTO roleDTO = (RoleDTO) o;
        return Objects.equals(roleName, roleDTO.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleName);
    }
}
