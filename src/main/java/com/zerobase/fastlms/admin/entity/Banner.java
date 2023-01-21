package com.zerobase.fastlms.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Banner {

    @Id
    @GeneratedValue
    Long id;

    String bannerName;
    LocalDateTime addDt;
    String linkAddr;
    String openMode;
    long sortValue;
    boolean publicYn;

    String filename;
    String urlFilename;

}
