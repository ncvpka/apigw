package vn.supperapp.apigw.restful.controllers.product;


import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.supperapp.apigw.beans.ProductPropertiesInfo;
import vn.supperapp.apigw.beans.ProductPropertiesValueInfo;
import vn.supperapp.apigw.beans.UserLoggedInfo;
import vn.supperapp.apigw.configs.RsDefaultFilterMapping;
import vn.supperapp.apigw.configs.RsResponseFilterMapping;
import vn.supperapp.apigw.db.DbSessionMgt;
import vn.supperapp.apigw.db.dao.ProductPropertiesValueDAO;
import vn.supperapp.apigw.db.dto.Product;
import vn.supperapp.apigw.db.dto.ProductProperties;
import vn.supperapp.apigw.db.dto.ProductPropertiesValue;
import vn.supperapp.apigw.restful.controllers.BaseController;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.restful.models.product.ProductRequest;
import vn.supperapp.apigw.restful.models.product.ProductResponse;
import vn.supperapp.apigw.restful.models.productvalue.ProductValueRequest;
import vn.supperapp.apigw.restful.models.productvalue.ProductValueResponse;
import vn.supperapp.apigw.utils.CommonUtils;
import vn.supperapp.apigw.utils.ErrorCode;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Path("/product-properties-value")
@RsResponseFilterMapping
public class ProductPropertiesValueController extends BaseController {
    private final Logger logger;
    private ProductPropertiesValueDAO dao;
    private ProductPropertiesValueDAO productPropertiesValueDAO;


    public ProductPropertiesValueController() {
        this.logger = LoggerFactory.getLogger(ProductPropertiesValueController.class);
        productPropertiesValueDAO = new ProductPropertiesValueDAO();
    }

    @POST
    @Path("/create-trademark-value")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse createProductValue(@NotNull ProductValueRequest request) throws Exception {
        logger.info("#checkOut - Start: " + request.toLogString());
        Session session = null;
        ProductValueResponse response = new ProductValueResponse(ErrorCode.SUCCESS, language);
        try {
            session = DbSessionMgt.shared().getSession();
            logger.info("#info Check session");
            if (!checkDbSession(session)) {
                logger.info("#info DB Connection error");
                return BaseResponse.commonError(language);
            }
            if (CommonUtils.isNullOrEmpty(request.getValue()) || CommonUtils.isNullOrEmpty(request.getCode())) {
                logger.info("#checkInscene - Validate: time invalid");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            }
            ProductPropertiesValue productPropertiesValue = productPropertiesValueDAO.getProductPropertiesValueById(session, request.getCode());
            if (productPropertiesValue != null) {
                logger.info("#checkIn - Validate: valid shift");
                return BaseResponse.build(ErrorCode.ERR_MISSING_SHIFT, language);
            } else {
                productPropertiesValue = new ProductPropertiesValue();
                productPropertiesValue.setId(request.getId());
                productPropertiesValue.setPropertiesId(request.getPropertiesId());
                productPropertiesValue.setValue(request.getValue());
                productPropertiesValue.setCreateAt(new Date());
                productPropertiesValue.setUpdateAt(new Date());
                productPropertiesValue.setCode(request.getCode());
                productPropertiesValueDAO.save(session, productPropertiesValue);

                response.setProductPropertiesValue(productPropertiesValue);
                return response;
            }
        } catch (Exception ex) {
            logExceptions("#login - Error: ", ex);
            ex.printStackTrace();
            logger.error("#login - EXCEPTION: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }

    @POST
    @Path("/delete-trademark-value")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse deleteProductValue(@NotNull ProductValueRequest request) {
        logger.info("#getProductById - Start: " + request.toLogString());
        Session session = null;
        ProductValueResponse response = new ProductValueResponse(ErrorCode.SUCCESS, language);
        try {
            session = DbSessionMgt.shared().getSession();
            logger.info("#info Check session");
            if (!checkDbSession(session)) {
                logger.info("#info DB Connection error");
                return BaseResponse.commonError(language);
            }
            if (CommonUtils.isNullOrEmpty(String.valueOf(request.getId()))) {
                logger.info("#checkIn - Validate: Signature invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }
            ProductPropertiesValue productPropertiesValues = ProductPropertiesValueDAO.getProductPropertiesValueBy_Id(session, request.getId());
            if(productPropertiesValues == null) {
                logger.info("#getDayOffType: not data");
                return BaseResponse.commonError(ErrorCode.ERR_MISSING_DATA.name());
            } else {
                ProductPropertiesValueDAO.delete(session, productPropertiesValues.getClass(), productPropertiesValues.getId());
                {
                    response = new ProductValueResponse(ErrorCode.SUCCESS, language);
                    return response;
                }
            }
        } catch (Exception ex) {
            logExceptions("#login - Error: ", ex);
            ex.printStackTrace();
            logger.error("#login - EXCEPTION: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }
    @POST
    @Path("/update-trademark-value")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse updateProductValue(@NotNull ProductValueRequest request) {
        logger.info("#updateCheckInDetails - Start: " + request.toLogString());
        Session session = null;
        ProductValueResponse response = new ProductValueResponse(ErrorCode.SUCCESS, language);
        try {
            session = DbSessionMgt.shared().getSession();
            logger.info("#updateProductPropertiesValue Check session");
            if (!checkDbSession(session)) {
                logger.info("#updateProductPropertiesValue DB Connection error");
                return BaseResponse.commonError(language);
            }
            if(CommonUtils.isNullOrEmpty(request.getId().toString())) {
                logger.info("#updateProductPropertiesValue - Validate: id invalid");
                return BaseResponse.build(ErrorCode.ERR_INVALID_INFORMATION, language);
            }
            ProductPropertiesValue productPropertiesValue = productPropertiesValueDAO.getProductPropertiesValueBy_Id(session, request.getId());
            if(productPropertiesValue != null)
            {
                productPropertiesValue.setValue(request.getValue());
                productPropertiesValue.setCode(request.getCode());
                productPropertiesValueDAO.update(session, productPropertiesValue);
                response = new ProductValueResponse(ErrorCode.SUCCESS, language);
                return response;
            } else {
                logger.info("#updateProductPropertiesValue DB Connection error");
                return BaseResponse.commonError(language);
            }
        } catch (Exception ex) {
            logExceptions("#homeInfo - Error: ", ex);
            DbSessionMgt.shared().rollbackObject(session);
        } finally {
            DbSessionMgt.shared().closeObject(session);
        }
        return new BaseResponse();
    }
}
