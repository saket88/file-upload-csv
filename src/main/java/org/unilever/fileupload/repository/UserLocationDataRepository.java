package org.unilever.fileupload.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.unilever.fileupload.domain.UserLocationDataEntity;
import org.unilever.fileupload.model.UserLocationData;

public interface UserLocationDataRepository  extends JpaRepository<UserLocationDataEntity, Long> {
}
