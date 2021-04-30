package com.sngs.swayam.business.network.servicecall;

import android.content.Context;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;

import com.sngs.swayam.business.network.HashMapLog;
import com.sngs.swayam.business.network.webUtlis.Links;
import com.sngs.swayam.business.network.apiinterface.APIInterface;
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
import com.sngs.swayam.business.network.remotes.APIClient;

import java.io.File;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

public class ServiceCall extends AppCompatActivity {


    //Mobile Verify
    public static Call<MobileVerifyBaseResponse> callMobileVerify(Context context, String user_type, String contact_number) {

        HashMap<String, String> mBodyMap = new HashMap<String, String>();
        mBodyMap.put(Links.MobileVerifyDetail.User_Type,user_type);
        mBodyMap.put(Links.MobileVerifyDetail.Contact_Number,contact_number);

        HashMapLog.getHashMapLog("callMobileVerify", mBodyMap);

        return APIClient.getClient().create(APIInterface.class).postMobileVerify(mBodyMap);
    }

    //Customer SignUp
    public static Call<CustomerSignUpBaseResponse> callCustomerSignUp(Context context, String user_type, String customer_type,
                                                                      String customer_name, String customer_contact_number,
                                                                      String customer_emp_code) {

        HashMap<String, String> mBodyMap = new HashMap<String, String>();
        mBodyMap.put(Links.CustomerSignUpDetail.User_Type,user_type);
        mBodyMap.put(Links.CustomerSignUpDetail.Customer_Type,customer_type);
        mBodyMap.put(Links.CustomerSignUpDetail.Customer_Name,customer_name);
        mBodyMap.put(Links.CustomerSignUpDetail.Customer_Contact_Number,customer_contact_number);
        mBodyMap.put(Links.CustomerSignUpDetail.Customer_Employee_Code,customer_emp_code);

        HashMapLog.getHashMapLog("callCustomerSignUp", mBodyMap);

        return APIClient.getClient().create(APIInterface.class).postCustomerSignUp(mBodyMap);
    }

    //Customer Profile Detail
    public static Call<BaseResponse> callCustomerProfileDetail(Context context,String auth_id, String auth_token, String user_type, String Customer_Shop_Type,
                                                      String Customer_Shop_Name, String Customer_Contact_Number,
                                                      String Customer_Email, String Customer_Shop_Address1,
                                                               String Customer_Shop_Address2,
                                                               String Customer_Shop_State,
                                                               String Customer_Shop_City, String Customer_Shop_Area, String Customer_Shop_Pincode, File Image_file, String Customer_Shop_Country) {


        MediaType mediaType = MediaType.parse("multipart/form-data");
        MultipartBody.Part send_image_File = null;

        if(Image_file!=null)
        {
            RequestBody requestBody = RequestBody.create(mediaType,Image_file);
            send_image_File = MultipartBody.Part.createFormData(Links.CustomerProfileDetail.Customer_Profile_Picture,
                    Uri.fromFile(Image_file).toString(), requestBody);
        }

        RequestBody authid = RequestBody.create(MediaType.parse("text/plain"), auth_id);
        RequestBody authtoken = RequestBody.create(MediaType.parse("text/plain"), auth_token);
        RequestBody usertype = RequestBody.create(MediaType.parse("text/plain"), user_type);
        RequestBody CustomerShopType = RequestBody.create(MediaType.parse("text/plain"), Customer_Shop_Type);
        RequestBody CustomerShopName = RequestBody.create(MediaType.parse("text/plain"), Customer_Shop_Name);
        RequestBody CustomerContactNumber = RequestBody.create(MediaType.parse("text/plain"), Customer_Contact_Number);
        RequestBody CustomerEmail = RequestBody.create(MediaType.parse("text/plain"), Customer_Email);
        RequestBody CustomerShopAddress1 = RequestBody.create(MediaType.parse("text/plain"), Customer_Shop_Address1);
        RequestBody CustomerShopAddress2 = RequestBody.create(MediaType.parse("text/plain"), Customer_Shop_Address2);
        RequestBody CustomerShopState = RequestBody.create(MediaType.parse("text/plain"), Customer_Shop_State);
        RequestBody CustomerShopCity = RequestBody.create(MediaType.parse("text/plain"), Customer_Shop_City);
        RequestBody CustomerShopArea = RequestBody.create(MediaType.parse("text/plain"), Customer_Shop_Area);
        RequestBody CustomerShopPincode = RequestBody.create(MediaType.parse("text/plain"), Customer_Shop_Pincode);
        RequestBody CustomerShopCountry = RequestBody.create(MediaType.parse("text/plain"), Customer_Shop_Country);

        return APIClient.getClient().create(APIInterface.class).postCustomerProfileDetail(authid, authtoken, usertype, CustomerShopType, CustomerShopName, CustomerContactNumber,
                CustomerEmail, CustomerShopAddress1, CustomerShopAddress2, CustomerShopState,
                CustomerShopCity, CustomerShopArea, CustomerShopPincode,
                send_image_File, CustomerShopCountry);
    }

    //Customer Business Detail
    public static Call<BaseResponse> callCustomerBusinessDetail(Context context, String auth_id, String auth_token, String user_type, String customer_shop_workingDays,
                                                                      String customer_shop_startTime, String customer_shop_endTime, String customer_shop_lunchstartTime,String customer_shop_lunchendTime,
                                                                String customer_shop_year, String customer_shop_totalEmployee, String customer_shop_website,
                                                                String customer_shop_about) {

        HashMap<String, String> mBodyMap = new HashMap<String, String>();
        mBodyMap.put(Links.Header.Auth_ID,auth_id);
        mBodyMap.put(Links.Header.Auth_Token,auth_token);
        mBodyMap.put(Links.Header.User_Type,user_type);
        mBodyMap.put(Links.CustomerBusinessInfoDetail.customer_Shop_WorkingDays,customer_shop_workingDays);
        mBodyMap.put(Links.CustomerBusinessInfoDetail.customer_Shop_StartTime,customer_shop_startTime);
        mBodyMap.put(Links.CustomerBusinessInfoDetail.customer_Shop_EndTime,customer_shop_endTime);
        mBodyMap.put(Links.CustomerBusinessInfoDetail.customer_shop_LunchStartTime,customer_shop_lunchstartTime);
        mBodyMap.put(Links.CustomerBusinessInfoDetail.customer_shop_LunchEndTime,customer_shop_lunchendTime);
        mBodyMap.put(Links.CustomerBusinessInfoDetail.customer_Shop_Year,customer_shop_year);
        mBodyMap.put(Links.CustomerBusinessInfoDetail.customer_Shop_TotalEmployee,customer_shop_totalEmployee);
        mBodyMap.put(Links.CustomerBusinessInfoDetail.customer_Shop_Website,customer_shop_website);
        mBodyMap.put(Links.CustomerBusinessInfoDetail.customer_Shop_About,customer_shop_about);

        HashMapLog.getHashMapLog("callCustomerBusinessDetail", mBodyMap);

        return APIClient.getClient().create(APIInterface.class).postCustomerBusinessDetail(mBodyMap);
    }

    //Service Provider List
    public static Call<GetServiceProviderBaseResponse> callGetServiceProviderList(Context context, String auth_id, String auth_token, String user_type) {

        HashMap<String, String> mBodyMap = new HashMap<String, String>();
        mBodyMap.put(Links.Header.Auth_ID,auth_id);
        mBodyMap.put(Links.Header.Auth_Token,auth_token);
        mBodyMap.put(Links.Header.User_Type,user_type);

        HashMapLog.getHashMapLog("callGetServiceProviderList", mBodyMap);

        return APIClient.getClient().create(APIInterface.class).postGetServiceProviderList(mBodyMap);
    }

    //Get Category List
    public static Call<GetCategoryListBaseResponse> callGetCategoryList(Context context, String auth_id, String auth_token, String user_type,
                                                                        String service_id) {

        HashMap<String, String> mBodyMap = new HashMap<String, String>();
        mBodyMap.put(Links.Header.Auth_ID,auth_id);
        mBodyMap.put(Links.Header.Auth_Token,auth_token);
        mBodyMap.put(Links.Header.User_Type,user_type);
        mBodyMap.put(Links.GetCategoryListDetail.Service_Id,service_id);

        HashMapLog.getHashMapLog("callGetCategoryList", mBodyMap);

        return APIClient.getClient().create(APIInterface.class).postGetCategoryList(mBodyMap);
    }

    //Get Sub Category List
    public static Call<GetSubCategoryListBaseResponse> callGetSubCategoryList(Context context, String auth_id, String auth_token, String user_type,
                                                                              String service_id, String category_id) {

        HashMap<String, String> mBodyMap = new HashMap<String, String>();
        mBodyMap.put(Links.Header.Auth_ID,auth_id);
        mBodyMap.put(Links.Header.Auth_Token,auth_token);
        mBodyMap.put(Links.Header.User_Type,user_type);
        mBodyMap.put(Links.GetSubCategoryListDetail.Service_Id,service_id);
        mBodyMap.put(Links.GetSubCategoryListDetail.Category_Id,category_id);

        HashMapLog.getHashMapLog("callGetSubCategoryList", mBodyMap);

        return APIClient.getClient().create(APIInterface.class).postGetSubCategoryList(mBodyMap);
    }

    //Get Attribute List
    public static Call<GetAttributeListBaseResponse> callGetAttributeList(Context context,
                                                                          String auth_id, String auth_token,
                                                                          String user_type) {

        HashMap<String, String> mBodyMap = new HashMap<String, String>();
        mBodyMap.put(Links.Header.Auth_ID,auth_id);
        mBodyMap.put(Links.Header.Auth_Token,auth_token);
        mBodyMap.put(Links.Header.User_Type,user_type);

        HashMapLog.getHashMapLog("callGetAttributeList", mBodyMap);

        return APIClient.getClient().create(APIInterface.class).postGetAttributeListt(mBodyMap);
    }

    //Upload File
    public static Call<BaseResponse> callUploadFileDetail(Context context,String auth_id, String auth_token,
                                                          String user_type, String service_id,
                                                          String category_id, String sub_category_id,
                                                          String attribute_id, File Image_file,
                                                          File Image_file2, File Image_file3,
                                                          File Image_file4) {


        MediaType mediaType = MediaType.parse("multipart/form-data");
        MultipartBody.Part send_image_File = null;
        MultipartBody.Part send_image_File2 = null;
        MultipartBody.Part send_image_File3 = null;
        MultipartBody.Part send_image_File4 = null;

        if(Image_file!=null)
        {
            RequestBody requestBody = RequestBody.create(mediaType,Image_file);
            send_image_File = MultipartBody.Part.createFormData(Links.CategoryUploadFileDetail.Attachment1,
                    Uri.fromFile(Image_file).toString(), requestBody);
        }


        if(Image_file2!=null)
        {
            RequestBody requestBody = RequestBody.create(mediaType,Image_file2);
            send_image_File2 = MultipartBody.Part.createFormData(Links.CategoryUploadFileDetail.Attachment2,
                    Uri.fromFile(Image_file2).toString(), requestBody);
        }

        if(Image_file3!=null)
        {
            RequestBody requestBody = RequestBody.create(mediaType,Image_file3);
            send_image_File3 = MultipartBody.Part.createFormData(Links.CategoryUploadFileDetail.Attachment3,
                    Uri.fromFile(Image_file3).toString(), requestBody);
        }

        if(Image_file4!=null)
        {
            RequestBody requestBody = RequestBody.create(mediaType,Image_file4);
            send_image_File4 = MultipartBody.Part.createFormData(Links.CategoryUploadFileDetail.Attachment4,
                    Uri.fromFile(Image_file4).toString(), requestBody);
        }

        RequestBody authid = RequestBody.create(MediaType.parse("text/plain"), auth_id);
        RequestBody authtoken = RequestBody.create(MediaType.parse("text/plain"), auth_token);
        RequestBody usertype = RequestBody.create(MediaType.parse("text/plain"), user_type);
        RequestBody serviceid = RequestBody.create(MediaType.parse("text/plain"), service_id);
        RequestBody categoryid = RequestBody.create(MediaType.parse("text/plain"), category_id);
        RequestBody subcategoryid = RequestBody.create(MediaType.parse("text/plain"), sub_category_id);
        RequestBody attributeid = RequestBody.create(MediaType.parse("text/plain"), attribute_id);

        return APIClient.getClient().create(APIInterface.class).postCustomerUploadFile(authid, authtoken, usertype,
                serviceid, categoryid, subcategoryid, attributeid, send_image_File,
                send_image_File2, send_image_File3,send_image_File4);
    }

    //Customer Sign In
    public static Call<LoginBaseResponse> callCustomerSignIn(Context context, String contact_num, String user_type) {

        HashMap<String, String> mBodyMap = new HashMap<String, String>();
        mBodyMap.put(Links.Header.User_Type,user_type);
        mBodyMap.put(Links.Customer_Sign_In_Detail.User_Contact_Number,contact_num);

        HashMapLog.getHashMapLog("callCustomerSignIn", mBodyMap);

        return APIClient.getClient().create(APIInterface.class).postCustomerSignIn(mBodyMap);
    }


    //Customer Detail
    public static Call<CustomerDetailBaseResponse> callCustomerDetail(Context context, String auth_id, String auth_token,
                                                                      String user_type) {

        HashMap<String, String> mBodyMap = new HashMap<String, String>();
        mBodyMap.put(Links.Header.Auth_ID,auth_id);
        mBodyMap.put(Links.Header.Auth_Token,auth_token);
        mBodyMap.put(Links.Header.User_Type,user_type);


        HashMapLog.getHashMapLog("callCustomerDetail", mBodyMap);

        return APIClient.getClient().create(APIInterface.class).postCustomerDetail(mBodyMap);
    }

    //Get State List
    public static Call<GetStateListBaseResponse> callGetStateList(Context context, String auth_id, String auth_token, String user_type) {

        HashMap<String, String> mBodyMap = new HashMap<String, String>();
        mBodyMap.put(Links.Header.Auth_ID,auth_id);
        mBodyMap.put(Links.Header.Auth_Token,auth_token);
        mBodyMap.put(Links.Header.User_Type,user_type);

        HashMapLog.getHashMapLog("callGetStateList", mBodyMap);

        return APIClient.getClient().create(APIInterface.class).postGetStateList(mBodyMap);
    }

    //Get City List
    public static Call<GetCityListBaseResponse> callGetCityList(Context context, String auth_id, String auth_token, String user_type,
                                                                String state_id) {

        HashMap<String, String> mBodyMap = new HashMap<String, String>();
        mBodyMap.put(Links.Header.Auth_ID,auth_id);
        mBodyMap.put(Links.Header.Auth_Token,auth_token);
        mBodyMap.put(Links.Header.User_Type,user_type);
        mBodyMap.put(Links.City_List_Detail.state_Id,state_id);

        HashMapLog.getHashMapLog("callGetCityList", mBodyMap);

        return APIClient.getClient().create(APIInterface.class).postGetCityList(mBodyMap);
    }

    //Get City List
    public static Call<GetAreaListBaseResponse> callGetAreaList(Context context, String auth_id, String auth_token, String user_type,
                                                                String state_id, String city_id) {

        HashMap<String, String> mBodyMap = new HashMap<String, String>();
        mBodyMap.put(Links.Header.Auth_ID,auth_id);
        mBodyMap.put(Links.Header.Auth_Token,auth_token);
        mBodyMap.put(Links.Header.User_Type,user_type);
        mBodyMap.put(Links.Area_List_Detail.state_Id,state_id);
        mBodyMap.put(Links.Area_List_Detail.city_Id,city_id);

        HashMapLog.getHashMapLog("callGetAreaList", mBodyMap);

        return APIClient.getClient().create(APIInterface.class).postGetAreaList(mBodyMap);
    }

}

