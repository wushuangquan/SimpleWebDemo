package cn.com.yuns.servlet.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wsq
 * @version ShoppingItem.java  2020/7/29  上午8:34 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingItem {

    private Product product;

    private int quantity;
}
