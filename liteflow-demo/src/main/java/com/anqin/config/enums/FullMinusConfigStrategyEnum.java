package com.anqin.config.enums;

import com.anqin.config.FullMinusService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * 满减配置策略枚举
 *
 * @author Mr.An
 * @date 2024/03/02
 */
@Getter
@AllArgsConstructor
public enum FullMinusConfigStrategyEnum implements FullMinusService {

    /**
     * 平价
     */
    FAIR_PRICE(BigDecimal.valueOf(100), BigDecimal.valueOf(20)),

    /**
     * 低价
     */
    BARGAIN_PRICE(BigDecimal.valueOf(200), BigDecimal.valueOf(50));


    /**
     * 满
     */
    private final BigDecimal full;

    /**
     * 减去
     */
    private final BigDecimal minus;


    /**
     * 获取所有满减配置
     *
     * @return {@link List}<{@link FullMinus}>
     */
    @Override
    public List<FullMinus> getAllFullMinusConfig() {
        return Arrays.stream(FullMinusConfigStrategyEnum.values())
                .map(f -> new FullMinusService.FullMinus(f.getFull(), f.getMinus()))
                .toList();
    }
}
