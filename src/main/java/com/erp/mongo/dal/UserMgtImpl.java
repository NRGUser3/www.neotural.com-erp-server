package com.erp.mongo.dal;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.erp.mongo.model.Menu;
import com.erp.mongo.model.Submenu;
import com.erp.mongo.model.UserRole;

@Repository
public class UserMgtImpl implements UserMgtDAL {

	public static final Logger logger = LoggerFactory.getLogger(UserMgtImpl.class);

	@Autowired
	private MongoTemplate mongoTemplate;

	// save
	public UserRole save(UserRole user) {
		mongoTemplate.save(user);
		return user;
	}

	// load User list
	public List<UserRole> load(List<UserRole> userlist) {
		Query query = new Query();
		//query.with(new Sort(new Order(Direction.DESC, "invnumber"))).limit(5);
		query.with(new Sort(new Order(Direction.DESC, "invnumber")));
		query.addCriteria(Criteria.where("status").is("Active"));
		userlist = mongoTemplate.find(query,UserRole.class);
		return userlist;
	}

	public UserRole updateUser(UserRole user) {
		Update update = new Update();
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(user.getId()));
		update.set("invnumber", user.getInvnumber());
		update.set("username", user.getUsername());
		update.set("password", user.getPassword());
		update.set("userRole", user.getUserRole());
		update.set("status", "Active");
		update.set("menuItem1", user.getMenuItem1());
		update.set("menuItem2", user.getMenuItem2());
		update.set("menuItem3", user.getMenuItem3());
		update.set("menuItem4", user.getMenuItem4());
		update.set("menuItem5", user.getMenuItem5());
		update.set("menuItem6", user.getMenuItem6());
		update.set("menuItem7", user.getMenuItem7());
		update.set("menuItem8", user.getMenuItem8());
		update.set("purchasesubmenu1", user.getPurchasesubmenu1());
		update.set("purchasesubmenu2", user.getPurchasesubmenu2());
		update.set("purchasesubmenu3", user.getPurchasesubmenu3());
		update.set("productsubmenu1", user.getProductsubmenu1());
		update.set("productsubmenu2", user.getProductsubmenu2());
		update.set("productsubmenu3", user.getProductsubmenu3());
		update.set("salessubmenu1", user.getSalessubmenu1());
		update.set("salessubmenu2", user.getSalessubmenu2());
		update.set("salessubmenu3", user.getSalessubmenu3());
		update.set("salessubmenu4", user.getSalessubmenu4());
		update.set("salessubmenu5", user.getSalessubmenu5());
		update.set("departmentname", user.getDepartmentname());
		mongoTemplate.updateFirst(query, update, UserRole.class);
		return user;
	}

	public UserRole removeUser(String id) {
		UserRole response = null;
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		mongoTemplate.remove(query, UserRole.class);
		return response;
	}
	
	public List<Menu> getMenuName(String menuCode) {
		List<Menu> list=null;
		Query query = new Query();
		query.addCriteria(Criteria.where("menucode").is(menuCode));
		list = mongoTemplate.find(query,Menu.class);
		return list;
	}
	
	public List<Submenu> getSubMenuName(String subMenuCode){
		List<Submenu> list=null;
		Query query = new Query();
		query.addCriteria(Criteria.where("submenucode").is(subMenuCode));
		list = mongoTemplate.find(query,Submenu.class);
		return list;
	}

}
