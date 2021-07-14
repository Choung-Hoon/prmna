package lee.dao;

import java.util.List;
import java.util.Map;

import lee.bean.Product;
import lee.bean.ProductFile;
import lee.common.sqlMap.SqlMapBase;

import com.ibatis.sqlmap.client.SqlMapClient;


/**
 * Board Data Access Object
 */
public class ProductDao extends SqlMapBase {
	SqlMapClient sqlMapClient;

	public ProductDao(SqlMapClient sqlMapClient){
		this.sqlMapClient = sqlMapClient;
	}

	//--- board
	public Integer getProductListCnt(Map map) throws Exception{
        return (Integer)sqlMapClient.queryForObject("lee.product.getProductListCnt", map);
    }
	
	public Integer getSearchProductListCnt(Map map) throws Exception{
        return (Integer)sqlMapClient.queryForObject("lee.product.getSearchProductListCnt", map);
    }
	
	public Integer getProductMaxId() throws Exception{
	    return (Integer)sqlMapClient.queryForObject("lee.product.getProductMaxId");
	}
	
	public List<Product> getProductList(Map map) throws Exception{
	    return (List<Product>)sqlMapClient.queryForList("lee.product.getProductList", map);
	}
	
	public List<Product> getProductImgList(Map map) throws Exception{
        return (List<Product>)sqlMapClient.queryForList("lee.product.getProductImgList", map);
    }
	
	public List<Product> getSearchProductList(Map map) throws Exception{
        return (List<Product>)sqlMapClient.queryForList("lee.product.getSearchProductList", map);
    }
	
	public List<Product> getAllProductList(Map map)throws Exception{
        return (List<Product>)sqlMapClient.queryForList("lee.product.getAllProductList", map);
    }
	
	public Product getProduct(Map map)throws Exception{
        return (Product)sqlMapClient.queryForObject("lee.product.getProduct", map);
    }
	
	public void insertProduct(Product Product)throws Exception{
        sqlMapClient.insert("lee.product.insertProduct", Product);
    }
	
	public void updateProduct(Product product)throws Exception{
        sqlMapClient.update("lee.product.updateProduct", product);
    }
		
	public void deleteProduct(int bfileId)throws Exception{
        sqlMapClient.delete("lee.product.deleteProduct", bfileId);
    }
	
	//-- Product file
	public List<ProductFile> getProductFileList(int contId) throws Exception{
        return (List<ProductFile>)sqlMapClient.queryForList("lee.product.getProductFileList", contId);
    }
	
	public void insertProductFile(ProductFile ProductFile)throws Exception{
        sqlMapClient.insert("lee.product.insertProductFile", ProductFile);
    }
    
    public void updateProductFile(ProductFile ProductFile)throws Exception{
        sqlMapClient.update("lee.product.updateProductFile", ProductFile);
    }
    
    public void deleteProductFile(int fileId)throws Exception{
        sqlMapClient.delete("lee.product.deleteProductFile", fileId);
    }
    
}
