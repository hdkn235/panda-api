package com.hd.api.controller;

import com.hd.api.dto.DictDTO;
import com.hd.api.service.DictService;
import com.hd.app.bean.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 字典控制器
 */
@RestController
@RequestMapping(value = "/dict")
public class DictController extends BaseController {

	@Autowired
	private DictService dictService;

	/**
	 * 获取字典数据
	 * 
	 * @param type
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Response show(@RequestParam(value = "type", required = false) String type) {
		List<DictDTO> list = dictService.getByClassCode(type);
		if (list.size() > 0) {
			return new Response().success(list);
		} else {
			return new Response().empty();
		}
	}

}
