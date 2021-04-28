package ltd.leaves.mall.service;

import ltd.leaves.mall.entity.LeavesMallShoppingCartItem;
import ltd.leaves.mall.controller.vo.LeavesMallShoppingCartItemVO;

import java.util.List;

public interface LeavesMallShoppingCartService {

    /**
     * Save the item to your shopping cart
     *
     * @param leavesMallShoppingCartItem
     * @return
     */
    String saveNewBeeMallCartItem(LeavesMallShoppingCartItem leavesMallShoppingCartItem);

    /**
     * Modify the properties in the shopping cart
     *
     * @param leavesMallShoppingCartItem
     * @return
     */
    String updateNewBeeMallCartItem(LeavesMallShoppingCartItem leavesMallShoppingCartItem);

    /**
     * Get item details
     *
     * @param newBeeMallShoppingCartItemId
     * @return
     */
    LeavesMallShoppingCartItem getNewBeeMallCartItemById(Long newBeeMallShoppingCartItemId);

    /**
     * Delete the items in the shopping cart
     *
     * @param newBeeMallShoppingCartItemId
     * @return
     */
    Boolean deleteById(Long newBeeMallShoppingCartItemId);

    /**
     * Get the list data in my shopping cart
     *
     * @param newBeeMallUserId
     * @return
     */
    List<LeavesMallShoppingCartItemVO> getMyShoppingCartItems(Long newBeeMallUserId);
}
