package com.kepu.pojo;

import java.util.ArrayList;
import java.util.List;

public class StCommunityTeachDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StCommunityTeachDetailExample() {
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
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Integer value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Integer value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Integer value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Integer value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Integer value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Integer> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Integer> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Integer value1, Integer value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Integer value1, Integer value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andTeachidIsNull() {
            addCriterion("teachId is null");
            return (Criteria) this;
        }

        public Criteria andTeachidIsNotNull() {
            addCriterion("teachId is not null");
            return (Criteria) this;
        }

        public Criteria andTeachidEqualTo(Integer value) {
            addCriterion("teachId =", value, "teachid");
            return (Criteria) this;
        }

        public Criteria andTeachidNotEqualTo(Integer value) {
            addCriterion("teachId <>", value, "teachid");
            return (Criteria) this;
        }

        public Criteria andTeachidGreaterThan(Integer value) {
            addCriterion("teachId >", value, "teachid");
            return (Criteria) this;
        }

        public Criteria andTeachidGreaterThanOrEqualTo(Integer value) {
            addCriterion("teachId >=", value, "teachid");
            return (Criteria) this;
        }

        public Criteria andTeachidLessThan(Integer value) {
            addCriterion("teachId <", value, "teachid");
            return (Criteria) this;
        }

        public Criteria andTeachidLessThanOrEqualTo(Integer value) {
            addCriterion("teachId <=", value, "teachid");
            return (Criteria) this;
        }

        public Criteria andTeachidIn(List<Integer> values) {
            addCriterion("teachId in", values, "teachid");
            return (Criteria) this;
        }

        public Criteria andTeachidNotIn(List<Integer> values) {
            addCriterion("teachId not in", values, "teachid");
            return (Criteria) this;
        }

        public Criteria andTeachidBetween(Integer value1, Integer value2) {
            addCriterion("teachId between", value1, value2, "teachid");
            return (Criteria) this;
        }

        public Criteria andTeachidNotBetween(Integer value1, Integer value2) {
            addCriterion("teachId not between", value1, value2, "teachid");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andCoverpicIsNull() {
            addCriterion("coverPic is null");
            return (Criteria) this;
        }

        public Criteria andCoverpicIsNotNull() {
            addCriterion("coverPic is not null");
            return (Criteria) this;
        }

        public Criteria andCoverpicEqualTo(String value) {
            addCriterion("coverPic =", value, "coverpic");
            return (Criteria) this;
        }

        public Criteria andCoverpicNotEqualTo(String value) {
            addCriterion("coverPic <>", value, "coverpic");
            return (Criteria) this;
        }

        public Criteria andCoverpicGreaterThan(String value) {
            addCriterion("coverPic >", value, "coverpic");
            return (Criteria) this;
        }

        public Criteria andCoverpicGreaterThanOrEqualTo(String value) {
            addCriterion("coverPic >=", value, "coverpic");
            return (Criteria) this;
        }

        public Criteria andCoverpicLessThan(String value) {
            addCriterion("coverPic <", value, "coverpic");
            return (Criteria) this;
        }

        public Criteria andCoverpicLessThanOrEqualTo(String value) {
            addCriterion("coverPic <=", value, "coverpic");
            return (Criteria) this;
        }

        public Criteria andCoverpicLike(String value) {
            addCriterion("coverPic like", value, "coverpic");
            return (Criteria) this;
        }

        public Criteria andCoverpicNotLike(String value) {
            addCriterion("coverPic not like", value, "coverpic");
            return (Criteria) this;
        }

        public Criteria andCoverpicIn(List<String> values) {
            addCriterion("coverPic in", values, "coverpic");
            return (Criteria) this;
        }

        public Criteria andCoverpicNotIn(List<String> values) {
            addCriterion("coverPic not in", values, "coverpic");
            return (Criteria) this;
        }

        public Criteria andCoverpicBetween(String value1, String value2) {
            addCriterion("coverPic between", value1, value2, "coverpic");
            return (Criteria) this;
        }

        public Criteria andCoverpicNotBetween(String value1, String value2) {
            addCriterion("coverPic not between", value1, value2, "coverpic");
            return (Criteria) this;
        }

        public Criteria andVediourlIsNull() {
            addCriterion("vedioUrl is null");
            return (Criteria) this;
        }

        public Criteria andVediourlIsNotNull() {
            addCriterion("vedioUrl is not null");
            return (Criteria) this;
        }

        public Criteria andVediourlEqualTo(String value) {
            addCriterion("vedioUrl =", value, "vediourl");
            return (Criteria) this;
        }

        public Criteria andVediourlNotEqualTo(String value) {
            addCriterion("vedioUrl <>", value, "vediourl");
            return (Criteria) this;
        }

        public Criteria andVediourlGreaterThan(String value) {
            addCriterion("vedioUrl >", value, "vediourl");
            return (Criteria) this;
        }

        public Criteria andVediourlGreaterThanOrEqualTo(String value) {
            addCriterion("vedioUrl >=", value, "vediourl");
            return (Criteria) this;
        }

        public Criteria andVediourlLessThan(String value) {
            addCriterion("vedioUrl <", value, "vediourl");
            return (Criteria) this;
        }

        public Criteria andVediourlLessThanOrEqualTo(String value) {
            addCriterion("vedioUrl <=", value, "vediourl");
            return (Criteria) this;
        }

        public Criteria andVediourlLike(String value) {
            addCriterion("vedioUrl like", value, "vediourl");
            return (Criteria) this;
        }

        public Criteria andVediourlNotLike(String value) {
            addCriterion("vedioUrl not like", value, "vediourl");
            return (Criteria) this;
        }

        public Criteria andVediourlIn(List<String> values) {
            addCriterion("vedioUrl in", values, "vediourl");
            return (Criteria) this;
        }

        public Criteria andVediourlNotIn(List<String> values) {
            addCriterion("vedioUrl not in", values, "vediourl");
            return (Criteria) this;
        }

        public Criteria andVediourlBetween(String value1, String value2) {
            addCriterion("vedioUrl between", value1, value2, "vediourl");
            return (Criteria) this;
        }

        public Criteria andVediourlNotBetween(String value1, String value2) {
            addCriterion("vedioUrl not between", value1, value2, "vediourl");
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