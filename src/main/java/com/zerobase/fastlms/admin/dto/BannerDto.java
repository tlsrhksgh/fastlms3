package com.zerobase.fastlms.admin.dto;

import com.zerobase.fastlms.admin.entity.Banner;
import com.zerobase.fastlms.admin.model.CommonParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BannerDto {

    Long id;
    String bannerName;
    LocalDateTime addDt;
    String linkAddr;
    String openMode;
    long sortValue;
    boolean publicYn;

    String filename;
    String urlFilename;

    //추가컬럼
    long totalCount;
    long seq;

    public static BannerDto of(Banner banner) {

        return BannerDto.builder()
                .id(banner.getId())
                .bannerName(banner.getBannerName())
                .addDt(banner.getAddDt())
                .linkAddr(banner.getLinkAddr())
                .openMode(banner.getOpenMode())
                .sortValue(banner.getSortValue())
                .publicYn(banner.isPublicYn())
                .filename(banner.getFilename())
                .urlFilename(banner.getUrlFilename())
                .build();

    }
}
