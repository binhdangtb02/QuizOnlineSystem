package com.swp391.QuizSytem.repository;
import com.swp391.QuizSytem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long>{
    public boolean existsByName(String name);
    public Role findByName(String name);

}
