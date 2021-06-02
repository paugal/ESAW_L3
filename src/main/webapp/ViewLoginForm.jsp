<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form action="LoginController" method="POST">    
    <label class="w3-text-green" for="username"><b> User id </b></label>
    <input class="w3-input w3-border w3-light-grey" type="text" name="username" id="username" 
    	value="${login.username}" required>
    	
    <label class="w3-text-green" for="password"><b> Password </b></label>
    <input class="w3-input w3-border w3-light-grey" type="password" name="password" id="password"
    	value="${login.password}" required >
    	
    <input class="w3-btn w3-green" type="submit" name="sumbit" value="Submit">
</form>
