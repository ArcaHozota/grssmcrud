package jp.co.toshiba.ppok.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import jp.co.toshiba.ppok.entity.CityView;

/**
 * @author Administrator
 */
@Mapper
public interface CityViewMapper extends BaseMapper<CityView> {

	/**
	 * Search continents of cities located on.
	 *
	 * @return List<CityDto>
	 */
	List<CityView> selectContinents();

	/**
	 * Search nation's name of cities.
	 *
	 * @param continent name of continent which the nation located on.
	 * @return List<CityDto>
	 */
	List<CityView> selectNations(String continent);
}