package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.entity.Banner;
import com.zerobase.fastlms.admin.mapper.BannerMapper;
import com.zerobase.fastlms.admin.model.BannerInput;
import com.zerobase.fastlms.admin.model.BannerParam;
import com.zerobase.fastlms.admin.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BannerServiceImpl implements BannerService {

    private final BannerRepository bannerRepository;
    private final BannerMapper bannerMapper;

    @Override
    public List<BannerDto> list(BannerParam parameter) {

        long totalCount = bannerMapper.selectListCount(parameter);

        List<BannerDto> list = bannerMapper.selectList(parameter);
        if (!CollectionUtils.isEmpty(list)) {
            int i = 0;
            for (BannerDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i++;
            }
        }

        return list;
    }

    @Override
    public boolean add(BannerInput parameter) {

        String protocolType = "http://";

        Banner banner = Banner.builder()
                .bannerName(parameter.getBannerName())
                .urlFilename(parameter.getUrlFilename())
                .linkAddr(protocolType + parameter.getLinkAddr())
                .sortValue(parameter.getSortValue())
                .publicYn(parameter.isPublicYn())
                .filename(parameter.getFilename())
                .openMode(parameter.getOpenMode())
                .addDt(LocalDateTime.now())
                .build();

        bannerRepository.save(banner);

        return false;
    }

    @Override
    public BannerDto getById(long id) {

        return bannerRepository.findById(id).map(BannerDto::of).orElse(null);
    }

    @Override
    public boolean set(BannerInput parameter) {

        Optional<Banner> optionalBanner = bannerRepository.findById(parameter.getId());
        if (!optionalBanner.isPresent()) {
            return false;
        }

        Banner banner = optionalBanner.get();
        banner.setBannerName(parameter.getBannerName());
        banner.setFilename(parameter.getFilename());
        banner.setLinkAddr(parameter.getLinkAddr());
        banner.setOpenMode(parameter.getOpenMode());
        banner.setSortValue(parameter.getSortValue());
        banner.setPublicYn(parameter.isPublicYn());

        bannerRepository.save(banner);

        return true;
    }

    @Override
    public boolean delete(String idList) {

        if (idList != null && idList.length() > 0) {
            String[] ids = idList.split(",");
            for (String s : ids) {
                long id = 0L;
                try {
                    id = Long.parseLong(s);
                } catch (Exception e) {
                    System.out.println(e);
                    return false;
                }

                if (id > 0) {
                    bannerRepository.deleteById(id);
                }
            }
        }

        return true;
    }

    @Override
    public List<BannerDto> getBannerBySortValue(boolean publicYn) {

        List<Banner> banners = bannerRepository.findByPublicYnOrderBySortValueDesc(publicYn);

        return banners.stream()
                .map(BannerDto::of)
                .collect(Collectors.toList());
    }
}
