package com.kpi.controller;

import com.kpi.domain.Role;
import com.kpi.dto.response.RoleResponseDto;
import com.kpi.service.RoleService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
public class RoleController {
  private final RoleService service;

  @GetMapping("/{id}")
  public RoleResponseDto getById(@PathVariable Integer id) {
    return convertToDto(service.getById(id));
  }

  @GetMapping("/")
  public List<RoleResponseDto> getAll() {
    List<Role> role = service.getAll();
    return role.stream().map(this::convertToDto).collect(Collectors.toList());
  }

  private RoleResponseDto convertToDto(Role role) {
    return RoleResponseDto.builder().id(role.getId()).name(role.getName()).build();
  }
}
