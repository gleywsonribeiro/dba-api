package com.dba.dbaapi.repository;

import com.dba.dbaapi.model.UserDocumentation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDocumentationRepository extends JpaRepository<UserDocumentation, Long> {
}
