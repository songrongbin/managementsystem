package com.bins.managementsystem.permission.model;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Setter
@Getter
public class Role {
    private Long id;

    private String name;

    private String type;

    private List<Permission> permissions = new LinkedList<Permission>();

}
