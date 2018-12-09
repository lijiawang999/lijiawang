package com.zs.pms.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zs.pms.dao.ProductDao;
import com.zs.pms.dao.SkuDao;
import com.zs.pms.exception.AppException;
import com.zs.pms.po.TProduct;
import com.zs.pms.po.TSku;
import com.zs.pms.service.ProductService;
import com.zs.pms.service.RedisService;
@Transactional //开启事务
@Service
public class ProductServiceImpl implements ProductService {
   @Autowired
   ProductDao pd;
   @Autowired
   SkuDao sd;
  
	@Override
	/**
	 * 查询列表
	 */
	public List<TProduct> queryAll() {
		// TODO Auto-generated method stub
		return pd.queryAll();
	}
	/**
	 * 新增
	 */
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int addProduct(TProduct product) throws AppException {
		// TODO Auto-generated method stub
		int rtn=pd.insert(product);
		//颜色 尺码 材质数组
		String cls[] =product.getColors().split(",");
		String sizes[]=product.getSizess().split(",");
		String feas[]=product.getFeatures().split(",");
		//遍历新增
		for (String c : cls) {

			for (String s : sizes) {

				for (String f : feas) {
					// 新增sku
					TSku sku = new TSku();
					sku.setColor(Integer.parseInt(c)); // 颜色id
					sku.setCreator(product.getCreator()); // 创建者
					sku.setFeature(Integer.parseInt(f));// 材质id
					sku.setLimi(0); // 购买限制 默认0
					sku.setPid(product.getId());// 商品id
					sku.setPrice(0); // 市场价
					sku.setRecont(0);// 剩余数量
					sku.setSafcont(0);// 安全库存
					sku.setSellcont(0);// 销售数量
					sku.setSellcost(0);// 售价
					sku.setSiz(Integer.parseInt(s));// 尺码id
					//存中文
					sku.setSkuname(product.getPname()+""+sku.getColor()+""+sku.getSiz()+""+sku.getFeature());
					sku.setSkupic(product.getPicturl());
					sku.setTrancost(0);// 运费
					sku.setWpos("5号仓库");// 仓库号
					// 新增
					sd.insert(sku);

				}
			}
		}
		return product.getId();
	}

	/**
	 * 通过Id查询
	 */
	public TProduct queryById(int id) {
		// TODO Auto-generated method stub
		return pd.queryById(id);
	}

	

}
