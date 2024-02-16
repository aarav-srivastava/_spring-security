package com.spring.springsecurity.dto;

import com.spring.springsecurity.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDto {
    private int id;
    @NotBlank(message = "role name can't be null")
    private String roleName;
    private String description;
    public static Role fromDto(ModelMapper mapper, RoleDto roleDto){
        return mapper.map(roleDto, Role.class);
    }
    public static RoleDto toDto(ModelMapper mapper, Role role){
        return mapper.map(role, RoleDto.class);
    }
}
