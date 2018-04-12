package com.kepu.pojo;

import java.util.ArrayList;
import java.util.List;

public class StNewsQuestionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StNewsQuestionExample() {
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

        public Criteria andUridIsNull() {
            addCriterion("urid is null");
            return (Criteria) this;
        }

        public Criteria andUridIsNotNull() {
            addCriterion("urid is not null");
            return (Criteria) this;
        }

        public Criteria andUridEqualTo(Integer value) {
            addCriterion("urid =", value, "urid");
            return (Criteria) this;
        }

        public Criteria andUridNotEqualTo(Integer value) {
            addCriterion("urid <>", value, "urid");
            return (Criteria) this;
        }

        public Criteria andUridGreaterThan(Integer value) {
            addCriterion("urid >", value, "urid");
            return (Criteria) this;
        }

        public Criteria andUridGreaterThanOrEqualTo(Integer value) {
            addCriterion("urid >=", value, "urid");
            return (Criteria) this;
        }

        public Criteria andUridLessThan(Integer value) {
            addCriterion("urid <", value, "urid");
            return (Criteria) this;
        }

        public Criteria andUridLessThanOrEqualTo(Integer value) {
            addCriterion("urid <=", value, "urid");
            return (Criteria) this;
        }

        public Criteria andUridIn(List<Integer> values) {
            addCriterion("urid in", values, "urid");
            return (Criteria) this;
        }

        public Criteria andUridNotIn(List<Integer> values) {
            addCriterion("urid not in", values, "urid");
            return (Criteria) this;
        }

        public Criteria andUridBetween(Integer value1, Integer value2) {
            addCriterion("urid between", value1, value2, "urid");
            return (Criteria) this;
        }

        public Criteria andUridNotBetween(Integer value1, Integer value2) {
            addCriterion("urid not between", value1, value2, "urid");
            return (Criteria) this;
        }

        public Criteria andSubjectIsNull() {
            addCriterion("subject is null");
            return (Criteria) this;
        }

        public Criteria andSubjectIsNotNull() {
            addCriterion("subject is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectEqualTo(String value) {
            addCriterion("subject =", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotEqualTo(String value) {
            addCriterion("subject <>", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectGreaterThan(String value) {
            addCriterion("subject >", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectGreaterThanOrEqualTo(String value) {
            addCriterion("subject >=", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectLessThan(String value) {
            addCriterion("subject <", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectLessThanOrEqualTo(String value) {
            addCriterion("subject <=", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectLike(String value) {
            addCriterion("subject like", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotLike(String value) {
            addCriterion("subject not like", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectIn(List<String> values) {
            addCriterion("subject in", values, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotIn(List<String> values) {
            addCriterion("subject not in", values, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectBetween(String value1, String value2) {
            addCriterion("subject between", value1, value2, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotBetween(String value1, String value2) {
            addCriterion("subject not between", value1, value2, "subject");
            return (Criteria) this;
        }

        public Criteria andChiocecountIsNull() {
            addCriterion("chiocecount is null");
            return (Criteria) this;
        }

        public Criteria andChiocecountIsNotNull() {
            addCriterion("chiocecount is not null");
            return (Criteria) this;
        }

        public Criteria andChiocecountEqualTo(Integer value) {
            addCriterion("chiocecount =", value, "chiocecount");
            return (Criteria) this;
        }

        public Criteria andChiocecountNotEqualTo(Integer value) {
            addCriterion("chiocecount <>", value, "chiocecount");
            return (Criteria) this;
        }

        public Criteria andChiocecountGreaterThan(Integer value) {
            addCriterion("chiocecount >", value, "chiocecount");
            return (Criteria) this;
        }

        public Criteria andChiocecountGreaterThanOrEqualTo(Integer value) {
            addCriterion("chiocecount >=", value, "chiocecount");
            return (Criteria) this;
        }

        public Criteria andChiocecountLessThan(Integer value) {
            addCriterion("chiocecount <", value, "chiocecount");
            return (Criteria) this;
        }

        public Criteria andChiocecountLessThanOrEqualTo(Integer value) {
            addCriterion("chiocecount <=", value, "chiocecount");
            return (Criteria) this;
        }

        public Criteria andChiocecountIn(List<Integer> values) {
            addCriterion("chiocecount in", values, "chiocecount");
            return (Criteria) this;
        }

        public Criteria andChiocecountNotIn(List<Integer> values) {
            addCriterion("chiocecount not in", values, "chiocecount");
            return (Criteria) this;
        }

        public Criteria andChiocecountBetween(Integer value1, Integer value2) {
            addCriterion("chiocecount between", value1, value2, "chiocecount");
            return (Criteria) this;
        }

        public Criteria andChiocecountNotBetween(Integer value1, Integer value2) {
            addCriterion("chiocecount not between", value1, value2, "chiocecount");
            return (Criteria) this;
        }

        public Criteria andChoice1IsNull() {
            addCriterion("choice1 is null");
            return (Criteria) this;
        }

        public Criteria andChoice1IsNotNull() {
            addCriterion("choice1 is not null");
            return (Criteria) this;
        }

        public Criteria andChoice1EqualTo(String value) {
            addCriterion("choice1 =", value, "choice1");
            return (Criteria) this;
        }

        public Criteria andChoice1NotEqualTo(String value) {
            addCriterion("choice1 <>", value, "choice1");
            return (Criteria) this;
        }

        public Criteria andChoice1GreaterThan(String value) {
            addCriterion("choice1 >", value, "choice1");
            return (Criteria) this;
        }

        public Criteria andChoice1GreaterThanOrEqualTo(String value) {
            addCriterion("choice1 >=", value, "choice1");
            return (Criteria) this;
        }

        public Criteria andChoice1LessThan(String value) {
            addCriterion("choice1 <", value, "choice1");
            return (Criteria) this;
        }

        public Criteria andChoice1LessThanOrEqualTo(String value) {
            addCriterion("choice1 <=", value, "choice1");
            return (Criteria) this;
        }

        public Criteria andChoice1Like(String value) {
            addCriterion("choice1 like", value, "choice1");
            return (Criteria) this;
        }

        public Criteria andChoice1NotLike(String value) {
            addCriterion("choice1 not like", value, "choice1");
            return (Criteria) this;
        }

        public Criteria andChoice1In(List<String> values) {
            addCriterion("choice1 in", values, "choice1");
            return (Criteria) this;
        }

        public Criteria andChoice1NotIn(List<String> values) {
            addCriterion("choice1 not in", values, "choice1");
            return (Criteria) this;
        }

        public Criteria andChoice1Between(String value1, String value2) {
            addCriterion("choice1 between", value1, value2, "choice1");
            return (Criteria) this;
        }

        public Criteria andChoice1NotBetween(String value1, String value2) {
            addCriterion("choice1 not between", value1, value2, "choice1");
            return (Criteria) this;
        }

        public Criteria andChoice2IsNull() {
            addCriterion("choice2 is null");
            return (Criteria) this;
        }

        public Criteria andChoice2IsNotNull() {
            addCriterion("choice2 is not null");
            return (Criteria) this;
        }

        public Criteria andChoice2EqualTo(String value) {
            addCriterion("choice2 =", value, "choice2");
            return (Criteria) this;
        }

        public Criteria andChoice2NotEqualTo(String value) {
            addCriterion("choice2 <>", value, "choice2");
            return (Criteria) this;
        }

        public Criteria andChoice2GreaterThan(String value) {
            addCriterion("choice2 >", value, "choice2");
            return (Criteria) this;
        }

        public Criteria andChoice2GreaterThanOrEqualTo(String value) {
            addCriterion("choice2 >=", value, "choice2");
            return (Criteria) this;
        }

        public Criteria andChoice2LessThan(String value) {
            addCriterion("choice2 <", value, "choice2");
            return (Criteria) this;
        }

        public Criteria andChoice2LessThanOrEqualTo(String value) {
            addCriterion("choice2 <=", value, "choice2");
            return (Criteria) this;
        }

        public Criteria andChoice2Like(String value) {
            addCriterion("choice2 like", value, "choice2");
            return (Criteria) this;
        }

        public Criteria andChoice2NotLike(String value) {
            addCriterion("choice2 not like", value, "choice2");
            return (Criteria) this;
        }

        public Criteria andChoice2In(List<String> values) {
            addCriterion("choice2 in", values, "choice2");
            return (Criteria) this;
        }

        public Criteria andChoice2NotIn(List<String> values) {
            addCriterion("choice2 not in", values, "choice2");
            return (Criteria) this;
        }

        public Criteria andChoice2Between(String value1, String value2) {
            addCriterion("choice2 between", value1, value2, "choice2");
            return (Criteria) this;
        }

        public Criteria andChoice2NotBetween(String value1, String value2) {
            addCriterion("choice2 not between", value1, value2, "choice2");
            return (Criteria) this;
        }

        public Criteria andChoice3IsNull() {
            addCriterion("choice3 is null");
            return (Criteria) this;
        }

        public Criteria andChoice3IsNotNull() {
            addCriterion("choice3 is not null");
            return (Criteria) this;
        }

        public Criteria andChoice3EqualTo(String value) {
            addCriterion("choice3 =", value, "choice3");
            return (Criteria) this;
        }

        public Criteria andChoice3NotEqualTo(String value) {
            addCriterion("choice3 <>", value, "choice3");
            return (Criteria) this;
        }

        public Criteria andChoice3GreaterThan(String value) {
            addCriterion("choice3 >", value, "choice3");
            return (Criteria) this;
        }

        public Criteria andChoice3GreaterThanOrEqualTo(String value) {
            addCriterion("choice3 >=", value, "choice3");
            return (Criteria) this;
        }

        public Criteria andChoice3LessThan(String value) {
            addCriterion("choice3 <", value, "choice3");
            return (Criteria) this;
        }

        public Criteria andChoice3LessThanOrEqualTo(String value) {
            addCriterion("choice3 <=", value, "choice3");
            return (Criteria) this;
        }

        public Criteria andChoice3Like(String value) {
            addCriterion("choice3 like", value, "choice3");
            return (Criteria) this;
        }

        public Criteria andChoice3NotLike(String value) {
            addCriterion("choice3 not like", value, "choice3");
            return (Criteria) this;
        }

        public Criteria andChoice3In(List<String> values) {
            addCriterion("choice3 in", values, "choice3");
            return (Criteria) this;
        }

        public Criteria andChoice3NotIn(List<String> values) {
            addCriterion("choice3 not in", values, "choice3");
            return (Criteria) this;
        }

        public Criteria andChoice3Between(String value1, String value2) {
            addCriterion("choice3 between", value1, value2, "choice3");
            return (Criteria) this;
        }

        public Criteria andChoice3NotBetween(String value1, String value2) {
            addCriterion("choice3 not between", value1, value2, "choice3");
            return (Criteria) this;
        }

        public Criteria andChoice4IsNull() {
            addCriterion("choice4 is null");
            return (Criteria) this;
        }

        public Criteria andChoice4IsNotNull() {
            addCriterion("choice4 is not null");
            return (Criteria) this;
        }

        public Criteria andChoice4EqualTo(String value) {
            addCriterion("choice4 =", value, "choice4");
            return (Criteria) this;
        }

        public Criteria andChoice4NotEqualTo(String value) {
            addCriterion("choice4 <>", value, "choice4");
            return (Criteria) this;
        }

        public Criteria andChoice4GreaterThan(String value) {
            addCriterion("choice4 >", value, "choice4");
            return (Criteria) this;
        }

        public Criteria andChoice4GreaterThanOrEqualTo(String value) {
            addCriterion("choice4 >=", value, "choice4");
            return (Criteria) this;
        }

        public Criteria andChoice4LessThan(String value) {
            addCriterion("choice4 <", value, "choice4");
            return (Criteria) this;
        }

        public Criteria andChoice4LessThanOrEqualTo(String value) {
            addCriterion("choice4 <=", value, "choice4");
            return (Criteria) this;
        }

        public Criteria andChoice4Like(String value) {
            addCriterion("choice4 like", value, "choice4");
            return (Criteria) this;
        }

        public Criteria andChoice4NotLike(String value) {
            addCriterion("choice4 not like", value, "choice4");
            return (Criteria) this;
        }

        public Criteria andChoice4In(List<String> values) {
            addCriterion("choice4 in", values, "choice4");
            return (Criteria) this;
        }

        public Criteria andChoice4NotIn(List<String> values) {
            addCriterion("choice4 not in", values, "choice4");
            return (Criteria) this;
        }

        public Criteria andChoice4Between(String value1, String value2) {
            addCriterion("choice4 between", value1, value2, "choice4");
            return (Criteria) this;
        }

        public Criteria andChoice4NotBetween(String value1, String value2) {
            addCriterion("choice4 not between", value1, value2, "choice4");
            return (Criteria) this;
        }

        public Criteria andAnswerIsNull() {
            addCriterion("answer is null");
            return (Criteria) this;
        }

        public Criteria andAnswerIsNotNull() {
            addCriterion("answer is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerEqualTo(String value) {
            addCriterion("answer =", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotEqualTo(String value) {
            addCriterion("answer <>", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerGreaterThan(String value) {
            addCriterion("answer >", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerGreaterThanOrEqualTo(String value) {
            addCriterion("answer >=", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLessThan(String value) {
            addCriterion("answer <", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLessThanOrEqualTo(String value) {
            addCriterion("answer <=", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLike(String value) {
            addCriterion("answer like", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotLike(String value) {
            addCriterion("answer not like", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerIn(List<String> values) {
            addCriterion("answer in", values, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotIn(List<String> values) {
            addCriterion("answer not in", values, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerBetween(String value1, String value2) {
            addCriterion("answer between", value1, value2, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotBetween(String value1, String value2) {
            addCriterion("answer not between", value1, value2, "answer");
            return (Criteria) this;
        }

        public Criteria andNewsidIsNull() {
            addCriterion("newsId is null");
            return (Criteria) this;
        }

        public Criteria andNewsidIsNotNull() {
            addCriterion("newsId is not null");
            return (Criteria) this;
        }

        public Criteria andNewsidEqualTo(Integer value) {
            addCriterion("newsId =", value, "newsid");
            return (Criteria) this;
        }

        public Criteria andNewsidNotEqualTo(Integer value) {
            addCriterion("newsId <>", value, "newsid");
            return (Criteria) this;
        }

        public Criteria andNewsidGreaterThan(Integer value) {
            addCriterion("newsId >", value, "newsid");
            return (Criteria) this;
        }

        public Criteria andNewsidGreaterThanOrEqualTo(Integer value) {
            addCriterion("newsId >=", value, "newsid");
            return (Criteria) this;
        }

        public Criteria andNewsidLessThan(Integer value) {
            addCriterion("newsId <", value, "newsid");
            return (Criteria) this;
        }

        public Criteria andNewsidLessThanOrEqualTo(Integer value) {
            addCriterion("newsId <=", value, "newsid");
            return (Criteria) this;
        }

        public Criteria andNewsidIn(List<Integer> values) {
            addCriterion("newsId in", values, "newsid");
            return (Criteria) this;
        }

        public Criteria andNewsidNotIn(List<Integer> values) {
            addCriterion("newsId not in", values, "newsid");
            return (Criteria) this;
        }

        public Criteria andNewsidBetween(Integer value1, Integer value2) {
            addCriterion("newsId between", value1, value2, "newsid");
            return (Criteria) this;
        }

        public Criteria andNewsidNotBetween(Integer value1, Integer value2) {
            addCriterion("newsId not between", value1, value2, "newsid");
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