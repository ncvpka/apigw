package vn.supperapp.apigw.restful.models.productvalue;

import vn.supperapp.apigw.beans.ProductPropertiesValueInfo;
import vn.supperapp.apigw.db.dto.ProductProperties;
import vn.supperapp.apigw.db.dto.ProductPropertiesValue;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.utils.ErrorCode;

import java.util.List;

public class ProductValueResponse extends BaseResponse {

    private ProductPropertiesValueInfo product;
    private ProductPropertiesValue setProductPropertiesValue;

    public ProductValueResponse(ErrorCode success, String language) {

    }

    public ProductPropertiesValueInfo getProductValue() {
        return product;
    }

    public void setProductPropertiesValue(ProductPropertiesValue productPropertiesValue) {
        this.setProductPropertiesValue = productPropertiesValue;
    }

    public void setProductPropertiesValueInfo(List<ProductPropertiesValueInfo> productPropertiesValueInfos) {
    }
}
