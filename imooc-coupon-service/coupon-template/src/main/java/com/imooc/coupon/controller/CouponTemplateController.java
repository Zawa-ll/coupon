package com.example.coupon.controller;

import com.alibaba.fastjson.JSON;
import com.example.coupon.annotation.IgnorePermission;
import com.example.coupon.annotation.CouponPermission;
import com.example.coupon.entity.CouponTemplate;
import com.example.coupon.exception.CouponException;
import com.example.coupon.service.IBuildTemplateService;
import com.example.coupon.service.ITemplateBaseService;
import com.example.coupon.vo.CouponTemplateSDK;
import com.example.coupon.vo.TemplateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
public class CouponTemplateController {

    private final IBuildTemplateService buildTemplateService;

    private final ITemplateBaseService templateBaseService;

    @Autowired
    public CouponTemplateController(IBuildTemplateService buildTemplateService,
                                    ITemplateBaseService templateBaseService) {
        this.buildTemplateService = buildTemplateService;
        this.templateBaseService = templateBaseService;
    }

    /**
     * :: Construction of coupon templates
     * 127.0.0.1:7001/coupon-template/template/build
     * 127.0.0.1:9000//coupon-template/template/build
     * */
    @PostMapping("/template/build")
    @CouponPermission(description = "buildTemplate", readOnly = false)
    public CouponTemplate buildTemplate(@RequestBody TemplateRequest request)
            throws CouponException {
        log.info("Build Template: {}", JSON.toJSONString(request));
        return buildTemplateService.buildTemplate(request);
    }

    /**
     * 127.0.0.1:7001/coupon-template/template/info?id=1
     * 127.0.0.1:9000/example/coupon-template/template/info?id=1
     * */
    @GetMapping("/template/info")
    @CouponPermission(description = "buildTemplateInfo")
    public CouponTemplate buildTemplateInfo(@RequestParam("id") Integer id)
            throws CouponException {
        log.info("Build Template Info For: {}", id);
        return templateBaseService.buildTemplateInfo(id);
    }

    /**
     * 127.0.0.1:7001/coupon-template/template/sdk/all
     * 127.0.0.1:9000/example/coupon-template/template/sdk/all
     * */
    @GetMapping("/template/sdk/all")
    @IgnorePermission
    public List<CouponTemplateSDK> findAllUsableTemplate() {
        log.info("Find All Usable Template.");
        return templateBaseService.findAllUsableTemplate();
    }

    /**
     * Get the mapping of template ids to CouponTemplateSDK
     * 127.0.0.1:7001/coupon-template/template/sdk/infos
     * 127.0.0.1:9000/example/coupon-template/template/sdk/infos?ids=1,2
     * */
    @GetMapping("/template/sdk/infos")
    public Map<Integer, CouponTemplateSDK> findIds2TemplateSDK(
            @RequestParam("ids") Collection<Integer> ids
    ) {
        log.info("FindIds2TemplateSDK: {}", JSON.toJSONString(ids));
        return templateBaseService.findIds2TemplateSDK(ids);
    }
}
