package com.kepu.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StProductExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StProductExample() {
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

        public Criteria andClassfyidIsNull() {
            addCriterion("classfyId is null");
            return (Criteria) this;
        }

        public Criteria andClassfyidIsNotNull() {
            addCriterion("classfyId is not null");
            return (Criteria) this;
        }

        public Criteria andClassfyidEqualTo(Integer value) {
            addCriterion("classfyId =", value, "classfyid");
            return (Criteria) this;
        }

        public Criteria andClassfyidNotEqualTo(Integer value) {
            addCriterion("classfyId <>", value, "classfyid");
            return (Criteria) this;
        }

        public Criteria andClassfyidGreaterThan(Integer value) {
            addCriterion("classfyId >", value, "classfyid");
            return (Criteria) this;
        }

        public Criteria andClassfyidGreaterThanOrEqualTo(Integer value) {
            addCriterion("classfyId >=", value, "classfyid");
            return (Criteria) this;
        }

        public Criteria andClassfyidLessThan(Integer value) {
            addCriterion("classfyId <", value, "classfyid");
            return (Criteria) this;
        }

        public Criteria andClassfyidLessThanOrEqualTo(Integer value) {
            addCriterion("classfyId <=", value, "classfyid");
            return (Criteria) this;
        }

        public Criteria andClassfyidIn(List<Integer> values) {
            addCriterion("classfyId in", values, "classfyid");
            return (Criteria) this;
        }

        public Criteria andClassfyidNotIn(List<Integer> values) {
            addCriterion("classfyId not in", values, "classfyid");
            return (Criteria) this;
        }

        public Criteria andClassfyidBetween(Integer value1, Integer value2) {
            addCriterion("classfyId between", value1, value2, "classfyid");
            return (Criteria) this;
        }

        public Criteria andClassfyidNotBetween(Integer value1, Integer value2) {
            addCriterion("classfyId not between", value1, value2, "classfyid");
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

        public Criteria andIntroduceIsNull() {
            addCriterion("introduce is null");
            return (Criteria) this;
        }

        public Criteria andIntroduceIsNotNull() {
            addCriterion("introduce is not null");
            return (Criteria) this;
        }

        public Criteria andIntroduceEqualTo(String value) {
            addCriterion("introduce =", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotEqualTo(String value) {
            addCriterion("introduce <>", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceGreaterThan(String value) {
            addCriterion("introduce >", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceGreaterThanOrEqualTo(String value) {
            addCriterion("introduce >=", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLessThan(String value) {
            addCriterion("introduce <", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLessThanOrEqualTo(String value) {
            addCriterion("introduce <=", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLike(String value) {
            addCriterion("introduce like", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotLike(String value) {
            addCriterion("introduce not like", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceIn(List<String> values) {
            addCriterion("introduce in", values, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotIn(List<String> values) {
            addCriterion("introduce not in", values, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceBetween(String value1, String value2) {
            addCriterion("introduce between", value1, value2, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotBetween(String value1, String value2) {
            addCriterion("introduce not between", value1, value2, "introduce");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile not between", value1, value2, "mobile");
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

        public Criteria andMoneyEqualTo(BigDecimal value) {
            addCriterion("money =", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotEqualTo(BigDecimal value) {
            addCriterion("money <>", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThan(BigDecimal value) {
            addCriterion("money >", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("money >=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThan(BigDecimal value) {
            addCriterion("money <", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("money <=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyIn(List<BigDecimal> values) {
            addCriterion("money in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotIn(List<BigDecimal> values) {
            addCriterion("money not in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("money between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("money not between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andVillageidIsNull() {
            addCriterion("villageId is null");
            return (Criteria) this;
        }

        public Criteria andVillageidIsNotNull() {
            addCriterion("villageId is not null");
            return (Criteria) this;
        }

        public Criteria andVillageidEqualTo(Integer value) {
            addCriterion("villageId =", value, "villageid");
            return (Criteria) this;
        }

        public Criteria andVillageidNotEqualTo(Integer value) {
            addCriterion("villageId <>", value, "villageid");
            return (Criteria) this;
        }

        public Criteria andVillageidGreaterThan(Integer value) {
            addCriterion("villageId >", value, "villageid");
            return (Criteria) this;
        }

        public Criteria andVillageidGreaterThanOrEqualTo(Integer value) {
            addCriterion("villageId >=", value, "villageid");
            return (Criteria) this;
        }

        public Criteria andVillageidLessThan(Integer value) {
            addCriterion("villageId <", value, "villageid");
            return (Criteria) this;
        }

        public Criteria andVillageidLessThanOrEqualTo(Integer value) {
            addCriterion("villageId <=", value, "villageid");
            return (Criteria) this;
        }

        public Criteria andVillageidIn(List<Integer> values) {
            addCriterion("villageId in", values, "villageid");
            return (Criteria) this;
        }

        public Criteria andVillageidNotIn(List<Integer> values) {
            addCriterion("villageId not in", values, "villageid");
            return (Criteria) this;
        }

        public Criteria andVillageidBetween(Integer value1, Integer value2) {
            addCriterion("villageId between", value1, value2, "villageid");
            return (Criteria) this;
        }

        public Criteria andVillageidNotBetween(Integer value1, Integer value2) {
            addCriterion("villageId not between", value1, value2, "villageid");
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

        public Criteria andDetailpicsIsNull() {
            addCriterion("detailPics is null");
            return (Criteria) this;
        }

        public Criteria andDetailpicsIsNotNull() {
            addCriterion("detailPics is not null");
            return (Criteria) this;
        }

        public Criteria andDetailpicsEqualTo(String value) {
            addCriterion("detailPics =", value, "detailpics");
            return (Criteria) this;
        }

        public Criteria andDetailpicsNotEqualTo(String value) {
            addCriterion("detailPics <>", value, "detailpics");
            return (Criteria) this;
        }

        public Criteria andDetailpicsGreaterThan(String value) {
            addCriterion("detailPics >", value, "detailpics");
            return (Criteria) this;
        }

        public Criteria andDetailpicsGreaterThanOrEqualTo(String value) {
            addCriterion("detailPics >=", value, "detailpics");
            return (Criteria) this;
        }

        public Criteria andDetailpicsLessThan(String value) {
            addCriterion("detailPics <", value, "detailpics");
            return (Criteria) this;
        }

        public Criteria andDetailpicsLessThanOrEqualTo(String value) {
            addCriterion("detailPics <=", value, "detailpics");
            return (Criteria) this;
        }

        public Criteria andDetailpicsLike(String value) {
            addCriterion("detailPics like", value, "detailpics");
            return (Criteria) this;
        }

        public Criteria andDetailpicsNotLike(String value) {
            addCriterion("detailPics not like", value, "detailpics");
            return (Criteria) this;
        }

        public Criteria andDetailpicsIn(List<String> values) {
            addCriterion("detailPics in", values, "detailpics");
            return (Criteria) this;
        }

        public Criteria andDetailpicsNotIn(List<String> values) {
            addCriterion("detailPics not in", values, "detailpics");
            return (Criteria) this;
        }

        public Criteria andDetailpicsBetween(String value1, String value2) {
            addCriterion("detailPics between", value1, value2, "detailpics");
            return (Criteria) this;
        }

        public Criteria andDetailpicsNotBetween(String value1, String value2) {
            addCriterion("detailPics not between", value1, value2, "detailpics");
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

        public Criteria andClassfynameIsNull() {
            addCriterion("classfyName is null");
            return (Criteria) this;
        }

        public Criteria andClassfynameIsNotNull() {
            addCriterion("classfyName is not null");
            return (Criteria) this;
        }

        public Criteria andClassfynameEqualTo(String value) {
            addCriterion("classfyName =", value, "classfyname");
            return (Criteria) this;
        }

        public Criteria andClassfynameNotEqualTo(String value) {
            addCriterion("classfyName <>", value, "classfyname");
            return (Criteria) this;
        }

        public Criteria andClassfynameGreaterThan(String value) {
            addCriterion("classfyName >", value, "classfyname");
            return (Criteria) this;
        }

        public Criteria andClassfynameGreaterThanOrEqualTo(String value) {
            addCriterion("classfyName >=", value, "classfyname");
            return (Criteria) this;
        }

        public Criteria andClassfynameLessThan(String value) {
            addCriterion("classfyName <", value, "classfyname");
            return (Criteria) this;
        }

        public Criteria andClassfynameLessThanOrEqualTo(String value) {
            addCriterion("classfyName <=", value, "classfyname");
            return (Criteria) this;
        }

        public Criteria andClassfynameLike(String value) {
            addCriterion("classfyName like", value, "classfyname");
            return (Criteria) this;
        }

        public Criteria andClassfynameNotLike(String value) {
            addCriterion("classfyName not like", value, "classfyname");
            return (Criteria) this;
        }

        public Criteria andClassfynameIn(List<String> values) {
            addCriterion("classfyName in", values, "classfyname");
            return (Criteria) this;
        }

        public Criteria andClassfynameNotIn(List<String> values) {
            addCriterion("classfyName not in", values, "classfyname");
            return (Criteria) this;
        }

        public Criteria andClassfynameBetween(String value1, String value2) {
            addCriterion("classfyName between", value1, value2, "classfyname");
            return (Criteria) this;
        }

        public Criteria andClassfynameNotBetween(String value1, String value2) {
            addCriterion("classfyName not between", value1, value2, "classfyname");
            return (Criteria) this;
        }

        public Criteria andEndpicIsNull() {
            addCriterion("EndPic is null");
            return (Criteria) this;
        }

        public Criteria andEndpicIsNotNull() {
            addCriterion("EndPic is not null");
            return (Criteria) this;
        }

        public Criteria andEndpicEqualTo(String value) {
            addCriterion("EndPic =", value, "endpic");
            return (Criteria) this;
        }

        public Criteria andEndpicNotEqualTo(String value) {
            addCriterion("EndPic <>", value, "endpic");
            return (Criteria) this;
        }

        public Criteria andEndpicGreaterThan(String value) {
            addCriterion("EndPic >", value, "endpic");
            return (Criteria) this;
        }

        public Criteria andEndpicGreaterThanOrEqualTo(String value) {
            addCriterion("EndPic >=", value, "endpic");
            return (Criteria) this;
        }

        public Criteria andEndpicLessThan(String value) {
            addCriterion("EndPic <", value, "endpic");
            return (Criteria) this;
        }

        public Criteria andEndpicLessThanOrEqualTo(String value) {
            addCriterion("EndPic <=", value, "endpic");
            return (Criteria) this;
        }

        public Criteria andEndpicLike(String value) {
            addCriterion("EndPic like", value, "endpic");
            return (Criteria) this;
        }

        public Criteria andEndpicNotLike(String value) {
            addCriterion("EndPic not like", value, "endpic");
            return (Criteria) this;
        }

        public Criteria andEndpicIn(List<String> values) {
            addCriterion("EndPic in", values, "endpic");
            return (Criteria) this;
        }

        public Criteria andEndpicNotIn(List<String> values) {
            addCriterion("EndPic not in", values, "endpic");
            return (Criteria) this;
        }

        public Criteria andEndpicBetween(String value1, String value2) {
            addCriterion("EndPic between", value1, value2, "endpic");
            return (Criteria) this;
        }

        public Criteria andEndpicNotBetween(String value1, String value2) {
            addCriterion("EndPic not between", value1, value2, "endpic");
            return (Criteria) this;
        }

        public Criteria andCarouselIsNull() {
            addCriterion("carousel is null");
            return (Criteria) this;
        }

        public Criteria andCarouselIsNotNull() {
            addCriterion("carousel is not null");
            return (Criteria) this;
        }

        public Criteria andCarouselEqualTo(Integer value) {
            addCriterion("carousel =", value, "carousel");
            return (Criteria) this;
        }

        public Criteria andCarouselNotEqualTo(Integer value) {
            addCriterion("carousel <>", value, "carousel");
            return (Criteria) this;
        }

        public Criteria andCarouselGreaterThan(Integer value) {
            addCriterion("carousel >", value, "carousel");
            return (Criteria) this;
        }

        public Criteria andCarouselGreaterThanOrEqualTo(Integer value) {
            addCriterion("carousel >=", value, "carousel");
            return (Criteria) this;
        }

        public Criteria andCarouselLessThan(Integer value) {
            addCriterion("carousel <", value, "carousel");
            return (Criteria) this;
        }

        public Criteria andCarouselLessThanOrEqualTo(Integer value) {
            addCriterion("carousel <=", value, "carousel");
            return (Criteria) this;
        }

        public Criteria andCarouselIn(List<Integer> values) {
            addCriterion("carousel in", values, "carousel");
            return (Criteria) this;
        }

        public Criteria andCarouselNotIn(List<Integer> values) {
            addCriterion("carousel not in", values, "carousel");
            return (Criteria) this;
        }

        public Criteria andCarouselBetween(Integer value1, Integer value2) {
            addCriterion("carousel between", value1, value2, "carousel");
            return (Criteria) this;
        }

        public Criteria andCarouselNotBetween(Integer value1, Integer value2) {
            addCriterion("carousel not between", value1, value2, "carousel");
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

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
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

        public Criteria andAvatarIsNull() {
            addCriterion("avatar is null");
            return (Criteria) this;
        }

        public Criteria andAvatarIsNotNull() {
            addCriterion("avatar is not null");
            return (Criteria) this;
        }

        public Criteria andAvatarEqualTo(String value) {
            addCriterion("avatar =", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotEqualTo(String value) {
            addCriterion("avatar <>", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarGreaterThan(String value) {
            addCriterion("avatar >", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarGreaterThanOrEqualTo(String value) {
            addCriterion("avatar >=", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarLessThan(String value) {
            addCriterion("avatar <", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarLessThanOrEqualTo(String value) {
            addCriterion("avatar <=", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarLike(String value) {
            addCriterion("avatar like", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotLike(String value) {
            addCriterion("avatar not like", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarIn(List<String> values) {
            addCriterion("avatar in", values, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotIn(List<String> values) {
            addCriterion("avatar not in", values, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarBetween(String value1, String value2) {
            addCriterion("avatar between", value1, value2, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotBetween(String value1, String value2) {
            addCriterion("avatar not between", value1, value2, "avatar");
            return (Criteria) this;
        }

        public Criteria andCarouseltimeIsNull() {
            addCriterion("carouselTime is null");
            return (Criteria) this;
        }

        public Criteria andCarouseltimeIsNotNull() {
            addCriterion("carouselTime is not null");
            return (Criteria) this;
        }

        public Criteria andCarouseltimeEqualTo(Date value) {
            addCriterion("carouselTime =", value, "carouseltime");
            return (Criteria) this;
        }

        public Criteria andCarouseltimeNotEqualTo(Date value) {
            addCriterion("carouselTime <>", value, "carouseltime");
            return (Criteria) this;
        }

        public Criteria andCarouseltimeGreaterThan(Date value) {
            addCriterion("carouselTime >", value, "carouseltime");
            return (Criteria) this;
        }

        public Criteria andCarouseltimeGreaterThanOrEqualTo(Date value) {
            addCriterion("carouselTime >=", value, "carouseltime");
            return (Criteria) this;
        }

        public Criteria andCarouseltimeLessThan(Date value) {
            addCriterion("carouselTime <", value, "carouseltime");
            return (Criteria) this;
        }

        public Criteria andCarouseltimeLessThanOrEqualTo(Date value) {
            addCriterion("carouselTime <=", value, "carouseltime");
            return (Criteria) this;
        }

        public Criteria andCarouseltimeIn(List<Date> values) {
            addCriterion("carouselTime in", values, "carouseltime");
            return (Criteria) this;
        }

        public Criteria andCarouseltimeNotIn(List<Date> values) {
            addCriterion("carouselTime not in", values, "carouseltime");
            return (Criteria) this;
        }

        public Criteria andCarouseltimeBetween(Date value1, Date value2) {
            addCriterion("carouselTime between", value1, value2, "carouseltime");
            return (Criteria) this;
        }

        public Criteria andCarouseltimeNotBetween(Date value1, Date value2) {
            addCriterion("carouselTime not between", value1, value2, "carouseltime");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("userName is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("userName is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("userName =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("userName <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("userName >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("userName >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("userName <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("userName <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("userName like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("userName not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("userName in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("userName not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("userName between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("userName not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
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