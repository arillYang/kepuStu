package com.kepu.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StDeviceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StDeviceExample() {
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

        public Criteria andUidIsNull() {
            addCriterion("UID is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("UID is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Integer value) {
            addCriterion("UID =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Integer value) {
            addCriterion("UID <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Integer value) {
            addCriterion("UID >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("UID >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Integer value) {
            addCriterion("UID <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Integer value) {
            addCriterion("UID <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Integer> values) {
            addCriterion("UID in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Integer> values) {
            addCriterion("UID not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Integer value1, Integer value2) {
            addCriterion("UID between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Integer value1, Integer value2) {
            addCriterion("UID not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andAppldentifierIsNull() {
            addCriterion("appldentifier is null");
            return (Criteria) this;
        }

        public Criteria andAppldentifierIsNotNull() {
            addCriterion("appldentifier is not null");
            return (Criteria) this;
        }

        public Criteria andAppldentifierEqualTo(String value) {
            addCriterion("appldentifier =", value, "appldentifier");
            return (Criteria) this;
        }

        public Criteria andAppldentifierNotEqualTo(String value) {
            addCriterion("appldentifier <>", value, "appldentifier");
            return (Criteria) this;
        }

        public Criteria andAppldentifierGreaterThan(String value) {
            addCriterion("appldentifier >", value, "appldentifier");
            return (Criteria) this;
        }

        public Criteria andAppldentifierGreaterThanOrEqualTo(String value) {
            addCriterion("appldentifier >=", value, "appldentifier");
            return (Criteria) this;
        }

        public Criteria andAppldentifierLessThan(String value) {
            addCriterion("appldentifier <", value, "appldentifier");
            return (Criteria) this;
        }

        public Criteria andAppldentifierLessThanOrEqualTo(String value) {
            addCriterion("appldentifier <=", value, "appldentifier");
            return (Criteria) this;
        }

        public Criteria andAppldentifierLike(String value) {
            addCriterion("appldentifier like", value, "appldentifier");
            return (Criteria) this;
        }

        public Criteria andAppldentifierNotLike(String value) {
            addCriterion("appldentifier not like", value, "appldentifier");
            return (Criteria) this;
        }

        public Criteria andAppldentifierIn(List<String> values) {
            addCriterion("appldentifier in", values, "appldentifier");
            return (Criteria) this;
        }

        public Criteria andAppldentifierNotIn(List<String> values) {
            addCriterion("appldentifier not in", values, "appldentifier");
            return (Criteria) this;
        }

        public Criteria andAppldentifierBetween(String value1, String value2) {
            addCriterion("appldentifier between", value1, value2, "appldentifier");
            return (Criteria) this;
        }

        public Criteria andAppldentifierNotBetween(String value1, String value2) {
            addCriterion("appldentifier not between", value1, value2, "appldentifier");
            return (Criteria) this;
        }

        public Criteria andAppversionIsNull() {
            addCriterion("appVersion is null");
            return (Criteria) this;
        }

        public Criteria andAppversionIsNotNull() {
            addCriterion("appVersion is not null");
            return (Criteria) this;
        }

        public Criteria andAppversionEqualTo(String value) {
            addCriterion("appVersion =", value, "appversion");
            return (Criteria) this;
        }

        public Criteria andAppversionNotEqualTo(String value) {
            addCriterion("appVersion <>", value, "appversion");
            return (Criteria) this;
        }

        public Criteria andAppversionGreaterThan(String value) {
            addCriterion("appVersion >", value, "appversion");
            return (Criteria) this;
        }

        public Criteria andAppversionGreaterThanOrEqualTo(String value) {
            addCriterion("appVersion >=", value, "appversion");
            return (Criteria) this;
        }

        public Criteria andAppversionLessThan(String value) {
            addCriterion("appVersion <", value, "appversion");
            return (Criteria) this;
        }

        public Criteria andAppversionLessThanOrEqualTo(String value) {
            addCriterion("appVersion <=", value, "appversion");
            return (Criteria) this;
        }

        public Criteria andAppversionLike(String value) {
            addCriterion("appVersion like", value, "appversion");
            return (Criteria) this;
        }

        public Criteria andAppversionNotLike(String value) {
            addCriterion("appVersion not like", value, "appversion");
            return (Criteria) this;
        }

        public Criteria andAppversionIn(List<String> values) {
            addCriterion("appVersion in", values, "appversion");
            return (Criteria) this;
        }

        public Criteria andAppversionNotIn(List<String> values) {
            addCriterion("appVersion not in", values, "appversion");
            return (Criteria) this;
        }

        public Criteria andAppversionBetween(String value1, String value2) {
            addCriterion("appVersion between", value1, value2, "appversion");
            return (Criteria) this;
        }

        public Criteria andAppversionNotBetween(String value1, String value2) {
            addCriterion("appVersion not between", value1, value2, "appversion");
            return (Criteria) this;
        }

        public Criteria andApplicationIsNull() {
            addCriterion("application is null");
            return (Criteria) this;
        }

        public Criteria andApplicationIsNotNull() {
            addCriterion("application is not null");
            return (Criteria) this;
        }

        public Criteria andApplicationEqualTo(String value) {
            addCriterion("application =", value, "application");
            return (Criteria) this;
        }

        public Criteria andApplicationNotEqualTo(String value) {
            addCriterion("application <>", value, "application");
            return (Criteria) this;
        }

        public Criteria andApplicationGreaterThan(String value) {
            addCriterion("application >", value, "application");
            return (Criteria) this;
        }

        public Criteria andApplicationGreaterThanOrEqualTo(String value) {
            addCriterion("application >=", value, "application");
            return (Criteria) this;
        }

        public Criteria andApplicationLessThan(String value) {
            addCriterion("application <", value, "application");
            return (Criteria) this;
        }

        public Criteria andApplicationLessThanOrEqualTo(String value) {
            addCriterion("application <=", value, "application");
            return (Criteria) this;
        }

        public Criteria andApplicationLike(String value) {
            addCriterion("application like", value, "application");
            return (Criteria) this;
        }

        public Criteria andApplicationNotLike(String value) {
            addCriterion("application not like", value, "application");
            return (Criteria) this;
        }

        public Criteria andApplicationIn(List<String> values) {
            addCriterion("application in", values, "application");
            return (Criteria) this;
        }

        public Criteria andApplicationNotIn(List<String> values) {
            addCriterion("application not in", values, "application");
            return (Criteria) this;
        }

        public Criteria andApplicationBetween(String value1, String value2) {
            addCriterion("application between", value1, value2, "application");
            return (Criteria) this;
        }

        public Criteria andApplicationNotBetween(String value1, String value2) {
            addCriterion("application not between", value1, value2, "application");
            return (Criteria) this;
        }

        public Criteria andHardwareIsNull() {
            addCriterion("hardware is null");
            return (Criteria) this;
        }

        public Criteria andHardwareIsNotNull() {
            addCriterion("hardware is not null");
            return (Criteria) this;
        }

        public Criteria andHardwareEqualTo(String value) {
            addCriterion("hardware =", value, "hardware");
            return (Criteria) this;
        }

        public Criteria andHardwareNotEqualTo(String value) {
            addCriterion("hardware <>", value, "hardware");
            return (Criteria) this;
        }

        public Criteria andHardwareGreaterThan(String value) {
            addCriterion("hardware >", value, "hardware");
            return (Criteria) this;
        }

        public Criteria andHardwareGreaterThanOrEqualTo(String value) {
            addCriterion("hardware >=", value, "hardware");
            return (Criteria) this;
        }

        public Criteria andHardwareLessThan(String value) {
            addCriterion("hardware <", value, "hardware");
            return (Criteria) this;
        }

        public Criteria andHardwareLessThanOrEqualTo(String value) {
            addCriterion("hardware <=", value, "hardware");
            return (Criteria) this;
        }

        public Criteria andHardwareLike(String value) {
            addCriterion("hardware like", value, "hardware");
            return (Criteria) this;
        }

        public Criteria andHardwareNotLike(String value) {
            addCriterion("hardware not like", value, "hardware");
            return (Criteria) this;
        }

        public Criteria andHardwareIn(List<String> values) {
            addCriterion("hardware in", values, "hardware");
            return (Criteria) this;
        }

        public Criteria andHardwareNotIn(List<String> values) {
            addCriterion("hardware not in", values, "hardware");
            return (Criteria) this;
        }

        public Criteria andHardwareBetween(String value1, String value2) {
            addCriterion("hardware between", value1, value2, "hardware");
            return (Criteria) this;
        }

        public Criteria andHardwareNotBetween(String value1, String value2) {
            addCriterion("hardware not between", value1, value2, "hardware");
            return (Criteria) this;
        }

        public Criteria andPagenameIsNull() {
            addCriterion("pageName is null");
            return (Criteria) this;
        }

        public Criteria andPagenameIsNotNull() {
            addCriterion("pageName is not null");
            return (Criteria) this;
        }

        public Criteria andPagenameEqualTo(String value) {
            addCriterion("pageName =", value, "pagename");
            return (Criteria) this;
        }

        public Criteria andPagenameNotEqualTo(String value) {
            addCriterion("pageName <>", value, "pagename");
            return (Criteria) this;
        }

        public Criteria andPagenameGreaterThan(String value) {
            addCriterion("pageName >", value, "pagename");
            return (Criteria) this;
        }

        public Criteria andPagenameGreaterThanOrEqualTo(String value) {
            addCriterion("pageName >=", value, "pagename");
            return (Criteria) this;
        }

        public Criteria andPagenameLessThan(String value) {
            addCriterion("pageName <", value, "pagename");
            return (Criteria) this;
        }

        public Criteria andPagenameLessThanOrEqualTo(String value) {
            addCriterion("pageName <=", value, "pagename");
            return (Criteria) this;
        }

        public Criteria andPagenameLike(String value) {
            addCriterion("pageName like", value, "pagename");
            return (Criteria) this;
        }

        public Criteria andPagenameNotLike(String value) {
            addCriterion("pageName not like", value, "pagename");
            return (Criteria) this;
        }

        public Criteria andPagenameIn(List<String> values) {
            addCriterion("pageName in", values, "pagename");
            return (Criteria) this;
        }

        public Criteria andPagenameNotIn(List<String> values) {
            addCriterion("pageName not in", values, "pagename");
            return (Criteria) this;
        }

        public Criteria andPagenameBetween(String value1, String value2) {
            addCriterion("pageName between", value1, value2, "pagename");
            return (Criteria) this;
        }

        public Criteria andPagenameNotBetween(String value1, String value2) {
            addCriterion("pageName not between", value1, value2, "pagename");
            return (Criteria) this;
        }

        public Criteria andSystemversionIsNull() {
            addCriterion("systemVersion is null");
            return (Criteria) this;
        }

        public Criteria andSystemversionIsNotNull() {
            addCriterion("systemVersion is not null");
            return (Criteria) this;
        }

        public Criteria andSystemversionEqualTo(String value) {
            addCriterion("systemVersion =", value, "systemversion");
            return (Criteria) this;
        }

        public Criteria andSystemversionNotEqualTo(String value) {
            addCriterion("systemVersion <>", value, "systemversion");
            return (Criteria) this;
        }

        public Criteria andSystemversionGreaterThan(String value) {
            addCriterion("systemVersion >", value, "systemversion");
            return (Criteria) this;
        }

        public Criteria andSystemversionGreaterThanOrEqualTo(String value) {
            addCriterion("systemVersion >=", value, "systemversion");
            return (Criteria) this;
        }

        public Criteria andSystemversionLessThan(String value) {
            addCriterion("systemVersion <", value, "systemversion");
            return (Criteria) this;
        }

        public Criteria andSystemversionLessThanOrEqualTo(String value) {
            addCriterion("systemVersion <=", value, "systemversion");
            return (Criteria) this;
        }

        public Criteria andSystemversionLike(String value) {
            addCriterion("systemVersion like", value, "systemversion");
            return (Criteria) this;
        }

        public Criteria andSystemversionNotLike(String value) {
            addCriterion("systemVersion not like", value, "systemversion");
            return (Criteria) this;
        }

        public Criteria andSystemversionIn(List<String> values) {
            addCriterion("systemVersion in", values, "systemversion");
            return (Criteria) this;
        }

        public Criteria andSystemversionNotIn(List<String> values) {
            addCriterion("systemVersion not in", values, "systemversion");
            return (Criteria) this;
        }

        public Criteria andSystemversionBetween(String value1, String value2) {
            addCriterion("systemVersion between", value1, value2, "systemversion");
            return (Criteria) this;
        }

        public Criteria andSystemversionNotBetween(String value1, String value2) {
            addCriterion("systemVersion not between", value1, value2, "systemversion");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("userId is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userId is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(Integer value) {
            addCriterion("userId =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(Integer value) {
            addCriterion("userId <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(Integer value) {
            addCriterion("userId >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("userId >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(Integer value) {
            addCriterion("userId <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(Integer value) {
            addCriterion("userId <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<Integer> values) {
            addCriterion("userId in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<Integer> values) {
            addCriterion("userId not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(Integer value1, Integer value2) {
            addCriterion("userId between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(Integer value1, Integer value2) {
            addCriterion("userId not between", value1, value2, "userid");
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