package com.backtracking.MrDinner.domain.user.repository;

import com.backtracking.MrDinner.global.enumpackage.Department;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<User> containingUserName(String name){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%"+name+"%");
    }
    public static Specification<User> equalDepartment(Department department){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("department"), department);
    }

}
