package com.reptile.util;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @ProjectName: Reptile
 * @ClassName: ListUtil
 * @Description: TODO(一句话描述该类的功能)
 * @Author: VTF
 * @create: 2020-03-18 10:31
 */
public class ListUtil {

    /**
     * 将size为M的集合以每eachPieceSize为单位分隔成N组
     *
     * @param data          数据
     * @param eachPieceSize 每组的数量
     * @param <T>           泛型
     * @return N个分组
     */
    public static <T> List<List<T>> splitToPieces(Collection<T> data, int eachPieceSize) {
        if (CollectionUtils.isEmpty(data)) {
            return new ArrayList<>(0);
        }
        if (eachPieceSize <= 0) {
            throw new IllegalArgumentException("参数错误");
        }
        List<List<T>> result = new ArrayList<>();
        for (int index = 0; index < data.size(); index += eachPieceSize) {
            result.add(data.stream().skip(index).limit(eachPieceSize).collect(Collectors.toList()));
        }
        return result;
    }
}
