package com.wty.web;
import com.wty.service.OptionService;
import com.wty.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value = "/demo")
public class DemoController {
    @Autowired
    OptionService optionService;
    @RequestMapping(value = "/{type}/{id}")
    String getDemo(Model model, @PathVariable String type, @PathVariable String id) {
        model.addAttribute("type" + type);
        model.addAttribute("id" + id);
        ExcelUtil.storeFile(null, type, id);
        model.addAttribute("json",optionService.getJson(type,id));
        return "demo";
    }

    @RequestMapping(value = "/{type}/{id}/downlaod")
    public void downlaod(@PathVariable String type, @PathVariable String id, HttpServletResponse res) {
        res.setHeader("Content-Disposition", "attachment; filename=" + type + id + ".xlsx");
        res.setContentType("application/octet-stream; charset=utf-8");
        try {
            FileCopyUtils.copy(ExcelUtil.getInputStream(type + id + ".xlsx"), res.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
