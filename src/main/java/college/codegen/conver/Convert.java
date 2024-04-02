package college.codegen.conver;


import cn.hutool.core.collection.CollUtil;
import college.codegen.column.CodegenColumnRespVO;
import college.codegen.dal.dataobject.CodegenColumnDO;
import college.codegen.dal.dataobject.CodegenTableDO;
import college.codegen.object.BeanUtils;
import college.codegen.pojo.PageResult;
import college.codegen.table.CodegenTableRespVO;
import college.codegen.vo.CodegenDetailRespVO;
import college.codegen.vo.CodegenPreviewRespVO;
import com.baomidou.mybatisplus.core.metadata.IPage;


import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * User: EDY
 * Date: 2024/3/18
 * Time: 18:53
 * Version:V1.0
 */
public class Convert {

    public static CodegenDetailRespVO convert(CodegenTableDO table, List<CodegenColumnDO> columns) {
        CodegenDetailRespVO respVO = new CodegenDetailRespVO();
        respVO.setTable(BeanUtils.toBean(table, CodegenTableRespVO.class));
        respVO.setColumns(BeanUtils.toBean(columns, CodegenColumnRespVO.class));
        return respVO;
    }

    public static List<CodegenPreviewRespVO> convert(Map<String, String> codes) {
        return convertList(codes.entrySet(),
                entry -> {
                    CodegenPreviewRespVO respVO = new CodegenPreviewRespVO();
                    respVO.setFilePath(entry.getKey());
                    respVO.setCode(entry.getValue());
                    return respVO;
                });
    }

    public static <T, U> List<U> convertList(Collection<T> from, Function<T, U> func) {
        if (CollUtil.isEmpty(from)) {
            return new ArrayList<>();
        }
        return from.stream().map(func).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public static <R, T> PageResult<R> convertPage(IPage<T> page, Class<R> rClass, BiConsumer<T, R> consumer) {
        PageResult<R> result = new PageResult<>();
        result.setTotal(page.getTotal());
        ArrayList<R> list = new ArrayList<>();
        for (T orderMainPage : page.getRecords()) {
            R orderMainVO = null;
            try {
                orderMainVO = rClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            org.springframework.beans.BeanUtils.copyProperties(orderMainPage, orderMainVO);
            if (consumer != null) {
                consumer.accept(orderMainPage, orderMainVO);
            }
            list.add(orderMainVO);
        }
        result.setList(list);
        return result;
    }

    public static <R, T> PageResult<R> convertPage(IPage<T> page, Class<R> rClass) {
        return convertPage(page, rClass, null);
    }
}
