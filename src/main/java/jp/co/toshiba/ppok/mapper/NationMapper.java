package jp.co.toshiba.ppok.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import jp.co.toshiba.ppok.entity.Country;

@Mapper
public interface NationMapper extends BaseMapper<Country> {
}
