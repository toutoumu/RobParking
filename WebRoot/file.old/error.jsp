<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Exception!</title>
  </head>
  
  <body>
	<% Exception ex = (Exception)request.getAttribute("exception"); %>
	<H2>Exception: <%= ex.getMessage()%></H2>
	<P/>
	<% ex.printStackTrace(new java.io.PrintWriter(out)); %>
  </body>
</html>