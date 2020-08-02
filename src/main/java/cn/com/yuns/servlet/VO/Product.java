package cn.com.yuns.servlet.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wsq
 * @version Product.java  2020/7/29  上午8:33 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Integer id;

    private String name;

    private String description;

    private float price;
}
