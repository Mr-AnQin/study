package com.anqin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 产品
 *
 * @author Mr.An
 * @date 2024/03/02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    /**
     * 产品 ID
     */
    private String productId;

    /**
     * 产品类型
     */
    private String productType;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品价格
     */
    private BigDecimal productPrice;
}
