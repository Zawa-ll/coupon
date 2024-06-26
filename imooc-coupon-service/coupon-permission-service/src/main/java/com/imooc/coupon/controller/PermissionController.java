package com.example.coupon.controller;

import com.example.coupon.annotation.IgnoreResponseAdvice;
import com.example.coupon.service.PathService;
import com.example.coupon.service.PermissionService;
import com.example.coupon.vo.CheckPermissionRequest;
import com.example.coupon.vo.CreatePathRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class PermissionController {

    private final PathService pathService;
    private final PermissionService permissionService;

    @Autowired
    public PermissionController(PathService pathService,
                                PermissionService permissionService) {
        this.pathService = pathService;
        this.permissionService = permissionService;
    }

    @PostMapping("/create/path")
    public List<Integer> createPath(@RequestBody CreatePathRequest request) {

        log.info("createPath: {}", request.getPathInfos().size());
        return pathService.createPath(request);
    }

    @IgnoreResponseAdvice
    @PostMapping("/check/permission")
    public Boolean checkPermission(@RequestBody CheckPermissionRequest request) {

        log.info("checkPermission for args: {}, {}, {}",
                request.getUserId(), request.getUri(), request.getHttpMethod());
        return permissionService.checkPermission(
                request.getUserId(), request.getUri(), request.getHttpMethod()
        );
    }
}
