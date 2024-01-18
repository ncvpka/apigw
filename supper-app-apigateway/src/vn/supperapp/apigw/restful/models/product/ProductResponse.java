package vn.supperapp.apigw.restful.models.product;

import vn.supperapp.apigw.beans.DayOffInfo;
import vn.supperapp.apigw.beans.ProductPropertiesInfo;
import vn.supperapp.apigw.db.dto.ProductProperties;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.utils.ErrorCode;

import java.util.List;

import static config.sym.error;

public class ProductResponse extends BaseResponse {
    private ProductProperties productProperties;
    private List<ProductPropertiesInfo> productpropertiesInfo;

    public ProductResponse(ErrorCode success, String language) {
        super(error, language);
    }

    public ProductProperties getProduct() {
        return productProperties;
    }
    public List<ProductPropertiesInfo> getProductPropertiesInfo() {
        return productpropertiesInfo;
    }
    public void setProductProperties(ProductProperties product) {
        this.productProperties = product;
    }

    public void setProductPropertiesInfo(List<ProductPropertiesInfo> productPropertiesInfo) {
        this.productpropertiesInfo = productPropertiesInfo;
    }
}
