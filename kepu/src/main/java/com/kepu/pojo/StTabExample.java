package com.kepu.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StTabExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StTabExample() {
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

        public Criteria andTabidIsNull() {
            addCriterion("tabId is null");
            return (Criteria) this;
        }

        public Criteria andTabidIsNotNull() {
            addCriterion("tabId is not null");
            return (Criteria) this;
        }

        public Criteria andTabidEqualTo(Integer value) {
            addCriterion("tabId =", value, "tabid");
            return (Criteria) this;
        }

        public Criteria andTabidNotEqualTo(Integer value) {
            addCriterion("tabId <>", value, "tabid");
            return (Criteria) this;
        }

        public Criteria andTabidGreaterThan(Integer value) {
            addCriterion("tabId >", value, "tabid");
            return (Criteria) this;
        }

        public Criteria andTabidGreaterThanOrEqualTo(Integer value) {
            addCriterion("tabId >=", value, "tabid");
            return (Criteria) this;
        }

        public Criteria andTabidLessThan(Integer value) {
            addCriterion("tabId <", value, "tabid");
            return (Criteria) this;
        }

        public Criteria andTabidLessThanOrEqualTo(Integer value) {
            addCriterion("tabId <=", value, "tabid");
            return (Criteria) this;
        }

        public Criteria andTabidIn(List<Integer> values) {
            addCriterion("tabId in", values, "tabid");
            return (Criteria) this;
        }

        public Criteria andTabidNotIn(List<Integer> values) {
            addCriterion("tabId not in", values, "tabid");
            return (Criteria) this;
        }

        public Criteria andTabidBetween(Integer value1, Integer value2) {
            addCriterion("tabId between", value1, value2, "tabid");
            return (Criteria) this;
        }

        public Criteria andTabidNotBetween(Integer value1, Integer value2) {
            addCriterion("tabId not between", value1, value2, "tabid");
            return (Criteria) this;
        }

        public Criteria andTabnameIsNull() {
            addCriterion("tabName is null");
            return (Criteria) this;
        }

        public Criteria andTabnameIsNotNull() {
            addCriterion("tabName is not null");
            return (Criteria) this;
        }

        public Criteria andTabnameEqualTo(String value) {
            addCriterion("tabName =", value, "tabname");
            return (Criteria) this;
        }

        public Criteria andTabnameNotEqualTo(String value) {
            addCriterion("tabName <>", value, "tabname");
            return (Criteria) this;
        }

        public Criteria andTabnameGreaterThan(String value) {
            addCriterion("tabName >", value, "tabname");
            return (Criteria) this;
        }

        public Criteria andTabnameGreaterThanOrEqualTo(String value) {
            addCriterion("tabName >=", value, "tabname");
            return (Criteria) this;
        }

        public Criteria andTabnameLessThan(String value) {
            addCriterion("tabName <", value, "tabname");
            return (Criteria) this;
        }

        public Criteria andTabnameLessThanOrEqualTo(String value) {
            addCriterion("tabName <=", value, "tabname");
            return (Criteria) this;
        }

        public Criteria andTabnameLike(String value) {
            addCriterion("tabName like", value, "tabname");
            return (Criteria) this;
        }

        public Criteria andTabnameNotLike(String value) {
            addCriterion("tabName not like", value, "tabname");
            return (Criteria) this;
        }

        public Criteria andTabnameIn(List<String> values) {
            addCriterion("tabName in", values, "tabname");
            return (Criteria) this;
        }

        public Criteria andTabnameNotIn(List<String> values) {
            addCriterion("tabName not in", values, "tabname");
            return (Criteria) this;
        }

        public Criteria andTabnameBetween(String value1, String value2) {
            addCriterion("tabName between", value1, value2, "tabname");
            return (Criteria) this;
        }

        public Criteria andTabnameNotBetween(String value1, String value2) {
            addCriterion("tabName not between", value1, value2, "tabname");
            return (Criteria) this;
        }

        public Criteria andTabpicIsNull() {
            addCriterion("tabPic is null");
            return (Criteria) this;
        }

        public Criteria andTabpicIsNotNull() {
            addCriterion("tabPic is not null");
            return (Criteria) this;
        }

        public Criteria andTabpicEqualTo(String value) {
            addCriterion("tabPic =", value, "tabpic");
            return (Criteria) this;
        }

        public Criteria andTabpicNotEqualTo(String value) {
            addCriterion("tabPic <>", value, "tabpic");
            return (Criteria) this;
        }

        public Criteria andTabpicGreaterThan(String value) {
            addCriterion("tabPic >", value, "tabpic");
            return (Criteria) this;
        }

        public Criteria andTabpicGreaterThanOrEqualTo(String value) {
            addCriterion("tabPic >=", value, "tabpic");
            return (Criteria) this;
        }

        public Criteria andTabpicLessThan(String value) {
            addCriterion("tabPic <", value, "tabpic");
            return (Criteria) this;
        }

        public Criteria andTabpicLessThanOrEqualTo(String value) {
            addCriterion("tabPic <=", value, "tabpic");
            return (Criteria) this;
        }

        public Criteria andTabpicLike(String value) {
            addCriterion("tabPic like", value, "tabpic");
            return (Criteria) this;
        }

        public Criteria andTabpicNotLike(String value) {
            addCriterion("tabPic not like", value, "tabpic");
            return (Criteria) this;
        }

        public Criteria andTabpicIn(List<String> values) {
            addCriterion("tabPic in", values, "tabpic");
            return (Criteria) this;
        }

        public Criteria andTabpicNotIn(List<String> values) {
            addCriterion("tabPic not in", values, "tabpic");
            return (Criteria) this;
        }

        public Criteria andTabpicBetween(String value1, String value2) {
            addCriterion("tabPic between", value1, value2, "tabpic");
            return (Criteria) this;
        }

        public Criteria andTabpicNotBetween(String value1, String value2) {
            addCriterion("tabPic not between", value1, value2, "tabpic");
            return (Criteria) this;
        }

        public Criteria andTabtypeIsNull() {
            addCriterion("tabType is null");
            return (Criteria) this;
        }

        public Criteria andTabtypeIsNotNull() {
            addCriterion("tabType is not null");
            return (Criteria) this;
        }

        public Criteria andTabtypeEqualTo(Integer value) {
            addCriterion("tabType =", value, "tabtype");
            return (Criteria) this;
        }

        public Criteria andTabtypeNotEqualTo(Integer value) {
            addCriterion("tabType <>", value, "tabtype");
            return (Criteria) this;
        }

        public Criteria andTabtypeGreaterThan(Integer value) {
            addCriterion("tabType >", value, "tabtype");
            return (Criteria) this;
        }

        public Criteria andTabtypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("tabType >=", value, "tabtype");
            return (Criteria) this;
        }

        public Criteria andTabtypeLessThan(Integer value) {
            addCriterion("tabType <", value, "tabtype");
            return (Criteria) this;
        }

        public Criteria andTabtypeLessThanOrEqualTo(Integer value) {
            addCriterion("tabType <=", value, "tabtype");
            return (Criteria) this;
        }

        public Criteria andTabtypeIn(List<Integer> values) {
            addCriterion("tabType in", values, "tabtype");
            return (Criteria) this;
        }

        public Criteria andTabtypeNotIn(List<Integer> values) {
            addCriterion("tabType not in", values, "tabtype");
            return (Criteria) this;
        }

        public Criteria andTabtypeBetween(Integer value1, Integer value2) {
            addCriterion("tabType between", value1, value2, "tabtype");
            return (Criteria) this;
        }

        public Criteria andTabtypeNotBetween(Integer value1, Integer value2) {
            addCriterion("tabType not between", value1, value2, "tabtype");
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

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andTaburlIsNull() {
            addCriterion("tabUrl is null");
            return (Criteria) this;
        }

        public Criteria andTaburlIsNotNull() {
            addCriterion("tabUrl is not null");
            return (Criteria) this;
        }

        public Criteria andTaburlEqualTo(String value) {
            addCriterion("tabUrl =", value, "taburl");
            return (Criteria) this;
        }

        public Criteria andTaburlNotEqualTo(String value) {
            addCriterion("tabUrl <>", value, "taburl");
            return (Criteria) this;
        }

        public Criteria andTaburlGreaterThan(String value) {
            addCriterion("tabUrl >", value, "taburl");
            return (Criteria) this;
        }

        public Criteria andTaburlGreaterThanOrEqualTo(String value) {
            addCriterion("tabUrl >=", value, "taburl");
            return (Criteria) this;
        }

        public Criteria andTaburlLessThan(String value) {
            addCriterion("tabUrl <", value, "taburl");
            return (Criteria) this;
        }

        public Criteria andTaburlLessThanOrEqualTo(String value) {
            addCriterion("tabUrl <=", value, "taburl");
            return (Criteria) this;
        }

        public Criteria andTaburlLike(String value) {
            addCriterion("tabUrl like", value, "taburl");
            return (Criteria) this;
        }

        public Criteria andTaburlNotLike(String value) {
            addCriterion("tabUrl not like", value, "taburl");
            return (Criteria) this;
        }

        public Criteria andTaburlIn(List<String> values) {
            addCriterion("tabUrl in", values, "taburl");
            return (Criteria) this;
        }

        public Criteria andTaburlNotIn(List<String> values) {
            addCriterion("tabUrl not in", values, "taburl");
            return (Criteria) this;
        }

        public Criteria andTaburlBetween(String value1, String value2) {
            addCriterion("tabUrl between", value1, value2, "taburl");
            return (Criteria) this;
        }

        public Criteria andTaburlNotBetween(String value1, String value2) {
            addCriterion("tabUrl not between", value1, value2, "taburl");
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