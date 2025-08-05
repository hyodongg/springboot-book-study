<%--
  Created by IntelliJ IDEA.
  User: whgye
  Date: 25. 8. 5.
  Time: 오후 4:07
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=UTF-8"  %>
<html>
<body>
<h2>Spring MVC Demo - 회원목록</h2>
<table>
    <c:forEach var="member" items="${members}">
        <tr>
            <td>${member.id}</td>
            <td>${member.name}</td>
            <td>${member.email}</td>
            <td>${member.age}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>