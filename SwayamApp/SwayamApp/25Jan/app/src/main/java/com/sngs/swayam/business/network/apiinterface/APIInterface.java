package com.sngs.swayam.business.network.apiinterface;

import com.sngs.swayam.business.network.webUtlis.Links;
import com.sngs.swayam.business.network.model.Area.GetAreaListBaseResponse;
import com.sngs.swayam.business.network.model.Attribute.GetAttributeListBaseResponse;
import com.sngs.swayam.business.network.model.BaseResponse;
import com.sngs.swayam.business.network.model.Category.GetCategoryListBaseResponse;
import com.sngs.swayam.business.network.model.City.GetCityListBaseResponse;
import com.sngs.swayam.business.network.model.CustomerDetail.CustomerDetailBaseResponse;
import com.sngs.swayam.business.network.model.CustomerSignUp.CustomerSignUpBaseResponse;
import com.sngs.swayam.business.network.model.Login.LoginBaseResponse;
import com.sngs.swayam.business.network.model.MobileVerify.MobileVerifyBaseResponse;
import com.sngs.swayam.business.network.model.ServiceProvider.GetServiceProviderBaseResponse;
import com.sngs.swayam.business.network.model.State.GetStateListBaseResponse;
import com.sngs.swayam.business.network.model.SubCategory.GetSubCategoryListBaseResponse;

import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface APIInterface
{

    //Sign In
    @POST(Links.Customer_Sign_In)
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<LoginBaseResponse> postCustomerSignIn(@Body HashMap<String, String> mBodyMap);


    //Mobile Verify
    @POST(Links.Mobile_Verify)
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<MobileVerifyBaseResponse> postMobileVerify(@Body HashMap<String, String> mBodyMap);

    //Customer SignUp
    @POST(Links.Customer_Sign_Up)
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<CustomerSignUpBaseResponse> postCustomerSignUp(@Body HashMap<String, String> mBodyMap);

    //Customer Profile Detail
    @Multipart
    @POST(Links.Customer_Profile)
    Call<BaseResponse> postCustomerProfileDetail(@Part(Links.Header.Auth_ID) RequestBody authid,
                                                 @Part(Links.Header.Auth_Token) RequestBody authtoken,
                                                 @Part(Links.Header.User_Type) RequestBody usertype,
                                                 @Part(Links.CustomerProfileDetail.CustomerShopType) RequestBody CustomerShopType,
                                                 @Part(Links.CustomerProfileDetail.CustomerShopName) RequestBody CustomerShopName,
                                                 @Part(Links.CustomerProfileDetail.Customer_Contact_Number) RequestBody CustomerContactNumber,
                                                 @Part(Links.CustomerProfileDetail.Customer_Email) RequestBody CustomerEmail,
                                                 @Part(Links.CustomerProfileDetail.Customer_Shop_Address1) RequestBody CustomerShopAddress1,
                                                 @Part(Links.CustomerProfileDetail.Customer_Shop_Address2) RequestBody CustomerShopAddress2,
                                                 @Part(Links.CustomerProfileDetail.Customer_Shop_State) RequestBody CustomerShopState,
                                                 @Part(Links.CustomerProfileDetail.Customer_Shop_City) RequestBody CustomerShopCity,
                                                 @Part(Links.CustomerProfileDetail.Customer_Shop_Area) RequestBody CustomerShopArea,
                                                 @Part(Links.CustomerProfileDetail.Customer_Shop_Pincode) RequestBody CustomerShopPincode,
                                                 @Part MultipartBody.Part send_image_File,
                                                 @Part(Links.CustomerProfileDetail.Customer_Shop_Country) RequestBody CustomerShopCountry);

    //Customer Business Detail
    @POST(Links.Customer_Business_Detail)
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<BaseResponse> postCustomerBusinessDetail(@Body HashMap<String, String> mBodyMap);

    //Service Provider List
    @POST(Links.Get_Service_Provider_List)
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<GetServiceProviderBaseResponse> postGetServiceProviderList(@Body HashMap<String, String> mBodyMap);


    //Category List
    @POST(Links.Get_Category_List)
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<GetCategoryListBaseResponse> postGetCategoryList(@Body HashMap<String, String> mBodyMap);

    //Sub Category List
    @POST(Links.Get_Sub_Category_List)
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<GetSubCategoryListBaseResponse> postGetSubCategoryList(@Body HashMap<String, String> mBodyMap);

    //Attribute List
    @POST(Links.Get_Attribute_List)
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<GetAttributeListBaseResponse> postGetAttributeListt(@Body HashMap<String, String> mBodyMap);


    //Upload File
    @Multipart
    @POST(Links.Customer_Uploaded_File)
    Call<BaseResponse> postCustomerUploadFile(@Part(Links.Header.Auth_ID) RequestBody authid,
                                              @Part(Links.Header.Auth_Token) RequestBody authtoken,
                                              @Part(Links.Header.User_Type) RequestBody usertype,
                                              @Part(Links.CategoryUploadFileDetail.Service_Id) RequestBody service_id,
                                              @Part(Links.CategoryUploadFileDetail.Category_Id) RequestBody category_id,
                                              @Part(Links.CategoryUploadFileDetail.Sub_Category_Id) RequestBody sub_category_id,
                                              @Part(Links.CategoryUploadFileDetail.Attribute_Id) RequestBody attribute_id,
                                              @Part MultipartBody.Part send_image_File,
                                              @Part MultipartBody.Part send_image_File2,
                                              @Part MultipartBody.Part send_image_File3,
                                              @Part MultipartBody.Part send_image_File4);




    //Attribute List
    @POST(Links.Get_Customer_Detail)
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<CustomerDetailBaseResponse> postCustomerDetail(@Body HashMap<String, String> mBodyMap);

    //Get State List
    @POST(Links.Get_State_List)
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<GetStateListBaseResponse> postGetStateList(@Body HashMap<String, String> mBodyMap);

    //Get City List
    @POST(Links.Get_City_List)
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<GetCityListBaseResponse> postGetCityList(@Body HashMap<String, String> mBodyMap);

    //Get Area List
    @POST(Links.Get_Area_List)
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<GetAreaListBaseResponse> postGetAreaList(@Body HashMap<String, String> mBodyMap);

}
