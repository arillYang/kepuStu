package com.kepu.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StIntegralProductExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StIntegralProductExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCommoditynameIsNull() {
            addCriterion("commodityname is null");
            return (Criteria) this;
        }

        public Criteria andCommoditynameIsNotNull() {
            addCriterion("commodityname is not null");
            return (Criteria) this;
        }

        public Criteria andCommoditynameEqualTo(String value) {
            addCriterion("commodityname =", value, "commodityname");
            return (Criteria) this;
        }

        public Criteria andCommoditynameNotEqualTo(String value) {
            addCriterion("commodityname <>", value, "commodityname");
            return (Criteria) this;
        }

        public Criteria andCommoditynameGreaterThan(String value) {
            addCriterion("commodityname >", value, "commodityname");
            return (Criteria) this;
        }

        public Criteria andCommoditynameGreaterThanOrEqualTo(String value) {
            addCriterion("commodityname >=", value, "commodityname");
            return (Criteria) this;
        }

        public Criteria andCommoditynameLessThan(String value) {
            addCriterion("commodityname <", value, "commodityname");
            return (Criteria) this;
        }

        public Criteria andCommoditynameLessThanOrEqualTo(String value) {
            addCriterion("commodityname <=", value, "commodityname");
            return (Criteria) this;
        }

        public Criteria andCommoditynameLike(String value) {
            addCriterion("commodityname like", value, "commodityname");
            return (Criteria) this;
        }

        public Criteria andCommoditynameNotLike(String value) {
            addCriterion("commodityname not like", value, "commodityname");
            return (Criteria) this;
        }

        public Criteria andCommoditynameIn(List<String> values) {
            addCriterion("commodityname in", values, "commodityname");
            return (Criteria) this;
        }

        public Criteria andCommoditynameNotIn(List<String> values) {
            addCriterion("commodityname not in", values, "commodityname");
            return (Criteria) this;
        }

        public Criteria andCommoditynameBetween(String value1, String value2) {
            addCriterion("commodityname between", value1, value2, "commodityname");
            return (Criteria) this;
        }

        public Criteria andCommoditynameNotBetween(String value1, String value2) {
            addCriterion("commodityname not between", value1, value2, "commodityname");
            return (Criteria) this;
        }

        public Criteria andFreightIsNull() {
            addCriterion("freight is null");
            return (Criteria) this;
        }

        public Criteria andFreightIsNotNull() {
            addCriterion("freight is not null");
            return (Criteria) this;
        }

        public Criteria andFreightEqualTo(String value) {
            addCriterion("freight =", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotEqualTo(String value) {
            addCriterion("freight <>", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightGreaterThan(String value) {
            addCriterion("freight >", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightGreaterThanOrEqualTo(String value) {
            addCriterion("freight >=", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightLessThan(String value) {
            addCriterion("freight <", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightLessThanOrEqualTo(String value) {
            addCriterion("freight <=", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightLike(String value) {
            addCriterion("freight like", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotLike(String value) {
            addCriterion("freight not like", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightIn(List<String> values) {
            addCriterion("freight in", values, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotIn(List<String> values) {
            addCriterion("freight not in", values, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightBetween(String value1, String value2) {
            addCriterion("freight between", value1, value2, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotBetween(String value1, String value2) {
            addCriterion("freight not between", value1, value2, "freight");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNull() {
            addCriterion("money is null");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNotNull() {
            addCriterion("money is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyEqualTo(Double value) {
            addCriterion("money =", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotEqualTo(Double value) {
            addCriterion("money <>", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThan(Double value) {
            addCriterion("money >", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThanOrEqualTo(Double value) {
            addCriterion("money >=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThan(Double value) {
            addCriterion("money <", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThanOrEqualTo(Double value) {
            addCriterion("money <=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyIn(List<Double> values) {
            addCriterion("money in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotIn(List<Double> values) {
            addCriterion("money not in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyBetween(Double value1, Double value2) {
            addCriterion("money between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotBetween(Double value1, Double value2) {
            addCriterion("money not between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andIntegralIsNull() {
            addCriterion("integral is null");
            return (Criteria) this;
        }

        public Criteria andIntegralIsNotNull() {
            addCriterion("integral is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralEqualTo(Double value) {
            addCriterion("integral =", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralNotEqualTo(Double value) {
            addCriterion("integral <>", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralGreaterThan(Double value) {
            addCriterion("integral >", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralGreaterThanOrEqualTo(Double value) {
            addCriterion("integral >=", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralLessThan(Double value) {
            addCriterion("integral <", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralLessThanOrEqualTo(Double value) {
            addCriterion("integral <=", value, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralIn(List<Double> values) {
            addCriterion("integral in", values, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralNotIn(List<Double> values) {
            addCriterion("integral not in", values, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralBetween(Double value1, Double value2) {
            addCriterion("integral between", value1, value2, "integral");
            return (Criteria) this;
        }

        public Criteria andIntegralNotBetween(Double value1, Double value2) {
            addCriterion("integral not between", value1, value2, "integral");
            return (Criteria) this;
        }

        public Criteria andDisplaythepictureIsNull() {
            addCriterion("displaythepicture is null");
            return (Criteria) this;
        }

        public Criteria andDisplaythepictureIsNotNull() {
            addCriterion("displaythepicture is not null");
            return (Criteria) this;
        }

        public Criteria andDisplaythepictureEqualTo(String value) {
            addCriterion("displaythepicture =", value, "displaythepicture");
            return (Criteria) this;
        }

        public Criteria andDisplaythepictureNotEqualTo(String value) {
            addCriterion("displaythepicture <>", value, "displaythepicture");
            return (Criteria) this;
        }

        public Criteria andDisplaythepictureGreaterThan(String value) {
            addCriterion("displaythepicture >", value, "displaythepicture");
            return (Criteria) this;
        }

        public Criteria andDisplaythepictureGreaterThanOrEqualTo(String value) {
            addCriterion("displaythepicture >=", value, "displaythepicture");
            return (Criteria) this;
        }

        public Criteria andDisplaythepictureLessThan(String value) {
            addCriterion("displaythepicture <", value, "displaythepicture");
            return (Criteria) this;
        }

        public Criteria andDisplaythepictureLessThanOrEqualTo(String value) {
            addCriterion("displaythepicture <=", value, "displaythepicture");
            return (Criteria) this;
        }

        public Criteria andDisplaythepictureLike(String value) {
            addCriterion("displaythepicture like", value, "displaythepicture");
            return (Criteria) this;
        }

        public Criteria andDisplaythepictureNotLike(String value) {
            addCriterion("displaythepicture not like", value, "displaythepicture");
            return (Criteria) this;
        }

        public Criteria andDisplaythepictureIn(List<String> values) {
            addCriterion("displaythepicture in", values, "displaythepicture");
            return (Criteria) this;
        }

        public Criteria andDisplaythepictureNotIn(List<String> values) {
            addCriterion("displaythepicture not in", values, "displaythepicture");
            return (Criteria) this;
        }

        public Criteria andDisplaythepictureBetween(String value1, String value2) {
            addCriterion("displaythepicture between", value1, value2, "displaythepicture");
            return (Criteria) this;
        }

        public Criteria andDisplaythepictureNotBetween(String value1, String value2) {
            addCriterion("displaythepicture not between", value1, value2, "displaythepicture");
            return (Criteria) this;
        }

        public Criteria andDetailsIsNull() {
            addCriterion("details is null");
            return (Criteria) this;
        }

        public Criteria andDetailsIsNotNull() {
            addCriterion("details is not null");
            return (Criteria) this;
        }

        public Criteria andDetailsEqualTo(String value) {
            addCriterion("details =", value, "details");
            return (Criteria) this;
        }

        public Criteria andDetailsNotEqualTo(String value) {
            addCriterion("details <>", value, "details");
            return (Criteria) this;
        }

        public Criteria andDetailsGreaterThan(String value) {
            addCriterion("details >", value, "details");
            return (Criteria) this;
        }

        public Criteria andDetailsGreaterThanOrEqualTo(String value) {
            addCriterion("details >=", value, "details");
            return (Criteria) this;
        }

        public Criteria andDetailsLessThan(String value) {
            addCriterion("details <", value, "details");
            return (Criteria) this;
        }

        public Criteria andDetailsLessThanOrEqualTo(String value) {
            addCriterion("details <=", value, "details");
            return (Criteria) this;
        }

        public Criteria andDetailsLike(String value) {
            addCriterion("details like", value, "details");
            return (Criteria) this;
        }

        public Criteria andDetailsNotLike(String value) {
            addCriterion("details not like", value, "details");
            return (Criteria) this;
        }

        public Criteria andDetailsIn(List<String> values) {
            addCriterion("details in", values, "details");
            return (Criteria) this;
        }

        public Criteria andDetailsNotIn(List<String> values) {
            addCriterion("details not in", values, "details");
            return (Criteria) this;
        }

        public Criteria andDetailsBetween(String value1, String value2) {
            addCriterion("details between", value1, value2, "details");
            return (Criteria) this;
        }

        public Criteria andDetailsNotBetween(String value1, String value2) {
            addCriterion("details not between", value1, value2, "details");
            return (Criteria) this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("userid is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userid is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(Integer value) {
            addCriterion("userid =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(Integer value) {
            addCriterion("userid <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(Integer value) {
            addCriterion("userid >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("userid >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(Integer value) {
            addCriterion("userid <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(Integer value) {
            addCriterion("userid <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<Integer> values) {
            addCriterion("userid in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<Integer> values) {
            addCriterion("userid not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(Integer value1, Integer value2) {
            addCriterion("userid between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(Integer value1, Integer value2) {
            addCriterion("userid not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
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

        public Criteria andNumbeIsNull() {
            addCriterion("numbe is null");
            return (Criteria) this;
        }

        public Criteria andNumbeIsNotNull() {
            addCriterion("numbe is not null");
            return (Criteria) this;
        }

        public Criteria andNumbeEqualTo(Integer value) {
            addCriterion("numbe =", value, "numbe");
            return (Criteria) this;
        }

        public Criteria andNumbeNotEqualTo(Integer value) {
            addCriterion("numbe <>", value, "numbe");
            return (Criteria) this;
        }

        public Criteria andNumbeGreaterThan(Integer value) {
            addCriterion("numbe >", value, "numbe");
            return (Criteria) this;
        }

        public Criteria andNumbeGreaterThanOrEqualTo(Integer value) {
            addCriterion("numbe >=", value, "numbe");
            return (Criteria) this;
        }

        public Criteria andNumbeLessThan(Integer value) {
            addCriterion("numbe <", value, "numbe");
            return (Criteria) this;
        }

        public Criteria andNumbeLessThanOrEqualTo(Integer value) {
            addCriterion("numbe <=", value, "numbe");
            return (Criteria) this;
        }

        public Criteria andNumbeIn(List<Integer> values) {
            addCriterion("numbe in", values, "numbe");
            return (Criteria) this;
        }

        public Criteria andNumbeNotIn(List<Integer> values) {
            addCriterion("numbe not in", values, "numbe");
            return (Criteria) this;
        }

        public Criteria andNumbeBetween(Integer value1, Integer value2) {
            addCriterion("numbe between", value1, value2, "numbe");
            return (Criteria) this;
        }

        public Criteria andNumbeNotBetween(Integer value1, Integer value2) {
            addCriterion("numbe not between", value1, value2, "numbe");
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