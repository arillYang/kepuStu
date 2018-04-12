package com.kepu.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StLotteryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StLotteryExample() {
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

        public Criteria andDisplaypicIsNull() {
            addCriterion("displayPic is null");
            return (Criteria) this;
        }

        public Criteria andDisplaypicIsNotNull() {
            addCriterion("displayPic is not null");
            return (Criteria) this;
        }

        public Criteria andDisplaypicEqualTo(String value) {
            addCriterion("displayPic =", value, "displaypic");
            return (Criteria) this;
        }

        public Criteria andDisplaypicNotEqualTo(String value) {
            addCriterion("displayPic <>", value, "displaypic");
            return (Criteria) this;
        }

        public Criteria andDisplaypicGreaterThan(String value) {
            addCriterion("displayPic >", value, "displaypic");
            return (Criteria) this;
        }

        public Criteria andDisplaypicGreaterThanOrEqualTo(String value) {
            addCriterion("displayPic >=", value, "displaypic");
            return (Criteria) this;
        }

        public Criteria andDisplaypicLessThan(String value) {
            addCriterion("displayPic <", value, "displaypic");
            return (Criteria) this;
        }

        public Criteria andDisplaypicLessThanOrEqualTo(String value) {
            addCriterion("displayPic <=", value, "displaypic");
            return (Criteria) this;
        }

        public Criteria andDisplaypicLike(String value) {
            addCriterion("displayPic like", value, "displaypic");
            return (Criteria) this;
        }

        public Criteria andDisplaypicNotLike(String value) {
            addCriterion("displayPic not like", value, "displaypic");
            return (Criteria) this;
        }

        public Criteria andDisplaypicIn(List<String> values) {
            addCriterion("displayPic in", values, "displaypic");
            return (Criteria) this;
        }

        public Criteria andDisplaypicNotIn(List<String> values) {
            addCriterion("displayPic not in", values, "displaypic");
            return (Criteria) this;
        }

        public Criteria andDisplaypicBetween(String value1, String value2) {
            addCriterion("displayPic between", value1, value2, "displaypic");
            return (Criteria) this;
        }

        public Criteria andDisplaypicNotBetween(String value1, String value2) {
            addCriterion("displayPic not between", value1, value2, "displaypic");
            return (Criteria) this;
        }

        public Criteria andJoinnumIsNull() {
            addCriterion("joinNum is null");
            return (Criteria) this;
        }

        public Criteria andJoinnumIsNotNull() {
            addCriterion("joinNum is not null");
            return (Criteria) this;
        }

        public Criteria andJoinnumEqualTo(Integer value) {
            addCriterion("joinNum =", value, "joinnum");
            return (Criteria) this;
        }

        public Criteria andJoinnumNotEqualTo(Integer value) {
            addCriterion("joinNum <>", value, "joinnum");
            return (Criteria) this;
        }

        public Criteria andJoinnumGreaterThan(Integer value) {
            addCriterion("joinNum >", value, "joinnum");
            return (Criteria) this;
        }

        public Criteria andJoinnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("joinNum >=", value, "joinnum");
            return (Criteria) this;
        }

        public Criteria andJoinnumLessThan(Integer value) {
            addCriterion("joinNum <", value, "joinnum");
            return (Criteria) this;
        }

        public Criteria andJoinnumLessThanOrEqualTo(Integer value) {
            addCriterion("joinNum <=", value, "joinnum");
            return (Criteria) this;
        }

        public Criteria andJoinnumIn(List<Integer> values) {
            addCriterion("joinNum in", values, "joinnum");
            return (Criteria) this;
        }

        public Criteria andJoinnumNotIn(List<Integer> values) {
            addCriterion("joinNum not in", values, "joinnum");
            return (Criteria) this;
        }

        public Criteria andJoinnumBetween(Integer value1, Integer value2) {
            addCriterion("joinNum between", value1, value2, "joinnum");
            return (Criteria) this;
        }

        public Criteria andJoinnumNotBetween(Integer value1, Integer value2) {
            addCriterion("joinNum not between", value1, value2, "joinnum");
            return (Criteria) this;
        }

        public Criteria andVotenumIsNull() {
            addCriterion("voteNum is null");
            return (Criteria) this;
        }

        public Criteria andVotenumIsNotNull() {
            addCriterion("voteNum is not null");
            return (Criteria) this;
        }

        public Criteria andVotenumEqualTo(Integer value) {
            addCriterion("voteNum =", value, "votenum");
            return (Criteria) this;
        }

        public Criteria andVotenumNotEqualTo(Integer value) {
            addCriterion("voteNum <>", value, "votenum");
            return (Criteria) this;
        }

        public Criteria andVotenumGreaterThan(Integer value) {
            addCriterion("voteNum >", value, "votenum");
            return (Criteria) this;
        }

        public Criteria andVotenumGreaterThanOrEqualTo(Integer value) {
            addCriterion("voteNum >=", value, "votenum");
            return (Criteria) this;
        }

        public Criteria andVotenumLessThan(Integer value) {
            addCriterion("voteNum <", value, "votenum");
            return (Criteria) this;
        }

        public Criteria andVotenumLessThanOrEqualTo(Integer value) {
            addCriterion("voteNum <=", value, "votenum");
            return (Criteria) this;
        }

        public Criteria andVotenumIn(List<Integer> values) {
            addCriterion("voteNum in", values, "votenum");
            return (Criteria) this;
        }

        public Criteria andVotenumNotIn(List<Integer> values) {
            addCriterion("voteNum not in", values, "votenum");
            return (Criteria) this;
        }

        public Criteria andVotenumBetween(Integer value1, Integer value2) {
            addCriterion("voteNum between", value1, value2, "votenum");
            return (Criteria) this;
        }

        public Criteria andVotenumNotBetween(Integer value1, Integer value2) {
            addCriterion("voteNum not between", value1, value2, "votenum");
            return (Criteria) this;
        }

        public Criteria andViewnumIsNull() {
            addCriterion("viewNum is null");
            return (Criteria) this;
        }

        public Criteria andViewnumIsNotNull() {
            addCriterion("viewNum is not null");
            return (Criteria) this;
        }

        public Criteria andViewnumEqualTo(Integer value) {
            addCriterion("viewNum =", value, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewnumNotEqualTo(Integer value) {
            addCriterion("viewNum <>", value, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewnumGreaterThan(Integer value) {
            addCriterion("viewNum >", value, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("viewNum >=", value, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewnumLessThan(Integer value) {
            addCriterion("viewNum <", value, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewnumLessThanOrEqualTo(Integer value) {
            addCriterion("viewNum <=", value, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewnumIn(List<Integer> values) {
            addCriterion("viewNum in", values, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewnumNotIn(List<Integer> values) {
            addCriterion("viewNum not in", values, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewnumBetween(Integer value1, Integer value2) {
            addCriterion("viewNum between", value1, value2, "viewnum");
            return (Criteria) this;
        }

        public Criteria andViewnumNotBetween(Integer value1, Integer value2) {
            addCriterion("viewNum not between", value1, value2, "viewnum");
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

        public Criteria andDetailIsNull() {
            addCriterion("detail is null");
            return (Criteria) this;
        }

        public Criteria andDetailIsNotNull() {
            addCriterion("detail is not null");
            return (Criteria) this;
        }

        public Criteria andDetailEqualTo(String value) {
            addCriterion("detail =", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotEqualTo(String value) {
            addCriterion("detail <>", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailGreaterThan(String value) {
            addCriterion("detail >", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailGreaterThanOrEqualTo(String value) {
            addCriterion("detail >=", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailLessThan(String value) {
            addCriterion("detail <", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailLessThanOrEqualTo(String value) {
            addCriterion("detail <=", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailLike(String value) {
            addCriterion("detail like", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotLike(String value) {
            addCriterion("detail not like", value, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailIn(List<String> values) {
            addCriterion("detail in", values, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotIn(List<String> values) {
            addCriterion("detail not in", values, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailBetween(String value1, String value2) {
            addCriterion("detail between", value1, value2, "detail");
            return (Criteria) this;
        }

        public Criteria andDetailNotBetween(String value1, String value2) {
            addCriterion("detail not between", value1, value2, "detail");
            return (Criteria) this;
        }

        public Criteria andIplimitIsNull() {
            addCriterion("IpLimit is null");
            return (Criteria) this;
        }

        public Criteria andIplimitIsNotNull() {
            addCriterion("IpLimit is not null");
            return (Criteria) this;
        }

        public Criteria andIplimitEqualTo(Integer value) {
            addCriterion("IpLimit =", value, "iplimit");
            return (Criteria) this;
        }

        public Criteria andIplimitNotEqualTo(Integer value) {
            addCriterion("IpLimit <>", value, "iplimit");
            return (Criteria) this;
        }

        public Criteria andIplimitGreaterThan(Integer value) {
            addCriterion("IpLimit >", value, "iplimit");
            return (Criteria) this;
        }

        public Criteria andIplimitGreaterThanOrEqualTo(Integer value) {
            addCriterion("IpLimit >=", value, "iplimit");
            return (Criteria) this;
        }

        public Criteria andIplimitLessThan(Integer value) {
            addCriterion("IpLimit <", value, "iplimit");
            return (Criteria) this;
        }

        public Criteria andIplimitLessThanOrEqualTo(Integer value) {
            addCriterion("IpLimit <=", value, "iplimit");
            return (Criteria) this;
        }

        public Criteria andIplimitIn(List<Integer> values) {
            addCriterion("IpLimit in", values, "iplimit");
            return (Criteria) this;
        }

        public Criteria andIplimitNotIn(List<Integer> values) {
            addCriterion("IpLimit not in", values, "iplimit");
            return (Criteria) this;
        }

        public Criteria andIplimitBetween(Integer value1, Integer value2) {
            addCriterion("IpLimit between", value1, value2, "iplimit");
            return (Criteria) this;
        }

        public Criteria andIplimitNotBetween(Integer value1, Integer value2) {
            addCriterion("IpLimit not between", value1, value2, "iplimit");
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andJoinstartIsNull() {
            addCriterion("joinstart is null");
            return (Criteria) this;
        }

        public Criteria andJoinstartIsNotNull() {
            addCriterion("joinstart is not null");
            return (Criteria) this;
        }

        public Criteria andJoinstartEqualTo(Date value) {
            addCriterion("joinstart =", value, "joinstart");
            return (Criteria) this;
        }

        public Criteria andJoinstartNotEqualTo(Date value) {
            addCriterion("joinstart <>", value, "joinstart");
            return (Criteria) this;
        }

        public Criteria andJoinstartGreaterThan(Date value) {
            addCriterion("joinstart >", value, "joinstart");
            return (Criteria) this;
        }

        public Criteria andJoinstartGreaterThanOrEqualTo(Date value) {
            addCriterion("joinstart >=", value, "joinstart");
            return (Criteria) this;
        }

        public Criteria andJoinstartLessThan(Date value) {
            addCriterion("joinstart <", value, "joinstart");
            return (Criteria) this;
        }

        public Criteria andJoinstartLessThanOrEqualTo(Date value) {
            addCriterion("joinstart <=", value, "joinstart");
            return (Criteria) this;
        }

        public Criteria andJoinstartIn(List<Date> values) {
            addCriterion("joinstart in", values, "joinstart");
            return (Criteria) this;
        }

        public Criteria andJoinstartNotIn(List<Date> values) {
            addCriterion("joinstart not in", values, "joinstart");
            return (Criteria) this;
        }

        public Criteria andJoinstartBetween(Date value1, Date value2) {
            addCriterion("joinstart between", value1, value2, "joinstart");
            return (Criteria) this;
        }

        public Criteria andJoinstartNotBetween(Date value1, Date value2) {
            addCriterion("joinstart not between", value1, value2, "joinstart");
            return (Criteria) this;
        }

        public Criteria andJoinendIsNull() {
            addCriterion("joinend is null");
            return (Criteria) this;
        }

        public Criteria andJoinendIsNotNull() {
            addCriterion("joinend is not null");
            return (Criteria) this;
        }

        public Criteria andJoinendEqualTo(Date value) {
            addCriterion("joinend =", value, "joinend");
            return (Criteria) this;
        }

        public Criteria andJoinendNotEqualTo(Date value) {
            addCriterion("joinend <>", value, "joinend");
            return (Criteria) this;
        }

        public Criteria andJoinendGreaterThan(Date value) {
            addCriterion("joinend >", value, "joinend");
            return (Criteria) this;
        }

        public Criteria andJoinendGreaterThanOrEqualTo(Date value) {
            addCriterion("joinend >=", value, "joinend");
            return (Criteria) this;
        }

        public Criteria andJoinendLessThan(Date value) {
            addCriterion("joinend <", value, "joinend");
            return (Criteria) this;
        }

        public Criteria andJoinendLessThanOrEqualTo(Date value) {
            addCriterion("joinend <=", value, "joinend");
            return (Criteria) this;
        }

        public Criteria andJoinendIn(List<Date> values) {
            addCriterion("joinend in", values, "joinend");
            return (Criteria) this;
        }

        public Criteria andJoinendNotIn(List<Date> values) {
            addCriterion("joinend not in", values, "joinend");
            return (Criteria) this;
        }

        public Criteria andJoinendBetween(Date value1, Date value2) {
            addCriterion("joinend between", value1, value2, "joinend");
            return (Criteria) this;
        }

        public Criteria andJoinendNotBetween(Date value1, Date value2) {
            addCriterion("joinend not between", value1, value2, "joinend");
            return (Criteria) this;
        }

        public Criteria andVotestartIsNull() {
            addCriterion("votestart is null");
            return (Criteria) this;
        }

        public Criteria andVotestartIsNotNull() {
            addCriterion("votestart is not null");
            return (Criteria) this;
        }

        public Criteria andVotestartEqualTo(Date value) {
            addCriterion("votestart =", value, "votestart");
            return (Criteria) this;
        }

        public Criteria andVotestartNotEqualTo(Date value) {
            addCriterion("votestart <>", value, "votestart");
            return (Criteria) this;
        }

        public Criteria andVotestartGreaterThan(Date value) {
            addCriterion("votestart >", value, "votestart");
            return (Criteria) this;
        }

        public Criteria andVotestartGreaterThanOrEqualTo(Date value) {
            addCriterion("votestart >=", value, "votestart");
            return (Criteria) this;
        }

        public Criteria andVotestartLessThan(Date value) {
            addCriterion("votestart <", value, "votestart");
            return (Criteria) this;
        }

        public Criteria andVotestartLessThanOrEqualTo(Date value) {
            addCriterion("votestart <=", value, "votestart");
            return (Criteria) this;
        }

        public Criteria andVotestartIn(List<Date> values) {
            addCriterion("votestart in", values, "votestart");
            return (Criteria) this;
        }

        public Criteria andVotestartNotIn(List<Date> values) {
            addCriterion("votestart not in", values, "votestart");
            return (Criteria) this;
        }

        public Criteria andVotestartBetween(Date value1, Date value2) {
            addCriterion("votestart between", value1, value2, "votestart");
            return (Criteria) this;
        }

        public Criteria andVotestartNotBetween(Date value1, Date value2) {
            addCriterion("votestart not between", value1, value2, "votestart");
            return (Criteria) this;
        }

        public Criteria andVoteendIsNull() {
            addCriterion("voteend is null");
            return (Criteria) this;
        }

        public Criteria andVoteendIsNotNull() {
            addCriterion("voteend is not null");
            return (Criteria) this;
        }

        public Criteria andVoteendEqualTo(Date value) {
            addCriterion("voteend =", value, "voteend");
            return (Criteria) this;
        }

        public Criteria andVoteendNotEqualTo(Date value) {
            addCriterion("voteend <>", value, "voteend");
            return (Criteria) this;
        }

        public Criteria andVoteendGreaterThan(Date value) {
            addCriterion("voteend >", value, "voteend");
            return (Criteria) this;
        }

        public Criteria andVoteendGreaterThanOrEqualTo(Date value) {
            addCriterion("voteend >=", value, "voteend");
            return (Criteria) this;
        }

        public Criteria andVoteendLessThan(Date value) {
            addCriterion("voteend <", value, "voteend");
            return (Criteria) this;
        }

        public Criteria andVoteendLessThanOrEqualTo(Date value) {
            addCriterion("voteend <=", value, "voteend");
            return (Criteria) this;
        }

        public Criteria andVoteendIn(List<Date> values) {
            addCriterion("voteend in", values, "voteend");
            return (Criteria) this;
        }

        public Criteria andVoteendNotIn(List<Date> values) {
            addCriterion("voteend not in", values, "voteend");
            return (Criteria) this;
        }

        public Criteria andVoteendBetween(Date value1, Date value2) {
            addCriterion("voteend between", value1, value2, "voteend");
            return (Criteria) this;
        }

        public Criteria andVoteendNotBetween(Date value1, Date value2) {
            addCriterion("voteend not between", value1, value2, "voteend");
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