package com.schedular.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.schedular.model.EmailRequest;


@Repository
public interface EmailRepo extends JpaRepository<EmailRequest, String> {

}
