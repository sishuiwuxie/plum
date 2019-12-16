package ${package};

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import com.ibeetl.admin.core.service.CorePlatformService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.beetl.sql.core.engine.PageQuery;
import org.jxls.common.Context;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.ReaderConfig;
import org.jxls.reader.XLSReadMessage;
import org.jxls.reader.XLSReadStatus;
import org.jxls.reader.XLSReader;
import org.jxls.util.JxlsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ibeetl.admin.console.web.dto.DictExcelImportData;
import com.ibeetl.admin.console.web.query.UserQuery;
import com.ibeetl.admin.core.annotation.Function;
import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.entity.CoreDict;
import com.ibeetl.admin.core.entity.CoreUser;
import com.ibeetl.admin.core.file.FileItem;
import com.ibeetl.admin.core.file.FileService;
import com.ibeetl.admin.core.web.JsonResult;
import com.ibeetl.admin.core.util.*;
import ${basePackage}.entity.${entity.name};
import  ${basePackage}.entity.dto.${entity.name}DetailDTO;
import ${basePackage}.service.*;
import ${basePackage}.web.query.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * ${entity.comment} 接口
 */
\@Api(value = "${entity.comment} 有关接口")
\@Controller
public class ${entity.name}Controller{
    private final Logger log= LoggerFactory.getLogger(this.getClass());

    private static final String MODEL = "/${target.urlBase}/${entity.code}";

    @var service=entity.code+"Service";
    \@Autowired private CorePlatformService platformService;

    \@Autowired private ${entity.name}Service ${service};
    
    \@Autowired
    FileService fileService;
    /* 页面 */


    \@GetMapping(MODEL + "/index.do")
    \@Function("${basicfunctionCode}.query")
    \@ResponseBody
    \@ApiOperation(value = "${entity.comment} 列表")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/${target.urlBase}/${entity.code}/index.html") ;
        view.addObject("search", ${entity.name}Query.class.getName());
        return view;
    }

    \@GetMapping(MODEL + "/edit.do")
    \@Function("${basicfunctionCode}.edit")
    \@ResponseBody
    \@ApiOperation(value = "打开 ${entity.comment} 编辑页面")
    public ModelAndView edit(${entity.idAttribute.javaType} ${entity.idAttribute.name}) {
        ModelAndView view = new ModelAndView("/${target.urlBase}/${entity.code}/edit.html");
        ${entity.name} ${entity.code} = ${service}.queryById(${entity.idAttribute.name});
        view.addObject("${entity.code}", ${entity.code});
        return view;
    }

    \@GetMapping(MODEL + "/add.do")
    \@Function("${basicfunctionCode}.add")
    \@ResponseBody
    \@ApiOperation(value = "打开 ${entity.comment} 新增页面")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("/${target.urlBase}/${entity.code}/add.html");
        return view;
    }

    /* ajax json */

    \@PostMapping(MODEL + "/list.json")
    \@Function("${basicfunctionCode}.query")
    \@ResponseBody
    \@ApiOperation(value = "AJAX请求,${entity.comment} 分页列表查询")
    public JsonResult<PageQuery> list(${entity.name}Query condition)
    {
        PageQuery page = condition.getPageQuery();
        ${service}.queryDetailsByCondition(page);
        return JsonResult.success(page);
    }

    \@PostMapping(MODEL + "/add.json")
    \@Function("${basicfunctionCode}.add")
    \@ResponseBody
    \@ApiOperation(value = "AJAX请求,提交新增 ${entity.comment}")
    public JsonResult add(\@Validated(ValidateConfig.ADD.class)${entity.name} ${entity.code})
    {
        ${entity.code}.set${idMethodName}(UUIDUtil.uuid());

        @if(createUserIdFieldExists){
        ${entity.code}.setCreateUserId(platformService.getCurrentUser().getId());
        @}
        @if(createTimeFieldExists){
        ${entity.code}.setCreateTime(new Date());
        @}
        @if(lastUpdateTimeFieldExists){
        ${entity.code}.setLastUpdateTime(new Date());
        @}

        ${service}.save(${entity.code});
        return JsonResult.success();
    }

    \@PostMapping(MODEL + "/edit.json")
    \@Function("${basicfunctionCode}.edit")
    \@ResponseBody
    \@ApiOperation(value = "AJAX请求, 提交更新 ${entity.comment}")
    public JsonResult<String> update(\@Validated(ValidateConfig.UPDATE.class)  ${entity.name} ${entity.code}) {
        @if(lastUpdateTimeFieldExists){
        ${entity.code}.setLastUpdateTime(new Date());
        @}
        boolean success = ${service}.update(${entity.code});
        if (success) {
            return JsonResult.success();
        } else {
            return JsonResult.failMessage("保存失败");
        }
    }


   
    \@GetMapping(MODEL + "/view.json")
    \@Function("${basicfunctionCode}.query")
    \@ResponseBody
    \@ApiOperation(value = "AJAX请求,根据主键查询 ${entity.comment}")
    public JsonResult<${entity.name}DetailDTO>queryInfo(${entity.idAttribute.javaType} ${entity.idAttribute.name}) {
        ${entity.name}DetailDTO ${entity.code} = ${service}.queryDetailById( ${entity.idAttribute.name});
        return  JsonResult.success(${entity.code});
    }

    \@PostMapping(MODEL + "/delete.json")
    \@Function("${basicfunctionCode}.delete")
    \@ResponseBody
    \@ApiOperation(value = "AJAX请求,删除多个 ${entity.comment}")
    public JsonResult delete(String ids) {
        if (ids.endsWith(",")) {
            ids = StringUtils.substringBeforeLast(ids, ",");
        }
        //List<${entity.idAttribute.javaType}> idList = ConvertUtil.str2longs(ids);
        List<${entity.idAttribute.javaType}> idList = Arrays.asList(StringUtils.split(ids, ","));
        ${service}.batchDel${entity.name}(idList);
        return new JsonResult().success();
    }
    
    @if(entity.includeExcel){
    
    \@PostMapping(MODEL + "/excel/export.json")
    \@Function("${basicfunctionCode}.export")
    \@ResponseBody
    \@ApiOperation(value = "AJAX请求, 导出excel")
    public JsonResult<String> export(HttpServletResponse response,${entity.name}Query condition) {
        /**
         * 1)需要用你自己编写一个的excel模板
         * 2)通常excel导出需要关联更多数据，因此${service}.queryByCondition方法经常不符合需求，需要重写一个为模板导出的查询
         * 3)参考ConsoleDictController来实现模板导入导出
         */
        String excelTemplate ="excelTemplates/${target.urlBase}/${entity.code}/你的excel模板文件名字.xls";
        PageQuery<${entity.name}> page = condition.getPageQuery();
        //取出全部符合条件的
        page.setPageSize(Integer.MAX_VALUE);
        page.setPageNumber(1);
        page.setTotalRow(Integer.MAX_VALUE);
        //本次导出需要的数据
        List<${entity.name}> list =${service}.queryByCondition(page).getList();
        try(InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(excelTemplate)) {
            if(is==null) {
                throw new PlatformException("模板资源不存在："+excelTemplate);
            }
            FileItem item = fileService.createFileTemp("${entity.displayName}_"+DateUtil.now("yyyyMMddHHmmss")+".xls");
            OutputStream os = item.openOutputStream();
            Context context = new Context();
            context.putVar("list", list);
            JxlsHelper.getInstance().processTemplate(is, os, context);
            os.close();
            //下载参考FileSystemContorller
            return  JsonResult.success(item.getPath());
        } catch (IOException e) {
            throw new PlatformException(e.getMessage());
        }
        
    }
    
    \@PostMapping(MODEL + "/excel/import.do")
    \@Function("${basicfunctionCode}.import")
    \@ResponseBody
    \@ApiOperation(value = "AJAX请求,导入excel")
    public JsonResult importExcel(\@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
           return JsonResult.fail();
        }
        InputStream ins = file.getInputStream();
        /*解析模板并导入到数据库里,参考DictConsoleContorller，使用jxls reader读取excel数据*/
        ins.close();
        return JsonResult.success();
    }
    
    
    @}

}
