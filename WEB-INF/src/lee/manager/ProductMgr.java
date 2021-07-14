package lee.manager;

import java.util.List;
import java.util.Map;

import lee.bean.Product;
import lee.bean.ProductFile;
import lee.common.util.ExceptionUtil;
import lee.dao.ProductDao;

/**
 * Board Manager : TB_BOARD, TB_BOARD_COMMENT, TB_BOARD_FILE
 *
 * @author 
 * @version 1.0
 */
public class ProductMgr extends MgrBase {

	private ProductDao productDao;

	public ProductMgr() {
		super();
		productDao = new ProductDao(sqlMap);
	}

	//--- Product
	public int getProductListCnt(Map map) throws Exception {
        Integer cnt = 0;
        
        try {
            cnt = productDao.getProductListCnt(map);
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
        
        return cnt.intValue();
    }
	
	public int getSearchProductListCnt(Map map) throws Exception {
        Integer cnt = 0;
        
        try {
            cnt = productDao.getSearchProductListCnt(map);
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
        
        return cnt.intValue();
    }
	
	public int getProductMaxId() throws Exception {
        Integer cnt = 0;
        
        try {
            cnt = productDao.getProductMaxId();
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
        
        return cnt.intValue();
    }
	
	public Product[] getProductList(Map map) throws Exception {
	    List<Product> arrData = null;
	    Product[] result = null;
	    
	    try {
	        arrData = (List<Product>) productDao.getProductList(map);
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
        
        result = new Product[arrData.size()];
        arrData.toArray(result);
	    return result;
	}
	
	public Product[] getAllProductList(Map map) throws Exception {
	    List<Product> arrData = null;
	    Product[] result = null;
	    
	    try {
	        arrData = (List<Product>) productDao.getAllProductList(map);
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
        
        result = new Product[arrData.size()];
        arrData.toArray(result);
	    return result;
	}
	
	public Product[] getProductImgList(Map map) throws Exception {
        List<Product> arrData = null;
        Product[] result = null;
        
        try {
            arrData = (List<Product>) productDao.getProductImgList(map);
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
        
        result = new Product[arrData.size()];
        arrData.toArray(result);
        return result;
    }
	
	public Product[] getSearchProductList(Map map) throws Exception {
        List<Product> arrData = null;
        Product[] result = null;
        
        try {
            arrData = (List<Product>) productDao.getSearchProductList(map);
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
        
        result = new Product[arrData.size()];
        arrData.toArray(result);
        return result;
    }

	public Product getProduct(Map map) throws Exception {
        Product result = null;
        
        try {
            result = productDao.getProduct(map);
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
        
        return result;
    }
	
	public void insertProduct(Product Product) throws Exception {
	    try {
            sqlMap.startTransaction();
            productDao.insertProduct(Product);
            sqlMap.commitTransaction();
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        } finally {
            try {
                sqlMap.endTransaction();
            } catch (Exception e) {
                throw new MgrException(e);
            }
        }
    }
	
	public void updateProduct(Product Product) throws Exception {
        try {
            sqlMap.startTransaction();
            productDao.updateProduct(Product);
            sqlMap.commitTransaction();
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        } finally {
            try {
                sqlMap.endTransaction();
            } catch (Exception e) {
                throw new MgrException(e);
            }
        }
    }
	
	public void deleteProduct(int contId) throws Exception {
        try {
            sqlMap.startTransaction();
            productDao.deleteProduct(contId);
            sqlMap.commitTransaction();
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        } finally {
            try {
                sqlMap.endTransaction();
            } catch (Exception e) {
                throw new MgrException(e);
            }
        }
    }
	
		
	//-- Product file
	public ProductFile[] getProductFileList(int contId) throws Exception {
        List<ProductFile> arrData = null;
        ProductFile[] result = null;
        
        try {
            arrData = (List<ProductFile>)productDao.getProductFileList(contId);
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        }
        
        result = new ProductFile[arrData.size()];
        arrData.toArray(result);
        return result;
    }
	
	public void insertProductFile(ProductFile ProductFile) throws Exception {
        try {
            sqlMap.startTransaction();
            productDao.insertProductFile(ProductFile);
            sqlMap.commitTransaction();
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        } finally {
            try {
                sqlMap.endTransaction();
            } catch (Exception e) {
                throw new MgrException(e);
            }
        }
    }
    
    public void updateProductFile(ProductFile productFile) throws Exception {
        try {
            sqlMap.startTransaction();
            productDao.updateProductFile(productFile);
            sqlMap.commitTransaction();
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        } finally {
            try {
                sqlMap.endTransaction();
            } catch (Exception e) {
                throw new MgrException(e);
            }
        }
    }
    
    public void deleteProductFile(int fileId) throws Exception {
        try {
            sqlMap.startTransaction();
            productDao.deleteProductFile(fileId);
            sqlMap.commitTransaction();
        } catch (Exception e) {
            log.error(ExceptionUtil.getStackTrace(e));
            throw new MgrException(e);
        } finally {
            try {
                sqlMap.endTransaction();
            } catch (Exception e) {
                throw new MgrException(e);
            }
        }
    }
    
}
