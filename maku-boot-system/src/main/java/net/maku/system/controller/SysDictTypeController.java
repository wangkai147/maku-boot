package net.maku.system.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.maku.framework.common.page.PageResult;
import net.maku.framework.common.utils.Result;
import net.maku.system.convert.SysDictTypeConvert;
import net.maku.system.entity.SysDictTypeEntity;
import net.maku.system.query.SysDictTypeQuery;
import net.maku.system.service.SysDictTypeService;
import net.maku.system.vo.SysDictTypeVO;
import net.maku.system.vo.SysDictVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 字典类型
 *
 * @author wangkai
 */
@RestController
@RequestMapping("sys/dict/type")
@Tag(name = "字典类型")
@AllArgsConstructor
public class SysDictTypeController {
    private final SysDictTypeService sysDictTypeService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @PreAuthorize("hasAuthority('sys:dict:page')")
    public Result<PageResult<SysDictTypeVO>> page(@Valid SysDictTypeQuery query) {
        PageResult<SysDictTypeVO> page = sysDictTypeService.page(query);

        return Result.ok(page);
    }

    @GetMapping("list/sql")
    @Operation(summary = "动态SQL数据")
    @PreAuthorize("hasAuthority('sys:dict:page')")
    public Result<PageResult<SysDictVO.DictData>> listSql(Long id) {
        List<SysDictVO.DictData> list = sysDictTypeService.getDictSql(id);

        PageResult<SysDictVO.DictData> page = new PageResult<>(list, list.size());

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @PreAuthorize("hasAuthority('sys:dict:info')")
    public Result<SysDictTypeVO> get(@PathVariable("id") Long id) {
        SysDictTypeEntity entity = sysDictTypeService.getById(id);

        return Result.ok(SysDictTypeConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    @PreAuthorize("hasAuthority('sys:dict:save')")
    public Result<String> save(@RequestBody @Valid SysDictTypeVO vo) {
        sysDictTypeService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    @PreAuthorize("hasAuthority('sys:dict:update')")
    public Result<String> update(@RequestBody @Valid SysDictTypeVO vo) {
        sysDictTypeService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @PreAuthorize("hasAuthority('sys:dict:delete')")
    public Result<String> delete(@RequestBody List<Long> idList) {
        sysDictTypeService.delete(idList);

        return Result.ok();
    }

    @GetMapping("all")
    @Operation(summary = "全部字典数据")
    public Result<List<SysDictVO>> all() {
        List<SysDictVO> dictList = sysDictTypeService.getDictList();

        return Result.ok(dictList);
    }

}