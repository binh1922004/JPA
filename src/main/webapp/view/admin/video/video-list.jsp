<%--
  Created by IntelliJ IDEA.
  User: binh
  Date: 10/8/2024
  Time: 10:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/conmon/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>
<a href="<c:url value="/admin/video/add"/>"><b>Add video</b></a>
<table>
    <tr>
        <th>STT</th>
        <th>Title</th>
        <th>Poster</th>
        <th>Decription</th>
        <th>Views</th>
        <th>Active</th>
    </tr>
    <c:forEach items="${listvideo}" var="video" varStatus="STT" >
        <tr class="odd gradeX">
            <td>${STT.index+1 }</td>
            <td>${video.title}</td>
            <td>${video.poster}</td>
            <td>${video.description}</td>
            <td>${video.views}</td>
            <td>${video.active}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>