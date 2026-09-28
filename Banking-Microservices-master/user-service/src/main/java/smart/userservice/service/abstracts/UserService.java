package smart.userservice.service.abstracts;

import smart.userservice.entity.dto.UserDTO;
import smart.userservice.entity.request.UserSaveRequest;
import smart.userservice.entity.request.UserUpdateRequest;
import smart.userservice.utils.result.DataResult;
import smart.userservice.utils.result.Result;

import java.util.List;

/**
 * Copyright (c) 2024
 * All rights reserved.
 *
 * @author Emre Ünaldı
 */
public interface UserService {
    DataResult<UserDTO> save(UserSaveRequest userSaveRequest);
    DataResult<UserDTO> update(UserUpdateRequest userUpdateRequest);
    Result deleteById(Long userId);
    DataResult<UserDTO> findById(Long userId);
    DataResult<List<UserDTO>> findAll();
}
