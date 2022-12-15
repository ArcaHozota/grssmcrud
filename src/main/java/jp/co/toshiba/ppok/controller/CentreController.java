package jp.co.toshiba.ppok.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;


import jakarta.validation.Valid;
import jp.co.toshiba.ppok.service.CityDtoService;
import jp.co.toshiba.ppok.entity.CityDto;
import jp.co.toshiba.ppok.utils.RestMsg;

import javax.annotation.Resource;

/**
 * Center Terminal Controller Handle the retrieve and update requests.
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/public/grssmcrud")
public class CentreController {

    @Resource
    private CityDtoService cityDtoService;

    /**
     * Retrieve the city data.
     *
     * @return page(JSON)
     */
    @GetMapping(value = "/city")
    public RestMsg getCities(@RequestParam(value = "pageNum") final Integer pageNum,
                             @RequestParam("name") final String name) {
        // 聲明分頁構造器；
        final Page<CityDto> pageInfo = Page.of(pageNum, 15);
        // 聲明條件構造器；
        final LambdaQueryWrapper<CityDto> queryWrapper = Wrappers.lambdaQuery(new CityDto());
        // 添加過濾條件；
        queryWrapper.like(StringUtils.isNotEmpty(name), CityDto::getName, name);
        // 添加排序條件；
        queryWrapper.orderByAsc(CityDto::getId);
        // 執行查詢；
        cityDtoService.page(pageInfo, queryWrapper);
        return RestMsg.success().add("pageInfo", pageInfo);
    }

    /**
     * Search the selected city's name.
     *
     * @param id the ID of city
     * @return RestMsg.success().add(data)
     */
    @GetMapping(value = "/city/{id}")
    public RestMsg getCityInfo(@PathVariable("id") final Long id) {
        final CityDto city = cityDtoService.getCityInfo(id);
        return RestMsg.success().add("citySelected", city);
    }

    /**
     * Save the input messages.
     *
     * @param cityDto the input message of cities
     * @return RestMsg.success()
     */
    @PostMapping(value = "/city")
    public RestMsg saveCityInfos(@Valid final CityDto cityDto, final BindingResult result) {
        final Map<String, Object> map = new HashMap<>(5);
        if (result.hasErrors()) {
            final List<FieldError> fieldErrors = result.getFieldErrors();
            for (final FieldError fieldError : fieldErrors) {
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return RestMsg.failure().add("errorFields", map);
        } else {
            cityDtoService.saveCityInfo(cityDto);
            return RestMsg.success();
        }
    }

    /**
     * Update city info.
     *
     * @param cityDto the input message of cities
     * @return RestMsg.success()
     */
    @PutMapping(value = "/city/{id}")
    public RestMsg updateCityInfo(@RequestBody final CityDto cityDto) {
        cityDtoService.updateCityInfo(cityDto);
        return RestMsg.success();
    }

    /**
     * Delete the selected city info.
     *
     * @param id the ID of city
     * @return RestMsg.success()
     */
    @DeleteMapping(value = "/city/{id}")
    public RestMsg deleteCityInfo(@PathVariable("id") final Long id) {
        cityDtoService.deleteCityInfo(id);
        return RestMsg.success();
    }

    /**
     * Check the input city name already existed or not.
     *
     * @param cityName the input name
     * @return RestMsg.success()
     */
    @GetMapping(value = "/checklist")
    public RestMsg checkCityName(@RequestParam("cityName") final String cityName) {
        final String regex = "^[a-zA-Z_-]{4,17}$";
        if (cityName.matches(regex)) {
            if (cityDtoService.checkDuplicated(cityName)) {
                return RestMsg.failure().add("validatedMsg", "City name is duplicate.");
            } else {
                return RestMsg.success();
            }
        } else {
            return RestMsg.failure().add("validatedMsg", "Name of cities should be in 4~17 Latin alphabets.");
        }
    }

    /**
     * Get list of continents.
     *
     * @return RestMsg.success().add(data)
     */
    @GetMapping(value = "/continents")
    public RestMsg getListOfContinents() {
        final List<CityDto> list = cityDtoService.getContinents();
        return RestMsg.success().add("continents", list);
    }

    /**
     * Get list of nations.
     *
     * @return RestMsg.success().add(data)
     */
    @GetMapping(value = "/nations")
    public RestMsg getListOfNations(@RequestParam("continentVal") final String continent) {
        final List<CityDto> list = cityDtoService.getNations(continent);
        return RestMsg.success().add("nations", list);
    }
}