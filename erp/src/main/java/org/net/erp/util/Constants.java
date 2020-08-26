package org.net.erp.util;

public class Constants {

	/*
	 * Validation error messages
	 * 
	 * */
	public static final String ORGANIZATION_NAME = "Please enter organization name";
	public static final String ORGANIZATION_ADDRESS = "Please enter organization address";
	public static final String ORGANIZATION_PLAN = "Please select a plan";
	public static final String CUSTOMER_USERNAME = "Please enter your username";
	public static final String MEMBER_NOT_FOUND = "User not found please enter valid username";
	public static final String INCORRECT_MEMBER_PASSWORD = "Incorrect Password please enter valid password";
	public static final String CUSTOMER_PASSWORD = "Please enter your password";
	public static final String MEMBER_USERNAME = "Please enter member username";
	public static final String MEMBER_PHONENUMBER = "Please enter member phone number";
	public static final String MEMBER_FIRSTNAME = "Please enter member first name";
	public static final String MEMBER_LASTNAME = "Please enter member last name";
	public static final String MEMBER_PASSWORD = "Please enter member password";
	public static final String MEMBER_EMAILID = "Please enter member email id";
	public static final String CLIENT_EMAILID = "Please enter client email id";
	public static final String CLIENT_FULLNAME = "Please enter client full name";
	public static final String CLIENT_ADDRESS = "Please enter client address";
	public static final String CLIENT_PHONENUMBER = "Please enter client contact number";
	public static final String CLIENT_PINCODE = "Please enter client pin code";
	public static final String CLIENT_BIRTHDAY = "Please enter client birthday";
	public static final String SUPPLIER_EMAILID = "Please enter supplier email id";
	public static final String SUPPLIER_FULLNAME = "Please enter supplier name";
	public static final String SUPPLIER_ADDRESS = "Please enter supplier address";
	public static final String SUPPLIER_PHONENUMBER = "Please enter supplier contact number";
	public static final String SUPPLIER_PINCODE = "Please enter supplier pin code";	
	public static final String STAFF_BIRTHDAY = "Please enter staff birthday";
	public static final String STAFF_EMAILID = "Please enter staff email id";
	public static final String STAFF_FULLNAME = "Please enter staff full name";
	public static final String STAFF_ADDRESS = "Please enter staff address";
	public static final String STAFF_PHONENUMBER = "Please enter staff contact number";
	public static final String STAFF_PINCODE = "Please enter staff pin code";	
	public static final String STAFF_DESIGNATION = "Please enter staff designation";
	public static final String STAFF_START_DATE = "Please enter staff employment start date";
	public static final String STAFF_END_DATE = "Please enter staff employment end date";
	public static final String APPOINTMENT_DATE = "Please enter appointment date";
	public static final String SCHEDULE_DATE = "Please enter schedule date";
	public static final String STAFF_IN_TIME = "Please enter staff in time";
	public static final String STAFF_OUT_TIME = "Please enter staff out time";
	public static final String PRODUCT_NAME = "Please enter product name";
	public static final String PRODUCT_BRAND = "Please enter product brand";
	public static final String EMPTY_PRODUCT_BARCODE = "No Barcode Provided";
	public static final String EMPTY_GSTN_NO = "No GSTN No Provided";
	public static final String ORDER_QUANTITY = "Please enter order quantity";
	public static final String ORDER_TOTAL = "Please enter order total";
	public static final String ORDER_DATE = "Please enter order date";
	public static final String ORDER_COST_PRICE = "Please enter order cost price";
	public static final String CATEGORY_NAME = "Please enter category name";
	public static final String SERVICE_COST = "Please enter service cost";
	public static final String SERVICE_DURATION = "Please enter service duration";
	public static final String SERVICE_NAME = "Please enter service name";
	public static final String SALE_SELLING_PRICE = "Sales S.P can't be negative";
	public static final String SALE_COST_PRICE = "Sales C.P can't be negative";
	public static final String SALE_QUANTITY = "Sales quantity can't be zero or negative";
	public static final String SALE_DATE = "Please enter Sale Date";
	/*
	 * Form Fields
	 * */
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	/*
	 * Character's
	 * */
	public static final String AT = "@";
	public static final String DOT = ".";
	public static final String FORWARD_SLASH = "/";
	public static final String QUESTION_MARK = "?";
	public static final String EMPTY = "";
	public static final String COMMA = ",";
	public static final String COLON = ":";
	public static final String SPACE = " ";
	public static final String HYPHEN = "-";
	public static final String NEW_LINE = "\n";
	public static final String TAB = "\t";
	/*
	 * Redirect Jsp's
	 * */
	public static final String REDIRECT = "redirect:/";
	public static final String LOGIN_JSP = "login";
	public static final String DASHBOARD_JSP = "dashboard";
	public static final String CLIENT_JSP = "client";
	public static final String STAFF_JSP = "staff";
	public static final String REDIRECT_SCHDEULE = "redirect:/schedule";
	public static final String SERVICES_JSP = "services";
	public static final String REDIRECT_APPOINTMENT = "redirect:/appointment";
	public static final String REDIRECT_APPOINTMENT_NEW_FORM = "redirect:/appointment/add";
	public static final String REDIRECT_STAFF = "redirect:/staff";
	public static final String REDIRECT_SERVICES = "redirect:/services";
	public static final String APPOINMENTS_JSP = "appointments";
	public static final String SCHEDULE_JSP = "schedule";
	public static final String PRODUCT_JSP = "display-products-page";
	public static final String SALES_JSP = "display-sales-page";
	public static final String SUPPLIER_JSP = "display-new-supplier-page";
	public static final String ORDER_JSP = "display-new-order-page";
	public static final String NEW_ORDER_FORM = "new-order-form";
	public static final String EDIT_ORDER_FORM_JSP = "edit-order-form";
	public static final String NEW_SALE_FORM = "new-sales-form";
	public static final String EDIT_SALE_FORM = "edit-sales-form";
	public static final String PRODUCTS_JSP = "products";
	public static final String ORDERS_JSP = "newOrder";
	public static final String SUPPLIERS_JSP = "addSupplier";
	public static final String PROFILE_CREATION_JSP = "profileCreation";
	public static final String MEMBERSHIP_CREATION_JSP = "membership";
	public static final String ORGANIZATION_CREATION_JSP = "organization";
	public static final String REDIRECT_ORGANIZATION_CREATION_JSP = "redirect:/organization";
	public static final String SIDE_NAV_JSP = "nav-bar";	
	public static final String NEW_ORGANIZATION_FORM_JSP = "new-organization-form";
	public static final String NEW_STAFF_FORM = "new-staff-form";
	public static final String NEW_APPOINTMENT_FORM = "new-appointment-form";
	public static final String EDIT_APPOINTMENT_FORM_JSP = "edit-appointment-form";
	public static final String NEW_SCHEDULE_FORM = "new-schedule-form";
	public static final String EDIT_SCHEDULE_FORM_JSP = "edit-schedule-form";
	public static final String EDIT_STAFF_FORM = "edit-staff-form";
	public static final String NEW_MEMBER_FORM_JSP = "new-member-form";
	/*
	 * Jsp Folder's
	 * */
	public static final String LAYOUT_FOLDER = "layout";
	public static final String FORM_FOLDER = "forms";
	public static final String DISPLAY_FOLDER = "display";
	public static final String INVENTORY_FOLDER = "inventory";
	public static final String BUY_FOLDER = "buy";
	/*
	 * Model Attributes
	 * */
	public static final String SERVICES_MAP = "servicesMap";
	public static final String CLIENT_PLAN_LIST = "clientPlanList";
	public static final String LOGIN_MEMBER = "loginMember";
	public static final String SUCCESSFULLY_UPDATED = "successFullyUpdated";
	public static final String UPDATE_PROFILE = "profileForm";
	public static final String CALCULATED_COMMISSION = "commission";
	public static final String STAFF_DETAILS_FORM = "staffDetailsForm";
	public static final String STAFF_FORM = "staffForm";
	public static final String EDIT_STAFF_FORM_ATTR = "editStaffForm";
	public static final String MEMBERSHIP_FORM = "membershipForm";
	public static final String EDIT_MEMBERSHIP_FORM = "editMembershipForm";
	public static final String SCHEDULE_FORM = "scheduleForm";
	public static final String EDIT_SCHEDULE_FORM = "editScheduleForm";
	public static final String CLIENT_ORGANIZATION_FORM = "clientOrganizationForm";
	public static final String APPOINTMENT_FORM = "appointmentForm";
	public static final String EDIT_APPOINTMENT_FORM = "editAppointmentForm";
	public static final String EDIT_APPOINTMENT_DETAILS = "editAppointmentDetails";
	public static final String OUT_OF_STOCK = "outOfStock";
	public static final String SERVICE_FORM = "serviceForm";
	public static final String EDIT_SERVICE_FORM_ATTR = "editServiceForm";
	public static final String CATEGORY_FORM = "categoryForm";
	public static final String EXISTING_PRODUCT = "productExists";
	public static final String EXISTING_CATEGORY = "categoryExists";
	public static final String EXISTING_EDIT_CATEGORY = "editCategoryExists";
	public static final String EXISTING_SERVICE = "serviceExists";
	public static final String EXISTING_EDIT_SERVICE = "editServiceExists";
	public static final String EXISTING_CLIENT = "clientExists";
	public static final String EXISTING_EDIT_CLIENT = "editClientExists";
	public static final String EXISTING_STAFF = "staffExists";
	public static final String EXISTING_EDIT_STAFF = "editStaffExists";
	public static final String EXISTING_SUPPLIER = "supplierExists";
	public static final String EXISTING_EDIT_SUPPLIER = "editSupplierExists";
	public static final String EXISTING_APPOINTMENT = "appointmentExists";
	public static final String EDIT_CATEGORY_FORM_ATTR = "editCategoryForm";
	public static final String ORDER_FORM = "orderForm";
	public static final String EDIT_ORDER_FORM = "editOrderForm";
	public static final String PRODUCT_FORM = "productForm";
	public static final String EDIT_PRODUCT_FORM = "editProductForm";
	public static final String SALES_FORM = "salesForm";
	public static final String EDIT_SALES_FORM = "editSalesForm";
	public static final String SUPPLIER_FORM = "supplierForm";
	public static final String EDIT_SUPPLIER_FORM = "editSupplierForm";
	public static final String CLIENT_FORM = "clientForm";
	public static final String NEW_CLIENT_FROM_APPOINTMENT = "newClient";
	public static final String EDIT_CLIENT_FORM = "editClientForm";
	public static final String REGISTER_MEMBER_FORM = "registerMemberForm";
	public static final String REGISTER_ORGANIZATION_FORM = "registerOrganizationForm";
	/*
	 * Date Format
	 * */
	public static final String DATE_FORMAT_ONE = "MM/dd/yyyy";
	/*
	 * Session Attributes
	 * */
	public static final String SESSION_FIRTSNAME = "session_firstname";
	public static final String SESSION_CLIENTID = "session_clientId";
	public static final String SESSION_ORGANIZATION_KEY = "session_organization_key";
	/*
	 * JSON attributes
	 * */
	public static final String SORT_ASC = "asc";
	public static final String CLIENT_FIELD = "client_id";
	public static final String CATEGORY_FIELD = "category_id";
	public static final String STOCK_FIELD = "stock_id";
	public static final String APPOINTMENT_FIELD = "appointmentId";
	public static final String SERVICE_FIELD = "service_id";
	public static final String STAFF_FIELD = "staff_id";
	public static final String PRODUCT_FIELD = "product_id";
	public static final String SALES_FIELD = "sales_id";
	public static final String ORDER_FIELD = "order_id";
	/*
	 * Operation Status constants
	 * */
	public static final String OP_STATUS_SUCCESSFUL = "successful";
	public static final String OP_STATUS_UNSUCCESSFUL = "failed";
	/*
	 * */
	public static final String ORDER_STATUS_BOOKED = "Booked";
	public static final String ORDER_STATUS_INTRANSIT = "InTransit";
	public static final String ORDER_STATUS_DELETED = "Cancelled";
	public static final String ORDER_STATUS_RECEIVED = "Received";
	public static final String ORDER_STATUS_SELECT = "Select";
	public static final String PRODUCT_STATUS_BOOKED = "Booked";
	public static final String PRODUCT_STATUS_DELETED = "Cancelled";
	public static final String PRODUCT_STATUS_RECEIVED = "Received";
	public static final String PRODUCT_STATUS_SELECT = "Select";
	public static final String APPOINTMENT_STATUS_BOOKED = "Booked";
	public static final String APPOINTMENT_STATUS_COMPLETED = "Completed";
	/*
	 * */
	public static final String RUPPEE_SYMBOL = "₹";
	/*
	 * */
	public static final String DATA_KEY = "data";
	/*
	 * */
	public static final int INACTIVE_STATUS = 0;
	public static final String INACTIVE_STATUS_STR = "0";
	public static final int ACTIVE_STATUS = 1;
	public static final String ACTIVE_STATUS_STR = "0";
	/*
	 * */
	public static final String FAVICON = "/favicon.ico";
	public static final String REDIRECT_URL = "redirectUrl";
	public static final String REDIRECT_TRUE = "{\"redirect\": \"true\"}";
	public static final String URL_ERROR = "error";
	public static final String USER_NOT_FOUND = "userNotFound";
	public static final String USER_HAS_EXPIRED = "userExpired";
	public static final String STATIC_RESOURCES = "assets";
	public static final String STATIC_RESOURCES_CSS = ".css";
	public static final String STATIC_RESOURCES_FONTAWESOME = "fontawesome";
	public static final String REDIRECT_URL_MSG = "Dear User, Kindly Login to perform operations";
	/*
	 * */
	public static final String USER_NOT_FOUND_DB = "No_Authority";
	public static final String USER_IS_DISABLED = "User is disabled";
	public static final String USER_IS_LOCKED = "User account is locked";
	
}
