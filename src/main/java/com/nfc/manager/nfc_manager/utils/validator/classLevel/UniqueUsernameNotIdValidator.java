package com.nfc.manager.nfc_manager.utils.validator.classLevel;

import com.nfc.manager.nfc_manager.entity.DTO.UserEditDTO;
import com.nfc.manager.nfc_manager.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class UniqueUsernameNotIdValidator implements ConstraintValidator<UniqueUsernameNotId,Object> {
    private final UserService userService;

    public UniqueUsernameNotIdValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            UserEditDTO userEditDTO = (UserEditDTO) value;
            Long id = userEditDTO.getId();
            String username = userEditDTO.getUsername().trim();

            return id != null
                    ? userService.isUsernameUniqueForUpdate(id, username)
                    : userService.isUsernameUniqueForCreate(username);
        }catch (Exception e){
            throw new RuntimeException("Failed validating uniqueness username", e);
        }
    }
}
