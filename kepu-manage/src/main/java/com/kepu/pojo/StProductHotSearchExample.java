package com.kepu.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StProductHotSearchExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StProductHotSearchExample() {
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

        public Criteria andWordIsNull() {
            addCriterion("word is null");
            return (Criteria) this;
        }

        public Criteria andWordIsNotNull() {
            addCriterion("word is not null");
            return (Criteria) this;
        }

        public Criteria andWordEqualTo(String value) {
            addCriterion("word =", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordNotEqualTo(String value) {
            addCriterion("word <>", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordGreaterThan(String value) {
            addCriterion("word >", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordGreaterThanOrEqualTo(String value) {
            addCriterion("word >=", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordLessThan(String value) {
            addCriterion("word <", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordLessThanOrEqualTo(String value) {
            addCriterion("word <=", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordLike(String value) {
            addCriterion("word like", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordNotLike(String value) {
            addCriterion("word not like", value, "word");
            return (Criteria) this;
        }

        public Criteria andWordIn(List<String> values) {
            addCriterion("word in", values, "word");
            return (Criteria) this;
        }

        public Criteria andWordNotIn(List<String> values) {
            addCriterion("word not in", values, "word");
            return (Criteria) this;
        }

        public Criteria andWordBetween(String value1, String value2) {
            addCriterion("word between", value1, value2, "word");
            return (Criteria) this;
        }

        public Criteria andWordNotBetween(String value1, String value2) {
            addCriterion("word not between", value1, value2, "word");
            return (Criteria) this;
        }

        public Criteria andSearchnumIsNull() {
            addCriterion("searchNum is null");
            return (Criteria) this;
        }

        public Criteria andSearchnumIsNotNull() {
            addCriterion("searchNum is not null");
            return (Criteria) this;
        }

        public Criteria andSearchnumEqualTo(Integer value) {
            addCriterion("searchNum =", value, "searchnum");
            return (Criteria) this;
        }

        public Criteria andSearchnumNotEqualTo(Integer value) {
            addCriterion("searchNum <>", value, "searchnum");
            return (Criteria) this;
        }

        public Criteria andSearchnumGreaterThan(Integer value) {
            addCriterion("searchNum >", value, "searchnum");
            return (Criteria) this;
        }

        public Criteria andSearchnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("searchNum >=", value, "searchnum");
            return (Criteria) this;
        }

        public Criteria andSearchnumLessThan(Integer value) {
            addCriterion("searchNum <", value, "searchnum");
            return (Criteria) this;
        }

        public Criteria andSearchnumLessThanOrEqualTo(Integer value) {
            addCriterion("searchNum <=", value, "searchnum");
            return (Criteria) this;
        }

        public Criteria andSearchnumIn(List<Integer> values) {
            addCriterion("searchNum in", values, "searchnum");
            return (Criteria) this;
        }

        public Criteria andSearchnumNotIn(List<Integer> values) {
            addCriterion("searchNum not in", values, "searchnum");
            return (Criteria) this;
        }

        public Criteria andSearchnumBetween(Integer value1, Integer value2) {
            addCriterion("searchNum between", value1, value2, "searchnum");
            return (Criteria) this;
        }

        public Criteria andSearchnumNotBetween(Integer value1, Integer value2) {
            addCriterion("searchNum not between", value1, value2, "searchnum");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createTime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createTime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createTime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createTime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createTime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createTime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createTime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createTime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createTime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createTime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updateTime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updateTime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updateTime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updateTime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updateTime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updateTime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updateTime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updateTime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updateTime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updateTime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updateTime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updateTime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andAdminsetIsNull() {
            addCriterion("adminSet is null");
            return (Criteria) this;
        }

        public Criteria andAdminsetIsNotNull() {
            addCriterion("adminSet is not null");
            return (Criteria) this;
        }

        public Criteria andAdminsetEqualTo(Integer value) {
            addCriterion("adminSet =", value, "adminset");
            return (Criteria) this;
        }

        public Criteria andAdminsetNotEqualTo(Integer value) {
            addCriterion("adminSet <>", value, "adminset");
            return (Criteria) this;
        }

        public Criteria andAdminsetGreaterThan(Integer value) {
            addCriterion("adminSet >", value, "adminset");
            return (Criteria) this;
        }

        public Criteria andAdminsetGreaterThanOrEqualTo(Integer value) {
            addCriterion("adminSet >=", value, "adminset");
            return (Criteria) this;
        }

        public Criteria andAdminsetLessThan(Integer value) {
            addCriterion("adminSet <", value, "adminset");
            return (Criteria) this;
        }

        public Criteria andAdminsetLessThanOrEqualTo(Integer value) {
            addCriterion("adminSet <=", value, "adminset");
            return (Criteria) this;
        }

        public Criteria andAdminsetIn(List<Integer> values) {
            addCriterion("adminSet in", values, "adminset");
            return (Criteria) this;
        }

        public Criteria andAdminsetNotIn(List<Integer> values) {
            addCriterion("adminSet not in", values, "adminset");
            return (Criteria) this;
        }

        public Criteria andAdminsetBetween(Integer value1, Integer value2) {
            addCriterion("adminSet between", value1, value2, "adminset");
            return (Criteria) this;
        }

        public Criteria andAdminsetNotBetween(Integer value1, Integer value2) {
            addCriterion("adminSet not between", value1, value2, "adminset");
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