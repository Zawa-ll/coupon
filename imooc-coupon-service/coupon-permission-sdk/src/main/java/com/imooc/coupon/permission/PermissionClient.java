package com.example.coupon.permission;

import com.example.coupon.vo.CheckPermissionRequest;
import com.example.coupon.vo.CommonResponse;
import com.example.coupon.vo.CreatePathRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 路径创建与权限校验功能 Feign 接口实现
  
 */
@FeignClient(value = "eureka-client-coupon-permission")
public interface PermissionClient {

    @RequestMapping(value = "/coupon-permission/create/path",
            method = RequestMethod.POST)
    CommonResponse<List<Integer>> createPath(
            @RequestBody CreatePathRequest request
            );

    @RequestMapping(value = "/coupon-permission/check/permission",
    method = RequestMethod.POST)
    Boolean checkPermission(@RequestBody CheckPermissionRequest request);
}
