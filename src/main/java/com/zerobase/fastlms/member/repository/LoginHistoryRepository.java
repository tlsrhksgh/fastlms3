package com.zerobase.fastlms.member.repository;

import com.zerobase.fastlms.admin.dto.LoginHistoryDto;
import com.zerobase.fastlms.member.entity.LoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoginHistoryRepository extends JpaRepository<LoginHistory, Long> {

    List<LoginHistory> findByUserId(String userId);

    LoginHistory findTop1ByUserIdOrderByLoginDtDesc(String UserId);
}
