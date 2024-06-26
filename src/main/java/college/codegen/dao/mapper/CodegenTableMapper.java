package college.codegen.dao.mapper;


import college.codegen.dao.entity.CodegenTableDO;
import college.codegen.vo.CodegenTablePageReqVO;
import college.codegen.vo.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CodegenTableMapper extends BaseMapper<CodegenTableDO> {

    default List<CodegenTableDO> selectListByDataSourceConfigId(Long dataSourceConfigId) {
        return selectList(Wrappers.lambdaQuery(CodegenTableDO.class).eq(CodegenTableDO::getDataSourceConfigId, dataSourceConfigId));
    }

    default CodegenTableDO selectByTableNameAndDataSourceConfigId(String tableName, Long dataSourceConfigId) {
        return selectOne(Wrappers.lambdaQuery(CodegenTableDO.class).eq(CodegenTableDO::getTableName, tableName)
                .eq(CodegenTableDO::getDataSourceConfigId, dataSourceConfigId));
    }

    default PageResult<CodegenTableDO> selectPage(CodegenTablePageReqVO pageReqVO) {
        LambdaQueryWrapper<CodegenTableDO> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(pageReqVO.getTableName()), CodegenTableDO::getTableName, pageReqVO.getTableName())
                .like(StringUtils.isNotBlank(pageReqVO.getTableComment()), CodegenTableDO::getTableComment, pageReqVO.getTableComment())
                .like(StringUtils.isNotBlank(pageReqVO.getClassName()), CodegenTableDO::getClassName, pageReqVO.getClassName())
                .orderByDesc(CodegenTableDO::getUpdateTime);
        IPage<CodegenTableDO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        selectPage(page, lambdaQueryWrapper);
        PageResult pageResult = new PageResult<>(page.getRecords(), page.getTotal());
        pageResult.setList(page.getRecords());
        return pageResult;
    }

    default List<CodegenTableDO> selectListByTemplateTypeAndMasterTableId(Integer templateType, Long masterTableId) {
        return selectList(Wrappers.lambdaQuery(CodegenTableDO.class)
                .eq(CodegenTableDO::getTemplateType, templateType)
                .eq(CodegenTableDO::getMasterTableId, masterTableId));
    }
}
