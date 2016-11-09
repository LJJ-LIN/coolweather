package com.coolweather.app.util;

import android.text.TextUtils;

import com.coolweather.app.db.CoolWeatherDB;
import com.coolweather.app.model.City;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Province;

public class Utility {

	//解析和处理服务器返回的省级信息
	public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB,
			String response){
		if(!TextUtils.isEmpty(response)){
			String[] allProvince = response.split(",");
			if(allProvince!=null && allProvince.length>0){
				for(String p :allProvince){
					String[] array = p.split("\\|");
					Province province = new Province();
					province.setProvinceCode(array[0]);
					province.setProvinceName(array[1]);
					//将解析出来的数据存储到province表
					coolWeatherDB.saveProvince(province);
				}
				return true;
			}
		}
		return false;
	}
	
	//解析和处理服务器返回的市级信息
		public synchronized static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB,
				String response,int provinceId){
			if(!TextUtils.isEmpty(response)){
				String[] allCity = response.split(",");
				if(allCity!=null && allCity.length>0){
					for(String p :allCity){
						String[] array = p.split("\\|");
						City city = new City();
						city.setCityCode(array[0]);
						city.setCityName(array[1]);
						city.setProvinceId(provinceId);
						//将解析出来的数据存储到city表
						coolWeatherDB.saveCity(city);
					}
					return true;
				}
			}
			return false;
		}
		
		//解析和处理服务器返回的县级信息
		public synchronized static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB,
				String response,int cityId){
			if(!TextUtils.isEmpty(response)){
				String[] allCounty = response.split(",");
				if(allCounty!=null && allCounty.length>0){
					for(String p :allCounty){
						String[] array = p.split("\\|");
						County county = new County();
						county.setCountyCode(array[0]);
						county.setCountyName(array[1]);
						county.setCityId(cityId);
						//将解析出来的数据存储到county表
						coolWeatherDB.saveCounty(county);
					}
					return true;
				}
			}
			return false;
		}
}
