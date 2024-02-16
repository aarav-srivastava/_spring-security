package com.spring.springsecurity.dto;

import com.spring.springsecurity.entity.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileDto {
    public int id;
    @NotBlank(message = "login name can't be empty")
    public String loginName;
    public String password;
    public static UserProfile fromDto(ModelMapper mapper,UserProfileDto userProfileDto){
        TypeMap<UserProfileDto,UserProfile> typeMap = mapper.getTypeMap(UserProfileDto.class, UserProfile.class);
        if(typeMap == null){
            mapper.addMappings(new PropertyMap<UserProfileDto, UserProfile>() {
                @Override
                protected void configure() {
                    skip(destination.getPwdHash()); // this ensures that password is not copied to dto object.
                }
            });
        }
        return mapper.map(userProfileDto, UserProfile.class);
    }
    public static UserProfileDto toDto(ModelMapper mapper, UserProfile userProfile){
        TypeMap<UserProfile,UserProfileDto> typeMap = mapper.getTypeMap(UserProfile.class, UserProfileDto.class);
        if(typeMap==null){
            mapper.addMappings(new PropertyMap<UserProfile,UserProfileDto>() {
                @Override
                protected void configure() {
                    map().setPassword(null);
                }
            });
        }
        return mapper.map(userProfile, UserProfileDto.class);
    }
}
