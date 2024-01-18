package vn.supperapp.apigw.restful.controllers.product;


import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.supperapp.apigw.beans.ProductPropertiesInfo;
import vn.supperapp.apigw.configs.RsResponseFilterMapping;
import vn.supperapp.apigw.db.DbSessionMgt;
import vn.supperapp.apigw.db.dao.ProductPropertiesDAO;
import vn.supperapp.apigw.db.dao.ProductPropertiesValueDAO;
import vn.supperapp.apigw.db.dto.*;
import vn.supperapp.apigw.restful.controllers.BaseController;
import vn.supperapp.apigw.restful.models.BaseResponse;
import vn.supperapp.apigw.restful.models.product.ProductRequest;
import vn.supperapp.apigw.restful.models.product.ProductResponse;
import vn.supperapp.apigw.utils.CommonUtils;
import vn.supperapp.apigw.utils.ErrorCode;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Path("/product-properties")
@RsResponseFilterMapping
public class ProductPropertiesController extends BaseController {


    private final Logger logger;
    private final String PRODUCT_PROPERTIES_VALUE = "PRODUCT_PROPERTIES_VALUE";
    private final String PRODUCT_PROPERTIES = "PRODUCT_PROPERTIES";
    private ProductPropertiesDAO productPropertiesDAO;
    private ProductPropertiesValueDAO productPropertiesValueDAO;

    public ProductPropertiesController() {
        this.logger = LoggerFactory.getLogger(ProductPropertiesController.class);
        productPropertiesDAO = new ProductPropertiesDAO();
        productPropertiesValueDAO = new ProductPropertiesValueDAO();
    }

    @POST
    @Path("/create-trademark")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse createProduct(@NotNull ProductRequest request) throws Exception {
        logger.info("#checkOut - Start: " + request.toLogString());
        Session session = null;
        ProductResponse response = new ProductResponse(ErrorCode.SUCCESS, language);
        try {
            session = DbSessionMgt.shared().getSession();
            logger.info("#info Check session");
            if (!checkDbSession(session)) {
                logger.info("#info DB Connection error");
                return BaseResponse.commonError(language);
            }
            if (CommonUtils.isNullOrEmpty(request.getName()) || CommonUtils.isNullOrEmpty(request.getCode())) {
                logger.info("#checkInscene - Validate: time invalid");
                return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
            }
            ProductProperties productProperties = productPropertiesDAO.checkCode(session, request.getCode());
            if (productProperties != null) {
                logger.info("#checkInscene -  Validate: valid shift");
                return BaseResponse.build(ErrorCode.ERR_MISSING_PARAMETERS, language);
            } else {
                productProperties = new ProductProperties();
                productProperties.setId(request.getId());
                productProperties.setParentId(request.getParentId());
                productProperties.setName(request.getName());
                productProperties.setImage(request.getImage());
                productProperties.setCreateAt(new Date());
                productProperties.setUpdateAt(new Date());
                productProperties.setType(request.getType());
                productProperties.setCode(request.getCode());
                productPropertiesDAO.save(session, productProperties);

                ProductPropertiesValue productPropertiesValue = new ProductPropertiesValue();
                productPropertiesValue.setPropertiesId(productProperties.getId());
                productPropertiesValue.setValue(request.getValue());
                productPropertiesValue.setParentId(request.getParentId());
                productPropertiesValue.setCreateAt(new Date());
                productPropertiesValue.setUpdateAt(new Date());
                productPropertiesValue.setCode(request.getCodeValue());
                productPropertiesValueDAO.save(session, productPropertiesValue);
            }
            response.setProductProperties(productProperties);
            return response;
        }
        catch (Exception ex) {
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
        @Path("/get-product-properties-by-id")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public BaseResponse getProductPropertiesBy_Id (@NotNull ProductRequest request) {
            logger.info("#checkOut - Start: " + request.toLogString());
            Session session = null;
            ProductResponse response = new ProductResponse(ErrorCode.SUCCESS, language);
            try {
                session = DbSessionMgt.shared().getSession();
                logger.info("#info Check session");
                if (!checkDbSession(session)) {
                    logger.info("#info DB Connection error");
                    return BaseResponse.commonError(language);
                }
                if (CommonUtils.isNullOrEmpty(request.getId().toString())) {
                    logger.info("#checkIn - Validate: Signature invalid");
                    return BaseResponse.build(ErrorCode.ERR_PARAMETERS_INVALID, language);
                }
                List<ProductPropertiesInfo> productProperties = productPropertiesDAO.getProductPropertiesInfoById(session,  request.getId());
                if (productProperties == null) {
                    logger.info("#getProductProperties: not data");
                    return BaseResponse.commonError(ErrorCode.ERR_MISSING_DATA.name());
                } else {
                    response.setProductPropertiesInfo(productProperties);
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
    @Path("/update-trademark")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BaseResponse updateProduct(@NotNull ProductRequest request) {
        logger.info("#updateCheckInDetails - Start: " + request.toLogString());
        Session session = null;
        ProductResponse response = new ProductResponse(ErrorCode.SUCCESS, language);
        try {
            session = DbSessionMgt.shared().getSession();
            logger.info("#updateCheckInDetails Check session");
            if(CommonUtils.isNullOrEmpty(request.getId().toString())) {
                logger.info("#updateCheckInDetails - Validate: id invalid");
                return BaseResponse.build(ErrorCode.ERR_INVALID_INFORMATION, language);
            }
            if (!checkDbSession(session)) {
                logger.info("#updateCheckInDetails DB Connection error");
                return BaseResponse.commonError(language);
            }
            ProductProperties productProperties = productPropertiesDAO.getProductPropertiesById(session, request.getId());
            if(productProperties == null) {
                logger.info("#updateCheckInDetails DB Connection error");
                return BaseResponse.commonError(language);
            } else {
                productProperties.setName(request.getName());
                productProperties.setCode(request.getCode());
                productPropertiesDAO.update(session, productProperties);


                response = new ProductResponse(ErrorCode.SUCCESS, language);
                return response;
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



