package jp.co.toshiba.ppok.controller;

/**
 * Center Terminal Controller
 * Handle the retrieve and update requests.
 *
 * @author Administrator
 */
//@RestController
//public class CityController {
//
//    @Resource
//
//
//    /**
//     * Retrieve the city data.
//     *
//     * @return page(JSON)
//     */
//    @GetMapping(value = "/city")
//    public Message getCities(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
//        PageMethod.startPage(pageNum, 10);
//        List<City> citiesList = cityService.getAll();
//        PageInfo<City> pages = new PageInfo<>(citiesList, 7);
//        return Message.success().add("pageInfo", pages);
//    }
//
//    /**
//     * Save the inputed messages.
//     *
//     * @param city the input message of cities;
//     * @return Message.success();
//     */
//    @PostMapping(value = "/city")
//    public Message saveCityInfos(@Valid City city, BindingResult result) {
//        Map<String, Object> map = new HashMap<>();
//        if (result.hasErrors()) {
//            List<FieldError> fieldErrors = result.getFieldErrors();
//            for (FieldError fieldError : fieldErrors) {
//                map.put(fieldError.getField(), fieldError.getDefaultMessage());
//            }
//            return Message.failure().add("errorFields", map);
//        } else {
//            cityService.insert(city);
//            return Message.success();
//        }
//    }
//
//    /**
//     * Check the input city name already existed or not.
//     */
//    @GetMapping(value = "/checklist")
//    public Message checkCityName(@RequestParam("cityName") String cityName) {
//        final String regex = "^[a-zA-Z_-]{4,17}$";
//        if (cityName.matches(regex)) {
//            if (cityService.selectCount(null) == 0) {
//                // wrong
//                return Message.success();
//            } else {
//                return Message.failure().add("validatedMsg", "City name is duplicate.");
//            }
//        } else {
//            return Message.failure().add("validatedMsg", "Name of cities should be in 4~17 Latin alphabets.");
//        }
//    }
//
//    /**
//     * Search the selected city's name.
//     */
//    @GetMapping(value = "/city/{id}")
//    public Message getCityName(@PathVariable("id") Integer id) {
//        City cities = cityService.selectById(id);
//        return Message.success().add("citiesSelected", cities);
//    }
//
//    /**
//     * Save the input changed city info.
//     */
//    @PutMapping(value = "/city/{id}")
//    public Message saveCityChanges(@RequestBody City city) {
//        cityService.updateById(city);
//        return Message.success();
//    }
//
//    /**
//     * Delete the selected city info.
//     */
//    @DeleteMapping(value = "/city/{id}")
//    public Message deleteCityInfo(@PathVariable("id") Integer id) {
//        cityService.deleteById(id);
//        return Message.success();
//    }
//}
