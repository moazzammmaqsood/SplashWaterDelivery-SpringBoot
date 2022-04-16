package com.splash.service.impl;

import java.text.ParseException;
import java.util.*;

import com.splash.controller.vendor.*;
import com.splash.domain.constants.AppConstants;
import com.splash.domain.entity.*;
import com.splash.entity.model.SummaryMonthly;
import com.splash.repository.*;
import com.splash.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.splash.controller.base.BaseService;
import com.splash.domain.ApiException;
import com.splash.domain.constants.ApiStatusCodes;
import com.splash.domain.constants.ErrorMessages;
import com.splash.entity.model.ClientDetails;
import com.splash.entity.model.SummaryDaily;
import com.splash.entity.model.SummaryDelivery;
import com.splash.service.VendorService;
import com.splash.utils.Utils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class VendorServiceImpl extends BaseService implements VendorService  {

	@Autowired
	UserRepository userrepo;

	@Autowired
	ClientRepository clientrepo;
	
	@Autowired
	OrderRepository orderrepo;

	@Autowired
	VendorRepository vendorrepo; 
	
	@Autowired
    PasswordEncoder passwordEncoder;

	@Autowired
	SmsRepository smsrepo;

	@Autowired
	FinanceRepository financeRepo;


//	public void updatingrate(){
//
//		Map<Integer,Integer> clientidrate=new HashMap<>();
//		List<OrderEntity> orders =orderrepo.findAll();
//		for (OrderEntity order:
//				orders) {
//			if(order.getRate()==null) {
//				if (clientidrate.containsKey(order.getClientid())) {
//					order.setRate(clientidrate.get(order.getClientid()));
//				} else {
//					ClientEntity clientEntity = clientrepo.getOne(order.getClientid());
//					if (clientEntity != null) {
////				System.out.println(order);
////				System.out.println(clientEntity);
//						clientidrate.put(order.getClientid(), clientEntity.getRate());
////				System.out.println(clientidrate);
//						order.setRate(clientEntity.getRate());
//					}
//
//				}
//				System.out.println(order);
//				orderrepo.save(order);
//
//			}
//		}
//
//	}


	@Override
	public List<GetClientsResponse> getClients() {

	User user = getCurrentUser();
	
	Optional<UserEntity> optionaluser=userrepo.findByusername(user.getUsername()); 
	if(!optionaluser.isPresent())
	{
		throw new ApiException(ApiStatusCodes.INTERNAL_ERROR, ErrorMessages.USERNAME_NOT_FOUND);
	}
	
	List<UserEntity> users=userrepo.findAllByCreatedby(optionaluser.get().getUsername());
		
	List<GetClientsResponse> list=new ArrayList<GetClientsResponse>();
	if(user!=null) {
		for (UserEntity userEntity : users) {
			
			Optional<ClientEntity> client= clientrepo.findByuserid(userEntity.getUserid());
			
			if(!client.isPresent()) break;
			OrderEntity order = orderrepo.getClientlastDelivery(client.get().getClientid());
			GetClientsResponse clientres=new GetClientsResponse(client.get().getAddress(),userEntity.getName(),userEntity.getPhone(),client.get().getClientid(),client.get().getRate(),client.get().getFrequency(),client.get().getBottles(),order.getDate());
					list.add( clientres) ;		

		}
	}
	
	return list;
		
		

		
	}

	
	

	@Override
	public String getuser() {
		User user = getCurrentUser();
		return user.getUsername() ;
	}


	@Override
	public void addUser(ClientRequest request) {
		
 
		User user = getCurrentUser();
		 
		Optional<UserEntity> optionaluser=userrepo.findByusername(user.getUsername()); 
		if(!optionaluser.isPresent())
		{ 
			throw new ApiException(ApiStatusCodes.SERVER_ERROR, ErrorMessages.USERNAME_NOT_FOUND);
		}
 
		VendorEntity vendor = vendorrepo.findByUserid(optionaluser.get().getUserid());
		
		 
		if(vendor==null) { 
			throw new ApiException(ApiStatusCodes.SERVER_ERROR, ErrorMessages.VENDOR_NOT_FOUND);
		}
 
		int nextval=userrepo.findlastuserid() + 1 ;
		 
		String Clientusername=generateusername(vendor.getShortcode(),nextval);
 
		UserEntity newuser =new UserEntity(request.getEmail(),passwordEncoder.encode("splash123123"),request.getName(),request.getContactno(),Clientusername,"C",optionaluser.get().getUsername());
		
		 
		newuser.setCreatedon(new Date());
		newuser.setStatus("E");
		userrepo.save(newuser);
		 
		ClientEntity client=new ClientEntity(newuser.getUserid(), request.getAddress(), request.getRate(), vendor.getVendorid(), request.getFrequency(), request.getNoofbottles(),request.getDeposit(),request.getOncall());
		clientrepo.save(client);
	 

		if(request.getLastdelivery()!=null && !request.getLastdelivery().isEmpty()) { 
			OrderEntity order=new OrderEntity();
			order.setBottlesdelivered(request.getLastbottles());
			order.setBottlesrecieved(request.getLastrecieved());
			order.setPayment(request.getLastpayment());
			order.setClientid(client.getClientid());
			order.setVendorid(vendor.getVendorid());
			order.setStatus("A");
			try {
				order.setDate(Utils.StringtoDate(request.getLastdelivery()));
			} catch (ParseException e) { 
				throw new ApiException(ApiStatusCodes.SERVER_ERROR, e.getLocalizedMessage());
			}
			
			orderrepo.save(order);
	 
		}
			
	}
	
	public String generateusername(String shortcode,int nextval) {
		
		String username= "";
		String usernum= String.valueOf(nextval);
		username=shortcode+StringUtils.leftPad(usernum, 5,"0" );
		
		
		return username;
	}
	
	
	@Override
	public void updateUser(ClientUpdateRequest request) {
	
		User sessionuser= getCurrentUser();
		
		if(request==null) {
			throw new ApiException(ApiStatusCodes.BAD_REQUEST,ErrorMessages.NULL_REQUEST);
		}
		
	 Optional<UserEntity>  user =  userrepo.findByusername(request.getUsername());
		
	 if(!user.isPresent()) {
			throw new ApiException(ApiStatusCodes.INTERNAL_ERROR,ErrorMessages.USERNAME_NOT_FOUND);
	 }
	 
	 
	 if(!user.get().getCreatedby().equals(sessionuser.getUsername())) {
			throw new ApiException(ApiStatusCodes.UNAUTHORIZED,ErrorMessages.AUTHENTICATION_FAILED);
	 }
	 
	 Optional<ClientEntity> client= clientrepo.findByuserid(user.get().getUserid());

	 if(!client.isPresent()) {
			throw new ApiException(ApiStatusCodes.INTERNAL_ERROR,ErrorMessages.CLIENT_NOT_FOUND);
	 }
		
	 
	 ClientEntity Clientupdate= client.get();
	 if(Clientupdate==null) {
		 throw new ApiException(ApiStatusCodes.INTERNAL_ERROR,ErrorMessages.CLIENT_NOT_FOUND);
	 }
	 Clientupdate.setAddress(request.getAddress());
	 Clientupdate.setBottles(request.getNoofbottles());
	 Clientupdate.setFrequency(request.getDaysdelivery());
	 Clientupdate.setRate(request.getRate());
	 Clientupdate.setDeposit(request.getDeposit());
	 Clientupdate.setOncall(request.getOncall());
	 
	 clientrepo.save(Clientupdate);
	 
	 UserEntity Userupdate =user.get();
	 if(Userupdate==null) {
		 throw new ApiException(ApiStatusCodes.INTERNAL_ERROR,ErrorMessages.USERNAME_NOT_FOUND);
	 }
	 
	 Userupdate.setPhone(request.getContactno());
	 System.out.println(Userupdate);
	 userrepo.save(Userupdate);
	 
	 
	};
	
	
	
	
	public void DeliverBottle(BottleDelivered request) {
		
		
		OrderEntity order= new OrderEntity();
		
		User user =getCurrentUser();
		
		Optional<UserEntity> vendoruser= userrepo.findByusername(user.getUsername());
		
		if(!vendoruser.isPresent()) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.USERNAME_NOT_FOUND);	
		}
		
		VendorEntity vendor= vendorrepo.findByUserid(vendoruser.get().getUserid());
		if(vendor==null) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.USERNAME_NOT_FOUND);
		}	
		
		ClientEntity client= clientrepo.getOne(request.getClientid());
		if(client==null) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.USERNAME_NOT_FOUND);
		}
	
		
		UserEntity clientuser= userrepo.getOne(client.getUserid());
		
		if(clientuser==null) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.USERNAME_NOT_FOUND);
		}
		
		if( client.getVendorid()!= vendor.getVendorid()) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.UNAUTHORIZED_USER_TYPE);
		}
		
		order.setClientid(client.getClientid());
		order.setVendorid(vendor.getVendorid());
		order.setBottlesdelivered(request.getBottlesdel());
		order.setBottlesrecieved(request.getBottlesrec());
		order.setPayment(request.getPayment());
		order.setRate(client.getRate());
		try {
			order.setDate(Utils.StringtoDate(request.getDate()));
		} catch (ParseException e) { 
			throw new ApiException(ApiStatusCodes.SERVER_ERROR, e.getLocalizedMessage());
		}
		 
		order.setStatus("A");
		
		orderrepo.save(order);
		ClientTotalDetail  clientTotal = orderrepo.getClientTotalDetail(client.getClientid());
		SmsEntity smsEntity=new SmsEntity();
		smsEntity.setPhoneno(clientuser.getPhone());
		smsEntity.setStatus("N");
		smsEntity.setUserid(clientuser.getUserid());
		Long payments = orderrepo.getPayments(client.getClientid());
		int payment= clientTotal.getBill()-payments.intValue();

		senddeliverysms(smsEntity,clientuser.getName(),vendor.getName(),order.getBottlesdelivered(),order.getBottlesrecieved(),order.getPayment(),order.getDate(),payment,order.getOrderid());
		
	}


	@Override
	public List<ClientDelivery> getDeliveries() {

		User user =getCurrentUser();
		
		Optional<UserEntity> vendoruser= userrepo.findByusername(user.getUsername());
		
		if(!vendoruser.isPresent()) {
			throw new ApiException(ApiStatusCodes.INTERNAL_ERROR,ErrorMessages.USERNAME_NOT_FOUND);	
		}
		
		VendorEntity vendor= vendorrepo.findByUserid(vendoruser.get().getUserid());
		if(vendor==null) {
			throw new ApiException(ApiStatusCodes.INTERNAL_ERROR,ErrorMessages.USERNAME_NOT_FOUND);
		}
		
		List<ClientDelivery> clientdel=orderrepo.getDailydelivery(vendor.getVendorid());
		if(clientdel==null || clientdel.isEmpty()) {
			return null;
		}
		 
		List<ClientDelivery> resultlist=new ArrayList<>();
		for (ClientDelivery clientDelivery : clientdel) {
			 
			if(clientDelivery.bottlefinished()) {
				resultlist.add(clientDelivery);
			}
		}
		
		return resultlist;
	}


	@Override
	public ClientDetails getclient(int clientid, int userid) { 
		User user =getCurrentUser();
		
		Optional<UserEntity> vendoruser= userrepo.findByusername(user.getUsername());
		
		if(!vendoruser.isPresent()) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.USERNAME_NOT_FOUND);
		}
		
		VendorEntity  vendor = vendorrepo.findByUserid(vendoruser.get().getUserid());
		if(vendor==null) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.VENDOR_NOT_FOUND);
		}
	
		
		Optional<ClientEntity> clientent=clientrepo.findById(clientid);
		if(!clientent.isPresent()) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.CLIENT_NOT_FOUND);
		}

		
		if(clientent.get().getUserid()!=userid) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.UNAUTHORIZED_USER_TYPE);
		}
		
		if(clientent.get().getVendorid()!=vendor.getVendorid()) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.UNAUTHORIZED_USER_TYPE);
		}
		
		Optional<UserEntity> userent =userrepo.findById(userid);
		if(!userent.isPresent()) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.USER_NOT_FOUND);
		}
		
		ClientTotalDetail clienttotal = orderrepo.getClientTotalDetail(clientid);
		Long payments = orderrepo.getPayments(clientid);
		if(payments==null){
			payments=0L;
		}


		ClientDetails clientdetails;
		int bottlesholding =0;
		int payment=0;
		int paymentrecieved=0;
		if(clienttotal!= null) {
			clienttotal.setTotalpayment(payments.intValue());
		 bottlesholding =  clienttotal.getTotalbottles()-clienttotal.getTotalrecieved();
		 payment= clienttotal.getBill()-clienttotal.getTotalpayment();
		 paymentrecieved=clienttotal.getTotalpayment();
		
		
//		System.out.println(clienttotal);
			if(clienttotal.getDate()!=null) {
				 clientdetails= new ClientDetails(userid, clientid, userent.get().getName(), userent.get().getPhone(),clientent.get().getAddress(), clienttotal.getTotalbottles() , bottlesholding, clientent.get().getRate(), Utils.Datetostring(clienttotal.getDate()),clientent.get().getFrequency(),payment,paymentrecieved,clientent.get().getDeposit(),clientent.get().getBottles(),clientent.get().getOncall());	
			}else {
				 clientdetails= new ClientDetails(userid, clientid, userent.get().getName(), userent.get().getPhone(),clientent.get().getAddress(), clienttotal.getTotalbottles(),bottlesholding, clientent.get().getRate(), " ",clientent.get().getFrequency(),payment,paymentrecieved,clientent.get().getDeposit(),clientent.get().getBottles(),clientent.get().getOncall());
			}
		 
		} else {
			 clientdetails= new ClientDetails(userid, clientid, userent.get().getName(), userent.get().getPhone(),clientent.get().getAddress(),0,bottlesholding, clientent.get().getRate(), " ",clientent.get().getFrequency(),payment,paymentrecieved,clientent.get().getDeposit(),clientent.get().getBottles(),clientent.get().getOncall());
		}
		return clientdetails;
	}




	@Override
	public List<UserClient> getClientsbyvendor() {
		User user = getCurrentUser(); 
		
		Optional<UserEntity> optionaluser=userrepo.findByusername(user.getUsername()); 
		
		if(!optionaluser.isPresent())
		{
			throw new ApiException(ApiStatusCodes.SERVER_ERROR, ErrorMessages.USERNAME_NOT_FOUND);
		}
		
		VendorEntity  vendor = vendorrepo.findByUserid(optionaluser.get().getUserid());
		if(vendor==null) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.VENDOR_NOT_FOUND);
		}
		
		
		 List<UserClient>  clients=clientrepo.getbyClientsbyvendor(vendor.getVendorid());

		if(clients!=null || !clients.isEmpty()) return clients ;
		else return null;
		
	}




	@Override
	public List<OrderEntity> getClientOrders(int clientid) {
		User user = getCurrentUser(); 
		
		Optional<UserEntity> optionaluser=userrepo.findByusername(user.getUsername());
		if(!optionaluser.isPresent())
		{
			throw new ApiException(ApiStatusCodes.SERVER_ERROR, ErrorMessages.USERNAME_NOT_FOUND);
		}
		
		VendorEntity  vendor = vendorrepo.findByUserid(optionaluser.get().getUserid());
		if(vendor==null) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.VENDOR_NOT_FOUND);
		}
		
		ClientEntity client =clientrepo.getOne(clientid);
		if(client==null) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.CLIENT_NOT_FOUND);
		}
		
		if (client.getVendorid()!=vendor.getVendorid()) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.UNAUTHORIZED_USER_TYPE);
		}
		Optional<List<OrderEntity>> orders= orderrepo.findAllByclientidOrderByDateAsc(clientid);
		
		if(orders.isPresent()) {
			return orders.get();
		}else {
			return null;
		}
 }




	@Override
	public void deleteClientOrder(int orderid) {

		User user = getCurrentUser(); 
		
		Optional<UserEntity> optionaluser=userrepo.findByusername(user.getUsername());
		if(!optionaluser.isPresent())
		{
			throw new ApiException(ApiStatusCodes.SERVER_ERROR, ErrorMessages.USERNAME_NOT_FOUND);
		}
		
		VendorEntity  vendor = vendorrepo.findByUserid(optionaluser.get().getUserid());
		if(vendor==null) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.VENDOR_NOT_FOUND);
		}
		
		if(orderid==0) {
			throw new ApiException(ApiStatusCodes.BAD_REQUEST,ErrorMessages.NULL_REQUEST);
		}
		OrderEntity order=orderrepo.getOne(orderid);
		if(order.getVendorid()!=vendor.getVendorid()) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.UNAUTHORIZED_USER_TYPE);
		}
		
		if(order.getStatus().equals("D")) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.ORDER_ALREADY_DELETED);
		}
		
		order.setStatus("D");
		
		orderrepo.save(order);
		
		deletesms(orderid);
 	}




	@Override
	public void editUser(EditClientRequest request) {

		User user = getCurrentUser();
		 
		Optional<UserEntity> optionaluser=userrepo.findByusername(user.getUsername()); 
		if(!optionaluser.isPresent())
		{ 
			throw new ApiException(ApiStatusCodes.SERVER_ERROR, ErrorMessages.USERNAME_NOT_FOUND);
		}
 
		VendorEntity vendor = vendorrepo.findByUserid(optionaluser.get().getUserid());
		
		 
		if(vendor==null) { 
			throw new ApiException(ApiStatusCodes.SERVER_ERROR, ErrorMessages.VENDOR_NOT_FOUND);
		}
  
		ClientEntity oldclient =clientrepo.getOne(request.getClientid());
		if(oldclient.getVendorid()!=vendor.getVendorid()) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR, ErrorMessages.UNAUTHORIZED_USER_TYPE);
		}
		
		if(oldclient.getUserid()!=request.getUserid()) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR, ErrorMessages.INVALID_USER_REQUEST);
		}
		UserEntity olduser= userrepo.getOne(oldclient.getUserid());
		olduser.setEmail(request.getEmail());
		olduser.setName(request.getName());
		olduser.setPhone(request.getContactno());
	  
	 
		userrepo.save(olduser);
		 
		oldclient.setAddress(request.getAddress());
		oldclient.setRate(request.getRate());
		oldclient.setFrequency(request.getFrequency());
		oldclient.setBottles(request.getNoofbottles());
		oldclient.setDeposit(request.getDeposit());
		oldclient.setOncall(request.getOncall());
	 	clientrepo.save(oldclient);

	}




	@Override
	public VendorEntity getVendor(int id) {
	    return vendorrepo.getOne(id);
	 }




	@Override
	public List<SummaryDelivery> getVendorDailySummaryDeliveries(String date) {
		
		User user = getCurrentUser();
		 
		Optional<UserEntity> optionaluser=userrepo.findByusername(user.getUsername()); 
		if(!optionaluser.isPresent())
		{ 
			throw new ApiException(ApiStatusCodes.SERVER_ERROR, ErrorMessages.USERNAME_NOT_FOUND);
		}
 
		VendorEntity vendor = vendorrepo.findByUserid(optionaluser.get().getUserid());
		
		 
		if(vendor==null) { 
			throw new ApiException(ApiStatusCodes.SERVER_ERROR, ErrorMessages.VENDOR_NOT_FOUND);
		}
		
		List<SummaryDelivery> list =orderrepo.getdeliveryBydate(date, vendor.getVendorid());
		
		if(list==null || list.isEmpty()) {
			throw new ApiException(ApiStatusCodes.DATA_NOT_FOUND,ErrorMessages.DATANOTFOUND);
			
		}
		return list;
	}




	@Override
	public SummaryDaily getVendorSummarybyDate(String date) {
		User user = getCurrentUser();
		 
		Optional<UserEntity> optionaluser=userrepo.findByusername(user.getUsername()); 
		if(!optionaluser.isPresent())
		{ 
			throw new ApiException(ApiStatusCodes.SERVER_ERROR, ErrorMessages.USERNAME_NOT_FOUND);
		}
 
		VendorEntity vendor = vendorrepo.findByUserid(optionaluser.get().getUserid());
		
		 
		if(vendor==null) { 
			throw new ApiException(ApiStatusCodes.SERVER_ERROR, ErrorMessages.VENDOR_NOT_FOUND);
		}
		
		SummaryDaily summary =orderrepo.getDailySummary(date, vendor.getVendorid());
		
		if(summary==null ) {
			throw new ApiException(ApiStatusCodes.DATA_NOT_FOUND,ErrorMessages.DATANOTFOUND);
			
		}

		if(summary.getBottlesdelivered()==null){
			throw new ApiException(ApiStatusCodes.DATA_NOT_FOUND,ErrorMessages.DATANOTFOUND);

		}
		return summary;
	}

	@Override
	public void senddeliverysms(SmsEntity entity, String name, String Vendorname, int noofbottles,int noofbottlerec,int payment, Date date,int remainingpayment,int orderid) {
		Map<String , String> map=new HashMap<>();
		String[] firstname = name.split(" ");
		map.put(AppConstants.NAMEKEY,firstname[0]);
		map.put(AppConstants.VENDORNAMEKEY,Vendorname);
		map.put(AppConstants.NOOFBOTTLEKEY,String.valueOf(noofbottles));
		map.put(AppConstants.DATEKEY,Utils.getdatetostring(date));
		map.put(AppConstants.BOTTLESRECIEVEDKEY,String.valueOf(noofbottlerec));
		map.put(AppConstants.PAYMENTRECIEVED,String.valueOf(payment));
		map.put(AppConstants.REMAININGBALANCEKEY,String.valueOf(remainingpayment));
		entity.setSenttime(new Date());
		entity.setSmstext(Utils.getsmstext(AppConstants.DELIVERYTEXT,map));
		entity.setOrderid(orderid);

		smsrepo.save(entity);


	}

	@Override
	public void deletesms(int orderid) {

		SmsEntity sms= smsrepo.findByOrderid(orderid);
		if(sms!=null){
			sms.setStatus("E");
			sms.setResponse("OrderDeleted");
			smsrepo.save(sms);
		}
	}

	@Override
	public void disableclient(int clientid) {
		User user = getCurrentUser();

		Optional<UserEntity> optionaluser=userrepo.findByusername(user.getUsername());
		if(!optionaluser.isPresent())
		{
			throw new ApiException(ApiStatusCodes.SERVER_ERROR, ErrorMessages.USERNAME_NOT_FOUND);
		}

		VendorEntity  vendor = vendorrepo.findByUserid(optionaluser.get().getUserid());
		if(vendor==null) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.VENDOR_NOT_FOUND);
		}

		ClientEntity client =clientrepo.getOne(clientid);
		if(client==null) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.CLIENT_NOT_FOUND);
		}

		if (client.getVendorid()!=vendor.getVendorid()) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.UNAUTHORIZED_USER_TYPE);
		}

		Optional<UserEntity> clientUser=userrepo.findById(client.getUserid());
		if(!clientUser.isPresent())
		{
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.USER_NOT_FOUND);

		}

		UserEntity userUpdate = clientUser.get();
		userUpdate.setStatus("D");
		userrepo.save(userUpdate);

	}

	@Override
	public void enableclient(int clientid) {
		User user = getCurrentUser();

		Optional<UserEntity> optionaluser=userrepo.findByusername(user.getUsername());
		if(!optionaluser.isPresent())
		{
			throw new ApiException(ApiStatusCodes.SERVER_ERROR, ErrorMessages.USERNAME_NOT_FOUND);
		}

		VendorEntity  vendor = vendorrepo.findByUserid(optionaluser.get().getUserid());
		if(vendor==null) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.VENDOR_NOT_FOUND);
		}

		ClientEntity client =clientrepo.getOne(clientid);
		if(client==null) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.CLIENT_NOT_FOUND);
		}

		if (client.getVendorid()!=vendor.getVendorid()) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.UNAUTHORIZED_USER_TYPE);
		}

		Optional<UserEntity> clientUser=userrepo.findById(client.getUserid());
		if(!clientUser.isPresent())
		{
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.USER_NOT_FOUND);

		}

		UserEntity userUpdate = clientUser.get();
		userUpdate.setStatus("E");
		userrepo.save(userUpdate);

	}

	@Override
	public void addFinance(FinanceRequest request) {
		User user = getCurrentUser();

		if(request==null){
			throw new ApiException(ApiStatusCodes.BAD_REQUEST,ErrorMessages.INVALID_USER_REQUEST);
		}

		Optional<UserEntity> optionaluser=userrepo.findByusername(user.getUsername());
		if(!optionaluser.isPresent())
		{
			throw new ApiException(ApiStatusCodes.SERVER_ERROR, ErrorMessages.USERNAME_NOT_FOUND);
		}

		VendorEntity  vendor = vendorrepo.findByUserid(optionaluser.get().getUserid());
		if(vendor==null) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.VENDOR_NOT_FOUND);
		}


		FinanceEntitiy entitiy=new FinanceEntitiy();
		entitiy.setStatus("A");
		if(request.getType().equals("I")){
			entitiy.setIncome(request.getAmount());
		}else{
			entitiy.setExpense(request.getAmount());
		}
		entitiy.setRemarks(request.getRemark());
		entitiy.setVendorid(vendor.getVendorid());
		try {
			entitiy.setDate(Utils.StringtoDate(request.getDate()));
		} catch (ParseException e) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.DATE_NOT_FORMATTED);

		}

		financeRepo.save(entitiy);

	}

	@Override
	public void deleteFinance(int financeid) {

		User user = getCurrentUser();

		if(financeid==0){
			throw new ApiException(ApiStatusCodes.BAD_REQUEST,ErrorMessages.INVALID_USER_REQUEST);
		}

		Optional<UserEntity> optionaluser=userrepo.findByusername(user.getUsername());
		if(!optionaluser.isPresent())
		{
			throw new ApiException(ApiStatusCodes.SERVER_ERROR, ErrorMessages.USERNAME_NOT_FOUND);
		}

		VendorEntity  vendor = vendorrepo.findByUserid(optionaluser.get().getUserid());
		if(vendor==null) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.VENDOR_NOT_FOUND);
		}

		Optional<FinanceEntitiy> financeEntity=		financeRepo.findById(financeid);

			if(!financeEntity.isPresent()){
				throw new ApiException(ApiStatusCodes.DATA_NOT_FOUND,ErrorMessages.DATANOTFOUND);
			}

			if (financeEntity.get().getVendorid()!=vendor.getVendorid()){
				throw new ApiException(ApiStatusCodes.UNAUTHORIZED,ErrorMessages.UNAUTHORIZED_USER_TYPE);
			}

			financeRepo.delete(financeEntity.get());


	}

	@Override
	public List<FinanceEntitiy> getFinanceByDateVendorId(String date) {

		User user = getCurrentUser();


		Date date1 =null;
		try {
			date1= Utils.StringtoDate(date);

		}catch (Exception e){
			throw new ApiException(ApiStatusCodes.BAD_REQUEST, ErrorMessages.DATE_NOT_FORMATTED);
		}
		String findMonth=null;
		if(date1!=null)
		{
			findMonth=Utils.fetchYearAndMonth(date1);
		}



		Optional<UserEntity> optionaluser=userrepo.findByusername(user.getUsername());
		if(!optionaluser.isPresent())
		{
			throw new ApiException(ApiStatusCodes.SERVER_ERROR, ErrorMessages.USERNAME_NOT_FOUND);
		}

		VendorEntity  vendor = vendorrepo.findByUserid(optionaluser.get().getUserid());
		if(vendor==null) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR,ErrorMessages.VENDOR_NOT_FOUND);
		}

		List<FinanceEntitiy> financeEntity=		financeRepo.getList(findMonth+"%",vendor.getVendorid());

		if(financeEntity.isEmpty()){
			throw new ApiException(ApiStatusCodes.DATA_NOT_FOUND,ErrorMessages.DATANOTFOUND);
		}



		return financeEntity;
	}

	@Override
	public SummaryMonthly getVendorSummarybyMonth(String date) {
		User user = getCurrentUser();

		Date date1 =null;
		try {
		 date1= Utils.StringtoDate(date);

		}catch (Exception e){
			throw new ApiException(ApiStatusCodes.BAD_REQUEST, ErrorMessages.DATE_NOT_FORMATTED);
		}
		String findMonth=null;
		if(date1!=null)
		{
		findMonth=Utils.fetchYearAndMonth(date1);
		}

		Optional<UserEntity> optionaluser=userrepo.findByusername(user.getUsername());
		if(!optionaluser.isPresent())
		{
			throw new ApiException(ApiStatusCodes.SERVER_ERROR, ErrorMessages.USERNAME_NOT_FOUND);
		}

		VendorEntity vendor = vendorrepo.findByUserid(optionaluser.get().getUserid());


		if(vendor==null) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR, ErrorMessages.VENDOR_NOT_FOUND);
		}

		SummaryMonthly summary =orderrepo.getMonthlySunmary(findMonth+"%", vendor.getVendorid());

		if(summary==null ) {
			throw new ApiException(ApiStatusCodes.DATA_NOT_FOUND,ErrorMessages.DATANOTFOUND);

		}

		return summary;
	}



	@Override
	public void sendSms() {

		List<SmsEntity> smsList = smsrepo.fetchUnSentSms();

		for (SmsEntity sms:
			 smsList) {

			String phoneno = sms.getPhoneno();
			phoneno = phoneno.trim();
			if (phoneno != null || !phoneno.isEmpty() || phoneno.length() == 11) {

				if (phoneno.contains(",")) {
					phoneno = phoneno.substring(0, phoneno.indexOf(","));
				}
				phoneno = phoneno.replace("+", "");
				phoneno = phoneno.replace("-", "");
				phoneno = phoneno.replace(" ", "");

				try {

					if (phoneno.substring(0, 1).equals("92")) {
						phoneno = phoneno.replaceFirst("92", "0");
					}
				HttpEntity<String> response=	Utils.sendSmsUtil(sms.getSmstext(), phoneno);
					if(response.getBody().contains("Accepted")){
						sms.setSenttime(new Date());
						sms.setResponse(response.getBody());
						sms.setStatus("Y");
						smsrepo.save(sms);
					}else{
						sms.setSenttime(new Date());
						sms.setResponse(response.getBody());
						sms.setStatus("E");
						smsrepo.save(sms);
					}
				}catch (Exception e){
					sms.setSenttime(new Date());
					sms.setResponse(e.getMessage());
					sms.setStatus("E");
					smsrepo.save(sms);
				}
				}

		}



	}

	@Override
	public void sendCustomSms(CustomSmsRequest smsRequest) {
		User user = getCurrentUser();
		Optional<UserEntity> optionaluser=userrepo.findByusername(user.getUsername());
		if(!optionaluser.isPresent())
		{
			throw new ApiException(ApiStatusCodes.SERVER_ERROR, ErrorMessages.USERNAME_NOT_FOUND);
		}

		VendorEntity vendor = vendorrepo.findByUserid(optionaluser.get().getUserid());


		if(vendor==null) {
			throw new ApiException(ApiStatusCodes.SERVER_ERROR, ErrorMessages.VENDOR_NOT_FOUND);
		}
		List<String> list = userrepo.getAllNumbers(vendor.getVendorid());
		System.out.println(list);
		StringBuilder stringBuilder=new StringBuilder();
		int count =0;
		for (String number:
			 list) {

			String phoneno = number;
			phoneno = phoneno.trim();
			if (phoneno != null || !phoneno.isEmpty() || phoneno.length() == 11) {

				if (phoneno.contains(",")) {
					phoneno = phoneno.substring(0, phoneno.indexOf(","));
				}
				phoneno = phoneno.replace("+", "");
				phoneno = phoneno.replace("-", "");
				phoneno = phoneno.replace(" ", "");
				stringBuilder.append(phoneno);
				if(list.size()!=count){
					stringBuilder.append(",");
				}

					count++;
			}
		}

		try {

			HttpEntity<String> response = Utils.sendSmsUtil(smsRequest.getSms(), stringBuilder.toString());
			System.out.println(response.getBody());
			if (response.getBody().contains("Accepted")) {
				System.out.println("sms sent"+ " total count "+count);
			} else {
				System.out.println("sms not sent"+" total count "+count);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
