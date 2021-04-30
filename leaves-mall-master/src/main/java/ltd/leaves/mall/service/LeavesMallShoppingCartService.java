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
    String saveLeavesMallCartItem(LeavesMallShoppingCartItem leavesMallShoppingCartItem);

    /**
     * Modify the properties in the shopping cart
     *
     * @param leavesMallShoppingCartItem
     * @return
     */
    String updateLeavesMallCartItem(LeavesMallShoppingCartItem leavesMallShoppingCartItem);

    /**
     * Get item details
     *
     * @param leavesMallShoppingCartItemId
     * @return
     */
    LeavesMallShoppingCartItem getLeavesMallCartItemById(Long leavesMallShoppingCartItemId);

    /**
     * Delete the items in the shopping cart
     *
     * @param leavesMallShoppingCartItemId
     * @return
     */
    Boolean deleteById(Long leavesMallShoppingCartItemId);

    /**
     * Get the list data in my shopping cart
     *
     * @param leavesMallUserId
     * @return
     */
    List<LeavesMallShoppingCartItemVO> getMyShoppingCartItems(Long leavesMallUserId);
}
