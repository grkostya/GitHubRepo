<%@ page language="java" contentType="text/html; charset=utf8"  pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">

<!-- Init resources -->
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title><spring:message code="label.title" /></title>


    <link rel="stylesheet" type="text/css" href="/resources/css/jquery.dataTables.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/resources/css/buttons.css" rel="stylesheet">

    <script type="text/javascript" language="javascript" src="/resources/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" language="javascript" src="/resources/js/jquery.dataTables.min.js"></script>

    <script type="text/javascript" class="init">

        $(document).ready(function() {
            $('#prices').dataTable();
        } );

    </script>

</head>

<!-- Display data -->
<body>
<br>
<h3><spring:message text="Prices" /></h3>

<!-- Grid is displayed by js ,see logic above -->
  <table id="prices" class="display" cellspacing="0" width="100%" >

     <thead>
    <tr  align="center">
        <th align="center"><spring:message code="label.pricecode" /></th>
        <th align="center"><spring:message code="label.name" /></th>
        <th align="center"><spring:message code="label.price" /></th>
        <th align="center"><spring:message code="label.price_date" /></th>
        <th align="center"><spring:message code="label.action" /></th>

    </tr>
  </thead>
   <tbody>
    <c:forEach items="${prices}" var="pVal">
        <tr>
            <td align="center"><c:out value="${pVal.priceCode}" /></td>
            <td align="center"><c:out value="${pVal.name}"/></td>
            <td align="center"><c:out value="${pVal.price}"/></td>
            <td align="center"><fmt:formatDate  type="date" pattern="dd/MM/yyyy"  value="${pVal.pricedate}"/></td>
            <td align="center"><a href="delete/${pVal.priceCode}"><spring:message code="label.delete"/></a></td>
        </tr>
        </c:forEach>
       </tbody>
   </table>
<br>

<!-- Display buttons -->

<div style="float:left;margin: 10px; padding: 10px;">
    <form method= "POST"   action="fileUpload" enctype="multipart/form-data">
        <input type="file" name="file"/>
        <c:choose>
            <c:when test="${strServerFilePath == null}" >
                <input type="submit" value="Upload" />
            </c:when>
            <c:otherwise>
                <input type="submit" value="Upload" disabled="disabled" />
            </c:otherwise>
        </c:choose>
    </form>
</div>

<div style="float:left;margin: 10px; padding: 10px;">
    <form method= "GET" action="UpdateFromFile" >
        <c:choose>
            <c:when test="${strServerFilePath != null}" >
                <input type="submit" value="Update from file" />
            </c:when>
            <c:otherwise>
                <input type="submit" value="Update from file" disabled="disabled" />
            </c:otherwise>
        </c:choose>
    </form>
</div>

<br>
</body>
</html>
