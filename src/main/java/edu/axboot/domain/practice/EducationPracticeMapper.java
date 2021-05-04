package edu.axboot.domain.practice;

import com.chequer.axboot.core.mybatis.MyBatisMapper;

import java.util.HashMap;
import java.util.List;
// Mapper정의
// public interface EducationPracticeMapper extends MyBatisMapper {
public interface EducationPracticeMapper {
    List<EducationPractice> select(HashMap<String, String> params);
    EducationPractice selectOne(Long id);

    int insert(EducationPractice educationPractice);
    int update(EducationPractice educationPractice);
    int delete(Long id);
}
