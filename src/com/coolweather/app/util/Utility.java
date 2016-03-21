package com.coolweather.app.util;

import com.coolweather.app.db.CoolWeatherDB;
import com.coolweather.app.model.City;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Province;

import android.text.TextUtils;

public class Utility {

	/**
	 * 解析和处理服务器返回的省级数据
	 * 
	 * @param db
	 * @param response
	 * @return
	 */
	public synchronized static boolean handleProvincesResponse(CoolWeatherDB db, String response) {
		if (!TextUtils.isEmpty(response)) {
			String[] allProvinces = response.split(",");
			if (allProvinces != null && allProvinces.length > 0) {
				for (String p : allProvinces) {
					String[] array = p.split("\\|");
					Province province = new Province();
					province.setProvinceCode(array[0]);
					province.setProvinceName(array[1]);
					db.saveProvince(province);
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * 解析和处理服务器返回的城市数据
	 * 
	 * @param db
	 * @param response
	 * @return
	 */
	public synchronized static boolean handleCitesResponse(CoolWeatherDB db, String response, int provinceId) {
		if (!TextUtils.isEmpty(response)) {
			String[] allCites = response.split(",");
			if (allCites != null && allCites.length > 0) {
				for (String c : allCites) {
					String[] array = c.split("\\|");
					City city = new City();
					city.setCityCode(array[0]);
					city.setCityName(array[1]);
					city.setProvinceId(provinceId);
					db.saveCity(city);
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * 解析和处理服务器返回的县城数据
	 * 
	 * @param db
	 * @param response
	 * @return
	 */
	public synchronized static boolean handleCountiesResponse(CoolWeatherDB db, String response, int cityId) {
		if (!TextUtils.isEmpty(response)) {
			String[] allCounties = response.split(",");
			if (allCounties != null && allCounties.length > 0) {
				for (String c : allCounties) {
					String[] array = c.split("\\|");
					County county = new County();
					county.setCountyCode(array[0]);
					county.setCountyName(array[1]);
					county.setCityId(cityId);
					db.saveCounty(county);
				}
				return true;
			}
		}
		return false;
	}
}
