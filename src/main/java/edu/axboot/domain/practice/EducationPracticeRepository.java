package edu.axboot.domain.practice;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationPracticeRepository extends AXBootJPAQueryDSLRepository<EducationPractice, Long> {
}
