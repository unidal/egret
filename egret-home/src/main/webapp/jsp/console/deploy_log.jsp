<%@ page contentType="text/plain; charset=utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="ctx" type="com.dianping.egret.console.page.deploy.Context" scope="request" />
<jsp:useBean id="payload" type="com.dianping.egret.console.page.deploy.Payload" scope="request" />
<jsp:useBean id="model" type="com.dianping.egret.console.page.deploy.Model" scope="request" />
{
"offset":${model.offset}, 
"content": "${model.quotedLog}",
"hosts": [ 
<c:forEach var="plan" items="${model.plans}" varStatus="s1">
"index": ${plan.index},
"host": "${plan.host}",
"status": [<c:forEach var="status" items="${plan.statuses}" varStatus="s2">
"${status}"<c:if test="${not s2.first}">,</c:if>
</c:forEach>]<c:if test="${not s1.first}">,</c:if>
</c:forEach>]
}