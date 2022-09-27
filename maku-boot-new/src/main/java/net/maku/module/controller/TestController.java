package net.maku.module.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.maku.framework.common.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 新模块测试
 * <p>
 * 一、@Controller、@RestController<p>
 * 1. 在@controller注解中，返回的是字符串匹配的模板名称，即直接渲染视图，与html页面配合使用的。<p>
 * 2. 若想用@Controller返回json字符串，则得配合@ResponseBody。<p>
 * 3. 使用@RestController，则无法返回页面，配置的视图解析器不起作用，返回json字符串。<p>
 * 4. 在controller上加注解@Controller 和@RestController都可以在前端调用接口<p>
 * 综上所述：@RestController = @ResponseBody + @Controller.
 * <p>
 * 二、RequestMapping<p>
 * 作用：将请求和处理请求的控制器方法关联起来，建立映射关系。<p>
 * 位置：<p>
 * 1. 标识类：设置映射请求的请求路径的初始信息<p>
 * 2. 表示方法：设置映射请求的请求路径的具体信息<p>
 *
 * @author wangkai
 */
@RestController
@RequestMapping("new/test")
@Tag(name = "新模块测试")
@AllArgsConstructor
public class TestController {

    @GetMapping()
    @Operation(summary = "测试接口")
    public Result<String> test() {

        return Result.ok("测试数据");
    }
}
