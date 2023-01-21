package com.zerobase.fastlms.admin.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BannerInput extends CommonParam {

    long id;
    String bannerName;
    String openMode;
    String linkAddr;
    LocalDateTime addDt;
    long sortValue;
    boolean publicYn;

    //ADD
    String filename;
    String urlFilename;

    String idList;

}
