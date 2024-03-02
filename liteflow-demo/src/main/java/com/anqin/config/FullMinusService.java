package com.anqin.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

/**
 * 满减
 *
 * @author Mr.An
 * @date 2024/03/02
 */
public interface FullMinusService {


    /**
     * 获取所有满减配置
     *
     * @return {@link List}<{@link FullMinus}>
     */
    List<FullMinus> getAllFullMinusConfig();

    /**
     * 满减
     *
     * @author Mr.An
     * @date 2024/03/02
     */
    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    class FullMinus {

        /**
         * 满
         */
        private BigDecimal full;

        /**
         * 减去
         */
        private BigDecimal minus;
    }
}
