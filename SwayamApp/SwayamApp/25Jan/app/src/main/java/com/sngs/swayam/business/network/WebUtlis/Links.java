package com.sngs.swayam.business.network.webUtlis;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidadvance.topsnackbar.TSnackbar;
import com.sngs.swayam.business.R;
import com.sngs.swayam.business.models.State_Model;
import com.sngs.swayam.business.network.model.Area.AreaListDatum;
import com.sngs.swayam.business.network.model.Attribute.AttributeListDatum;
import com.sngs.swayam.business.network.model.Category.CategoryListDatum;
import com.sngs.swayam.business.network.model.City.CityListDatum;
import com.sngs.swayam.business.network.model.ServiceProvider.ServiceListDatum;
import com.sngs.swayam.business.network.model.State.StateListDatum;
import com.sngs.swayam.business.network.model.SubCategory.SubCategoryListDatum;

import java.util.ArrayList;
import java.util.List;

public class Links {

   public  static final String SERVER_URL = "https://www.digisysindiatech.com/swayam/app/API/";
  //  public  static final String SERVER_URL = "https://sohumngs.com/swayam/app/API/";
    public  static final String User_Type = "4";


    //API lINKS
    public  static  final String Mobile_Verify = "mobileVerify.php";
    public  static  final String Customer_Sign_Up = "customerSignUp.php";
    public  static  final String Customer_Profile = "customerProfileDetail.php";
    public  static  final String Customer_Business_Detail = "customerBusinessDetail.php";
    public  static  final String Get_Service_Provider_List = "getServiceList.php";
    public  static  final String Get_Category_List = "getCategoryList.php";
    public  static  final String Get_Sub_Category_List = "getSubCategoryList.php";
    public  static  final String Get_Attribute_List = "getAttributeList.php";
    public  static  final String Customer_Uploaded_File = "customerDetail.php";
    public  static  final String Customer_Sign_In = "customerSignIn.php";
    public  static  final String Get_Customer_Detail = "getCustomerDetail.php";
    public  static  final String Get_State_List = "getStateList.php";
    public  static  final String Get_City_List = "getCityList.php";
    public  static  final String Get_Area_List = "getAreaList.php";

    public static List<String> services_provider = new ArrayList<>();
    public static List<State_Model> select_state = new ArrayList<>();
    public static List<State_Model> select_city = new ArrayList<>();
    public static List<State_Model> select_service_area = new ArrayList<>();
    public static List<String> notification_list = new ArrayList<>();
    public static  ArrayList<String> selected_image_array_list = new ArrayList<String>();

    public static List<ServiceListDatum> service_list = new ArrayList<>();
    public static List<CategoryListDatum> category_list = new ArrayList<>();
    public static List<SubCategoryListDatum> sub_category_list = new ArrayList<>();
    public static List<AttributeListDatum> attribute_list = new ArrayList<>();
    public static List<String> Banner_list = new ArrayList<>();
    public static  List<String> selected_category_ids_list = new ArrayList<String>();

    public static  List<StateListDatum> state_list = new ArrayList<>();
    public static  List<CityListDatum> city_list = new ArrayList<>();
    public static  List<AreaListDatum> area_list = new ArrayList<>();

    public static String selected_state_name = "";
    public static String selected_city_name = "";
    public static String selected_servies_name = "";
    public static String selected_role = "";


    public static String selected_service_id = "";
    public static String selected_category_id = "";

    public static class Header {
        public static final String Auth_ID = "authId";
        public static final String Auth_Token = "authToken";
        public static final String User_Type = "userType";
        public static final String Reg_Id = "regId";
        public static final String Device_Id = "device_id";
    }

    public static class MobileVerifyDetail {
        public static final String User_Type = "userType";
        public static final String Contact_Number = "contactNumber";
    }

    public static class CustomerSignUpDetail {
        public static final String User_Type = "userType";
        public static final String Customer_Type = "customerType";
        public static final String Customer_Name = "customerName";
        public static final String Customer_Contact_Number = "customerContactNumber";
        public static final String Customer_Employee_Code = "customerEmployeeCode";
    }

    public static class CustomerProfileDetail {
        public static final String CustomerShopType = "customerShopType";
        public static final String CustomerShopName = "customerShopName";
        public static final String Customer_Contact_Number = "customerAlternateContactNumber";
        public static final String Customer_Email = "customerEmail";
        public static final String Customer_Shop_Address1 = "customerShopAddress1";
        public static final String Customer_Shop_Address2 = "customerShopAddress2";
        public static final String Customer_Shop_State = "customerShopState";
        public static final String Customer_Shop_City = "customerShopCity";
        public static final String Customer_Shop_Area = "customerShopArea";
        public static final String Customer_Shop_Pincode = "customerShopPincode";
        public static final String Customer_Profile_Picture = "customerProfilePicture";
        public static final String Customer_Shop_Country = "customerShopCountry";
    }

    public static class CustomerBusinessInfoDetail {
        public static final String customer_Shop_WorkingDays = "customerShopWorkingDays";
        public static final String customer_Shop_StartTime = "customerShopStartTime";
        public static final String customer_Shop_EndTime = "customerShopEndTime";
        public static final String customer_shop_LunchStartTime = "customerShopLunchStartTime";
        public static final String customer_shop_LunchEndTime = "customerShopLunchEndTime";
        public static final String customer_Shop_Year = "customerShopYear";
        public static final String customer_Shop_TotalEmployee = "customerShopTotalEmployee";
        public static final String customer_Shop_Website = "customerShopWebsite";
        public static final String customer_Shop_About = "customerShopAbout";
    }

    public static class GetCategoryListDetail {
        public static final String Service_Id = "serviceId";
    }

    public static class GetSubCategoryListDetail {
        public static final String Service_Id = "serviceId";
        public static final String Category_Id = "categoryId";
    }

    public static class CategoryUploadFileDetail {
        public static final String Service_Id = "serviceId";
        public static final String Category_Id = "categoryId";
        public static final String Sub_Category_Id = "subCategoryId";
        public static final String Attribute_Id = "attributeId";
        public static final String Attachment1 = "attachment1";
        public static final String Attachment2 = "attachment2";
        public static final String Attachment3 = "attachment3";
        public static final String Attachment4 = "attachment4";
    }

    public static class Customer_Sign_In_Detail {
        public static final String User_Contact_Number = "userContactNumber";
    }

    public static class City_List_Detail {
        public static final String state_Id = "stateId";
    }

    public static class Area_List_Detail {
        public static final String state_Id = "stateId";
        public static final String city_Id= "cityId";
    }


    public static void snack_bar(Context context, RelativeLayout main_layout, String mes){
        TSnackbar snackbar = TSnackbar.make(main_layout, ""+mes, TSnackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        TextView textView = (TextView) snackbarView.findViewById(R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }

}
