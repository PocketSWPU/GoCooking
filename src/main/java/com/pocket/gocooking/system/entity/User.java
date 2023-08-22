package com.pocket.gocooking.system.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Pocket
 */
@Data
@Schema(description = "用户实体类")
public class User {
    @Schema(description = "用户id")
    Integer id;
    String username;
    String password;
    Integer gender;
    Integer age;
    Integer province;
}
