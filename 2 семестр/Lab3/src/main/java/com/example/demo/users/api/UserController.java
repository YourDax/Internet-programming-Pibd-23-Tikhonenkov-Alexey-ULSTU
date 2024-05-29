package com.example.demo.users.api;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.core.configurations.Constants;
import com.example.demo.users.model.UserEntity;
import com.example.demo.users.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(Constants.API_URL + "/user")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    private UserEntity toEntity(UserDTO dto) {
        return modelMapper.map(dto, UserEntity.class);
    }

    private UserDTO toDto(UserEntity entity) {
        return modelMapper.map(entity, UserDTO.class);
    }

    @GetMapping()
    public List<UserDTO> getAll() {
        return userService.getAll().stream().map(this::toDto).toList();
    }

    @GetMapping("/{id}")
    public UserDTO get(@PathVariable(name = "id") Integer id) {
        return toDto(userService.get(id));
    }

    @PostMapping
    public UserDTO create(@RequestBody @Valid UserDTO userDTO) {

        return toDto(userService.create(toEntity(userDTO)));
    }

    @PutMapping("/{id}")
    public UserDTO update(@PathVariable(name = "id") Integer id, @RequestBody UserDTO userDTO) {
        return toDto(userService.update(id, toEntity(userDTO)));
    }

    @DeleteMapping("/{id}")
    public UserDTO delete(@PathVariable(name = "id") Integer id) {
        return toDto(userService.delete(id));
    }
}
