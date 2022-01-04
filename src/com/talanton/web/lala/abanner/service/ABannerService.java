package com.talanton.web.lala.abanner.service;

import com.talanton.web.lala.abanner.model.Banner;
import com.talanton.web.lala.abanner.model.BannerInfo;

public interface ABannerService {
	Banner getBanner(int kind, int position);
	int addBanner(Banner banner);
	int updateBanner(Banner banner);
	int addBannerInfo(BannerInfo bannerInfo);
	Banner getBannerOnly(int kind, int position);
	int updateBannerInfo(BannerInfo bannerInfo);
	int removeBannerInfo(int info_id);
}