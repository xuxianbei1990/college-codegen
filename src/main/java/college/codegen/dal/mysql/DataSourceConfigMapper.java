package college.codegen.dal.mysql;


import college.codegen.dal.dataobject.DataSourceConfigDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据源配置 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface DataSourceConfigMapper extends BaseMapper<DataSourceConfigDO> {
}
