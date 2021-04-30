package ltd.leaves.mall.service.impl;

import ltd.leaves.mall.common.ServiceResultEnum;
import ltd.leaves.mall.dao.LeavesMallGoodsMapper;
import ltd.leaves.mall.entity.LeavesMallGoods;
import ltd.leaves.mall.service.LeavesMallGoodsService;
import ltd.leaves.mall.util.BeanUtil;
import ltd.leaves.mall.util.PageQueryUtil;
import ltd.leaves.mall.util.PageResult;
import ltd.leaves.mall.controller.vo.LeavesMallSearchGoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LeavesMallGoodsServiceImpl implements LeavesMallGoodsService {

    @Autowired
    private LeavesMallGoodsMapper goodsMapper;

    @Override
    public PageResult getLeavesMallGoodsPage(PageQueryUtil pageUtil) {
        List<LeavesMallGoods> goodsList = goodsMapper.findLeavesMallGoodsList(pageUtil);
        int total = goodsMapper.getTotalLeavesMallGoods(pageUtil);
        PageResult pageResult = new PageResult(goodsList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public String saveLeavesMallGoods(LeavesMallGoods goods) {
        if (goodsMapper.insertSelective(goods) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public void batchSaveLeavesMallGoods(List<LeavesMallGoods> leavesMallGoodsList) {
        if (!CollectionUtils.isEmpty(leavesMallGoodsList)) {
            goodsMapper.batchInsert(leavesMallGoodsList);
        }
    }

    @Override
    public String updateLeavesMallGoods(LeavesMallGoods goods) {
        LeavesMallGoods temp = goodsMapper.selectByPrimaryKey(goods.getGoodsId());
        if (temp == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        goods.setUpdateTime(new Date());
        if (goodsMapper.updateByPrimaryKeySelective(goods) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public LeavesMallGoods getLeavesMallGoodsById(Long id) {
        return goodsMapper.selectByPrimaryKey(id);
    }
    
    @Override
    public Boolean batchUpdateSellStatus(Long[] ids, int sellStatus) {
        return goodsMapper.batchUpdateSellStatus(ids, sellStatus) > 0;
    }

    @Override
    public PageResult searchLeavesMallGoods(PageQueryUtil pageUtil) {
        List<LeavesMallGoods> goodsList = goodsMapper.findLeavesMallGoodsListBySearch(pageUtil);
        int total = goodsMapper.getTotalLeavesMallGoodsBySearch(pageUtil);
        List<LeavesMallSearchGoodsVO> leavesMallSearchGoodsVOS = new ArrayList<>();
        if (!CollectionUtils.isEmpty(goodsList)) {
            leavesMallSearchGoodsVOS = BeanUtil.copyList(goodsList, LeavesMallSearchGoodsVO.class);
            for (LeavesMallSearchGoodsVO leavesMallSearchGoodsVO : leavesMallSearchGoodsVOS) {
                String goodsName = leavesMallSearchGoodsVO.getGoodsName();
                String goodsIntro = leavesMallSearchGoodsVO.getGoodsIntro();
                // An issue with a string that is too long resulting in text overrun
                if (goodsName.length() > 28) {
                    goodsName = goodsName.substring(0, 28) + "...";
                    leavesMallSearchGoodsVO.setGoodsName(goodsName);
                }
                if (goodsIntro.length() > 30) {
                    goodsIntro = goodsIntro.substring(0, 30) + "...";
                    leavesMallSearchGoodsVO.setGoodsIntro(goodsIntro);
                }
            }
        }
        PageResult pageResult = new PageResult(leavesMallSearchGoodsVOS, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
}
