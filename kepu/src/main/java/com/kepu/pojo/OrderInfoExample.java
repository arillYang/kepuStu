package com.kepu.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Integer value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Integer value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Integer value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Integer value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Integer> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Integer> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andSysAreaIdIsNull() {
            addCriterion("sys_area_id is null");
            return (Criteria) this;
        }

        public Criteria andSysAreaIdIsNotNull() {
            addCriterion("sys_area_id is not null");
            return (Criteria) this;
        }

        public Criteria andSysAreaIdEqualTo(String value) {
            addCriterion("sys_area_id =", value, "sysAreaId");
            return (Criteria) this;
        }

        public Criteria andSysAreaIdNotEqualTo(String value) {
            addCriterion("sys_area_id <>", value, "sysAreaId");
            return (Criteria) this;
        }

        public Criteria andSysAreaIdGreaterThan(String value) {
            addCriterion("sys_area_id >", value, "sysAreaId");
            return (Criteria) this;
        }

        public Criteria andSysAreaIdGreaterThanOrEqualTo(String value) {
            addCriterion("sys_area_id >=", value, "sysAreaId");
            return (Criteria) this;
        }

        public Criteria andSysAreaIdLessThan(String value) {
            addCriterion("sys_area_id <", value, "sysAreaId");
            return (Criteria) this;
        }

        public Criteria andSysAreaIdLessThanOrEqualTo(String value) {
            addCriterion("sys_area_id <=", value, "sysAreaId");
            return (Criteria) this;
        }

        public Criteria andSysAreaIdLike(String value) {
            addCriterion("sys_area_id like", value, "sysAreaId");
            return (Criteria) this;
        }

        public Criteria andSysAreaIdNotLike(String value) {
            addCriterion("sys_area_id not like", value, "sysAreaId");
            return (Criteria) this;
        }

        public Criteria andSysAreaIdIn(List<String> values) {
            addCriterion("sys_area_id in", values, "sysAreaId");
            return (Criteria) this;
        }

        public Criteria andSysAreaIdNotIn(List<String> values) {
            addCriterion("sys_area_id not in", values, "sysAreaId");
            return (Criteria) this;
        }

        public Criteria andSysAreaIdBetween(String value1, String value2) {
            addCriterion("sys_area_id between", value1, value2, "sysAreaId");
            return (Criteria) this;
        }

        public Criteria andSysAreaIdNotBetween(String value1, String value2) {
            addCriterion("sys_area_id not between", value1, value2, "sysAreaId");
            return (Criteria) this;
        }

        public Criteria andOrderAddressIsNull() {
            addCriterion("order_address is null");
            return (Criteria) this;
        }

        public Criteria andOrderAddressIsNotNull() {
            addCriterion("order_address is not null");
            return (Criteria) this;
        }

        public Criteria andOrderAddressEqualTo(String value) {
            addCriterion("order_address =", value, "orderAddress");
            return (Criteria) this;
        }

        public Criteria andOrderAddressNotEqualTo(String value) {
            addCriterion("order_address <>", value, "orderAddress");
            return (Criteria) this;
        }

        public Criteria andOrderAddressGreaterThan(String value) {
            addCriterion("order_address >", value, "orderAddress");
            return (Criteria) this;
        }

        public Criteria andOrderAddressGreaterThanOrEqualTo(String value) {
            addCriterion("order_address >=", value, "orderAddress");
            return (Criteria) this;
        }

        public Criteria andOrderAddressLessThan(String value) {
            addCriterion("order_address <", value, "orderAddress");
            return (Criteria) this;
        }

        public Criteria andOrderAddressLessThanOrEqualTo(String value) {
            addCriterion("order_address <=", value, "orderAddress");
            return (Criteria) this;
        }

        public Criteria andOrderAddressLike(String value) {
            addCriterion("order_address like", value, "orderAddress");
            return (Criteria) this;
        }

        public Criteria andOrderAddressNotLike(String value) {
            addCriterion("order_address not like", value, "orderAddress");
            return (Criteria) this;
        }

        public Criteria andOrderAddressIn(List<String> values) {
            addCriterion("order_address in", values, "orderAddress");
            return (Criteria) this;
        }

        public Criteria andOrderAddressNotIn(List<String> values) {
            addCriterion("order_address not in", values, "orderAddress");
            return (Criteria) this;
        }

        public Criteria andOrderAddressBetween(String value1, String value2) {
            addCriterion("order_address between", value1, value2, "orderAddress");
            return (Criteria) this;
        }

        public Criteria andOrderAddressNotBetween(String value1, String value2) {
            addCriterion("order_address not between", value1, value2, "orderAddress");
            return (Criteria) this;
        }

        public Criteria andOrderPhoneIsNull() {
            addCriterion("order_phone is null");
            return (Criteria) this;
        }

        public Criteria andOrderPhoneIsNotNull() {
            addCriterion("order_phone is not null");
            return (Criteria) this;
        }

        public Criteria andOrderPhoneEqualTo(String value) {
            addCriterion("order_phone =", value, "orderPhone");
            return (Criteria) this;
        }

        public Criteria andOrderPhoneNotEqualTo(String value) {
            addCriterion("order_phone <>", value, "orderPhone");
            return (Criteria) this;
        }

        public Criteria andOrderPhoneGreaterThan(String value) {
            addCriterion("order_phone >", value, "orderPhone");
            return (Criteria) this;
        }

        public Criteria andOrderPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("order_phone >=", value, "orderPhone");
            return (Criteria) this;
        }

        public Criteria andOrderPhoneLessThan(String value) {
            addCriterion("order_phone <", value, "orderPhone");
            return (Criteria) this;
        }

        public Criteria andOrderPhoneLessThanOrEqualTo(String value) {
            addCriterion("order_phone <=", value, "orderPhone");
            return (Criteria) this;
        }

        public Criteria andOrderPhoneLike(String value) {
            addCriterion("order_phone like", value, "orderPhone");
            return (Criteria) this;
        }

        public Criteria andOrderPhoneNotLike(String value) {
            addCriterion("order_phone not like", value, "orderPhone");
            return (Criteria) this;
        }

        public Criteria andOrderPhoneIn(List<String> values) {
            addCriterion("order_phone in", values, "orderPhone");
            return (Criteria) this;
        }

        public Criteria andOrderPhoneNotIn(List<String> values) {
            addCriterion("order_phone not in", values, "orderPhone");
            return (Criteria) this;
        }

        public Criteria andOrderPhoneBetween(String value1, String value2) {
            addCriterion("order_phone between", value1, value2, "orderPhone");
            return (Criteria) this;
        }

        public Criteria andOrderPhoneNotBetween(String value1, String value2) {
            addCriterion("order_phone not between", value1, value2, "orderPhone");
            return (Criteria) this;
        }

        public Criteria andOrderZipcodeIsNull() {
            addCriterion("order_zipcode is null");
            return (Criteria) this;
        }

        public Criteria andOrderZipcodeIsNotNull() {
            addCriterion("order_zipcode is not null");
            return (Criteria) this;
        }

        public Criteria andOrderZipcodeEqualTo(String value) {
            addCriterion("order_zipcode =", value, "orderZipcode");
            return (Criteria) this;
        }

        public Criteria andOrderZipcodeNotEqualTo(String value) {
            addCriterion("order_zipcode <>", value, "orderZipcode");
            return (Criteria) this;
        }

        public Criteria andOrderZipcodeGreaterThan(String value) {
            addCriterion("order_zipcode >", value, "orderZipcode");
            return (Criteria) this;
        }

        public Criteria andOrderZipcodeGreaterThanOrEqualTo(String value) {
            addCriterion("order_zipcode >=", value, "orderZipcode");
            return (Criteria) this;
        }

        public Criteria andOrderZipcodeLessThan(String value) {
            addCriterion("order_zipcode <", value, "orderZipcode");
            return (Criteria) this;
        }

        public Criteria andOrderZipcodeLessThanOrEqualTo(String value) {
            addCriterion("order_zipcode <=", value, "orderZipcode");
            return (Criteria) this;
        }

        public Criteria andOrderZipcodeLike(String value) {
            addCriterion("order_zipcode like", value, "orderZipcode");
            return (Criteria) this;
        }

        public Criteria andOrderZipcodeNotLike(String value) {
            addCriterion("order_zipcode not like", value, "orderZipcode");
            return (Criteria) this;
        }

        public Criteria andOrderZipcodeIn(List<String> values) {
            addCriterion("order_zipcode in", values, "orderZipcode");
            return (Criteria) this;
        }

        public Criteria andOrderZipcodeNotIn(List<String> values) {
            addCriterion("order_zipcode not in", values, "orderZipcode");
            return (Criteria) this;
        }

        public Criteria andOrderZipcodeBetween(String value1, String value2) {
            addCriterion("order_zipcode between", value1, value2, "orderZipcode");
            return (Criteria) this;
        }

        public Criteria andOrderZipcodeNotBetween(String value1, String value2) {
            addCriterion("order_zipcode not between", value1, value2, "orderZipcode");
            return (Criteria) this;
        }

        public Criteria andOrderUserIsNull() {
            addCriterion("order_user is null");
            return (Criteria) this;
        }

        public Criteria andOrderUserIsNotNull() {
            addCriterion("order_user is not null");
            return (Criteria) this;
        }

        public Criteria andOrderUserEqualTo(String value) {
            addCriterion("order_user =", value, "orderUser");
            return (Criteria) this;
        }

        public Criteria andOrderUserNotEqualTo(String value) {
            addCriterion("order_user <>", value, "orderUser");
            return (Criteria) this;
        }

        public Criteria andOrderUserGreaterThan(String value) {
            addCriterion("order_user >", value, "orderUser");
            return (Criteria) this;
        }

        public Criteria andOrderUserGreaterThanOrEqualTo(String value) {
            addCriterion("order_user >=", value, "orderUser");
            return (Criteria) this;
        }

        public Criteria andOrderUserLessThan(String value) {
            addCriterion("order_user <", value, "orderUser");
            return (Criteria) this;
        }

        public Criteria andOrderUserLessThanOrEqualTo(String value) {
            addCriterion("order_user <=", value, "orderUser");
            return (Criteria) this;
        }

        public Criteria andOrderUserLike(String value) {
            addCriterion("order_user like", value, "orderUser");
            return (Criteria) this;
        }

        public Criteria andOrderUserNotLike(String value) {
            addCriterion("order_user not like", value, "orderUser");
            return (Criteria) this;
        }

        public Criteria andOrderUserIn(List<String> values) {
            addCriterion("order_user in", values, "orderUser");
            return (Criteria) this;
        }

        public Criteria andOrderUserNotIn(List<String> values) {
            addCriterion("order_user not in", values, "orderUser");
            return (Criteria) this;
        }

        public Criteria andOrderUserBetween(String value1, String value2) {
            addCriterion("order_user between", value1, value2, "orderUser");
            return (Criteria) this;
        }

        public Criteria andOrderUserNotBetween(String value1, String value2) {
            addCriterion("order_user not between", value1, value2, "orderUser");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIsNull() {
            addCriterion("order_type is null");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIsNotNull() {
            addCriterion("order_type is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTypeEqualTo(Integer value) {
            addCriterion("order_type =", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotEqualTo(Integer value) {
            addCriterion("order_type <>", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeGreaterThan(Integer value) {
            addCriterion("order_type >", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_type >=", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLessThan(Integer value) {
            addCriterion("order_type <", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLessThanOrEqualTo(Integer value) {
            addCriterion("order_type <=", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIn(List<Integer> values) {
            addCriterion("order_type in", values, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotIn(List<Integer> values) {
            addCriterion("order_type not in", values, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeBetween(Integer value1, Integer value2) {
            addCriterion("order_type between", value1, value2, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("order_type not between", value1, value2, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderStatuIsNull() {
            addCriterion("order_statu is null");
            return (Criteria) this;
        }

        public Criteria andOrderStatuIsNotNull() {
            addCriterion("order_statu is not null");
            return (Criteria) this;
        }

        public Criteria andOrderStatuEqualTo(Integer value) {
            addCriterion("order_statu =", value, "orderStatu");
            return (Criteria) this;
        }

        public Criteria andOrderStatuNotEqualTo(Integer value) {
            addCriterion("order_statu <>", value, "orderStatu");
            return (Criteria) this;
        }

        public Criteria andOrderStatuGreaterThan(Integer value) {
            addCriterion("order_statu >", value, "orderStatu");
            return (Criteria) this;
        }

        public Criteria andOrderStatuGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_statu >=", value, "orderStatu");
            return (Criteria) this;
        }

        public Criteria andOrderStatuLessThan(Integer value) {
            addCriterion("order_statu <", value, "orderStatu");
            return (Criteria) this;
        }

        public Criteria andOrderStatuLessThanOrEqualTo(Integer value) {
            addCriterion("order_statu <=", value, "orderStatu");
            return (Criteria) this;
        }

        public Criteria andOrderStatuIn(List<Integer> values) {
            addCriterion("order_statu in", values, "orderStatu");
            return (Criteria) this;
        }

        public Criteria andOrderStatuNotIn(List<Integer> values) {
            addCriterion("order_statu not in", values, "orderStatu");
            return (Criteria) this;
        }

        public Criteria andOrderStatuBetween(Integer value1, Integer value2) {
            addCriterion("order_statu between", value1, value2, "orderStatu");
            return (Criteria) this;
        }

        public Criteria andOrderStatuNotBetween(Integer value1, Integer value2) {
            addCriterion("order_statu not between", value1, value2, "orderStatu");
            return (Criteria) this;
        }

        public Criteria andOrderSubjctIdIsNull() {
            addCriterion("order_subjct_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderSubjctIdIsNotNull() {
            addCriterion("order_subjct_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderSubjctIdEqualTo(String value) {
            addCriterion("order_subjct_id =", value, "orderSubjctId");
            return (Criteria) this;
        }

        public Criteria andOrderSubjctIdNotEqualTo(String value) {
            addCriterion("order_subjct_id <>", value, "orderSubjctId");
            return (Criteria) this;
        }

        public Criteria andOrderSubjctIdGreaterThan(String value) {
            addCriterion("order_subjct_id >", value, "orderSubjctId");
            return (Criteria) this;
        }

        public Criteria andOrderSubjctIdGreaterThanOrEqualTo(String value) {
            addCriterion("order_subjct_id >=", value, "orderSubjctId");
            return (Criteria) this;
        }

        public Criteria andOrderSubjctIdLessThan(String value) {
            addCriterion("order_subjct_id <", value, "orderSubjctId");
            return (Criteria) this;
        }

        public Criteria andOrderSubjctIdLessThanOrEqualTo(String value) {
            addCriterion("order_subjct_id <=", value, "orderSubjctId");
            return (Criteria) this;
        }

        public Criteria andOrderSubjctIdLike(String value) {
            addCriterion("order_subjct_id like", value, "orderSubjctId");
            return (Criteria) this;
        }

        public Criteria andOrderSubjctIdNotLike(String value) {
            addCriterion("order_subjct_id not like", value, "orderSubjctId");
            return (Criteria) this;
        }

        public Criteria andOrderSubjctIdIn(List<String> values) {
            addCriterion("order_subjct_id in", values, "orderSubjctId");
            return (Criteria) this;
        }

        public Criteria andOrderSubjctIdNotIn(List<String> values) {
            addCriterion("order_subjct_id not in", values, "orderSubjctId");
            return (Criteria) this;
        }

        public Criteria andOrderSubjctIdBetween(String value1, String value2) {
            addCriterion("order_subjct_id between", value1, value2, "orderSubjctId");
            return (Criteria) this;
        }

        public Criteria andOrderSubjctIdNotBetween(String value1, String value2) {
            addCriterion("order_subjct_id not between", value1, value2, "orderSubjctId");
            return (Criteria) this;
        }

        public Criteria andOrderPayIsNull() {
            addCriterion("order_pay is null");
            return (Criteria) this;
        }

        public Criteria andOrderPayIsNotNull() {
            addCriterion("order_pay is not null");
            return (Criteria) this;
        }

        public Criteria andOrderPayEqualTo(Integer value) {
            addCriterion("order_pay =", value, "orderPay");
            return (Criteria) this;
        }

        public Criteria andOrderPayNotEqualTo(Integer value) {
            addCriterion("order_pay <>", value, "orderPay");
            return (Criteria) this;
        }

        public Criteria andOrderPayGreaterThan(Integer value) {
            addCriterion("order_pay >", value, "orderPay");
            return (Criteria) this;
        }

        public Criteria andOrderPayGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_pay >=", value, "orderPay");
            return (Criteria) this;
        }

        public Criteria andOrderPayLessThan(Integer value) {
            addCriterion("order_pay <", value, "orderPay");
            return (Criteria) this;
        }

        public Criteria andOrderPayLessThanOrEqualTo(Integer value) {
            addCriterion("order_pay <=", value, "orderPay");
            return (Criteria) this;
        }

        public Criteria andOrderPayIn(List<Integer> values) {
            addCriterion("order_pay in", values, "orderPay");
            return (Criteria) this;
        }

        public Criteria andOrderPayNotIn(List<Integer> values) {
            addCriterion("order_pay not in", values, "orderPay");
            return (Criteria) this;
        }

        public Criteria andOrderPayBetween(Integer value1, Integer value2) {
            addCriterion("order_pay between", value1, value2, "orderPay");
            return (Criteria) this;
        }

        public Criteria andOrderPayNotBetween(Integer value1, Integer value2) {
            addCriterion("order_pay not between", value1, value2, "orderPay");
            return (Criteria) this;
        }

        public Criteria andOrderDesIsNull() {
            addCriterion("order_des is null");
            return (Criteria) this;
        }

        public Criteria andOrderDesIsNotNull() {
            addCriterion("order_des is not null");
            return (Criteria) this;
        }

        public Criteria andOrderDesEqualTo(String value) {
            addCriterion("order_des =", value, "orderDes");
            return (Criteria) this;
        }

        public Criteria andOrderDesNotEqualTo(String value) {
            addCriterion("order_des <>", value, "orderDes");
            return (Criteria) this;
        }

        public Criteria andOrderDesGreaterThan(String value) {
            addCriterion("order_des >", value, "orderDes");
            return (Criteria) this;
        }

        public Criteria andOrderDesGreaterThanOrEqualTo(String value) {
            addCriterion("order_des >=", value, "orderDes");
            return (Criteria) this;
        }

        public Criteria andOrderDesLessThan(String value) {
            addCriterion("order_des <", value, "orderDes");
            return (Criteria) this;
        }

        public Criteria andOrderDesLessThanOrEqualTo(String value) {
            addCriterion("order_des <=", value, "orderDes");
            return (Criteria) this;
        }

        public Criteria andOrderDesLike(String value) {
            addCriterion("order_des like", value, "orderDes");
            return (Criteria) this;
        }

        public Criteria andOrderDesNotLike(String value) {
            addCriterion("order_des not like", value, "orderDes");
            return (Criteria) this;
        }

        public Criteria andOrderDesIn(List<String> values) {
            addCriterion("order_des in", values, "orderDes");
            return (Criteria) this;
        }

        public Criteria andOrderDesNotIn(List<String> values) {
            addCriterion("order_des not in", values, "orderDes");
            return (Criteria) this;
        }

        public Criteria andOrderDesBetween(String value1, String value2) {
            addCriterion("order_des between", value1, value2, "orderDes");
            return (Criteria) this;
        }

        public Criteria andOrderDesNotBetween(String value1, String value2) {
            addCriterion("order_des not between", value1, value2, "orderDes");
            return (Criteria) this;
        }

        public Criteria andOrderDateIsNull() {
            addCriterion("order_date is null");
            return (Criteria) this;
        }

        public Criteria andOrderDateIsNotNull() {
            addCriterion("order_date is not null");
            return (Criteria) this;
        }

        public Criteria andOrderDateEqualTo(Date value) {
            addCriterion("order_date =", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateNotEqualTo(Date value) {
            addCriterion("order_date <>", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateGreaterThan(Date value) {
            addCriterion("order_date >", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateGreaterThanOrEqualTo(Date value) {
            addCriterion("order_date >=", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateLessThan(Date value) {
            addCriterion("order_date <", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateLessThanOrEqualTo(Date value) {
            addCriterion("order_date <=", value, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateIn(List<Date> values) {
            addCriterion("order_date in", values, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateNotIn(List<Date> values) {
            addCriterion("order_date not in", values, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateBetween(Date value1, Date value2) {
            addCriterion("order_date between", value1, value2, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderDateNotBetween(Date value1, Date value2) {
            addCriterion("order_date not between", value1, value2, "orderDate");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIsNull() {
            addCriterion("order_code is null");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIsNotNull() {
            addCriterion("order_code is not null");
            return (Criteria) this;
        }

        public Criteria andOrderCodeEqualTo(String value) {
            addCriterion("order_code =", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotEqualTo(String value) {
            addCriterion("order_code <>", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeGreaterThan(String value) {
            addCriterion("order_code >", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeGreaterThanOrEqualTo(String value) {
            addCriterion("order_code >=", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLessThan(String value) {
            addCriterion("order_code <", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLessThanOrEqualTo(String value) {
            addCriterion("order_code <=", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLike(String value) {
            addCriterion("order_code like", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotLike(String value) {
            addCriterion("order_code not like", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIn(List<String> values) {
            addCriterion("order_code in", values, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotIn(List<String> values) {
            addCriterion("order_code not in", values, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeBetween(String value1, String value2) {
            addCriterion("order_code between", value1, value2, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotBetween(String value1, String value2) {
            addCriterion("order_code not between", value1, value2, "orderCode");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNull() {
            addCriterion("product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(String value) {
            addCriterion("product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(String value) {
            addCriterion("product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(String value) {
            addCriterion("product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(String value) {
            addCriterion("product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(String value) {
            addCriterion("product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(String value) {
            addCriterion("product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLike(String value) {
            addCriterion("product_id like", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotLike(String value) {
            addCriterion("product_id not like", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<String> values) {
            addCriterion("product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<String> values) {
            addCriterion("product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(String value1, String value2) {
            addCriterion("product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(String value1, String value2) {
            addCriterion("product_id not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andBillPriceIsNull() {
            addCriterion("bill_price is null");
            return (Criteria) this;
        }

        public Criteria andBillPriceIsNotNull() {
            addCriterion("bill_price is not null");
            return (Criteria) this;
        }

        public Criteria andBillPriceEqualTo(Double value) {
            addCriterion("bill_price =", value, "billPrice");
            return (Criteria) this;
        }

        public Criteria andBillPriceNotEqualTo(Double value) {
            addCriterion("bill_price <>", value, "billPrice");
            return (Criteria) this;
        }

        public Criteria andBillPriceGreaterThan(Double value) {
            addCriterion("bill_price >", value, "billPrice");
            return (Criteria) this;
        }

        public Criteria andBillPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("bill_price >=", value, "billPrice");
            return (Criteria) this;
        }

        public Criteria andBillPriceLessThan(Double value) {
            addCriterion("bill_price <", value, "billPrice");
            return (Criteria) this;
        }

        public Criteria andBillPriceLessThanOrEqualTo(Double value) {
            addCriterion("bill_price <=", value, "billPrice");
            return (Criteria) this;
        }

        public Criteria andBillPriceIn(List<Double> values) {
            addCriterion("bill_price in", values, "billPrice");
            return (Criteria) this;
        }

        public Criteria andBillPriceNotIn(List<Double> values) {
            addCriterion("bill_price not in", values, "billPrice");
            return (Criteria) this;
        }

        public Criteria andBillPriceBetween(Double value1, Double value2) {
            addCriterion("bill_price between", value1, value2, "billPrice");
            return (Criteria) this;
        }

        public Criteria andBillPriceNotBetween(Double value1, Double value2) {
            addCriterion("bill_price not between", value1, value2, "billPrice");
            return (Criteria) this;
        }

        public Criteria andBillNumIsNull() {
            addCriterion("bill_num is null");
            return (Criteria) this;
        }

        public Criteria andBillNumIsNotNull() {
            addCriterion("bill_num is not null");
            return (Criteria) this;
        }

        public Criteria andBillNumEqualTo(Double value) {
            addCriterion("bill_num =", value, "billNum");
            return (Criteria) this;
        }

        public Criteria andBillNumNotEqualTo(Double value) {
            addCriterion("bill_num <>", value, "billNum");
            return (Criteria) this;
        }

        public Criteria andBillNumGreaterThan(Double value) {
            addCriterion("bill_num >", value, "billNum");
            return (Criteria) this;
        }

        public Criteria andBillNumGreaterThanOrEqualTo(Double value) {
            addCriterion("bill_num >=", value, "billNum");
            return (Criteria) this;
        }

        public Criteria andBillNumLessThan(Double value) {
            addCriterion("bill_num <", value, "billNum");
            return (Criteria) this;
        }

        public Criteria andBillNumLessThanOrEqualTo(Double value) {
            addCriterion("bill_num <=", value, "billNum");
            return (Criteria) this;
        }

        public Criteria andBillNumIn(List<Double> values) {
            addCriterion("bill_num in", values, "billNum");
            return (Criteria) this;
        }

        public Criteria andBillNumNotIn(List<Double> values) {
            addCriterion("bill_num not in", values, "billNum");
            return (Criteria) this;
        }

        public Criteria andBillNumBetween(Double value1, Double value2) {
            addCriterion("bill_num between", value1, value2, "billNum");
            return (Criteria) this;
        }

        public Criteria andBillNumNotBetween(Double value1, Double value2) {
            addCriterion("bill_num not between", value1, value2, "billNum");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsCodeIsNull() {
            addCriterion("order_logistics_code is null");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsCodeIsNotNull() {
            addCriterion("order_logistics_code is not null");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsCodeEqualTo(String value) {
            addCriterion("order_logistics_code =", value, "orderLogisticsCode");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsCodeNotEqualTo(String value) {
            addCriterion("order_logistics_code <>", value, "orderLogisticsCode");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsCodeGreaterThan(String value) {
            addCriterion("order_logistics_code >", value, "orderLogisticsCode");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsCodeGreaterThanOrEqualTo(String value) {
            addCriterion("order_logistics_code >=", value, "orderLogisticsCode");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsCodeLessThan(String value) {
            addCriterion("order_logistics_code <", value, "orderLogisticsCode");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsCodeLessThanOrEqualTo(String value) {
            addCriterion("order_logistics_code <=", value, "orderLogisticsCode");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsCodeLike(String value) {
            addCriterion("order_logistics_code like", value, "orderLogisticsCode");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsCodeNotLike(String value) {
            addCriterion("order_logistics_code not like", value, "orderLogisticsCode");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsCodeIn(List<String> values) {
            addCriterion("order_logistics_code in", values, "orderLogisticsCode");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsCodeNotIn(List<String> values) {
            addCriterion("order_logistics_code not in", values, "orderLogisticsCode");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsCodeBetween(String value1, String value2) {
            addCriterion("order_logistics_code between", value1, value2, "orderLogisticsCode");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsCodeNotBetween(String value1, String value2) {
            addCriterion("order_logistics_code not between", value1, value2, "orderLogisticsCode");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsDateIsNull() {
            addCriterion("order_logistics_date is null");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsDateIsNotNull() {
            addCriterion("order_logistics_date is not null");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsDateEqualTo(Date value) {
            addCriterion("order_logistics_date =", value, "orderLogisticsDate");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsDateNotEqualTo(Date value) {
            addCriterion("order_logistics_date <>", value, "orderLogisticsDate");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsDateGreaterThan(Date value) {
            addCriterion("order_logistics_date >", value, "orderLogisticsDate");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsDateGreaterThanOrEqualTo(Date value) {
            addCriterion("order_logistics_date >=", value, "orderLogisticsDate");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsDateLessThan(Date value) {
            addCriterion("order_logistics_date <", value, "orderLogisticsDate");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsDateLessThanOrEqualTo(Date value) {
            addCriterion("order_logistics_date <=", value, "orderLogisticsDate");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsDateIn(List<Date> values) {
            addCriterion("order_logistics_date in", values, "orderLogisticsDate");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsDateNotIn(List<Date> values) {
            addCriterion("order_logistics_date not in", values, "orderLogisticsDate");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsDateBetween(Date value1, Date value2) {
            addCriterion("order_logistics_date between", value1, value2, "orderLogisticsDate");
            return (Criteria) this;
        }

        public Criteria andOrderLogisticsDateNotBetween(Date value1, Date value2) {
            addCriterion("order_logistics_date not between", value1, value2, "orderLogisticsDate");
            return (Criteria) this;
        }

        public Criteria andOrderLogiscompanyCodeIsNull() {
            addCriterion("order_logiscompany_code is null");
            return (Criteria) this;
        }

        public Criteria andOrderLogiscompanyCodeIsNotNull() {
            addCriterion("order_logiscompany_code is not null");
            return (Criteria) this;
        }

        public Criteria andOrderLogiscompanyCodeEqualTo(String value) {
            addCriterion("order_logiscompany_code =", value, "orderLogiscompanyCode");
            return (Criteria) this;
        }

        public Criteria andOrderLogiscompanyCodeNotEqualTo(String value) {
            addCriterion("order_logiscompany_code <>", value, "orderLogiscompanyCode");
            return (Criteria) this;
        }

        public Criteria andOrderLogiscompanyCodeGreaterThan(String value) {
            addCriterion("order_logiscompany_code >", value, "orderLogiscompanyCode");
            return (Criteria) this;
        }

        public Criteria andOrderLogiscompanyCodeGreaterThanOrEqualTo(String value) {
            addCriterion("order_logiscompany_code >=", value, "orderLogiscompanyCode");
            return (Criteria) this;
        }

        public Criteria andOrderLogiscompanyCodeLessThan(String value) {
            addCriterion("order_logiscompany_code <", value, "orderLogiscompanyCode");
            return (Criteria) this;
        }

        public Criteria andOrderLogiscompanyCodeLessThanOrEqualTo(String value) {
            addCriterion("order_logiscompany_code <=", value, "orderLogiscompanyCode");
            return (Criteria) this;
        }

        public Criteria andOrderLogiscompanyCodeLike(String value) {
            addCriterion("order_logiscompany_code like", value, "orderLogiscompanyCode");
            return (Criteria) this;
        }

        public Criteria andOrderLogiscompanyCodeNotLike(String value) {
            addCriterion("order_logiscompany_code not like", value, "orderLogiscompanyCode");
            return (Criteria) this;
        }

        public Criteria andOrderLogiscompanyCodeIn(List<String> values) {
            addCriterion("order_logiscompany_code in", values, "orderLogiscompanyCode");
            return (Criteria) this;
        }

        public Criteria andOrderLogiscompanyCodeNotIn(List<String> values) {
            addCriterion("order_logiscompany_code not in", values, "orderLogiscompanyCode");
            return (Criteria) this;
        }

        public Criteria andOrderLogiscompanyCodeBetween(String value1, String value2) {
            addCriterion("order_logiscompany_code between", value1, value2, "orderLogiscompanyCode");
            return (Criteria) this;
        }

        public Criteria andOrderLogiscompanyCodeNotBetween(String value1, String value2) {
            addCriterion("order_logiscompany_code not between", value1, value2, "orderLogiscompanyCode");
            return (Criteria) this;
        }

        public Criteria andOrderLogiscompanyPriceIsNull() {
            addCriterion("order_logiscompany_price is null");
            return (Criteria) this;
        }

        public Criteria andOrderLogiscompanyPriceIsNotNull() {
            addCriterion("order_logiscompany_price is not null");
            return (Criteria) this;
        }

        public Criteria andOrderLogiscompanyPriceEqualTo(Double value) {
            addCriterion("order_logiscompany_price =", value, "orderLogiscompanyPrice");
            return (Criteria) this;
        }

        public Criteria andOrderLogiscompanyPriceNotEqualTo(Double value) {
            addCriterion("order_logiscompany_price <>", value, "orderLogiscompanyPrice");
            return (Criteria) this;
        }

        public Criteria andOrderLogiscompanyPriceGreaterThan(Double value) {
            addCriterion("order_logiscompany_price >", value, "orderLogiscompanyPrice");
            return (Criteria) this;
        }

        public Criteria andOrderLogiscompanyPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("order_logiscompany_price >=", value, "orderLogiscompanyPrice");
            return (Criteria) this;
        }

        public Criteria andOrderLogiscompanyPriceLessThan(Double value) {
            addCriterion("order_logiscompany_price <", value, "orderLogiscompanyPrice");
            return (Criteria) this;
        }

        public Criteria andOrderLogiscompanyPriceLessThanOrEqualTo(Double value) {
            addCriterion("order_logiscompany_price <=", value, "orderLogiscompanyPrice");
            return (Criteria) this;
        }

        public Criteria andOrderLogiscompanyPriceIn(List<Double> values) {
            addCriterion("order_logiscompany_price in", values, "orderLogiscompanyPrice");
            return (Criteria) this;
        }

        public Criteria andOrderLogiscompanyPriceNotIn(List<Double> values) {
            addCriterion("order_logiscompany_price not in", values, "orderLogiscompanyPrice");
            return (Criteria) this;
        }

        public Criteria andOrderLogiscompanyPriceBetween(Double value1, Double value2) {
            addCriterion("order_logiscompany_price between", value1, value2, "orderLogiscompanyPrice");
            return (Criteria) this;
        }

        public Criteria andOrderLogiscompanyPriceNotBetween(Double value1, Double value2) {
            addCriterion("order_logiscompany_price not between", value1, value2, "orderLogiscompanyPrice");
            return (Criteria) this;
        }

        public Criteria andSellUserIdIsNull() {
            addCriterion("sell_user_id is null");
            return (Criteria) this;
        }

        public Criteria andSellUserIdIsNotNull() {
            addCriterion("sell_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andSellUserIdEqualTo(Integer value) {
            addCriterion("sell_user_id =", value, "sellUserId");
            return (Criteria) this;
        }

        public Criteria andSellUserIdNotEqualTo(Integer value) {
            addCriterion("sell_user_id <>", value, "sellUserId");
            return (Criteria) this;
        }

        public Criteria andSellUserIdGreaterThan(Integer value) {
            addCriterion("sell_user_id >", value, "sellUserId");
            return (Criteria) this;
        }

        public Criteria andSellUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sell_user_id >=", value, "sellUserId");
            return (Criteria) this;
        }

        public Criteria andSellUserIdLessThan(Integer value) {
            addCriterion("sell_user_id <", value, "sellUserId");
            return (Criteria) this;
        }

        public Criteria andSellUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("sell_user_id <=", value, "sellUserId");
            return (Criteria) this;
        }

        public Criteria andSellUserIdIn(List<Integer> values) {
            addCriterion("sell_user_id in", values, "sellUserId");
            return (Criteria) this;
        }

        public Criteria andSellUserIdNotIn(List<Integer> values) {
            addCriterion("sell_user_id not in", values, "sellUserId");
            return (Criteria) this;
        }

        public Criteria andSellUserIdBetween(Integer value1, Integer value2) {
            addCriterion("sell_user_id between", value1, value2, "sellUserId");
            return (Criteria) this;
        }

        public Criteria andSellUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sell_user_id not between", value1, value2, "sellUserId");
            return (Criteria) this;
        }

        public Criteria andBuyUserIdIsNull() {
            addCriterion("buy_user_id is null");
            return (Criteria) this;
        }

        public Criteria andBuyUserIdIsNotNull() {
            addCriterion("buy_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andBuyUserIdEqualTo(Integer value) {
            addCriterion("buy_user_id =", value, "buyUserId");
            return (Criteria) this;
        }

        public Criteria andBuyUserIdNotEqualTo(Integer value) {
            addCriterion("buy_user_id <>", value, "buyUserId");
            return (Criteria) this;
        }

        public Criteria andBuyUserIdGreaterThan(Integer value) {
            addCriterion("buy_user_id >", value, "buyUserId");
            return (Criteria) this;
        }

        public Criteria andBuyUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("buy_user_id >=", value, "buyUserId");
            return (Criteria) this;
        }

        public Criteria andBuyUserIdLessThan(Integer value) {
            addCriterion("buy_user_id <", value, "buyUserId");
            return (Criteria) this;
        }

        public Criteria andBuyUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("buy_user_id <=", value, "buyUserId");
            return (Criteria) this;
        }

        public Criteria andBuyUserIdIn(List<Integer> values) {
            addCriterion("buy_user_id in", values, "buyUserId");
            return (Criteria) this;
        }

        public Criteria andBuyUserIdNotIn(List<Integer> values) {
            addCriterion("buy_user_id not in", values, "buyUserId");
            return (Criteria) this;
        }

        public Criteria andBuyUserIdBetween(Integer value1, Integer value2) {
            addCriterion("buy_user_id between", value1, value2, "buyUserId");
            return (Criteria) this;
        }

        public Criteria andBuyUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("buy_user_id not between", value1, value2, "buyUserId");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNull() {
            addCriterion("pay_type is null");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNotNull() {
            addCriterion("pay_type is not null");
            return (Criteria) this;
        }

        public Criteria andPayTypeEqualTo(Integer value) {
            addCriterion("pay_type =", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotEqualTo(Integer value) {
            addCriterion("pay_type <>", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThan(Integer value) {
            addCriterion("pay_type >", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_type >=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThan(Integer value) {
            addCriterion("pay_type <", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThanOrEqualTo(Integer value) {
            addCriterion("pay_type <=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeIn(List<Integer> values) {
            addCriterion("pay_type in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotIn(List<Integer> values) {
            addCriterion("pay_type not in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeBetween(Integer value1, Integer value2) {
            addCriterion("pay_type between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_type not between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPayEndTimeIsNull() {
            addCriterion("pay_end_time is null");
            return (Criteria) this;
        }

        public Criteria andPayEndTimeIsNotNull() {
            addCriterion("pay_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andPayEndTimeEqualTo(Date value) {
            addCriterion("pay_end_time =", value, "payEndTime");
            return (Criteria) this;
        }

        public Criteria andPayEndTimeNotEqualTo(Date value) {
            addCriterion("pay_end_time <>", value, "payEndTime");
            return (Criteria) this;
        }

        public Criteria andPayEndTimeGreaterThan(Date value) {
            addCriterion("pay_end_time >", value, "payEndTime");
            return (Criteria) this;
        }

        public Criteria andPayEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("pay_end_time >=", value, "payEndTime");
            return (Criteria) this;
        }

        public Criteria andPayEndTimeLessThan(Date value) {
            addCriterion("pay_end_time <", value, "payEndTime");
            return (Criteria) this;
        }

        public Criteria andPayEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("pay_end_time <=", value, "payEndTime");
            return (Criteria) this;
        }

        public Criteria andPayEndTimeIn(List<Date> values) {
            addCriterion("pay_end_time in", values, "payEndTime");
            return (Criteria) this;
        }

        public Criteria andPayEndTimeNotIn(List<Date> values) {
            addCriterion("pay_end_time not in", values, "payEndTime");
            return (Criteria) this;
        }

        public Criteria andPayEndTimeBetween(Date value1, Date value2) {
            addCriterion("pay_end_time between", value1, value2, "payEndTime");
            return (Criteria) this;
        }

        public Criteria andPayEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("pay_end_time not between", value1, value2, "payEndTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andOrderNoteIsNull() {
            addCriterion("order_note is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoteIsNotNull() {
            addCriterion("order_note is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoteEqualTo(String value) {
            addCriterion("order_note =", value, "orderNote");
            return (Criteria) this;
        }

        public Criteria andOrderNoteNotEqualTo(String value) {
            addCriterion("order_note <>", value, "orderNote");
            return (Criteria) this;
        }

        public Criteria andOrderNoteGreaterThan(String value) {
            addCriterion("order_note >", value, "orderNote");
            return (Criteria) this;
        }

        public Criteria andOrderNoteGreaterThanOrEqualTo(String value) {
            addCriterion("order_note >=", value, "orderNote");
            return (Criteria) this;
        }

        public Criteria andOrderNoteLessThan(String value) {
            addCriterion("order_note <", value, "orderNote");
            return (Criteria) this;
        }

        public Criteria andOrderNoteLessThanOrEqualTo(String value) {
            addCriterion("order_note <=", value, "orderNote");
            return (Criteria) this;
        }

        public Criteria andOrderNoteLike(String value) {
            addCriterion("order_note like", value, "orderNote");
            return (Criteria) this;
        }

        public Criteria andOrderNoteNotLike(String value) {
            addCriterion("order_note not like", value, "orderNote");
            return (Criteria) this;
        }

        public Criteria andOrderNoteIn(List<String> values) {
            addCriterion("order_note in", values, "orderNote");
            return (Criteria) this;
        }

        public Criteria andOrderNoteNotIn(List<String> values) {
            addCriterion("order_note not in", values, "orderNote");
            return (Criteria) this;
        }

        public Criteria andOrderNoteBetween(String value1, String value2) {
            addCriterion("order_note between", value1, value2, "orderNote");
            return (Criteria) this;
        }

        public Criteria andOrderNoteNotBetween(String value1, String value2) {
            addCriterion("order_note not between", value1, value2, "orderNote");
            return (Criteria) this;
        }

        public Criteria andBillTitleIsNull() {
            addCriterion("bill_title is null");
            return (Criteria) this;
        }

        public Criteria andBillTitleIsNotNull() {
            addCriterion("bill_title is not null");
            return (Criteria) this;
        }

        public Criteria andBillTitleEqualTo(String value) {
            addCriterion("bill_title =", value, "billTitle");
            return (Criteria) this;
        }

        public Criteria andBillTitleNotEqualTo(String value) {
            addCriterion("bill_title <>", value, "billTitle");
            return (Criteria) this;
        }

        public Criteria andBillTitleGreaterThan(String value) {
            addCriterion("bill_title >", value, "billTitle");
            return (Criteria) this;
        }

        public Criteria andBillTitleGreaterThanOrEqualTo(String value) {
            addCriterion("bill_title >=", value, "billTitle");
            return (Criteria) this;
        }

        public Criteria andBillTitleLessThan(String value) {
            addCriterion("bill_title <", value, "billTitle");
            return (Criteria) this;
        }

        public Criteria andBillTitleLessThanOrEqualTo(String value) {
            addCriterion("bill_title <=", value, "billTitle");
            return (Criteria) this;
        }

        public Criteria andBillTitleLike(String value) {
            addCriterion("bill_title like", value, "billTitle");
            return (Criteria) this;
        }

        public Criteria andBillTitleNotLike(String value) {
            addCriterion("bill_title not like", value, "billTitle");
            return (Criteria) this;
        }

        public Criteria andBillTitleIn(List<String> values) {
            addCriterion("bill_title in", values, "billTitle");
            return (Criteria) this;
        }

        public Criteria andBillTitleNotIn(List<String> values) {
            addCriterion("bill_title not in", values, "billTitle");
            return (Criteria) this;
        }

        public Criteria andBillTitleBetween(String value1, String value2) {
            addCriterion("bill_title between", value1, value2, "billTitle");
            return (Criteria) this;
        }

        public Criteria andBillTitleNotBetween(String value1, String value2) {
            addCriterion("bill_title not between", value1, value2, "billTitle");
            return (Criteria) this;
        }

        public Criteria andBillDescIsNull() {
            addCriterion("bill_desc is null");
            return (Criteria) this;
        }

        public Criteria andBillDescIsNotNull() {
            addCriterion("bill_desc is not null");
            return (Criteria) this;
        }

        public Criteria andBillDescEqualTo(String value) {
            addCriterion("bill_desc =", value, "billDesc");
            return (Criteria) this;
        }

        public Criteria andBillDescNotEqualTo(String value) {
            addCriterion("bill_desc <>", value, "billDesc");
            return (Criteria) this;
        }

        public Criteria andBillDescGreaterThan(String value) {
            addCriterion("bill_desc >", value, "billDesc");
            return (Criteria) this;
        }

        public Criteria andBillDescGreaterThanOrEqualTo(String value) {
            addCriterion("bill_desc >=", value, "billDesc");
            return (Criteria) this;
        }

        public Criteria andBillDescLessThan(String value) {
            addCriterion("bill_desc <", value, "billDesc");
            return (Criteria) this;
        }

        public Criteria andBillDescLessThanOrEqualTo(String value) {
            addCriterion("bill_desc <=", value, "billDesc");
            return (Criteria) this;
        }

        public Criteria andBillDescLike(String value) {
            addCriterion("bill_desc like", value, "billDesc");
            return (Criteria) this;
        }

        public Criteria andBillDescNotLike(String value) {
            addCriterion("bill_desc not like", value, "billDesc");
            return (Criteria) this;
        }

        public Criteria andBillDescIn(List<String> values) {
            addCriterion("bill_desc in", values, "billDesc");
            return (Criteria) this;
        }

        public Criteria andBillDescNotIn(List<String> values) {
            addCriterion("bill_desc not in", values, "billDesc");
            return (Criteria) this;
        }

        public Criteria andBillDescBetween(String value1, String value2) {
            addCriterion("bill_desc between", value1, value2, "billDesc");
            return (Criteria) this;
        }

        public Criteria andBillDescNotBetween(String value1, String value2) {
            addCriterion("bill_desc not between", value1, value2, "billDesc");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNull() {
            addCriterion("balance is null");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNotNull() {
            addCriterion("balance is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceEqualTo(Double value) {
            addCriterion("balance =", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotEqualTo(Double value) {
            addCriterion("balance <>", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThan(Double value) {
            addCriterion("balance >", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThanOrEqualTo(Double value) {
            addCriterion("balance >=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThan(Double value) {
            addCriterion("balance <", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThanOrEqualTo(Double value) {
            addCriterion("balance <=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceIn(List<Double> values) {
            addCriterion("balance in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotIn(List<Double> values) {
            addCriterion("balance not in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceBetween(Double value1, Double value2) {
            addCriterion("balance between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotBetween(Double value1, Double value2) {
            addCriterion("balance not between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andCreditIsNull() {
            addCriterion("credit is null");
            return (Criteria) this;
        }

        public Criteria andCreditIsNotNull() {
            addCriterion("credit is not null");
            return (Criteria) this;
        }

        public Criteria andCreditEqualTo(Double value) {
            addCriterion("credit =", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotEqualTo(Double value) {
            addCriterion("credit <>", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditGreaterThan(Double value) {
            addCriterion("credit >", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditGreaterThanOrEqualTo(Double value) {
            addCriterion("credit >=", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditLessThan(Double value) {
            addCriterion("credit <", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditLessThanOrEqualTo(Double value) {
            addCriterion("credit <=", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditIn(List<Double> values) {
            addCriterion("credit in", values, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotIn(List<Double> values) {
            addCriterion("credit not in", values, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditBetween(Double value1, Double value2) {
            addCriterion("credit between", value1, value2, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotBetween(Double value1, Double value2) {
            addCriterion("credit not between", value1, value2, "credit");
            return (Criteria) this;
        }

        public Criteria andRatioIsNull() {
            addCriterion("ratio is null");
            return (Criteria) this;
        }

        public Criteria andRatioIsNotNull() {
            addCriterion("ratio is not null");
            return (Criteria) this;
        }

        public Criteria andRatioEqualTo(Double value) {
            addCriterion("ratio =", value, "ratio");
            return (Criteria) this;
        }

        public Criteria andRatioNotEqualTo(Double value) {
            addCriterion("ratio <>", value, "ratio");
            return (Criteria) this;
        }

        public Criteria andRatioGreaterThan(Double value) {
            addCriterion("ratio >", value, "ratio");
            return (Criteria) this;
        }

        public Criteria andRatioGreaterThanOrEqualTo(Double value) {
            addCriterion("ratio >=", value, "ratio");
            return (Criteria) this;
        }

        public Criteria andRatioLessThan(Double value) {
            addCriterion("ratio <", value, "ratio");
            return (Criteria) this;
        }

        public Criteria andRatioLessThanOrEqualTo(Double value) {
            addCriterion("ratio <=", value, "ratio");
            return (Criteria) this;
        }

        public Criteria andRatioIn(List<Double> values) {
            addCriterion("ratio in", values, "ratio");
            return (Criteria) this;
        }

        public Criteria andRatioNotIn(List<Double> values) {
            addCriterion("ratio not in", values, "ratio");
            return (Criteria) this;
        }

        public Criteria andRatioBetween(Double value1, Double value2) {
            addCriterion("ratio between", value1, value2, "ratio");
            return (Criteria) this;
        }

        public Criteria andRatioNotBetween(Double value1, Double value2) {
            addCriterion("ratio not between", value1, value2, "ratio");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("is_delete is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Integer value) {
            addCriterion("is_delete =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Integer value) {
            addCriterion("is_delete <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Integer value) {
            addCriterion("is_delete >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_delete >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Integer value) {
            addCriterion("is_delete <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Integer value) {
            addCriterion("is_delete <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Integer> values) {
            addCriterion("is_delete in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Integer> values) {
            addCriterion("is_delete not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Integer value1, Integer value2) {
            addCriterion("is_delete between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Integer value1, Integer value2) {
            addCriterion("is_delete not between", value1, value2, "isDelete");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}