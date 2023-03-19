<%@ page import="com.toast.vo.Member" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/3/18
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Member> members = (List<Member>) request.getAttribute("list");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>部门列表数据</h1>
    <%
        for (Member m : members) {
    %>
    <span><%=m.toString()%></span>
    <%
    }
    %>
</body>
</html>
