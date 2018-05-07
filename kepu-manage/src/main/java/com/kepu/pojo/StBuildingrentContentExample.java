package com.kepu.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StBuildingrentContentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StBuildingrentContentExample() {
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

        public Criteria andRealnameIsNull() {
            addCriterion("realName is null");
            return (Criteria) this;
        }

        public Criteria andRealnameIsNotNull() {
            addCriterion("realName is not null");
            return (Criteria) this;
        }

        public Criteria andRealnameEqualTo(String value) {
            addCriterion("realName =", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotEqualTo(String value) {
            addCriterion("realName <>", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameGreaterThan(String value) {
            addCriterion("realName >", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameGreaterThanOrEqualTo(String value) {
            addCriterion("realName >=", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLessThan(String value) {
            addCriterion("realName <", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLessThanOrEqualTo(String value) {
            addCriterion("realName <=", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameLike(String value) {
            addCriterion("realName like", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotLike(String value) {
            addCriterion("realName not like", value, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameIn(List<String> values) {
            addCriterion("realName in", values, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotIn(List<String> values) {
            addCriterion("realName not in", values, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameBetween(String value1, String value2) {
            addCriterion("realName between", value1, value2, "realname");
            return (Criteria) this;
        }

        public Criteria andRealnameNotBetween(String value1, String value2) {
            addCriterion("realName not between", value1, value2, "realname");
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

        public Criteria andNicknameIsNull() {
            addCriterion("nickName is null");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNotNull() {
            addCriterion("nickName is not null");
            return (Criteria) this;
        }

        public Criteria andNicknameEqualTo(String value) {
            addCriterion("nickName =", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotEqualTo(String value) {
            addCriterion("nickName <>", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThan(String value) {
            addCriterion("nickName >", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("nickName >=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThan(String value) {
            addCriterion("nickName <", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThanOrEqualTo(String value) {
            addCriterion("nickName <=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLike(String value) {
            addCriterion("nickName like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotLike(String value) {
            addCriterion("nickName not like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameIn(List<String> values) {
            addCriterion("nickName in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotIn(List<String> values) {
            addCriterion("nickName not in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameBetween(String value1, String value2) {
            addCriterion("nickName between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotBetween(String value1, String value2) {
            addCriterion("nickName not between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andLowpriceIsNull() {
            addCriterion("lowprice is null");
            return (Criteria) this;
        }

        public Criteria andLowpriceIsNotNull() {
            addCriterion("lowprice is not null");
            return (Criteria) this;
        }

        public Criteria andLowpriceEqualTo(BigDecimal value) {
            addCriterion("lowprice =", value, "lowprice");
            return (Criteria) this;
        }

        public Criteria andLowpriceNotEqualTo(BigDecimal value) {
            addCriterion("lowprice <>", value, "lowprice");
            return (Criteria) this;
        }

        public Criteria andLowpriceGreaterThan(BigDecimal value) {
            addCriterion("lowprice >", value, "lowprice");
            return (Criteria) this;
        }

        public Criteria andLowpriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("lowprice >=", value, "lowprice");
            return (Criteria) this;
        }

        public Criteria andLowpriceLessThan(BigDecimal value) {
            addCriterion("lowprice <", value, "lowprice");
            return (Criteria) this;
        }

        public Criteria andLowpriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("lowprice <=", value, "lowprice");
            return (Criteria) this;
        }

        public Criteria andLowpriceIn(List<BigDecimal> values) {
            addCriterion("lowprice in", values, "lowprice");
            return (Criteria) this;
        }

        public Criteria andLowpriceNotIn(List<BigDecimal> values) {
            addCriterion("lowprice not in", values, "lowprice");
            return (Criteria) this;
        }

        public Criteria andLowpriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("lowprice between", value1, value2, "lowprice");
            return (Criteria) this;
        }

        public Criteria andLowpriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("lowprice not between", value1, value2, "lowprice");
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

        public Criteria andLowsizeIsNull() {
            addCriterion("lowsize is null");
            return (Criteria) this;
        }

        public Criteria andLowsizeIsNotNull() {
            addCriterion("lowsize is not null");
            return (Criteria) this;
        }

        public Criteria andLowsizeEqualTo(Double value) {
            addCriterion("lowsize =", value, "lowsize");
            return (Criteria) this;
        }

        public Criteria andLowsizeNotEqualTo(Double value) {
            addCriterion("lowsize <>", value, "lowsize");
            return (Criteria) this;
        }

        public Criteria andLowsizeGreaterThan(Double value) {
            addCriterion("lowsize >", value, "lowsize");
            return (Criteria) this;
        }

        public Criteria andLowsizeGreaterThanOrEqualTo(Double value) {
            addCriterion("lowsize >=", value, "lowsize");
            return (Criteria) this;
        }

        public Criteria andLowsizeLessThan(Double value) {
            addCriterion("lowsize <", value, "lowsize");
            return (Criteria) this;
        }

        public Criteria andLowsizeLessThanOrEqualTo(Double value) {
            addCriterion("lowsize <=", value, "lowsize");
            return (Criteria) this;
        }

        public Criteria andLowsizeIn(List<Double> values) {
            addCriterion("lowsize in", values, "lowsize");
            return (Criteria) this;
        }

        public Criteria andLowsizeNotIn(List<Double> values) {
            addCriterion("lowsize not in", values, "lowsize");
            return (Criteria) this;
        }

        public Criteria andLowsizeBetween(Double value1, Double value2) {
            addCriterion("lowsize between", value1, value2, "lowsize");
            return (Criteria) this;
        }

        public Criteria andLowsizeNotBetween(Double value1, Double value2) {
            addCriterion("lowsize not between", value1, value2, "lowsize");
            return (Criteria) this;
        }

        public Criteria andSelltypeIsNull() {
            addCriterion("sellType is null");
            return (Criteria) this;
        }

        public Criteria andSelltypeIsNotNull() {
            addCriterion("sellType is not null");
            return (Criteria) this;
        }

        public Criteria andSelltypeEqualTo(Integer value) {
            addCriterion("sellType =", value, "selltype");
            return (Criteria) this;
        }

        public Criteria andSelltypeNotEqualTo(Integer value) {
            addCriterion("sellType <>", value, "selltype");
            return (Criteria) this;
        }

        public Criteria andSelltypeGreaterThan(Integer value) {
            addCriterion("sellType >", value, "selltype");
            return (Criteria) this;
        }

        public Criteria andSelltypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("sellType >=", value, "selltype");
            return (Criteria) this;
        }

        public Criteria andSelltypeLessThan(Integer value) {
            addCriterion("sellType <", value, "selltype");
            return (Criteria) this;
        }

        public Criteria andSelltypeLessThanOrEqualTo(Integer value) {
            addCriterion("sellType <=", value, "selltype");
            return (Criteria) this;
        }

        public Criteria andSelltypeIn(List<Integer> values) {
            addCriterion("sellType in", values, "selltype");
            return (Criteria) this;
        }

        public Criteria andSelltypeNotIn(List<Integer> values) {
            addCriterion("sellType not in", values, "selltype");
            return (Criteria) this;
        }

        public Criteria andSelltypeBetween(Integer value1, Integer value2) {
            addCriterion("sellType between", value1, value2, "selltype");
            return (Criteria) this;
        }

        public Criteria andSelltypeNotBetween(Integer value1, Integer value2) {
            addCriterion("sellType not between", value1, value2, "selltype");
            return (Criteria) this;
        }

        public Criteria andHighpriceIsNull() {
            addCriterion("highprice is null");
            return (Criteria) this;
        }

        public Criteria andHighpriceIsNotNull() {
            addCriterion("highprice is not null");
            return (Criteria) this;
        }

        public Criteria andHighpriceEqualTo(BigDecimal value) {
            addCriterion("highprice =", value, "highprice");
            return (Criteria) this;
        }

        public Criteria andHighpriceNotEqualTo(BigDecimal value) {
            addCriterion("highprice <>", value, "highprice");
            return (Criteria) this;
        }

        public Criteria andHighpriceGreaterThan(BigDecimal value) {
            addCriterion("highprice >", value, "highprice");
            return (Criteria) this;
        }

        public Criteria andHighpriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("highprice >=", value, "highprice");
            return (Criteria) this;
        }

        public Criteria andHighpriceLessThan(BigDecimal value) {
            addCriterion("highprice <", value, "highprice");
            return (Criteria) this;
        }

        public Criteria andHighpriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("highprice <=", value, "highprice");
            return (Criteria) this;
        }

        public Criteria andHighpriceIn(List<BigDecimal> values) {
            addCriterion("highprice in", values, "highprice");
            return (Criteria) this;
        }

        public Criteria andHighpriceNotIn(List<BigDecimal> values) {
            addCriterion("highprice not in", values, "highprice");
            return (Criteria) this;
        }

        public Criteria andHighpriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("highprice between", value1, value2, "highprice");
            return (Criteria) this;
        }

        public Criteria andHighpriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("highprice not between", value1, value2, "highprice");
            return (Criteria) this;
        }

        public Criteria andHighsizeIsNull() {
            addCriterion("highsize is null");
            return (Criteria) this;
        }

        public Criteria andHighsizeIsNotNull() {
            addCriterion("highsize is not null");
            return (Criteria) this;
        }

        public Criteria andHighsizeEqualTo(Double value) {
            addCriterion("highsize =", value, "highsize");
            return (Criteria) this;
        }

        public Criteria andHighsizeNotEqualTo(Double value) {
            addCriterion("highsize <>", value, "highsize");
            return (Criteria) this;
        }

        public Criteria andHighsizeGreaterThan(Double value) {
            addCriterion("highsize >", value, "highsize");
            return (Criteria) this;
        }

        public Criteria andHighsizeGreaterThanOrEqualTo(Double value) {
            addCriterion("highsize >=", value, "highsize");
            return (Criteria) this;
        }

        public Criteria andHighsizeLessThan(Double value) {
            addCriterion("highsize <", value, "highsize");
            return (Criteria) this;
        }

        public Criteria andHighsizeLessThanOrEqualTo(Double value) {
            addCriterion("highsize <=", value, "highsize");
            return (Criteria) this;
        }

        public Criteria andHighsizeIn(List<Double> values) {
            addCriterion("highsize in", values, "highsize");
            return (Criteria) this;
        }

        public Criteria andHighsizeNotIn(List<Double> values) {
            addCriterion("highsize not in", values, "highsize");
            return (Criteria) this;
        }

        public Criteria andHighsizeBetween(Double value1, Double value2) {
            addCriterion("highsize between", value1, value2, "highsize");
            return (Criteria) this;
        }

        public Criteria andHighsizeNotBetween(Double value1, Double value2) {
            addCriterion("highsize not between", value1, value2, "highsize");
            return (Criteria) this;
        }

        public Criteria andHoursetyperequireIsNull() {
            addCriterion("hourseTypeRequire is null");
            return (Criteria) this;
        }

        public Criteria andHoursetyperequireIsNotNull() {
            addCriterion("hourseTypeRequire is not null");
            return (Criteria) this;
        }

        public Criteria andHoursetyperequireEqualTo(String value) {
            addCriterion("hourseTypeRequire =", value, "hoursetyperequire");
            return (Criteria) this;
        }

        public Criteria andHoursetyperequireNotEqualTo(String value) {
            addCriterion("hourseTypeRequire <>", value, "hoursetyperequire");
            return (Criteria) this;
        }

        public Criteria andHoursetyperequireGreaterThan(String value) {
            addCriterion("hourseTypeRequire >", value, "hoursetyperequire");
            return (Criteria) this;
        }

        public Criteria andHoursetyperequireGreaterThanOrEqualTo(String value) {
            addCriterion("hourseTypeRequire >=", value, "hoursetyperequire");
            return (Criteria) this;
        }

        public Criteria andHoursetyperequireLessThan(String value) {
            addCriterion("hourseTypeRequire <", value, "hoursetyperequire");
            return (Criteria) this;
        }

        public Criteria andHoursetyperequireLessThanOrEqualTo(String value) {
            addCriterion("hourseTypeRequire <=", value, "hoursetyperequire");
            return (Criteria) this;
        }

        public Criteria andHoursetyperequireLike(String value) {
            addCriterion("hourseTypeRequire like", value, "hoursetyperequire");
            return (Criteria) this;
        }

        public Criteria andHoursetyperequireNotLike(String value) {
            addCriterion("hourseTypeRequire not like", value, "hoursetyperequire");
            return (Criteria) this;
        }

        public Criteria andHoursetyperequireIn(List<String> values) {
            addCriterion("hourseTypeRequire in", values, "hoursetyperequire");
            return (Criteria) this;
        }

        public Criteria andHoursetyperequireNotIn(List<String> values) {
            addCriterion("hourseTypeRequire not in", values, "hoursetyperequire");
            return (Criteria) this;
        }

        public Criteria andHoursetyperequireBetween(String value1, String value2) {
            addCriterion("hourseTypeRequire between", value1, value2, "hoursetyperequire");
            return (Criteria) this;
        }

        public Criteria andHoursetyperequireNotBetween(String value1, String value2) {
            addCriterion("hourseTypeRequire not between", value1, value2, "hoursetyperequire");
            return (Criteria) this;
        }

        public Criteria andLocationrequireIsNull() {
            addCriterion("locationRequire is null");
            return (Criteria) this;
        }

        public Criteria andLocationrequireIsNotNull() {
            addCriterion("locationRequire is not null");
            return (Criteria) this;
        }

        public Criteria andLocationrequireEqualTo(String value) {
            addCriterion("locationRequire =", value, "locationrequire");
            return (Criteria) this;
        }

        public Criteria andLocationrequireNotEqualTo(String value) {
            addCriterion("locationRequire <>", value, "locationrequire");
            return (Criteria) this;
        }

        public Criteria andLocationrequireGreaterThan(String value) {
            addCriterion("locationRequire >", value, "locationrequire");
            return (Criteria) this;
        }

        public Criteria andLocationrequireGreaterThanOrEqualTo(String value) {
            addCriterion("locationRequire >=", value, "locationrequire");
            return (Criteria) this;
        }

        public Criteria andLocationrequireLessThan(String value) {
            addCriterion("locationRequire <", value, "locationrequire");
            return (Criteria) this;
        }

        public Criteria andLocationrequireLessThanOrEqualTo(String value) {
            addCriterion("locationRequire <=", value, "locationrequire");
            return (Criteria) this;
        }

        public Criteria andLocationrequireLike(String value) {
            addCriterion("locationRequire like", value, "locationrequire");
            return (Criteria) this;
        }

        public Criteria andLocationrequireNotLike(String value) {
            addCriterion("locationRequire not like", value, "locationrequire");
            return (Criteria) this;
        }

        public Criteria andLocationrequireIn(List<String> values) {
            addCriterion("locationRequire in", values, "locationrequire");
            return (Criteria) this;
        }

        public Criteria andLocationrequireNotIn(List<String> values) {
            addCriterion("locationRequire not in", values, "locationrequire");
            return (Criteria) this;
        }

        public Criteria andLocationrequireBetween(String value1, String value2) {
            addCriterion("locationRequire between", value1, value2, "locationrequire");
            return (Criteria) this;
        }

        public Criteria andLocationrequireNotBetween(String value1, String value2) {
            addCriterion("locationRequire not between", value1, value2, "locationrequire");
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

        public Criteria andTradeIsNull() {
            addCriterion("trade is null");
            return (Criteria) this;
        }

        public Criteria andTradeIsNotNull() {
            addCriterion("trade is not null");
            return (Criteria) this;
        }

        public Criteria andTradeEqualTo(Integer value) {
            addCriterion("trade =", value, "trade");
            return (Criteria) this;
        }

        public Criteria andTradeNotEqualTo(Integer value) {
            addCriterion("trade <>", value, "trade");
            return (Criteria) this;
        }

        public Criteria andTradeGreaterThan(Integer value) {
            addCriterion("trade >", value, "trade");
            return (Criteria) this;
        }

        public Criteria andTradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("trade >=", value, "trade");
            return (Criteria) this;
        }

        public Criteria andTradeLessThan(Integer value) {
            addCriterion("trade <", value, "trade");
            return (Criteria) this;
        }

        public Criteria andTradeLessThanOrEqualTo(Integer value) {
            addCriterion("trade <=", value, "trade");
            return (Criteria) this;
        }

        public Criteria andTradeIn(List<Integer> values) {
            addCriterion("trade in", values, "trade");
            return (Criteria) this;
        }

        public Criteria andTradeNotIn(List<Integer> values) {
            addCriterion("trade not in", values, "trade");
            return (Criteria) this;
        }

        public Criteria andTradeBetween(Integer value1, Integer value2) {
            addCriterion("trade between", value1, value2, "trade");
            return (Criteria) this;
        }

        public Criteria andTradeNotBetween(Integer value1, Integer value2) {
            addCriterion("trade not between", value1, value2, "trade");
            return (Criteria) this;
        }

        public Criteria andClassifynameIsNull() {
            addCriterion("classifyName is null");
            return (Criteria) this;
        }

        public Criteria andClassifynameIsNotNull() {
            addCriterion("classifyName is not null");
            return (Criteria) this;
        }

        public Criteria andClassifynameEqualTo(String value) {
            addCriterion("classifyName =", value, "classifyname");
            return (Criteria) this;
        }

        public Criteria andClassifynameNotEqualTo(String value) {
            addCriterion("classifyName <>", value, "classifyname");
            return (Criteria) this;
        }

        public Criteria andClassifynameGreaterThan(String value) {
            addCriterion("classifyName >", value, "classifyname");
            return (Criteria) this;
        }

        public Criteria andClassifynameGreaterThanOrEqualTo(String value) {
            addCriterion("classifyName >=", value, "classifyname");
            return (Criteria) this;
        }

        public Criteria andClassifynameLessThan(String value) {
            addCriterion("classifyName <", value, "classifyname");
            return (Criteria) this;
        }

        public Criteria andClassifynameLessThanOrEqualTo(String value) {
            addCriterion("classifyName <=", value, "classifyname");
            return (Criteria) this;
        }

        public Criteria andClassifynameLike(String value) {
            addCriterion("classifyName like", value, "classifyname");
            return (Criteria) this;
        }

        public Criteria andClassifynameNotLike(String value) {
            addCriterion("classifyName not like", value, "classifyname");
            return (Criteria) this;
        }

        public Criteria andClassifynameIn(List<String> values) {
            addCriterion("classifyName in", values, "classifyname");
            return (Criteria) this;
        }

        public Criteria andClassifynameNotIn(List<String> values) {
            addCriterion("classifyName not in", values, "classifyname");
            return (Criteria) this;
        }

        public Criteria andClassifynameBetween(String value1, String value2) {
            addCriterion("classifyName between", value1, value2, "classifyname");
            return (Criteria) this;
        }

        public Criteria andClassifynameNotBetween(String value1, String value2) {
            addCriterion("classifyName not between", value1, value2, "classifyname");
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