package com.dsfy.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsfy.entity.Region;
import com.dsfy.service.IRegionService;

@Controller
@RequestMapping(value = "/RegionService", produces = { "application/json;charset=UTF-8" })
public class RegionService {

	@Resource(name = "RegionService")
	IRegionService regionService;

	/**
	 * 根据父节点的主键查询地区信息 selecte * from region where parentId = id
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getByParent.do")
	public List<Region> getByParent(String id) {
		if (StringUtils.isEmpty(id)) {
			return null;
		}
		List<Region> regions = regionService.getByPrentId(Integer.parseInt(id));
		return regions;
	}
}
