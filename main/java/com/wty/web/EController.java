package com.wty.web;

import com.wty.service.OptionService;
import com.wty.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 *
 */
@Controller
@RequestMapping(value = "/series")
public class EController {
    @Autowired
    OptionService optionService;

    @RequestMapping(value = "/{type}/{id}/upload",method = RequestMethod.POST)
    public String upload(@RequestParam(value = "file") MultipartFile file, @PathVariable String type, @PathVariable String id, Model model) {
        ExcelUtil.storeFile(file, type, id);
        model.addAttribute("json", optionService.getJson(type, id));
        return "user";
    }

}
