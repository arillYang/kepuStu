package com.kepu.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StLotteryVoteExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StLotteryVoteExample() {
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

        public Criteria andLotteryidIsNull() {
            addCriterion("lotteryId is null");
            return (Criteria) this;
        }

        public Criteria andLotteryidIsNotNull() {
            addCriterion("lotteryId is not null");
            return (Criteria) this;
        }

        public Criteria andLotteryidEqualTo(Integer value) {
            addCriterion("lotteryId =", value, "lotteryid");
            return (Criteria) this;
        }

        public Criteria andLotteryidNotEqualTo(Integer value) {
            addCriterion("lotteryId <>", value, "lotteryid");
            return (Criteria) this;
        }

        public Criteria andLotteryidGreaterThan(Integer value) {
            addCriterion("lotteryId >", value, "lotteryid");
            return (Criteria) this;
        }

        public Criteria andLotteryidGreaterThanOrEqualTo(Integer value) {
            addCriterion("lotteryId >=", value, "lotteryid");
            return (Criteria) this;
        }

        public Criteria andLotteryidLessThan(Integer value) {
            addCriterion("lotteryId <", value, "lotteryid");
            return (Criteria) this;
        }

        public Criteria andLotteryidLessThanOrEqualTo(Integer value) {
            addCriterion("lotteryId <=", value, "lotteryid");
            return (Criteria) this;
        }

        public Criteria andLotteryidIn(List<Integer> values) {
            addCriterion("lotteryId in", values, "lotteryid");
            return (Criteria) this;
        }

        public Criteria andLotteryidNotIn(List<Integer> values) {
            addCriterion("lotteryId not in", values, "lotteryid");
            return (Criteria) this;
        }

        public Criteria andLotteryidBetween(Integer value1, Integer value2) {
            addCriterion("lotteryId between", value1, value2, "lotteryid");
            return (Criteria) this;
        }

        public Criteria andLotteryidNotBetween(Integer value1, Integer value2) {
            addCriterion("lotteryId not between", value1, value2, "lotteryid");
            return (Criteria) this;
        }

        public Criteria andJoinidIsNull() {
            addCriterion("joinId is null");
            return (Criteria) this;
        }

        public Criteria andJoinidIsNotNull() {
            addCriterion("joinId is not null");
            return (Criteria) this;
        }

        public Criteria andJoinidEqualTo(Integer value) {
            addCriterion("joinId =", value, "joinid");
            return (Criteria) this;
        }

        public Criteria andJoinidNotEqualTo(Integer value) {
            addCriterion("joinId <>", value, "joinid");
            return (Criteria) this;
        }

        public Criteria andJoinidGreaterThan(Integer value) {
            addCriterion("joinId >", value, "joinid");
            return (Criteria) this;
        }

        public Criteria andJoinidGreaterThanOrEqualTo(Integer value) {
            addCriterion("joinId >=", value, "joinid");
            return (Criteria) this;
        }

        public Criteria andJoinidLessThan(Integer value) {
            addCriterion("joinId <", value, "joinid");
            return (Criteria) this;
        }

        public Criteria andJoinidLessThanOrEqualTo(Integer value) {
            addCriterion("joinId <=", value, "joinid");
            return (Criteria) this;
        }

        public Criteria andJoinidIn(List<Integer> values) {
            addCriterion("joinId in", values, "joinid");
            return (Criteria) this;
        }

        public Criteria andJoinidNotIn(List<Integer> values) {
            addCriterion("joinId not in", values, "joinid");
            return (Criteria) this;
        }

        public Criteria andJoinidBetween(Integer value1, Integer value2) {
            addCriterion("joinId between", value1, value2, "joinid");
            return (Criteria) this;
        }

        public Criteria andJoinidNotBetween(Integer value1, Integer value2) {
            addCriterion("joinId not between", value1, value2, "joinid");
            return (Criteria) this;
        }

        public Criteria andJoinuserIsNull() {
            addCriterion("joinUser is null");
            return (Criteria) this;
        }

        public Criteria andJoinuserIsNotNull() {
            addCriterion("joinUser is not null");
            return (Criteria) this;
        }

        public Criteria andJoinuserEqualTo(Integer value) {
            addCriterion("joinUser =", value, "joinuser");
            return (Criteria) this;
        }

        public Criteria andJoinuserNotEqualTo(Integer value) {
            addCriterion("joinUser <>", value, "joinuser");
            return (Criteria) this;
        }

        public Criteria andJoinuserGreaterThan(Integer value) {
            addCriterion("joinUser >", value, "joinuser");
            return (Criteria) this;
        }

        public Criteria andJoinuserGreaterThanOrEqualTo(Integer value) {
            addCriterion("joinUser >=", value, "joinuser");
            return (Criteria) this;
        }

        public Criteria andJoinuserLessThan(Integer value) {
            addCriterion("joinUser <", value, "joinuser");
            return (Criteria) this;
        }

        public Criteria andJoinuserLessThanOrEqualTo(Integer value) {
            addCriterion("joinUser <=", value, "joinuser");
            return (Criteria) this;
        }

        public Criteria andJoinuserIn(List<Integer> values) {
            addCriterion("joinUser in", values, "joinuser");
            return (Criteria) this;
        }

        public Criteria andJoinuserNotIn(List<Integer> values) {
            addCriterion("joinUser not in", values, "joinuser");
            return (Criteria) this;
        }

        public Criteria andJoinuserBetween(Integer value1, Integer value2) {
            addCriterion("joinUser between", value1, value2, "joinuser");
            return (Criteria) this;
        }

        public Criteria andJoinuserNotBetween(Integer value1, Integer value2) {
            addCriterion("joinUser not between", value1, value2, "joinuser");
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

        public Criteria andVotetimeIsNull() {
            addCriterion("votetime is null");
            return (Criteria) this;
        }

        public Criteria andVotetimeIsNotNull() {
            addCriterion("votetime is not null");
            return (Criteria) this;
        }

        public Criteria andVotetimeEqualTo(Date value) {
            addCriterion("votetime =", value, "votetime");
            return (Criteria) this;
        }

        public Criteria andVotetimeNotEqualTo(Date value) {
            addCriterion("votetime <>", value, "votetime");
            return (Criteria) this;
        }

        public Criteria andVotetimeGreaterThan(Date value) {
            addCriterion("votetime >", value, "votetime");
            return (Criteria) this;
        }

        public Criteria andVotetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("votetime >=", value, "votetime");
            return (Criteria) this;
        }

        public Criteria andVotetimeLessThan(Date value) {
            addCriterion("votetime <", value, "votetime");
            return (Criteria) this;
        }

        public Criteria andVotetimeLessThanOrEqualTo(Date value) {
            addCriterion("votetime <=", value, "votetime");
            return (Criteria) this;
        }

        public Criteria andVotetimeIn(List<Date> values) {
            addCriterion("votetime in", values, "votetime");
            return (Criteria) this;
        }

        public Criteria andVotetimeNotIn(List<Date> values) {
            addCriterion("votetime not in", values, "votetime");
            return (Criteria) this;
        }

        public Criteria andVotetimeBetween(Date value1, Date value2) {
            addCriterion("votetime between", value1, value2, "votetime");
            return (Criteria) this;
        }

        public Criteria andVotetimeNotBetween(Date value1, Date value2) {
            addCriterion("votetime not between", value1, value2, "votetime");
            return (Criteria) this;
        }

        public Criteria andTownidIsNull() {
            addCriterion("townId is null");
            return (Criteria) this;
        }

        public Criteria andTownidIsNotNull() {
            addCriterion("townId is not null");
            return (Criteria) this;
        }

        public Criteria andTownidEqualTo(Integer value) {
            addCriterion("townId =", value, "townid");
            return (Criteria) this;
        }

        public Criteria andTownidNotEqualTo(Integer value) {
            addCriterion("townId <>", value, "townid");
            return (Criteria) this;
        }

        public Criteria andTownidGreaterThan(Integer value) {
            addCriterion("townId >", value, "townid");
            return (Criteria) this;
        }

        public Criteria andTownidGreaterThanOrEqualTo(Integer value) {
            addCriterion("townId >=", value, "townid");
            return (Criteria) this;
        }

        public Criteria andTownidLessThan(Integer value) {
            addCriterion("townId <", value, "townid");
            return (Criteria) this;
        }

        public Criteria andTownidLessThanOrEqualTo(Integer value) {
            addCriterion("townId <=", value, "townid");
            return (Criteria) this;
        }

        public Criteria andTownidIn(List<Integer> values) {
            addCriterion("townId in", values, "townid");
            return (Criteria) this;
        }

        public Criteria andTownidNotIn(List<Integer> values) {
            addCriterion("townId not in", values, "townid");
            return (Criteria) this;
        }

        public Criteria andTownidBetween(Integer value1, Integer value2) {
            addCriterion("townId between", value1, value2, "townid");
            return (Criteria) this;
        }

        public Criteria andTownidNotBetween(Integer value1, Integer value2) {
            addCriterion("townId not between", value1, value2, "townid");
            return (Criteria) this;
        }

        public Criteria andCountryidIsNull() {
            addCriterion("countryId is null");
            return (Criteria) this;
        }

        public Criteria andCountryidIsNotNull() {
            addCriterion("countryId is not null");
            return (Criteria) this;
        }

        public Criteria andCountryidEqualTo(Integer value) {
            addCriterion("countryId =", value, "countryid");
            return (Criteria) this;
        }

        public Criteria andCountryidNotEqualTo(Integer value) {
            addCriterion("countryId <>", value, "countryid");
            return (Criteria) this;
        }

        public Criteria andCountryidGreaterThan(Integer value) {
            addCriterion("countryId >", value, "countryid");
            return (Criteria) this;
        }

        public Criteria andCountryidGreaterThanOrEqualTo(Integer value) {
            addCriterion("countryId >=", value, "countryid");
            return (Criteria) this;
        }

        public Criteria andCountryidLessThan(Integer value) {
            addCriterion("countryId <", value, "countryid");
            return (Criteria) this;
        }

        public Criteria andCountryidLessThanOrEqualTo(Integer value) {
            addCriterion("countryId <=", value, "countryid");
            return (Criteria) this;
        }

        public Criteria andCountryidIn(List<Integer> values) {
            addCriterion("countryId in", values, "countryid");
            return (Criteria) this;
        }

        public Criteria andCountryidNotIn(List<Integer> values) {
            addCriterion("countryId not in", values, "countryid");
            return (Criteria) this;
        }

        public Criteria andCountryidBetween(Integer value1, Integer value2) {
            addCriterion("countryId between", value1, value2, "countryid");
            return (Criteria) this;
        }

        public Criteria andCountryidNotBetween(Integer value1, Integer value2) {
            addCriterion("countryId not between", value1, value2, "countryid");
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