<%@ page contentType="text/plain; charset=utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="ctx" type="com.dianping.egret.console.page.deploy.Context" scope="request" />
<jsp:useBean id="payload" type="com.dianping.egret.console.page.deploy.Payload" scope="request" />
<jsp:useBean id="model" type="com.dianping.egret.console.page.deploy.Model" scope="request" />
{"offset":${model.offset}, "content": "${model.log}","progress": ${model.progress}}
