package ltd.leaves.mall.service.impl;

import ltd.leaves.mall.common.ServiceResultEnum;
import ltd.leaves.mall.dao.IndexConfigMapper;
import ltd.leaves.mall.dao.LeavesMallGoodsMapper;
import ltd.leaves.mall.entity.IndexConfig;
import ltd.leaves.mall.entity.LeavesMallGoods;
import ltd.leaves.mall.service.LeavesMallIndexConfigService;
import ltd.leaves.mall.util.BeanUtil;
import ltd.leaves.mall.util.PageQueryUtil;
import ltd.leaves.mall.util.PageResult;
import ltd.leaves.mall.controller.vo.LeavesMallIndexConfigGoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeavesMallIndexConfigServiceImpl implements LeavesMallIndexConfigService {

    @Autowired
    private IndexConfigMapper indexConfigMapper;

    @Autowired
    private LeavesMallGoodsMapper goodsMapper;

    @Override
    public PageResult getConfigsPage(PageQueryUtil pageUtil) {
        List<IndexConfig> indexConfigs = indexConfigMapper.findIndexConfigList(pageUtil);
        int total = indexConfigMapper.getTotalIndexConfigs(pageUtil);
        PageResult pageResult = new PageResult(indexConfigs, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public String saveIndexConfig(IndexConfig indexConfig) {
        //todo Determine whether the product exists
        if (indexConfigMapper.insertSelective(indexConfig) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public String updateIndexConfig(IndexConfig indexConfig) {
        //todo Determine whether the product exists
        IndexConfig temp = indexConfigMapper.selectByPrimaryKey(indexConfig.getConfigId());
        if (temp == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        if (indexConfigMapper.updateByPrimaryKeySelective(indexConfig) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public IndexConfig getIndexConfigById(Long id) {
        return null;
    }

    @Override
    public List<LeavesMallIndexConfigGoodsVO> getConfigGoodsesForIndex(int configType, int number) {
        List<LeavesMallIndexConfigGoodsVO> leavesMallIndexConfigGoodsVOS = new ArrayList<>(number);
        List<IndexConfig> indexConfigs = indexConfigMapper.findIndexConfigsByTypeAndNum(configType, number);
        if (!CollectionUtils.isEmpty(indexConfigs)) {
            //Remove all goodsids
            List<Long> goodsIds = indexConfigs.stream().map(IndexConfig::getGoodsId).collect(Collectors.toList());
            List<LeavesMallGoods> leavesMallGoods = goodsMapper.selectByPrimaryKeys(goodsIds);
            leavesMallIndexConfigGoodsVOS = BeanUtil.copyList(leavesMallGoods, LeavesMallIndexConfigGoodsVO.class);
            for (LeavesMallIndexConfigGoodsVO leavesMallIndexConfigGoodsVO : leavesMallIndexConfigGoodsVOS) {
                String goodsName = leavesMallIndexConfigGoodsVO.getGoodsName();
                String goodsIntro = leavesMallIndexConfigGoodsVO.getGoodsIntro();
                // An issue with a string that is too long resulting in text overrun
                if (goodsName.length() > 30) {
                    goodsName = goodsName.substring(0, 30) + "...";
                    leavesMallIndexConfigGoodsVO.setGoodsName(goodsName);
                }
                if (goodsIntro.length() > 22) {
                    goodsIntro = goodsIntro.substring(0, 22) + "...";
                    leavesMallIndexConfigGoodsVO.setGoodsIntro(goodsIntro);
                }
            }
        }
        return leavesMallIndexConfigGoodsVOS;
    }

    @Override
    public Boolean deleteBatch(Long[] ids) {
        if (ids.length < 1) {
            return false;
        }
        //Delete the data
        return indexConfigMapper.deleteBatch(ids) > 0;
    }
}
