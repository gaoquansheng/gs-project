package cn.edu.cidp.pm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.cidp.common.enums.UserStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.edu.cidp.framework.aspectj.lang.annotation.Log;
import cn.edu.cidp.framework.aspectj.lang.enums.BusinessType;
import cn.edu.cidp.pm.domain.UnitReview;
import cn.edu.cidp.pm.service.IUnitReviewService;
import cn.edu.cidp.framework.web.controller.BaseController;
import cn.edu.cidp.framework.web.domain.AjaxResult;
import cn.edu.cidp.common.utils.poi.ExcelUtil;
import cn.edu.cidp.framework.web.page.TableDataInfo;

/**
 * 单位项目审核Controller
 *
 * @author william
 * @date 2020-10-20
 */
@RestController
@RequestMapping("/pm/unitreview")
public class UnitReviewController extends BaseController
{
    @Autowired
    private IUnitReviewService unitReviewService;

    /**
     * 查询单位项目审核列表
     */
//    @PreAuthorize("@ss.hasPermi('pm:unitreview:list')")
    @GetMapping("/list")
    public TableDataInfo list(UnitReview unitReview)
    {
        startPage();
        List<UnitReview> list = unitReviewService.selectUnitReviewList(unitReview);
        return getDataTable(list);
    }

    /**
     * 导出单位项目审核列表
     */
//    @PreAuthorize("@ss.hasPermi('pm:unitreview:export')")
    @Log(title = "单位项目审核", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(UnitReview unitReview)
    {
        List<UnitReview> list = unitReviewService.selectUnitReviewList(unitReview);
        ExcelUtil<UnitReview> util = new ExcelUtil<UnitReview>(UnitReview.class);
        return util.exportExcel(list, "unitreview");
    }

    /**
     * 获取单位项目审核详细信息
     */
//    @PreAuthorize("@ss.hasPermi('pm:unitreview:query')")
    @GetMapping(value = "/{projectId}")
    public AjaxResult getInfo(@PathVariable("projectId") Long projectId)
    {
        return AjaxResult.success(unitReviewService.selectUnitReviewById(projectId));
    }

    /**
     * 新增单位项目审核
     */
//    @PreAuthorize("@ss.hasPermi('pm:unitreview:add')")
    @Log(title = "单位项目审核", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UnitReview unitReview)
    {
        return toAjax(unitReviewService.insertUnitReview(unitReview));
    }

    /**
     * 修改单位项目审核
     */
//    @PreAuthorize("@ss.hasPermi('pm:unitreview:edit')")
    @Log(title = "单位项目审核", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UnitReview unitReview)
    {
        return toAjax(unitReviewService.updateUnitReview(unitReview));
    }

    /**
     * 删除单位项目审核
     */
//    @PreAuthorize("@ss.hasPermi('pm:unitreview:remove')")
    @Log(title = "单位项目审核", businessType = BusinessType.DELETE)
	@DeleteMapping("/{projectIds}")
    public AjaxResult remove(@PathVariable Long[] projectIds)
    {
        return toAjax(unitReviewService.deleteUnitReviewByIds(projectIds));
    }

    @GetMapping("/unitProjectStatus")
    public TableDataInfo getUnitProjectStatus(){
        ArrayList<HashMap<String,String>> list = new ArrayList<>();
        HashMap<String, String> map = new HashMap<String, String>(){
            {
                put("label","待单位推荐");
                put("value","1002");
            }
        };
        HashMap<String, String> map1 = new HashMap<String, String>(){
            {
                put("label","待单位推荐任务书");
                put("value","2002");
            }
        };
        HashMap<String, String> map2 = new HashMap<String, String>(){
            {
                put("label","待单位推荐验收材料");
                put("value","4002");
            }
        };
        list.add(map);
        list.add(map1);
        list.add(map2);
        return getDataTable(list);
    }


    @GetMapping("/projectStatus")
    public TableDataInfo getProjectStatus(){
        ArrayList<HashMap<String,String>> list = new ArrayList<>();
        HashMap<String, String> map = new HashMap<String, String>(){
            {
                put("label","待资格审查");
                put("value","1003");
            }
        };
        HashMap<String, String> map1 = new HashMap<String, String>(){
            {
                put("label","待入库");
                put("value","1004");
            }
        };
        HashMap<String, String> map2 = new HashMap<String, String>(){
            {
                put("label","待同行评议");
                put("value","1005");
            }
        };
        HashMap<String, String> map3 = new HashMap<String, String>(){
            {
                put("label","待综合评审");
                put("value","1006");
            }
        };
        HashMap<String, String> map4 = new HashMap<String, String>(){
            {
                put("label","待预算评审");
                put("value","1007");
            }
        };
        HashMap<String, String> map5 = new HashMap<String, String>(){
            {
                put("label","待立项");
                put("value","1008");
            }
        };
//        HashMap<String, String> map6 = new HashMap<String, String>(){
//            {
//                put("label","待填报任务书");
//                put("value","2001");
//            }
//        };
        HashMap<String, String> map7 = new HashMap<String, String>(){
            {
                put("label","待复查任务书");
                put("value","2003");
            }
        };
        HashMap<String, String> map8 = new HashMap<String, String>(){
            {
                put("label","待可研");
                put("value","3001");
            }
        };
        HashMap<String, String> map9 = new HashMap<String, String>(){
            {
                put("label","待初设");
                put("value","3002");
            }
        };
        HashMap<String, String> map10 = new HashMap<String, String>(){
            {
                put("label","待实施");
                put("value","3003");
            }
        };
//        HashMap<String, String> map11 = new HashMap<String, String>(){
//            {
//                put("label","待填报验收材料");
//                put("value","4001");
//            }
//        };
        HashMap<String, String> map12 = new HashMap<String, String>(){
            {
                put("label","待复查结题材料");
                put("value","4003");
            }
        };
        HashMap<String, String> map13 = new HashMap<String, String>(){
            {
                put("label","待结题验收");
                put("value","4004");
            }
        };
        list.add(map);
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
//        list.add(map6);
        list.add(map7);
        list.add(map8);
        list.add(map9);
        list.add(map10);
//        list.add(map11);
        list.add(map12);
        list.add(map13);
        return getDataTable(list);
    }
}
