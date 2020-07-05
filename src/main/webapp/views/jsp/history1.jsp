<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
      String url = "/history1";
%><html>
        <head> <meta http-equiv=refresh content=5;url=<%=url %>>             
        <script>
              <!--
                   window.moveTo(0,0);
             //-->
        </script>    
        </head>
       <body >               
       <b style=color:blue><span id=jump>3</span>  seconds left</b>        
       </body>
</html>       <script>
                      function countDown(secs){
                           jump.innerText=secs;
                           if(--secs>0)
                                    setTimeout("countDown("+secs+" )",1000);
                    }
                     countDown(3);
             </script>

