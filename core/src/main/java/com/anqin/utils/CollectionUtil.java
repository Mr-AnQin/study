package com.anqin.utils;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 集合 util
 *
 * @author Mr.An
 * @date 2024/02/27
 */
@UtilityClass
public class CollectionUtil {

    @SafeVarargs
    public static <T>  List<T> merge(List<T>... lists) {
        return merge(Arrays.asList(lists));
    }

    public static <T>  List<T> merge(List<List<T>> lists) {
        return lists.stream().flatMap(Collection::stream).distinct().collect(Collectors.toList());
    }

    public static <K, V> void mapReplaceKey(Map<K, V> map, K oldKey, K newKey) {
        map.put(newKey, map.get(oldKey));
        map.remove(oldKey);
    }

    /**
     * 映射替换键
     *
     * @param map       地图
     * @param functions 功能 按顺序执行
     */
    @SafeVarargs
    public static <K, V> void mapReplaceKeys(Map<K, V> map, Function<K, K>... functions) {
        Map<K, V> newMap = new LinkedHashMap<>(); // 保留顺序的新 Map

        map.forEach((key, value) -> {
            K newKey = key;
            for (Function<K, K> function : functions) {
                newKey = function.apply(newKey);
            }
            newMap.put(newKey, value);
        });
        map.clear(); // 清空原始 Map
        map.putAll(newMap); // 将新 Map 的内容放回原始 Map
    }

    /**
     * 映射替换键
     *
     * @param map       地图
     * @param functions 功能 按顺序执行
     */
    public static <K, V> void mapReplaceKeys(Map<K, V> map, BiFunction<K, V, K> functions) {

        Map<K, V> newMap = new LinkedHashMap<>(); // 保留顺序的新 Map
        map.forEach((key, value) -> newMap.put(functions.apply(key, value), value));

        map.clear(); // 清空原始 Map
        map.putAll(newMap); // 将新 Map 的内容放回原始 Map
    }

    /**
     * 映射替换值
     *
     * @param map       地图
     * @param functions 功能 按顺序执行
     */
    public static <K, V> void mapReplaceValues(Map<K, V> map, BiFunction<K, V, V> functions) {

        Map<K, V> newMap = new LinkedHashMap<>(); // 保留顺序的新 Map
        map.forEach((key, value) -> newMap.put(key, functions.apply(key, value)));

        map.clear(); // 清空原始 Map
        map.putAll(newMap); // 将新 Map 的内容放回原始 Map
    }

    public static <K, V> void mapReplaceValues(Map<K, V> map, Function<V, V> functions) {

        Map<K, V> newMap = new LinkedHashMap<>(); // 保留顺序的新 Map
        map.forEach((key, value) -> newMap.put(key, functions.apply(value)));

        map.clear(); // 清空原始 Map
        map.putAll(newMap); // 将新 Map 的内容放回原始 Map
    }

    /**
     * 映射值
     *
     * @param map      地图
     * @param function 功能
     * @return {@link Map}<{@link K}, {@link S}>
     */
    public static <K, V, S> Map<K, S> mapValues(Map<K, V> map, Function<V, S> function) {

        Map<K, S> newMap = new LinkedHashMap<>(); // 保留顺序的新 Map
        map.forEach((key, value) -> newMap.put(key, function.apply(value)));

        return newMap;
    }

    /**
     * 根据 key 修改value
     *
     * @param map       地图
     * @param key       钥匙
     * @param functions 功能
     */
    public static <K, V> void mapReplaceValue(Map<K, V> map, K key, Function<V, V> functions) {
        V v = map.get(key);
        if (v == null) {
            return;
        }
        V value = functions.apply(v);
        map.put(key, value);
    }

    /**
     * 映射列表
     *
     * @param list     列表
     * @param function 功能
     * @return {@link List}<{@link S}>
     */
    public static <S, V> List<S> mappingList(List<V> list, Function<V, S> function) {
        return list.stream()
                .map(function)
                .collect(Collectors.toList());
    }

}
