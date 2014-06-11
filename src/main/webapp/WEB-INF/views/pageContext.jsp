<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,org.apache.commons.lang3.StringUtils,org.apache.commons.lang3.ObjectUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="view" value="${pageContext.request.contextPath}/view_plugin"/>
<c:set var="context" value="${pageContext.request.contextPath}"/>

<script type="text/javascript" src="${view}/jQuery/jquery.min.js"></script>
<script type="text/javascript" src="${view}/bootstrap/js/bootstrap-dropdown.js"></script>
<script type="text/javascript" src="${view}/bootstrap/js/highlight.min.js"></script>
<script type="text/javascript" src="${view}/bootstrap/js/holder.min.js"></script>


<link type="text/css" rel="stylesheet" href="${view}/bootstrap/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="${view}/bootstrap/css/docs.css" />
<link type="text/css" rel="stylesheet" href="${view}/bootstrap/css/github.min.css" />